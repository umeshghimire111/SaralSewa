package com.SaralSewa.SaralSewa.shared.dto.request.action.user;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserByEmailRequest extends ModelBase {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
