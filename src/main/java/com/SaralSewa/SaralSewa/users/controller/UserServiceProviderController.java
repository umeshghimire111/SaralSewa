package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.provider.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceProviderResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceProviderResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ServiceProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.PROVIDERS)
@RequiredArgsConstructor
public class UserServiceProviderController extends BaseController {

    private final ServiceProviderService serviceProviderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewServiceProviderResponse> createProvider(@Valid @RequestBody CreateServiceProviderRequest request) {
        return serviceProviderService.createProvider(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewServiceProviderResponse> updateProvider(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateServiceProviderRequest request) {
        request.setProviderId(id);
        return serviceProviderService.updateProvider(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewServiceProviderResponse> getProviderById(@PathVariable Integer id) {
        GetProviderByIdRequest request = new GetProviderByIdRequest();
        request.setProviderId(id);
        return serviceProviderService.getProviderById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.USER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewServiceProviderResponse> getProviderByUserId(@PathVariable Integer id) {
        GetProviderByUserIdRequest request = new GetProviderByUserIdRequest();
        request.setUserId(id);
        return serviceProviderService.getProviderByUserId(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListServiceProviderResponse>> getAllProviders(@Valid PageRequest pageRequest) {
        return serviceProviderService.getAllProviders(pageRequest);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteProvider(@PathVariable Integer id) {
        DeleteServiceProviderRequest request = new DeleteServiceProviderRequest();
        request.setProviderId(id);
        return serviceProviderService.deleteProvider(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteProvider(@PathVariable Integer id) {
        SoftDeleteProviderRequest request = new SoftDeleteProviderRequest();
        request.setProviderId(id);
        return serviceProviderService.softDeleteProvider(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateProvider(@PathVariable Integer id) {
        ActivateProviderRequest request = new ActivateProviderRequest();
        request.setProviderId(id);
        return serviceProviderService.activateProvider(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateProvider(@PathVariable Integer id) {
        DeactivateProviderRequest request = new DeactivateProviderRequest();
        request.setProviderId(id);
        return serviceProviderService.deactivateProvider(request);
    }
}
