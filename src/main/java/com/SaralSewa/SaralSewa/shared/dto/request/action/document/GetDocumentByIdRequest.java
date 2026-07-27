package com.SaralSewa.SaralSewa.shared.dto.request.action.document;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDocumentByIdRequest extends ModelBase {

    @NotNull(message = "Document ID is required")
    private Integer documentId;
}
