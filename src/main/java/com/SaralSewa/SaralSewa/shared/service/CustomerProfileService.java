package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.customer.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListCustomerProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewCustomerProfileResponse;

public interface CustomerProfileService {

    ApiResponse<ViewCustomerProfileResponse> createProfile(CreateCustomerProfileRequest request);

    ApiResponse<ViewCustomerProfileResponse> updateProfile(UpdateCustomerProfileRequest request);

    ApiResponse<ViewCustomerProfileResponse> getProfileById(GetProfileByIdRequest request);

    ApiResponse<ViewCustomerProfileResponse> getProfileByUserId(GetProfileByUserIdRequest request);

    ApiResponse<PageResponse<ListCustomerProfileResponse>> getAllProfiles(PageRequest pageRequest);

    ApiResponse<?> deleteProfile(DeleteCustomerProfileRequest request);

    ApiResponse<?> softDeleteProfile(SoftDeleteProfileRequest request);

    ApiResponse<?> activateProfile(ActivateProfileRequest request);

    ApiResponse<?> deactivateProfile(DeactivateProfileRequest request);

    boolean existsByUserId(Integer userId);
}
