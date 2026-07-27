package com.SaralSewa.SaralSewa.shared.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
}
