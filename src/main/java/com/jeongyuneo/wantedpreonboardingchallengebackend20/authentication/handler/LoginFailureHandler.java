package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.handler;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.service.AuthenticationService;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.converter.JsonConverter;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.ApplicationExceptionStatus;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.dto.ExceptionResponse;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.service.UserReadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final AuthenticationService authenticationService;
    private final UserReadService userReadService;

    @Value("${cookie.domain}")
    private String cookieDomain;

    @Value("${zoneId}")
    private String zoneId;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        setResponseBody(response, ExceptionResponse.of(request, ApplicationExceptionStatus.USER_NOT_FOUND, zoneId));
    }

    private void setResponseBody(HttpServletResponse response, ExceptionResponse exceptionResponse) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JsonConverter.getWriteValueAsString(exceptionResponse));
    }
}
