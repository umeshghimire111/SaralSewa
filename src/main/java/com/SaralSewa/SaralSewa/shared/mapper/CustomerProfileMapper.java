package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewCustomerProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateCustomerProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListCustomerProfileResponse;
import com.SaralSewa.SaralSewa.shared.entity.CustomerProfiles;
import com.SaralSewa.SaralSewa.shared.entity.User;
import com.SaralSewa.SaralSewa.shared.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CustomerProfileMapper {

    @Autowired
    private UserRepository userRepository;

    public ViewCustomerProfileResponse viewDetails(CustomerProfiles profile) {
        if (profile == null) return null;

        ViewCustomerProfileResponse response = new ViewCustomerProfileResponse();
        response.setUserId(profile.getUser() != null ? profile.getUser().getId() : null);
        response.setUserFullName(profile.getUser() != null ?
                profile.getUser().getFirstName() + (profile.getUser().getLastName() != null ? " " + profile.getUser().getLastName() : "") : null);
        response.setUserEmail(profile.getUser() != null ? profile.getUser().getEmail() : null);
        response.setUserPhone(profile.getUser() != null ? profile.getUser().getPhone() : null);
        response.setAddress(profile.getAddress());
        response.setSavedAddresses(profile.getSavedAddresses());
        response.setPreferredPayment(profile.getPreferredPayment());
        response.setPreferredContact(profile.getPreferredContact());
        response.setNotificationPreferences(profile.getNotificationPreferences());
        response.setIsActive(profile.getIsActive());
        response.setCreatedAt(profile.getCreatedAt());
        response.setUpdatedAt(profile.getUpdatedAt());

        return response;
    }

    public abstract ListCustomerProfileResponse entityToResponse(CustomerProfiles profile);

    public List<ListCustomerProfileResponse> listCustomerProfiles(List<CustomerProfiles> profiles) {
        if (profiles == null) return null;
        return profiles.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public CustomerProfiles create(CreateCustomerProfileRequest request) {
        if (request == null) return null;

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));

        CustomerProfiles profile = new CustomerProfiles();
        profile.setUser(user);
        profile.setAddress(request.getAddress());
        profile.setSavedAddresses(request.getSavedAddresses());
        profile.setPreferredPayment(request.getPreferredPayment());
        profile.setPreferredContact(request.getPreferredContact());
        profile.setNotificationPreferences(request.getNotificationPreferences());
        profile.setIsActive(true);
        profile.setIsDeleted(false);
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());

        return profile;
    }

    public CustomerProfiles update(UpdateCustomerProfileRequest request, CustomerProfiles profile) {
        if (request == null || profile == null) return profile;

        if (request.getAddress() != null) {
            profile.setAddress(request.getAddress());
        }
        if (request.getSavedAddresses() != null) {
            profile.setSavedAddresses(request.getSavedAddresses());
        }
        if (request.getPreferredPayment() != null) {
            profile.setPreferredPayment(request.getPreferredPayment());
        }
        if (request.getPreferredContact() != null) {
            profile.setPreferredContact(request.getPreferredContact());
        }
        if (request.getNotificationPreferences() != null) {
            profile.setNotificationPreferences(request.getNotificationPreferences());
        }
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }

    public CustomerProfiles deactivate(CustomerProfiles profile) {
        if (profile == null) return profile;
        profile.setIsActive(false);
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }

    public CustomerProfiles activate(CustomerProfiles profile) {
        if (profile == null) return profile;
        profile.setIsActive(true);
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }

    public CustomerProfiles softDelete(CustomerProfiles profile) {
        if (profile == null) return profile;
        profile.setIsDeleted(true);
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }
}