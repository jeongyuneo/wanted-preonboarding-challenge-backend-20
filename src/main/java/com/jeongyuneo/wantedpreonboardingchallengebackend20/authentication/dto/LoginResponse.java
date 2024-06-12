package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.dto;

public record LoginResponse(
        String accessToken
) {

    public static LoginResponse from(String accessToken) {
        return new LoginResponse(accessToken);
    }
}
