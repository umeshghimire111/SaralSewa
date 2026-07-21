package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceResponse;
import com.SaralSewa.SaralSewa.shared.entity.Service;
import com.SaralSewa.SaralSewa.shared.entity.ServiceCategory;
import com.SaralSewa.SaralSewa.shared.repository.ServiceCategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ServiceMapper {

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    public ViewServiceResponse viewDetails(Service service) {
        if (service == null) return null;

        ViewServiceResponse response = new ViewServiceResponse();
        response.setName(service.getName());
        response.setCode(service.getCode());
        response.setDescription(service.getDescription());
        response.setCategoryId(Long.valueOf(service.getCategory() != null ? service.getCategory().getId() : null));
        response.setCategoryName(service.getCategory() != null ? service.getCategory().getName() : null);
        response.setCategoryCode(service.getCategory() != null ? service.getCategory().getCode() : null);
        response.setEstimatedDuration(service.getEstimatedDuration());
        response.setBasePrice(service.getBasePrice());
        response.setIsActive(service.getIsActive());
        response.setCreatedAt(service.getCreatedAt());
        response.setUpdatedAt(service.getUpdatedAt());

        return response;
    }

    public abstract ListServiceResponse entityToResponse(Service service);

    public List<ListServiceResponse> listServices(List<Service> services) {
        if (services == null) return null;
        return services.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public Service create(CreateServiceRequest request) {
        if (request == null) return null;

        ServiceCategory category = serviceCategoryRepository.findById(request.getCategoryId()).orElse(null);

        Service service = new Service();
        service.setName(request.getName());
        service.setCode(request.getCode());
        service.setDescription(request.getDescription());
        service.setCategory(category);
        service.setEstimatedDuration(request.getEstimatedDuration());
        service.setBasePrice(request.getBasePrice());
        service.setIsActive(true);
        service.setIsDeleted(false);
        service.setCreatedAt(LocalDateTime.now());
        service.setUpdatedAt(LocalDateTime.now());

        return service;
    }

    public Service update(UpdateServiceRequest request, Service service) {
        if (request == null || service == null) return service;

        if (request.getName() != null) {
            service.setName(request.getName());
        }
        if (request.getCode() != null) {
            service.setCode(request.getCode());
        }
        if (request.getDescription() != null) {
            service.setDescription(request.getDescription());
        }
        if (request.getCategoryId() != null) {
            ServiceCategory category = serviceCategoryRepository.findById(request.getCategoryId()).orElse(null);
            service.setCategory(category);
        }
        if (request.getEstimatedDuration() != null) {
            service.setEstimatedDuration(request.getEstimatedDuration());
        }
        if (request.getBasePrice() != null) {
            service.setBasePrice(request.getBasePrice());
        }
        service.setUpdatedAt(LocalDateTime.now());
        return service;
    }

    public Service deactivate(Service service) {
        if (service == null) return service;
        service.setIsActive(false);
        service.setUpdatedAt(LocalDateTime.now());
        return service;
    }

    public Service activate(Service service) {
        if (service == null) return service;
        service.setIsActive(true);
        service.setUpdatedAt(LocalDateTime.now());
        return service;
    }

    public Service softDelete(Service service) {
        if (service == null) return service;
        service.setIsDeleted(true);
        service.setUpdatedAt(LocalDateTime.now());
        return service;
    }
}