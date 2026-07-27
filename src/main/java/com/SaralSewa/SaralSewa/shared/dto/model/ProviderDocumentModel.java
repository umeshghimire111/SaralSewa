package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProviderDocumentModel extends ModelBase {
    private Integer providerId;
    private String documentName;
    private String documentType;
    private String documentUrl;
    private String verificationStatus;
    private String description;
    private LocalDateTime expiryDate;
    private Boolean isActive;
    private Boolean isDeleted;
}
