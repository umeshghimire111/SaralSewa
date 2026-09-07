package com.SaralSewa.SaralSewa.shared.dto.request.action;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResendOtpRequest extends ModelBase {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}