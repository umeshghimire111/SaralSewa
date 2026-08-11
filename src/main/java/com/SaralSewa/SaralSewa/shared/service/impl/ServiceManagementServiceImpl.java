package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.service.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceResponse;
import com.SaralSewa.SaralSewa.shared.entity.Service;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ServiceMapper;
import com.SaralSewa.SaralSewa.shared.repository.ServiceRepository;
import com.SaralSewa.SaralSewa.shared.service.ServiceManagementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceManagementServiceImpl implements ServiceManagementService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ApiResponse<ViewServiceResponse> createService(CreateServiceRequest request) {
        if (serviceRepository.existsByCode(request.getCode())) {
            throw new ApiException("Service code already exists.", HttpStatus.BAD_REQUEST);
        }
        Service service = serviceMapper.create(request);
        Service savedService = serviceRepository.save(service);
        return ApiResponse.created(serviceMapper.viewDetails(savedService), "Service created successfully.");
    }

    @Override
    public ApiResponse<ViewServiceResponse> updateService(UpdateServiceRequest request) {
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ApiException("Service not found.", HttpStatus.NOT_FOUND));
        service = serviceMapper.update(request, service);
        Service updatedService = serviceRepository.save(service);
        return ApiResponse.success(serviceMapper.viewDetails(updatedService), "Service updated successfully.");
    }

    @Override
    public ApiResponse<ViewServiceResponse> getServiceById(GetServiceByIdRequest request) {
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ApiException("Service not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(serviceMapper.viewDetails(service), "Service retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewServiceResponse> getServiceByCode(GetServiceByCodeRequest request) {
        Service service = serviceRepository.findByCode(request.getCode());
        if (service == null) {
            throw new ApiException("Service not found.", HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(serviceMapper.viewDetails(service), "Service retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListServiceResponse>> getAllServices(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<Service> services = serviceRepository.findAll(pageable);
        Page<ListServiceResponse> response = services.map(serviceMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Services retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListServiceResponse>> getServicesByCategory(GetServicesByCategoryRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<Service> services = serviceRepository.findByCategoryId(request.getCategoryId(), pageable);
        Page<ListServiceResponse> response = services.map(serviceMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Services by category retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteService(DeleteServiceRequest request) {
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ApiException("Service not found.", HttpStatus.NOT_FOUND));
        serviceRepository.delete(service);
        return ApiResponse.success(null, "Service deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteService(SoftDeleteServiceRequest request) {
        serviceRepository.softDeleteById(request.getServiceId());
        return ApiResponse.success(null, "Service soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateService(ActivateServiceRequest request) {
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ApiException("Service not found.", HttpStatus.NOT_FOUND));
        service = serviceMapper.activate(service);
        serviceRepository.save(service);
        return ApiResponse.success(null, "Service activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateService(DeactivateServiceRequest request) {
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ApiException("Service not found.", HttpStatus.NOT_FOUND));
        service = serviceMapper.deactivate(service);
        serviceRepository.save(service);
        return ApiResponse.success(null, "Service deactivated successfully.");
    }

    @Override
    public boolean existsByCode(String code) {
        return serviceRepository.existsByCode(code);
    }
}
