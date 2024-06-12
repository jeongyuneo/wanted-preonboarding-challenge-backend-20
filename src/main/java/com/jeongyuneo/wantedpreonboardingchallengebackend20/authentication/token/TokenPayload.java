package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.token;

public record TokenPayload(
        String accessToken,
        String refreshToken
) {
}
