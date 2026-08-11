package com.SaralSewa.SaralSewa.shared.dto.request.action.document;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDocumentsByProviderRequest extends ModelBase {

    @NotNull(message = "Provider ID is required")
    private Integer providerId;

    private PageRequest pageRequest;
}
