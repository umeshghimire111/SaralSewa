package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.constant.ApprovalStatusConstant;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateServiceProviderRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListServiceProviderResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewServiceProviderResponse;
import com.SaralSewa.SaralSewa.shared.entity.ApprovalStatus;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.repository.ApprovalStatusRepository;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ServiceProviderMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApprovalStatusRepository approvalStatusRepository;

    public ViewServiceProviderResponse viewDetails(ServiceProvider provider) {
        if (provider == null) return null;

        ViewServiceProviderResponse response = new ViewServiceProviderResponse();
        response.setUserId(Long.valueOf(provider.getUser() != null ? provider.getUser().getId() : null));
        response.setUserFullName(provider.getUser() != null ?
                provider.getUser().getFirstName() + (provider.getUser().getLastName() != null ? " " + provider.getUser().getLastName() : "") : null);
        response.setUserEmail(provider.getUser() != null ? provider.getUser().getEmail() : null);
        response.setUserPhone(provider.getUser() != null ? provider.getUser().getPhone() : null);
        response.setProfession(provider.getProfession());
        response.setExperienceYears(provider.getExperienceYears());
        response.setApprovalStatusName(provider.getApprovalStatus() != null ? provider.getApprovalStatus().getName() : null);
        response.setApprovalStatusCode(provider.getApprovalStatus() != null ? provider.getApprovalStatus().getCode() : null);
        response.setAverageRating(provider.getAverageRating());
        response.setIsActive(provider.getIsActive());
        response.setCreatedAt(provider.getCreatedAt());
        response.setUpdatedAt(provider.getUpdatedAt());

        return response;
    }

    public abstract ListServiceProviderResponse entityToResponse(ServiceProvider provider);

    public List<ListServiceProviderResponse> listServiceProviders(List<ServiceProvider> providers) {
        if (providers == null) return null;
        return providers.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public ServiceProvider create(CreateServiceProviderRequest request) {
        if (request == null) return null;

        User user = userRepository.findByUserId(request.getUserId());

        ServiceProvider provider = new ServiceProvider();
        provider.setUser(user);
        provider.setProfession(request.getProfession());
        provider.setExperienceYears(request.getExperienceYears());

        ApprovalStatus defaultStatus = approvalStatusRepository.findByCode(ApprovalStatusConstant.PENDING.getName());
        provider.setApprovalStatus(defaultStatus);

        provider.setAverageRating(BigDecimal.ZERO);

        provider.setIsActive(true);
        provider.setIsDeleted(false);
        provider.setCreatedAt(LocalDateTime.now());
        provider.setUpdatedAt(LocalDateTime.now());

        return provider;
    }

    public ServiceProvider update(UpdateServiceProviderRequest request, ServiceProvider provider) {
        if (request == null || provider == null) return provider;

        if (request.getProfession() != null) {
            provider.setProfession(request.getProfession());
        }
        if (request.getExperienceYears() != null) {
            provider.setExperienceYears(request.getExperienceYears());
        }
        if (request.getApprovalStatusCode() != null) {
            ApprovalStatus status = approvalStatusRepository.findByCode(request.getApprovalStatusCode());
            provider.setApprovalStatus(status);
        }
        provider.setUpdatedAt(LocalDateTime.now());
        return provider;
    }

    public ServiceProvider updateApprovalStatus(ServiceProvider provider, String statusCode) {
        if (provider == null || statusCode == null) return provider;
        ApprovalStatus status = approvalStatusRepository.findByCode(statusCode);
        provider.setApprovalStatus(status);
        provider.setUpdatedAt(LocalDateTime.now());
        return provider;
    }

    public ServiceProvider deactivate(ServiceProvider provider) {
        if (provider == null) return provider;
        provider.setIsActive(false);
        provider.setUpdatedAt(LocalDateTime.now());
        return provider;
    }

    public ServiceProvider activate(ServiceProvider provider) {
        if (provider == null) return provider;
        provider.setIsActive(true);
        provider.setUpdatedAt(LocalDateTime.now());
        return provider;
    }

    public ServiceProvider softDelete(ServiceProvider provider) {
        if (provider == null) return provider;
        provider.setIsDeleted(true);
        provider.setUpdatedAt(LocalDateTime.now());
        return provider;
    }
}