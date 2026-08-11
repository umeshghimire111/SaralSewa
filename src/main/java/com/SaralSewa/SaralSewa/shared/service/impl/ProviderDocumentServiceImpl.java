package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.document.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderDocumentResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderDocumentResponse;
import com.SaralSewa.SaralSewa.shared.entity.ProviderDocument;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ProviderDocumentMapper;
import com.SaralSewa.SaralSewa.shared.repository.ProviderDocumentRepository;
import com.SaralSewa.SaralSewa.shared.service.ProviderDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProviderDocumentServiceImpl implements ProviderDocumentService {

    private final ProviderDocumentRepository providerDocumentRepository;
    private final ProviderDocumentMapper providerDocumentMapper;

    @Override
    public ApiResponse<ViewProviderDocumentResponse> createDocument(CreateProviderDocumentRequest request) {
        ProviderDocument document = providerDocumentMapper.create(request);
        ProviderDocument savedDocument = providerDocumentRepository.save(document);
        return ApiResponse.created(providerDocumentMapper.viewDetails(savedDocument), "Document created successfully.");
    }

    @Override
    public ApiResponse<ViewProviderDocumentResponse> updateDocument(UpdateProviderDocumentRequest request) {
        ProviderDocument document = providerDocumentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new ApiException("Document not found.", HttpStatus.NOT_FOUND));
        document = providerDocumentMapper.update(request, document);
        ProviderDocument updatedDocument = providerDocumentRepository.save(document);
        return ApiResponse.success(providerDocumentMapper.viewDetails(updatedDocument), "Document updated successfully.");
    }

    @Override
    public ApiResponse<ViewProviderDocumentResponse> getDocumentById(GetDocumentByIdRequest request) {
        ProviderDocument document = providerDocumentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new ApiException("Document not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(providerDocumentMapper.viewDetails(document), "Document retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getAllDocuments(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<ProviderDocument> documents = providerDocumentRepository.findAll(pageable);
        Page<ListProviderDocumentResponse> response = documents.map(providerDocumentMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Documents retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByProvider(GetDocumentsByProviderRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<ProviderDocument> documents = providerDocumentRepository.findByProviderId(request.getProviderId(), pageable);
        Page<ListProviderDocumentResponse> response = documents.map(providerDocumentMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Provider documents retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByType(GetDocumentsByTypeRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<ProviderDocument> documents = providerDocumentRepository.findByDocumentType(request.getDocumentType(), pageable);
        Page<ListProviderDocumentResponse> response = documents.map(providerDocumentMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Documents by type retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByVerificationStatus(GetDocumentsByVerificationStatusRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<ProviderDocument> documents = providerDocumentRepository.findByVerificationStatus(request.getVerificationStatus(), pageable);
        Page<ListProviderDocumentResponse> response = documents.map(providerDocumentMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Documents by verification status retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteDocument(DeleteProviderDocumentRequest request) {
        ProviderDocument document = providerDocumentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new ApiException("Document not found.", HttpStatus.NOT_FOUND));
        providerDocumentRepository.delete(document);
        return ApiResponse.success(null, "Document deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteDocument(SoftDeleteDocumentRequest request) {
        providerDocumentRepository.softDeleteById(request.getDocumentId());
        return ApiResponse.success(null, "Document soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateDocument(ActivateDocumentRequest request) {
        ProviderDocument document = providerDocumentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new ApiException("Document not found.", HttpStatus.NOT_FOUND));
        document = providerDocumentMapper.activate(document);
        providerDocumentRepository.save(document);
        return ApiResponse.success(null, "Document activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateDocument(DeactivateDocumentRequest request) {
        ProviderDocument document = providerDocumentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new ApiException("Document not found.", HttpStatus.NOT_FOUND));
        document = providerDocumentMapper.deactivate(document);
        providerDocumentRepository.save(document);
        return ApiResponse.success(null, "Document deactivated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> verifyDocument(VerifyDocumentRequest request) {
        ProviderDocument document = providerDocumentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new ApiException("Document not found.", HttpStatus.NOT_FOUND));
        document.setVerificationStatus("VERIFIED");
        document.setUpdatedAt(java.time.LocalDateTime.now());
        providerDocumentRepository.save(document);
        return ApiResponse.success(null, "Document verified successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> rejectDocument(RejectDocumentRequest request) {
        ProviderDocument document = providerDocumentRepository.findById(request.getDocumentId())
                .orElseThrow(() -> new ApiException("Document not found.", HttpStatus.NOT_FOUND));
        document.setVerificationStatus("REJECTED");
        document.setUpdatedAt(java.time.LocalDateTime.now());
        providerDocumentRepository.save(document);
        return ApiResponse.success(null, "Document rejected successfully.");
    }
}
