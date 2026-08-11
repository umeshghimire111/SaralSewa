package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.constant.ApprovalStatusConstant;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.provider.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceProviderResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceProviderResponse;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ServiceProviderMapper;
import com.SaralSewa.SaralSewa.shared.repository.ServiceProviderRepository;
import com.SaralSewa.SaralSewa.shared.service.ServiceProviderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final ServiceProviderMapper serviceProviderMapper;

    @Override
    public ApiResponse<ViewServiceProviderResponse> createProvider(CreateServiceProviderRequest request) {
        if (serviceProviderRepository.existsByUserId(request.getUserId())) {
            throw new ApiException("User is already a provider.", HttpStatus.BAD_REQUEST);
        }
        ServiceProvider provider = serviceProviderMapper.create(request);
        ServiceProvider savedProvider = serviceProviderRepository.save(provider);
        return ApiResponse.created(serviceProviderMapper.viewDetails(savedProvider), "Provider created successfully.");
    }

    @Override
    public ApiResponse<ViewServiceProviderResponse> updateProvider(UpdateServiceProviderRequest request) {
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        provider = serviceProviderMapper.update(request, provider);
        ServiceProvider updatedProvider = serviceProviderRepository.save(provider);
        return ApiResponse.success(serviceProviderMapper.viewDetails(updatedProvider), "Provider updated successfully.");
    }

    @Override
    public ApiResponse<ViewServiceProviderResponse> getProviderById(GetProviderByIdRequest request) {
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(serviceProviderMapper.viewDetails(provider), "Provider retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewServiceProviderResponse> getProviderByUserId(GetProviderByUserIdRequest request) {
        ServiceProvider provider = serviceProviderRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new ApiException("Provider not found for this user.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(serviceProviderMapper.viewDetails(provider), "Provider retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListServiceProviderResponse>> getAllProviders(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<ServiceProvider> providers = serviceProviderRepository.findAll(pageable);
        Page<ListServiceProviderResponse> response = providers.map(serviceProviderMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Providers retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteProvider(DeleteServiceProviderRequest request) {
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        serviceProviderRepository.delete(provider);
        return ApiResponse.success(null, "Provider deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteProvider(SoftDeleteProviderRequest request) {
        serviceProviderRepository.softDeleteById(request.getProviderId());
        return ApiResponse.success(null, "Provider soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> approveProvider(ApproveProviderRequest request) {
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        serviceProviderMapper.updateApprovalStatus(provider, ApprovalStatusConstant.VERIFIED.getName());
        serviceProviderRepository.save(provider);
        return ApiResponse.success(null, "Provider approved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> rejectProvider(RejectProviderRequest request) {
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        serviceProviderMapper.updateApprovalStatus(provider, ApprovalStatusConstant.REJECTED.getName());
        serviceProviderRepository.save(provider);
        return ApiResponse.success(null, "Provider rejected successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateProvider(ActivateProviderRequest request) {
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        serviceProviderMapper.activate(provider);
        serviceProviderRepository.save(provider);
        return ApiResponse.success(null, "Provider activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateProvider(DeactivateProviderRequest request) {
        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider not found.", HttpStatus.NOT_FOUND));
        serviceProviderMapper.deactivate(provider);
        serviceProviderRepository.save(provider);
        return ApiResponse.success(null, "Provider deactivated successfully.");
    }

    @Override
    public boolean existsByUserId(Integer userId) {
        return serviceProviderRepository.existsByUserId(userId);
    }
}
