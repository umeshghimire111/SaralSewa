package com.SaralSewa.SaralSewa.shared.service;

import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageRequest;
import com.SaralSewa.SaralSewa.shared.core.dto.response.PageResponse;
import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;
import com.SaralSewa.SaralSewa.shared.dto.request.action.user.*;
import com.SaralSewa.SaralSewa.shared.dto.request.create.CreateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.delete.DeleteUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.request.update.UpdateUserRequest;
import com.SaralSewa.SaralSewa.shared.dto.response.list.ListUserResponse;
import com.SaralSewa.SaralSewa.shared.dto.response.view.ViewUserResponse;

public interface UserService {

    ApiResponse<ViewUserResponse> createUser(CreateUserRequest request);

    ApiResponse<ViewUserResponse> updateUser(UpdateUserRequest request);

    ApiResponse<ViewUserResponse> getUserById(GetUserByIdRequest request);

    ApiResponse<ViewUserResponse> getUserByEmail(GetUserByEmailRequest request);

    ApiResponse<PageResponse<ListUserResponse>> getAllUsers(PageRequest pageRequest);

    ApiResponse<?> deleteUser(DeleteUserRequest request);

    ApiResponse<?> softDeleteUser(SoftDeleteUserRequest request);

    ApiResponse<?> restoreUser(RestoreUserRequest request);

    ApiResponse<?> activateUser(ActivateUserRequest request);

    ApiResponse<?> deactivateUser(DeactivateUserRequest request);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
