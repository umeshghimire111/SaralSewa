package com.SaralSewa.SaralSewa.shared.dto.request.action.document;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDocumentsByVerificationStatusRequest extends ModelBase {

    @NotBlank(message = "Verification status is required")
    private String verificationStatus;

    private PageRequest pageRequest;
}
