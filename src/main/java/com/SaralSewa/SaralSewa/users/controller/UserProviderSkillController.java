package com.SaralSewa.SaralSewa.users.controller;

import com.SaralSewa.SaralSewa.shared.core.config.constant.ApiConstant;
import com.SaralSewa.SaralSewa.shared.core.controller.BaseController;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteProviderSkillRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.action.skill.*;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListProviderSkillResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewProviderSkillResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.service.ProviderSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.SLASH + ApiConstant.SKILLS)
@RequiredArgsConstructor
public class UserProviderSkillController extends BaseController {

    private final ProviderSkillService providerSkillService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ViewProviderSkillResponse> createSkill(@Valid @RequestBody CreateProviderSkillRequest request) {
        return providerSkillService.createSkill(request);
    }

    @PutMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewProviderSkillResponse> updateSkill(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProviderSkillRequest request) {
        request.setSkillId(id);
        return providerSkillService.updateSkill(request);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<ViewProviderSkillResponse> getSkillById(@PathVariable Integer id) {
        GetSkillByIdRequest request = new GetSkillByIdRequest();
        request.setSkillId(id);
        return providerSkillService.getSkillById(request);
    }

    @GetMapping
    public ApiResponse<PageResponse<ListProviderSkillResponse>> getAllSkills(@Valid PageRequest pageRequest) {
        return providerSkillService.getAllSkills(pageRequest);
    }

    @GetMapping(ApiConstant.SLASH + ApiConstant.PROVIDER + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<PageResponse<ListProviderSkillResponse>> getSkillsByProvider(
            @PathVariable Integer id,
            @Valid PageRequest pageRequest) {
        GetSkillsByProviderRequest request = new GetSkillsByProviderRequest();
        request.setProviderId(id);
        request.setPageRequest(pageRequest);
        return providerSkillService.getSkillsByProvider(request);
    }

    @DeleteMapping(ApiConstant.SLASH + ApiConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<?> deleteSkill(@PathVariable Integer id) {
        DeleteProviderSkillRequest request = new DeleteProviderSkillRequest();
        request.setSkillId(id);
        return providerSkillService.deleteSkill(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.SOFT_DELETE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> softDeleteSkill(@PathVariable Integer id) {
        SoftDeleteSkillRequest request = new SoftDeleteSkillRequest();
        request.setSkillId(id);
        return providerSkillService.softDeleteSkill(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.ACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> activateSkill(@PathVariable Integer id) {
        ActivateSkillRequest request = new ActivateSkillRequest();
        request.setSkillId(id);
        return providerSkillService.activateSkill(request);
    }

    @PatchMapping(ApiConstant.SLASH + ApiConstant.DEACTIVATE + ApiConstant.SLASH + ApiConstant.ID)
    public ApiResponse<?> deactivateSkill(@PathVariable Integer id) {
        DeactivateSkillRequest request = new DeactivateSkillRequest();
        request.setSkillId(id);
        return providerSkillService.deactivateSkill(request);
    }
}
