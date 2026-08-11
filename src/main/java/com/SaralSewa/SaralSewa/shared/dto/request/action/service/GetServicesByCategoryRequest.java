package com.SaralSewa.SaralSewa.shared.dto.request.action.service;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetServicesByCategoryRequest extends ModelBase {

    @NotNull(message = "Category ID is required")
    private Integer categoryId;

    private PageRequest pageRequest;
}
