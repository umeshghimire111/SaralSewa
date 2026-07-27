package com.SaralSewa.SaralSewa.shared.dto.request.action.category;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeactivateCategoryRequest extends ModelBase {

    @NotNull(message = "Category ID is required")
    private Integer categoryId;
}
