package com.SaralSewa.SaralSewa.shared.dto.response.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListProviderDocumentResponse {
    private Integer providerId;
    private String providerName;
    private String documentName;
    private String documentType;
    private String verificationStatus;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
