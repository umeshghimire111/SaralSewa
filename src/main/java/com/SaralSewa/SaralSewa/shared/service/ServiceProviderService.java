package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.provider.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceProviderResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceProviderResponse;

public interface ServiceProviderService {

    ApiResponse<ViewServiceProviderResponse> createProvider(CreateServiceProviderRequest request);

    ApiResponse<ViewServiceProviderResponse> updateProvider(UpdateServiceProviderRequest request);

    ApiResponse<ViewServiceProviderResponse> getProviderById(GetProviderByIdRequest request);

    ApiResponse<ViewServiceProviderResponse> getProviderByUserId(GetProviderByUserIdRequest request);

    ApiResponse<PageResponse<ListServiceProviderResponse>> getAllProviders(PageRequest pageRequest);

    ApiResponse<?> deleteProvider(DeleteServiceProviderRequest request);

    ApiResponse<?> softDeleteProvider(SoftDeleteProviderRequest request);

    ApiResponse<?> approveProvider(ApproveProviderRequest request);

    ApiResponse<?> rejectProvider(RejectProviderRequest request);

    ApiResponse<?> activateProvider(ActivateProviderRequest request);

    ApiResponse<?> deactivateProvider(DeactivateProviderRequest request);

    boolean existsByUserId(Integer userId);
}
