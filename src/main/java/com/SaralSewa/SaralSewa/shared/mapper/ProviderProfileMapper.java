package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.request.action.provider.ProviderProfileRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderProfileResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderProfileResponse;
import com.SaralSewa.SaralSewa.shared.entity.ProviderProfile;
import com.SaralSewa.SaralSewa.shared.entity.ServiceProvider;
import com.SaralSewa.SaralSewa.shared.repository.ServiceProviderRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProviderProfileMapper {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ViewProviderProfileResponse viewDetails(ProviderProfile profile) {
        if (profile == null) return null;

        ViewProviderProfileResponse response = new ViewProviderProfileResponse();
        response.setProviderId(profile.getProvider() != null ? profile.getProvider().getId() : null);
        response.setProviderName(profile.getProvider() != null ? profile.getProvider().getProfession() : null);
        response.setProviderProfession(profile.getProvider() != null ? profile.getProvider().getProfession() : null);
        response.setBio(profile.getBio());
        response.setYearsOfExperience(profile.getYearsOfExperience());
        response.setHourlyRate(profile.getHourlyRate());
        response.setCity(profile.getCity());
        response.setDistrict(profile.getDistrict());
        response.setProfileImage(profile.getProfileImage());
        response.setIsActive(profile.getIsActive());
        response.setCreatedAt(profile.getCreatedAt());
        response.setUpdatedAt(profile.getUpdatedAt());

        return response;
    }

    public abstract ListProviderProfileResponse entityToResponse(ProviderProfile profile);

    public List<ListProviderProfileResponse> listProfiles(List<ProviderProfile> profiles) {
        if (profiles == null) return null;
        return profiles.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public ProviderProfile create(ProviderProfileRequest request) {
        if (request == null) return null;


        ServiceProvider provider = serviceProviderRepository.findById(request.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found: " + request.getProviderId()));

        ProviderProfile profile = new ProviderProfile();
        profile.setProvider(provider);
        profile.setBio(request.getBio());
        profile.setYearsOfExperience(request.getYearsOfExperience());
        profile.setHourlyRate(request.getHourlyRate());
        profile.setCity(request.getCity());
        profile.setDistrict(request.getDistrict());
        profile.setProfileImage(request.getProfileImage());
        profile.setIsActive(true);
        profile.setIsDeleted(false);
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());

        return profile;
    }

    public ProviderProfile update(ProviderProfileRequest request, ProviderProfile profile) {
        if (request == null || profile == null) return profile;

        if (request.getBio() != null) {
            profile.setBio(request.getBio());
        }
        if (request.getYearsOfExperience() != null) {
            profile.setYearsOfExperience(request.getYearsOfExperience());
        }
        if (request.getHourlyRate() != null) {
            profile.setHourlyRate(request.getHourlyRate());
        }
        if (request.getCity() != null) {
            profile.setCity(request.getCity());
        }
        if (request.getDistrict() != null) {
            profile.setDistrict(request.getDistrict());
        }
        if (request.getProfileImage() != null) {
            profile.setProfileImage(request.getProfileImage());
        }
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }

    public ProviderProfile deactivate(ProviderProfile profile) {
        if (profile == null) return profile;
        profile.setIsActive(false);
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }

    public ProviderProfile activate(ProviderProfile profile) {
        if (profile == null) return profile;
        profile.setIsActive(true);
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }

    public ProviderProfile softDelete(ProviderProfile profile) {
        if (profile == null) return profile;
        profile.setIsDeleted(true);
        profile.setUpdatedAt(LocalDateTime.now());
        return profile;
    }
}