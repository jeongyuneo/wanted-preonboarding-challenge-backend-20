package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.handler;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.dto.LoginResponse;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.service.AuthenticationService;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.token.Token;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.token.TokenPayload;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.converter.JsonConverter;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.entity.User;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.service.UserReadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthenticationService authenticationService;
    private final UserReadService userReadService;

    @Value("${cookie.domain}")
    private String cookieDomain;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        User user = userReadService.findByUsername(extractUsername(authentication));
        TokenPayload payload = authenticationService.generateToken(user.getId());
        setResponseBody(response, payload.accessToken());
        setCookie(response, payload.refreshToken());
    }

    private String extractUsername(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    private void setResponseBody(HttpServletResponse response, String accessToken) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(JsonConverter.getWriteValueAsString(LoginResponse.from(accessToken)));
    }

    private void setCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie refreshTokenCookie = ResponseCookie.from(Token.REFRESH_TOKEN.getHeaderName(), refreshToken)
                .domain(cookieDomain)
                .path("/")
                .sameSite("None")
                .maxAge(Token.REFRESH_TOKEN.getExpirationTime())
                .httpOnly(true)
                .secure(true)
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    }
}
