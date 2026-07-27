package com.SaralSewa.SaralSewa.shared.dto.request.action.history;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetHistoryByIdRequest extends ModelBase {

    @NotNull(message = "History ID is required")
    private Integer historyId;
}
