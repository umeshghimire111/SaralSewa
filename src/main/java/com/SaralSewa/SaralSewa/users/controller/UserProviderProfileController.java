package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.provider.ProviderProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.profile.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderProfileResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ProviderProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.PROVIDER_PROFILES)
@RequiredArgsConstructor
public class UserProviderProfileController extends BaseController {

    private final ProviderProfileService providerProfileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewProviderProfileResponse> createProfile(@Valid @RequestBody ProviderProfileRequest request) {
        return providerProfileService.createProfile(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewProviderProfileResponse> updateProfile(
            @PathVariable Integer id,
            @Valid @RequestBody ProviderProfileRequest request) {
        request.setProviderId(id);
        return providerProfileService.updateProfile(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewProviderProfileResponse> getProfileById(@PathVariable Integer id) {
        GetProviderProfileByIdRequest request = new GetProviderProfileByIdRequest();
        request.setProfileId(id);
        return providerProfileService.getProfileById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewProviderProfileResponse> getProfileByProviderId(@PathVariable Integer id) {
        GetProviderProfileByProviderIdRequest request = new GetProviderProfileByProviderIdRequest();
        request.setProviderId(id);
        return providerProfileService.getProfileByProviderId(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListProviderProfileResponse>> getAllProfiles(@Valid PageRequest pageRequest) {
        return providerProfileService.getAllProfiles(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CITY + ApiConstant.SLASH + "{city}")
    public ApiResponse<PageResponse<ListProviderProfileResponse>> getProfilesByCity(
            @PathVariable String city,
            @Valid PageRequest pageRequest) {
        GetProfilesByCityRequest request = new GetProfilesByCityRequest();
        request.setCity(city);
        request.setPageRequest(pageRequest);
        return providerProfileService.getProfilesByCity(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.DISTRICT + ApiConstant.SLASH + "{district}")
    public ApiResponse<PageResponse<ListProviderProfileResponse>> getProfilesByDistrict(
            @PathVariable String district,
            @Valid PageRequest pageRequest) {
        GetProfilesByDistrictRequest request = new GetProfilesByDistrictRequest();
        request.setDistrict(district);
        request.setPageRequest(pageRequest);
        return providerProfileService.getProfilesByDistrict(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteProfile(@PathVariable Integer id) {
        DeleteProviderProfileRequest request = new DeleteProviderProfileRequest();
        request.setProfileId(id);
        return providerProfileService.deleteProfile(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteProfile(@PathVariable Integer id) {
        SoftDeleteProviderProfileRequest request = new SoftDeleteProviderProfileRequest();
        request.setProfileId(id);
        return providerProfileService.softDeleteProfile(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateProfile(@PathVariable Integer id) {
        ActivateProviderProfileRequest request = new ActivateProviderProfileRequest();
        request.setProfileId(id);
        return providerProfileService.activateProfile(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateProfile(@PathVariable Integer id) {
        DeactivateProviderProfileRequest request = new DeactivateProviderProfileRequest();
        request.setProfileId(id);
        return providerProfileService.deactivateProfile(request);
    }
}
