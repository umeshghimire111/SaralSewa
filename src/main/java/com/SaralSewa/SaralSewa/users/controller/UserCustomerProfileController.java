package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.customer.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListCustomerProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewCustomerProfileResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.CustomerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.CUSTOMER_PROFILES)
@RequiredArgsConstructor
public class UserCustomerProfileController extends BaseController {

    private final CustomerProfileService customerProfileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewCustomerProfileResponse> createProfile(@Valid @RequestBody CreateCustomerProfileRequest request) {
        return customerProfileService.createProfile(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewCustomerProfileResponse> updateProfile(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCustomerProfileRequest request) {
        request.setProfileId(id);
        return customerProfileService.updateProfile(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewCustomerProfileResponse> getProfileById(@PathVariable Integer id) {
        GetProfileByIdRequest request = new GetProfileByIdRequest();
        request.setProfileId(id);
        return customerProfileService.getProfileById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.USER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewCustomerProfileResponse> getProfileByUserId(@PathVariable Integer id) {
        GetProfileByUserIdRequest request = new GetProfileByUserIdRequest();
        request.setUserId(id);
        return customerProfileService.getProfileByUserId(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListCustomerProfileResponse>> getAllProfiles(@Valid PageRequest pageRequest) {
        return customerProfileService.getAllProfiles(pageRequest);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteProfile(@PathVariable Integer id) {
        DeleteCustomerProfileRequest request = new DeleteCustomerProfileRequest();
        request.setProfileId(id);
        return customerProfileService.deleteProfile(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteProfile(@PathVariable Integer id) {
        SoftDeleteProfileRequest request = new SoftDeleteProfileRequest();
        request.setProfileId(id);
        return customerProfileService.softDeleteProfile(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateProfile(@PathVariable Integer id) {
        ActivateProfileRequest request = new ActivateProfileRequest();
        request.setProfileId(id);
        return customerProfileService.activateProfile(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateProfile(@PathVariable Integer id) {
        DeactivateProfileRequest request = new DeactivateProfileRequest();
        request.setProfileId(id);
        return customerProfileService.deactivateProfile(request);
    }
}
