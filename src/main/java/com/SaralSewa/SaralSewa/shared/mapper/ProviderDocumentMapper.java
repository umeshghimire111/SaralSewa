package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderDocumentResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderDocumentResponse;
import com.SaralSewa.SaralSewa.shared.entity.ProviderDocument;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.repository.ServiceProviderRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProviderDocumentMapper {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ViewProviderDocumentResponse viewDetails(ProviderDocument document) {
        if (document == null) return null;

        ViewProviderDocumentResponse response = new ViewProviderDocumentResponse();
        response.setProviderId(Long.valueOf(document.getProvider() != null ? document.getProvider().getId() : null));
        response.setProviderName(document.getProvider() != null ? document.getProvider().getProfession() : null);
        response.setDocumentName(document.getDocumentName());
        response.setDocumentType(document.getDocumentType());
        response.setDocumentUrl(document.getDocumentUrl());
        response.setVerificationStatus(document.getVerificationStatus());
        response.setDescription(document.getDescription());
        response.setIsActive(document.getIsActive());
        response.setCreatedAt(document.getCreatedAt());
        response.setUpdatedAt(document.getUpdatedAt());

        return response;
    }

    public abstract ListProviderDocumentResponse entityToResponse(ProviderDocument document);

    public List<ListProviderDocumentResponse> listDocuments(List<ProviderDocument> documents) {
        if (documents == null) return null;
        return documents.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public ProviderDocument create(CreateProviderDocumentRequest request) {
        if (request == null) return null;

        ServiceProvider provider = serviceProviderRepository.findByUserId(request.getProviderId());

        ProviderDocument document = new ProviderDocument();
        document.setProvider(provider);
        document.setDocumentName(request.getDocumentName());
        document.setDocumentType(request.getDocumentType());
        document.setDocumentUrl(request.getDocumentUrl());
        document.setVerificationStatus(request.getVerificationStatus());
        document.setDescription(request.getDescription());
        request.getExpiryDate();
        document.setIsActive(true);
        document.setIsDeleted(false);
        document.setCreatedAt(LocalDateTime.now());
        document.setUpdatedAt(LocalDateTime.now());

        return document;
    }

    public ProviderDocument update(UpdateProviderDocumentRequest request, ProviderDocument document) {
        if (request == null || document == null) return document;

        if (request.getDocumentName() != null) {
            document.setDocumentName(request.getDocumentName());
        }
        if (request.getDocumentType() != null) {
            document.setDocumentType(request.getDocumentType());
        }
        if (request.getDocumentUrl() != null) {
            document.setDocumentUrl(request.getDocumentUrl());
        }
        if (request.getVerificationStatus() != null) {
            document.setVerificationStatus(request.getVerificationStatus());
        }
        if (request.getDescription() != null) {
            document.setDescription(request.getDescription());
        }
        if (request.getExpiryDate() != null) {
            request.getExpiryDate();
        }
        document.setUpdatedAt(LocalDateTime.now());
        return document;
    }

    public ProviderDocument deactivate(ProviderDocument document) {
        if (document == null) return document;
        document.setIsActive(false);
        document.setUpdatedAt(LocalDateTime.now());
        return document;
    }

    public ProviderDocument activate(ProviderDocument document) {
        if (document == null) return document;
        document.setIsActive(true);
        document.setUpdatedAt(LocalDateTime.now());
        return document;
    }

    public ProviderDocument softDelete(ProviderDocument document) {
        if (document == null) return document;
        document.setIsDeleted(true);
        document.setUpdatedAt(LocalDateTime.now());
        return document;
    }
}