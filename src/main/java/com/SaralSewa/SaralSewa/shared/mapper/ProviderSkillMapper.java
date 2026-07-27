package com.SaralSewa.SaralSewa.shared.mapper;

import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderSkillResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderSkillResponse;
import com.SaralSewa.SaralSewa.shared.entity.ProviderSkill;
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
public abstract class ProviderSkillMapper {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ViewProviderSkillResponse viewDetails(ProviderSkill skill) {
        if (skill == null) return null;

        ViewProviderSkillResponse response = new ViewProviderSkillResponse();
        response.setProviderId(skill.getProvider() != null ? skill.getProvider().getId() : null);
        response.setProviderName(skill.getProvider() != null ? skill.getProvider().getProfession() : null);
        response.setProviderProfession(skill.getProvider() != null ? skill.getProvider().getProfession() : null);
        response.setSkillName(skill.getSkillName());
        response.setDescription(skill.getDescription());
        response.setIsActive(skill.getIsActive());
        response.setCreatedAt(skill.getCreatedAt());
        response.setUpdatedAt(skill.getUpdatedAt());

        return response;
    }

    public abstract ListProviderSkillResponse entityToResponse(ProviderSkill skill);

    public List<ListProviderSkillResponse> listSkills(List<ProviderSkill> skills) {
        if (skills == null) return null;
        return skills.stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public ProviderSkill create(CreateProviderSkillRequest request) {
        if (request == null) return null;

        ServiceProvider provider = serviceProviderRepository.findByUserId(request.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found: " + request.getProviderId()));

        ProviderSkill skill = new ProviderSkill();
        skill.setProvider(provider);
        skill.setSkillName(request.getSkillName());
        skill.setDescription(request.getDescription());
        skill.setIsActive(true);
        skill.setIsDeleted(false);
        skill.setCreatedAt(LocalDateTime.now());
        skill.setUpdatedAt(LocalDateTime.now());

        return skill;
    }

    public ProviderSkill update(UpdateProviderSkillRequest request, ProviderSkill skill) {
        if (request == null || skill == null) return skill;

        if (request.getSkillName() != null) {
            skill.setSkillName(request.getSkillName());
        }
        if (request.getDescription() != null) {
            skill.setDescription(request.getDescription());
        }
        skill.setUpdatedAt(LocalDateTime.now());
        return skill;
    }

    public ProviderSkill deactivate(ProviderSkill skill) {
        if (skill == null) return skill;
        skill.setIsActive(false);
        skill.setUpdatedAt(LocalDateTime.now());
        return skill;
    }

    public ProviderSkill activate(ProviderSkill skill) {
        if (skill == null) return skill;
        skill.setIsActive(true);
        skill.setUpdatedAt(LocalDateTime.now());
        return skill;
    }

    public ProviderSkill softDelete(ProviderSkill skill) {
        if (skill == null) return skill;
        skill.setIsDeleted(true);
        skill.setUpdatedAt(LocalDateTime.now());
        return skill;
    }
}