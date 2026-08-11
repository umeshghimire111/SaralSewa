package com.SaralSewa.SaralSewa.shared.service.impl;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.skill.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderSkillResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderSkillResponse;
import com.SaralSewa.SaralSewa.shared.entity.ProviderSkill;
import com.SaralSewa.SaralSewa.shared.exception.ApiException;
import com.SaralSewa.SaralSewa.shared.mapper.ProviderSkillMapper;
import com.SaralSewa.SaralSewa.shared.repository.ProviderSkillRepository;
import com.SaralSewa.SaralSewa.shared.service.ProviderSkillService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProviderSkillServiceImpl implements ProviderSkillService {

    private final ProviderSkillRepository providerSkillRepository;
    private final ProviderSkillMapper providerSkillMapper;

    @Override
    public ApiResponse<ViewProviderSkillResponse> createSkill(CreateProviderSkillRequest request) {
        ProviderSkill skill = providerSkillMapper.create(request);
        ProviderSkill savedSkill = providerSkillRepository.save(skill);
        return ApiResponse.created(providerSkillMapper.viewDetails(savedSkill), "Skill created successfully.");
    }

    @Override
    public ApiResponse<ViewProviderSkillResponse> updateSkill(UpdateProviderSkillRequest request) {
        ProviderSkill skill = providerSkillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ApiException("Skill not found.", HttpStatus.NOT_FOUND));
        skill = providerSkillMapper.update(request, skill);
        ProviderSkill updatedSkill = providerSkillRepository.save(skill);
        return ApiResponse.success(providerSkillMapper.viewDetails(updatedSkill), "Skill updated successfully.");
    }

    @Override
    public ApiResponse<ViewProviderSkillResponse> getSkillById(GetSkillByIdRequest request) {
        ProviderSkill skill = providerSkillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ApiException("Skill not found.", HttpStatus.NOT_FOUND));
        return ApiResponse.success(providerSkillMapper.viewDetails(skill), "Skill retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderSkillResponse>> getAllSkills(PageRequest pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        Page<ProviderSkill> skills = providerSkillRepository.findAll(pageable);
        Page<ListProviderSkillResponse> response = skills.map(providerSkillMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Skills retrieved successfully.");
    }

    @Override
    public ApiResponse<PageResponse<ListProviderSkillResponse>> getSkillsByProvider(GetSkillsByProviderRequest request) {
        Pageable pageable = request.getPageRequest() != null ? request.getPageRequest().toPageable() : PageRequest.of(0, 20).toPageable();
        Page<ProviderSkill> skills = providerSkillRepository.findByProviderId(request.getProviderId(), pageable);
        Page<ListProviderSkillResponse> response = skills.map(providerSkillMapper::entityToResponse);
        return ApiResponse.success(PageResponse.from(response), "Provider skills retrieved successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deleteSkill(DeleteProviderSkillRequest request) {
        ProviderSkill skill = providerSkillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ApiException("Skill not found.", HttpStatus.NOT_FOUND));
        providerSkillRepository.delete(skill);
        return ApiResponse.success(null, "Skill deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> softDeleteSkill(SoftDeleteSkillRequest request) {
        providerSkillRepository.softDeleteById(request.getSkillId());
        return ApiResponse.success(null, "Skill soft deleted successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> activateSkill(ActivateSkillRequest request) {
        ProviderSkill skill = providerSkillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ApiException("Skill not found.", HttpStatus.NOT_FOUND));
        skill = providerSkillMapper.activate(skill);
        providerSkillRepository.save(skill);
        return ApiResponse.success(null, "Skill activated successfully.");
    }

    @Override
    @Transactional
    public ApiResponse<?> deactivateSkill(DeactivateSkillRequest request) {
        ProviderSkill skill = providerSkillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ApiException("Skill not found.", HttpStatus.NOT_FOUND));
        skill = providerSkillMapper.deactivate(skill);
        providerSkillRepository.save(skill);
        return ApiResponse.success(null, "Skill deactivated successfully.");
    }
}
