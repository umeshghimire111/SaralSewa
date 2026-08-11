package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ResponseUtil;
import com.SaralSewa.SaralSewa.shared.dto.request.action.category.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceCategoryResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceCategoryResponse;
import com.SaralSewa.SaralSewa.shared.entity.ServiceCategory;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ServiceCategoryMapper;
import com.SaralSewa.SaralSewa.shared.repository.ServiceCategoryRepository;
import com.SaralSewa.SaralSewa.shared.service.ServiceCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ServiceCategoryMapper serviceCategoryMapper;

    @Override
    public ApiResponse<ViewServiceCategoryResponse> createCategory(CreateServiceCategoryRequest request) {
        if (serviceCategoryRepository.existsByCode(request.getCode())) {
            throw new ApiException("Category code already exists.", HttpStatus.BAD_REQUEST);
        }
        ServiceCategory category = serviceCategoryMapper.create(request);
        ServiceCategory savedCategory = serviceCategoryRepository.save(category);
        return ResponseUtil.getSuccessfulApiResponse(serviceCategoryMapper.viewDetails(savedCategory), "Category created successfully.");
    }

    @Override
    public ApiResponse<ViewServiceCategoryResponse> updateCategory(UpdateServiceCategoryRequest request) {
        ServiceCategory category = serviceCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found.", HttpStatus.NOT_FOUND));
        category = serviceCategoryMapper.update(request, category);
        ServiceCategory updatedCategory = serviceCategoryRepository.save(category);
        return ApiResponse.success(serviceCategoryMapper.viewDetails(updatedCategory), "Category updated successfully.");
    }

    @Override
    public ApiResponse<ViewServiceCategoryResponse> getCategoryById(GetCategoryByIdRequest request) {
        ServiceCategory category = serviceCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(serviceCategoryMapper.viewDetails(category), "Category retrieved successfully.");
    }

    @Override
    public ApiResponse<ViewServiceCategoryResponse> getCategoryByCode(GetCategoryByCodeRequest request) {
        ServiceCategory category = serviceCategoryRepository.findByCode(request.getCode());
        if (category == null) {
            throw new ApiException("Category not found.", HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(serviceCategoryMapper.viewDetails(category), "Category retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListServiceCategoryResponse>> getAllCategories(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<ServiceCategory> categories = serviceCategoryRepository.findAll(pageable);
        Page<ListServiceCategoryResponse> response = categories.map(serviceCategoryMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Categories retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteCategory(DeleteServiceCategoryRequest request) {
        ServiceCategory category = serviceCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found.", HttpStatus.NOT_FOUND));
        serviceCategoryRepository.delete(category);
        return ApiResponse.success(null, "Category deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteCategory(SoftDeleteCategoryRequest request) {
        serviceCategoryRepository.softDeleteById(request.getCategoryId());
        return ApiResponse.success(null, "Category soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateCategory(ActivateCategoryRequest request) {
        ServiceCategory category = serviceCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found.", HttpStatus.NOT_FOUND));
        category = serviceCategoryMapper.activate(category);
        serviceCategoryRepository.save(category);
        return ApiResponse.success(null, "Category activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateCategory(DeactivateCategoryRequest request) {
        ServiceCategory category = serviceCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found.", HttpStatus.NOT_FOUND));
        category = serviceCategoryMapper.deactivate(category);
        serviceCategoryRepository.save(category);
        return ApiResponse.success(null, "Category deactivated successfully.");
    }

    @Override
    public boolean existsByCode(String code) {
        return serviceCategoryRepository.existsByCode(code);
    }
}
