package com.SaralSewa.SaralSewa.shared.dto.model;

import com.SaralSewa.SaralSewa.shared.core.entity.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserModel extends ModelBase {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String profileImage;
    private String description;
    private String roleCode;
    private String statusCode;
    private Boolean isActive;
    private Boolean isDeleted;
    private LocalDateTime lastLoggedInTime;
    private Integer wrongPasswordAttemptCount;
}
