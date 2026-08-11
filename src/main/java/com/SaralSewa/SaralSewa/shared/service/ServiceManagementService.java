package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.service.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceResponse;

public interface ServiceManagementService {

    ApiResponse<ViewServiceResponse> createService(CreateServiceRequest request);

    ApiResponse<ViewServiceResponse> updateService(UpdateServiceRequest request);

    ApiResponse<ViewServiceResponse> getServiceById(GetServiceByIdRequest request);

    ApiResponse<ViewServiceResponse> getServiceByCode(GetServiceByCodeRequest request);

    ApiResponse<PageResponse<ListServiceResponse>> getAllServices(PageRequest pageRequest);

    ApiResponse<PageResponse<ListServiceResponse>> getServicesByCategory(GetServicesByCategoryRequest request);

    ApiResponse<?> deleteService(DeleteServiceRequest request);

    ApiResponse<?> softDeleteService(SoftDeleteServiceRequest request);

    ApiResponse<?> activateService(ActivateServiceRequest request);

    ApiResponse<?> deactivateService(DeactivateServiceRequest request);

    boolean existsByCode(String code);
}
