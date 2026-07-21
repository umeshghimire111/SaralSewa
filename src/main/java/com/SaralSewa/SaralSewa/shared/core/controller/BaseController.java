package com.SaralSewa.SaralSewa.shared.core.controller;


import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected ResponseEntity<ApiResponse<?>> ok(ApiResponse<?> response) {
        return ResponseEntity.ok(response);
    }

    protected ResponseEntity<ApiResponse<?>> created(ApiResponse<?> response) {
        return ResponseEntity.status(201).body(response);
    }

    protected ResponseEntity<ApiResponse<?>> badRequest(ApiResponse<?> response) {
        return ResponseEntity.badRequest().body(response);
    }
}

