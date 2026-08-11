package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.action.skill.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderSkillResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderSkillResponse;

public interface ProviderSkillService {

    ApiResponse<ViewProviderSkillResponse> createSkill(CreateProviderSkillRequest request);

    ApiResponse<ViewProviderSkillResponse> updateSkill(UpdateProviderSkillRequest request);

    ApiResponse<ViewProviderSkillResponse> getSkillById(GetSkillByIdRequest request);

    ApiResponse<PageResponse<ListProviderSkillResponse>> getAllSkills(PageRequest pageRequest);

    ApiResponse<PageResponse<ListProviderSkillResponse>> getSkillsByProvider(GetSkillsByProviderRequest request);

    ApiResponse<?> deleteSkill(DeleteProviderSkillRequest request);

    ApiResponse<?> softDeleteSkill(SoftDeleteSkillRequest request);

    ApiResponse<?> activateSkill(ActivateSkillRequest request);

    ApiResponse<?> deactivateSkill(DeactivateSkillRequest request);
}
