package com.SaralSewa.SaralSewa.shared.core.security;



import com.SaralSewa.SaralSewa.shared.core.dto.response.ApiResponse;
import com.SaralSewa.SaralSewa.shared.core.dto.response.ResponseUtil;
import com.SaralSewa.SaralSewa.shared.entity.UserToken;
import com.SaralSewa.SaralSewa.shared.repository.UserTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomLogoutHandler implements LogoutHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomLogoutHandler.class);


    private final UserTokenRepository userTokenRepository;
    public  ObjectMapper objectMappers;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        response.setContentType("application/json");
        ApiResponse<?> apiResponse;
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            apiResponse = ResponseUtil.getFailureResponse("Invalid token");
            writeResponse(response, apiResponse);
            return;
        }

        String token = authHeader.substring(7);
        UserToken storedToken = userTokenRepository.findByAccessTokenAndLoggedOutFalse(token).orElse(null);

        if (storedToken != null) {
            storedToken.setLoggedOut(true);
            userTokenRepository.save(storedToken);
            LOG.info("Logged out successfully");
            apiResponse = ResponseUtil.getSuccessfulApiResponse("Logged out successfully");
            writeResponse(response, apiResponse);
            return;
        }
        apiResponse = ResponseUtil.getFailureResponse("Logout Unsuccessful");
        writeResponse(response, apiResponse);
    }

    private void writeResponse( HttpServletResponse response, ApiResponse<?> apiResponse) {
        response.setContentType("application/json");
        try {
            response.getWriter().write(objectMappers.writeValueAsString(apiResponse));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




