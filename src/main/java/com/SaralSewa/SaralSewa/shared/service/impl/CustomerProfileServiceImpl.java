package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.customer.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListCustomerProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewCustomerProfileResponse;
import com.SaralSewa.SaralSewa.shared.entity.CustomerProfiles;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.CustomerProfileMapper;
import com.SaralSewa.SaralSewa.shared.repository.CustomerProfileRepository;
import com.SaralSewa.SaralSewa.shared.service.CustomerProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileMapper customerProfileMapper;

    @Override
    public ApiResponse<ViewCustomerProfileResponse> createProfile(CreateCustomerProfileRequest request) {
        if (customerProfileRepository.existsByUserId(request.getUserId())) {
            throw new ApiException("Customer profile already exists for this user.", HttpStatus.BAD_REQUEST);
        }
        CustomerProfiles profile = customerProfileMapper.create(request);
        CustomerProfiles savedProfile = customerProfileRepository.save(profile);
        return ApiResponse.created(customerProfileMapper.viewDetails(savedProfile), "Customer profile created successfully.");
    }

    @Override
    public ApiResponse<ViewCustomerProfileResponse> updateProfile(UpdateCustomerProfileRequest request) {
        CustomerProfiles profile = customerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Customer profile not found.", HttpStatus.NOT_FOUND));
        profile = customerProfileMapper.update(request, profile);
        CustomerProfiles updatedProfile = customerProfileRepository.save(profile);
        return ApiResponse.success(customerProfileMapper.viewDetails(updatedProfile), "Customer profile updated successfully.");
    }

    @Override
    public ApiResponse<ViewCustomerProfileResponse> getProfileById(GetProfileByIdRequest request) {
        CustomerProfiles profile = customerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Customer profile not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(customerProfileMapper.viewDetails(profile), "Customer profile retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewCustomerProfileResponse> getProfileByUserId(GetProfileByUserIdRequest request) {
        CustomerProfiles profile = customerProfileRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new ApiException("Customer profile not found for this user.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(customerProfileMapper.viewDetails(profile), "Customer profile retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListCustomerProfileResponse>> getAllProfiles(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<CustomerProfiles> profiles = customerProfileRepository.findAll(pageable);
        Page<ListCustomerProfileResponse> response = profiles.map(customerProfileMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Customer profiles retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteProfile(DeleteCustomerProfileRequest request) {
        CustomerProfiles profile = customerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Customer profile not found.", HttpStatus.NOT_FOUND));
        customerProfileRepository.delete(profile);
        return ApiResponse.success(null, "Customer profile deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteProfile(SoftDeleteProfileRequest request) {
        customerProfileRepository.softDeleteById(request.getProfileId());
        return ApiResponse.success(null, "Customer profile soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateProfile(ActivateProfileRequest request) {
        CustomerProfiles profile = customerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Customer profile not found.", HttpStatus.NOT_FOUND));
        profile = customerProfileMapper.activate(profile);
        customerProfileRepository.save(profile);
        return ApiResponse.success(null, "Customer profile activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateProfile(DeactivateProfileRequest request) {
        CustomerProfiles profile = customerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Customer profile not found.", HttpStatus.NOT_FOUND));
        profile = customerProfileMapper.deactivate(profile);
        customerProfileRepository.save(profile);
        return ApiResponse.success(null, "Customer profile deactivated successfully.");
    }

    @Override
    public boolean existsByUserId(Integer userId) {
        return customerProfileRepository.existsByUserId(userId);
    }
}
