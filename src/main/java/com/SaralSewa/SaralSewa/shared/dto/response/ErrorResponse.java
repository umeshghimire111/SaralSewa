package com.SaralSewa.SaralSewa.shared.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String details;
    private Map<String, String> validationErrors;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
}
