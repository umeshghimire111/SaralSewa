package com.SaralSewa.SaralSewa.shared.dto.request.action.review;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetReviewsByCustomerRequest extends ModelBase {

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    private PageRequest pageRequest;
}
