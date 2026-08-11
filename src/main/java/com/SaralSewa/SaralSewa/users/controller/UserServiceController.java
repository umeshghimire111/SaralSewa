package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.service.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ServiceManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.SERVICES)
@RequiredArgsConstructor
public class UserServiceController extends BaseController {

    private final ServiceManagementService serviceManagementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewServiceResponse> createService(@Valid @RequestBody CreateServiceRequest request) {
        return serviceManagementService.createService(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewServiceResponse> updateService(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateServiceRequest request) {
        request.setServiceId(id);
        return serviceManagementService.updateService(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewServiceResponse> getServiceById(@PathVariable Integer id) {
        GetServiceByIdRequest request = new GetServiceByIdRequest();
        request.setServiceId(id);
        return serviceManagementService.getServiceById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CODE + ApiConstant.SLASH + "{code}")
    public ApiResponse<ViewServiceResponse> getServiceByCode(@PathVariable String code) {
        GetServiceByCodeRequest request = new GetServiceByCodeRequest();
        request.setCode(code);
        return serviceManagementService.getServiceByCode(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListServiceResponse>> getAllServices(@Valid PageRequest pageRequest) {
        return serviceManagementService.getAllServices(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CATEGORY + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListServiceResponse>> getServicesByCategory(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetServicesByCategoryRequest request = new GetServicesByCategoryRequest();
        request.setCategoryId(id);
        request.setPageRequest(pageRequest);
        return serviceManagementService.getServicesByCategory(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteService(@PathVariable Integer id) {
        DeleteServiceRequest request = new DeleteServiceRequest();
        request.setServiceId(id);
        return serviceManagementService.deleteService(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteService(@PathVariable Integer id) {
        SoftDeleteServiceRequest request = new SoftDeleteServiceRequest();
        request.setServiceId(id);
        return serviceManagementService.softDeleteService(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateService(@PathVariable Integer id) {
        ActivateServiceRequest request = new ActivateServiceRequest();
        request.setServiceId(id);
        return serviceManagementService.activateService(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateService(@PathVariable Integer id) {
        DeactivateServiceRequest request = new DeactivateServiceRequest();
        request.setServiceId(id);
        return serviceManagementService.deactivateService(request);
    }
}
