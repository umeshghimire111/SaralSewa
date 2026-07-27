package com.SaralSewa.SaralSewa.shared.dto.request.create;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateProviderDocumentRequest extends ModelBase {

    @NotNull(message = "Provider ID is required")
    private Integer providerId;

    @NotBlank(message = "Document name is required")
    @Size(max = 100, message = "Document name must be less than 100 characters")
    private String documentName;

    @NotBlank(message = "Document type is required")
    @Size(max = 50, message = "Document type must be less than 50 characters")
    private String documentType;

    @NotBlank(message = "Document URL is required")
    @Size(max = 255, message = "Document URL must be less than 255 characters")
    private String documentUrl;

    @NotBlank(message = "Verification status is required")
    @Size(max = 50, message = "Verification status must be less than 50 characters")
    private String verificationStatus;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private LocalDateTime expiryDate;
}
