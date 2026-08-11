package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.category.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceCategoryResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceCategoryResponse;

public interface ServiceCategoryService {

    ApiResponse<ViewServiceCategoryResponse> createCategory(CreateServiceCategoryRequest request);

    ApiResponse<ViewServiceCategoryResponse> updateCategory(UpdateServiceCategoryRequest request);

    ApiResponse<ViewServiceCategoryResponse> getCategoryById(GetCategoryByIdRequest request);

    ApiResponse<ViewServiceCategoryResponse> getCategoryByCode(GetCategoryByCodeRequest request);

    ApiResponse<PageResponse<ListServiceCategoryResponse>> getAllCategories(PageRequest pageRequest);

    ApiResponse<?> deleteCategory(DeleteServiceCategoryRequest request);

    ApiResponse<?> softDeleteCategory(SoftDeleteCategoryRequest request);

    ApiResponse<?> activateCategory(ActivateCategoryRequest request);

    ApiResponse<?> deactivateCategory(DeactivateCategoryRequest request);

    boolean existsByCode(String code);
}
