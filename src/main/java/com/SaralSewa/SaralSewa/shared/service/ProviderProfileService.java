package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.profile.*;
import com.SaralSewa.SaralSewa.shared.dto.request.action.provider.ProviderProfileRequest;

import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderProfileResponse;

public interface ProviderProfileService {

    ApiResponse<ViewProviderProfileResponse> createProfile(ProviderProfileRequest request);

    ApiResponse<ViewProviderProfileResponse> updateProfile(ProviderProfileRequest request);

    ApiResponse<ViewProviderProfileResponse> getProfileById(GetProviderProfileByIdRequest request);

    ApiResponse<ViewProviderProfileResponse> getProfileByProviderId(GetProviderProfileByProviderIdRequest request);

    ApiResponse<PageResponse<ListProviderProfileResponse>> getAllProfiles(PageRequest pageRequest);

    ApiResponse<PageResponse<ListProviderProfileResponse>> getProfilesByCity(GetProfilesByCityRequest request);

    ApiResponse<PageResponse<ListProviderProfileResponse>> getProfilesByDistrict(GetProfilesByDistrictRequest request);

    ApiResponse<?> deleteProfile(DeleteProviderProfileRequest request);

    ApiResponse<?> softDeleteProfile(SoftDeleteProviderProfileRequest request);

    ApiResponse<?> activateProfile(ActivateProviderProfileRequest request);

    ApiResponse<?> deactivateProfile(DeactivateProviderProfileRequest request);

    boolean existsByProviderId(Integer providerId);
}
