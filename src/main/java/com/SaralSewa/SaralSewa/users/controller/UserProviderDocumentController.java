package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderDocumentRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.document.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderDocumentResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderDocumentResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ProviderDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.DOCUMENTS)
@RequiredArgsConstructor
public class UserProviderDocumentController extends BaseController {

    private final ProviderDocumentService providerDocumentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewProviderDocumentResponse> createDocument(@Valid @RequestBody CreateProviderDocumentRequest request) {
        return providerDocumentService.createDocument(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewProviderDocumentResponse> updateDocument(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProviderDocumentRequest request) {
        request.setDocumentId(id);
        return providerDocumentService.updateDocument(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewProviderDocumentResponse> getDocumentById(@PathVariable Integer id) {
        GetDocumentByIdRequest request = new GetDocumentByIdRequest();
        request.setDocumentId(id);
        return providerDocumentService.getDocumentById(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getAllDocuments(@Valid PageRequest pageRequest) {
        return providerDocumentService.getAllDocuments(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByProvider(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetDocumentsByProviderRequest request = new GetDocumentsByProviderRequest();
        request.setProviderId(id);
        request.setPageRequest(pageRequest);
        return providerDocumentService.getDocumentsByProvider(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.TYPE + ApiConstant.SLASH + "{type}")
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByType(
            @PathVariable String type,
            @Valid PageRequest pageRequest) {
        GetDocumentsByTypeRequest request = new GetDocumentsByTypeRequest();
        request.setDocumentType(type);
        request.setPageRequest(pageRequest);
        return providerDocumentService.getDocumentsByType(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.VERIFICATION_STATUS + ApiConstant.SLASH + "{status}")
    public ApiResponse<PageResponse<ListProviderDocumentResponse>> getDocumentsByVerificationStatus(
            @PathVariable String status,
            @Valid PageRequest pageRequest) {
        GetDocumentsByVerificationStatusRequest request = new GetDocumentsByVerificationStatusRequest();
        request.setVerificationStatus(status);
        request.setPageRequest(pageRequest);
        return providerDocumentService.getDocumentsByVerificationStatus(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteDocument(@PathVariable Integer id) {
        DeleteProviderDocumentRequest request = new DeleteProviderDocumentRequest();
        request.setDocumentId(id);
        return providerDocumentService.deleteDocument(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteDocument(@PathVariable Integer id) {
        SoftDeleteDocumentRequest request = new SoftDeleteDocumentRequest();
        request.setDocumentId(id);
        return providerDocumentService.softDeleteDocument(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateDocument(@PathVariable Integer id) {
        ActivateDocumentRequest request = new ActivateDocumentRequest();
        request.setDocumentId(id);
        return providerDocumentService.activateDocument(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateDocument(@PathVariable Integer id) {
        DeactivateDocumentRequest request = new DeactivateDocumentRequest();
        request.setDocumentId(id);
        return providerDocumentService.deactivateDocument(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.VERIFY + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> verifyDocument(@PathVariable Integer id) {
        VerifyDocumentRequest request = new VerifyDocumentRequest();
        request.setDocumentId(id);
        return providerDocumentService.verifyDocument(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.REJECT + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> rejectDocument(@PathVariable Integer id) {
        RejectDocumentRequest request = new RejectDocumentRequest();
        request.setDocumentId(id);
        return providerDocumentService.rejectDocument(request);
    }
}
