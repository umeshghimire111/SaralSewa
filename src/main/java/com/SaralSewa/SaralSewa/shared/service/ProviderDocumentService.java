package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.document.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderDocumentResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderDocumentResponse;

public interface ProviderDocumentService {

    ApiResponse<ViewProviderDocumentResponse> createDocument(CreateProviderDocumentRequest request);

    ApiResponse<ViewProviderDocumentResponse> updateDocument(UpdateProviderDocumentRequest request);

    ApiResponse<ViewProviderDocumentResponse> getDocumentById(GetDocumentByIdRequest request);

    ApiResponse<PageResponse<ListProviderDocumentResponse>> getAllDocuments(PageRequest pageRequest);

    ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByProvider(GetDocumentsByProviderRequest request);

    ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByType(GetDocumentsByTypeRequest request);

    ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByVerificationStatus(GetDocumentsByVerificationStatusRequest request);

    ApiResponse<?> deleteDocument(DeleteProviderDocumentRequest request);

    ApiResponse<?> softDeleteDocument(SoftDeleteDocumentRequest request);

    ApiResponse<?> activateDocument(ActivateDocumentRequest request);

    ApiResponse<?> deactivateDocument(DeactivateDocumentRequest request);

    ApiResponse<?> verifyDocument(VerifyDocumentRequest request);

    ApiResponse<?> rejectDocument(RejectDocumentRequest request);
}
