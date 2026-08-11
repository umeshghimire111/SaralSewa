package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.category.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceCategoryResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceCategoryResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ServiceCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.CATEGORIES)
@RequiredArgsConstructor
public class UserServiceCategoryController extends BaseController {

    private final ServiceCategoryService serviceCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewServiceCategoryResponse> createCategory(@Valid @RequestBody CreateServiceCategoryRequest request) {
        return serviceCategoryService.createCategory(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewServiceCategoryResponse> updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateServiceCategoryRequest request) {
        request.setCategoryId(id);
        return serviceCategoryService.updateCategory(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewServiceCategoryResponse> getCategoryById(@PathVariable Integer id) {
        GetCategoryByIdRequest request = new GetCategoryByIdRequest();
        request.setCategoryId(id);
        return serviceCategoryService.getCategoryById(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.CODE + ApiConstant.SLASH + "{code}")
    public ApiResponse<ViewServiceCategoryResponse> getCategoryByCode(@PathVariable String code) {
        GetCategoryByCodeRequest request = new GetCategoryByCodeRequest();
        request.setCode(code);
        return serviceCategoryService.getCategoryByCode(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListServiceCategoryResponse>> getAllCategories(@Valid PageRequest pageRequest) {
        return serviceCategoryService.getAllCategories(pageRequest);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteCategory(@PathVariable Integer id) {
        DeleteServiceCategoryRequest request = new DeleteServiceCategoryRequest();
        request.setCategoryId(id);
        return serviceCategoryService.deleteCategory(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteCategory(@PathVariable Integer id) {
        SoftDeleteCategoryRequest request = new SoftDeleteCategoryRequest();
        request.setCategoryId(id);
        return serviceCategoryService.softDeleteCategory(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateCategory(@PathVariable Integer id) {
        ActivateCategoryRequest request = new ActivateCategoryRequest();
        request.setCategoryId(id);
        return serviceCategoryService.activateCategory(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateCategory(@PathVariable Integer id) {
        DeactivateCategoryRequest request = new DeactivateCategoryRequest();
        request.setCategoryId(id);
        return serviceCategoryService.deactivateCategory(request);
    }
}
