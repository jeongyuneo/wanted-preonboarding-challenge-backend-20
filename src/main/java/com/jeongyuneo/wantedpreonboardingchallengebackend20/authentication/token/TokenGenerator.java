package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.token;

public interface TokenGenerator {

    String generateAccessToken(Long userId);

    String generateRefreshToken(Long userId);
}
