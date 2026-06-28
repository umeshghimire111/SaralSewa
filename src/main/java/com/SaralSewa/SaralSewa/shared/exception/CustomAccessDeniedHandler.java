package com.SaralSewa.SaralSewa.shared.exception;



import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;

@Component

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public CustomAccessDeniedHandler() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public void handle( HttpServletRequest request,
                       HttpServletResponse response,
                        AccessDeniedException accessDeniedException) throws IOException{
        response.setContentType("application/json");
        ApiResponse<?> apiResponse;
        apiResponse = ResponseUtil.getUnAuthorized("Unauthorized Request: Access Denied!");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));

    }
}
