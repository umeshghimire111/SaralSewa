package com.SaralSewa.SaralSewa.shared.dto.response.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListUserResponse {
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    private String roleName;
    private String statusName;
    private Boolean isActive;
    private String profileImage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
