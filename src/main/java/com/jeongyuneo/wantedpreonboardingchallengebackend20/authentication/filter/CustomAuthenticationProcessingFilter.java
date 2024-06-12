package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.filter;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.converter.JsonConverter;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.ApplicationExceptionStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Map;

public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final String DEFAULT_LOGIN_PROCESSING_URL = "/login";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public CustomAuthenticationProcessingFilter() {
        super(DEFAULT_LOGIN_PROCESSING_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        validateContentType(request.getContentType());
        Map<String, Object> body = JsonConverter.getBody(request);
        return getAuthenticationManager()
                .authenticate(UsernamePasswordAuthenticationToken.unauthenticated(body.get(USERNAME), body.get(PASSWORD)));
    }

    private void validateContentType(String contentType) {
        if (contentType == null || !contentType.equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException(ApplicationExceptionStatus.UNSUPPORTED_CONTENT_TYPE.getMessage());
        }
    }
}
