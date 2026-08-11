package com.SaralSewa.SaralSewa.shared.service.impl;


import com.SaralSewa.SaralSewa.shared.core.dto.response.*;
import com.SaralSewa.SaralSewa.shared.dto.request.action.profile.*;
import com.SaralSewa.SaralSewa.shared.dto.request.action.provider.ProviderProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderProfileResponse;
import com.SaralSewa.SaralSewa.shared.entity.ProviderProfile;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ProviderProfileMapper;
import com.SaralSewa.SaralSewa.shared.repository.ProviderProfileRepository;
import com.SaralSewa.SaralSewa.shared.service.ProviderProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProviderProfileServiceImpl implements ProviderProfileService {

    private final ProviderProfileRepository providerProfileRepository;
    private final ProviderProfileMapper providerProfileMapper;

    @Override
    public ApiResponse<ViewProviderProfileResponse> createProfile(ProviderProfileRequest request) {
        if (providerProfileRepository.existsByProviderId(request.getProviderId())) {
          return  ResponseUtil.getBeanValidationFailureResponse("Provider profile already exists.");
        }
        ProviderProfile profile = providerProfileMapper.create(request);
        ProviderProfile savedProfile = providerProfileRepository.save(profile);
        return ResponseUtil.getSuccessfulApiResponse(providerProfileMapper.viewDetails(savedProfile), "Provider profile created successfully.");
    }

    @Override
    public ApiResponse<ViewProviderProfileResponse> updateProfile(ProviderProfileRequest request) {
        ProviderProfile profile = providerProfileRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ApiException("Provider profile not found.", HttpStatus.NOT_FOUND));
        profile = providerProfileMapper.update(request, profile);
        ProviderProfile updatedProfile = providerProfileRepository.save(profile);
        return ResponseUtil.getSuccessfulApiResponse(providerProfileMapper.viewDetails(updatedProfile), "Provider profile updated successfully.");
    }

    @Override
    public ApiResponse<ViewProviderProfileResponse> getProfileById(GetProviderProfileByIdRequest request) {
        ProviderProfile profile = providerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Provider profile not found.", HttpStatus.NOT_FOUND));
        return ResponseUtil.getSuccessfulApiResponse(providerProfileMapper.viewDetails(profile), "Provider profile retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewProviderProfileResponse> getProfileByProviderId(GetProviderProfileByProviderIdRequest request) {
        ProviderProfile profile = providerProfileRepository.findByProviderId(request.getProviderId());
        return ResponseUtil.getSuccessfulApiResponse(providerProfileMapper.viewDetails(profile), "Provider profile retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderProfileResponse>> getAllProfiles(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<ProviderProfile> profiles = providerProfileRepository.findAll(pageable);
        Page<ListProviderProfileResponse> response = profiles.map(providerProfileMapper::entityToResponse);
        return ResponseUtil.getSuccessfulApiResponse(PageResponse.from(response), "Provider profiles retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderProfileResponse>> getProfilesByCity(GetProfilesByCityRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<ProviderProfile> profiles = providerProfileRepository.findByCityContainingIgnoreCase(request.getCity(), pageable);
        Page<ListProviderProfileResponse> response = profiles.map(providerProfileMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Provider profiles by city retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderProfileResponse>> getProfilesByDistrict(GetProfilesByDistrictRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<ProviderProfile> profiles = providerProfileRepository.findByDistrictContainingIgnoreCase(request.getDistrict(), pageable);
        Page<ListProviderProfileResponse> response = profiles.map(providerProfileMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Provider profiles by district retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteProfile(DeleteProviderProfileRequest request) {
        ProviderProfile profile = providerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Provider profile not found.", HttpStatus.NOT_FOUND));
        providerProfileRepository.delete(profile);
        return ResponseUtil.getSuccessfulApiResponse(null, "Provider profile deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteProfile(SoftDeleteProviderProfileRequest request) {
        providerProfileRepository.softDeleteById(request.getProfileId());
        return ResponseUtil.getSuccessfulApiResponse(null, "Provider profile soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateProfile(ActivateProviderProfileRequest request) {
        ProviderProfile profile = providerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Provider profile not found.", HttpStatus.NOT_FOUND));
        profile = providerProfileMapper.activate(profile);
        providerProfileRepository.save(profile);
        return ResponseUtil.getSuccessfulApiResponse(null, "Provider profile activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateProfile(DeactivateProviderProfileRequest request) {
        ProviderProfile profile = providerProfileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new ApiException("Provider profile not found.", HttpStatus.NOT_FOUND));
        profile = providerProfileMapper.deactivate(profile);
        providerProfileRepository.save(profile);
        return ResponseUtil.getSuccessfulApiResponse(null, "Provider profile deactivated successfully.");
    }

    @Override
    public boolean existsByProviderId(Integer providerId) {
        return providerProfileRepository.existsByProviderId(providerId);
    }
}
