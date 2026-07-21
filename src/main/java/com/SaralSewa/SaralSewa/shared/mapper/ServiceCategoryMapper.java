package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceCategoryRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceCategoryResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceCategoryResponse;
import com.SaralSewa.SaralSewa.shared.entity.ServiceCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ServiceCategoryMapper {

    public ViewServiceCategoryResponse viewDetails(ServiceCategory category) {
        if (category == null) return null;

        ViewServiceCategoryResponse response = new ViewServiceCategoryResponse();
        response.setName(category.getName());
        response.setCode(category.getCode());
        response.setDescription(category.getDescription());
        response.setIsActive(category.getIsActive());
        response.setServiceCount(0);
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());

        return response;
    }

    public abstract ListServiceCategoryResponse entityToResponse(ServiceCategory category);

    public List<ListServiceCategoryResponse> listCategories(List<ServiceCategory> categories) {
        if (categories == null) return null;
        return categories.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public ServiceCategory create(CreateServiceCategoryRequest request) {
        if (request == null) return null;

        ServiceCategory category = new ServiceCategory();
        category.setName(request.getName());
        category.setCode(request.getCode());
        category.setDescription(request.getDescription());
        category.setIsActive(true);
        category.setIsDeleted(false);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        return category;
    }

    public ServiceCategory update(UpdateServiceCategoryRequest request, ServiceCategory category) {
        if (request == null || category == null) return category;

        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getCode() != null) {
            category.setCode(request.getCode());
        }
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }

    public ServiceCategory deactivate(ServiceCategory category) {
        if (category == null) return category;
        category.setIsActive(false);
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }

    public ServiceCategory activate(ServiceCategory category) {
        if (category == null) return category;
        category.setIsActive(true);
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }

    public ServiceCategory softDelete(ServiceCategory category) {
        if (category == null) return category;
        category.setIsDeleted(true);
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }
}