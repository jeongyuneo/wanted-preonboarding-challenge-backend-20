package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtManager implements TokenManager {

    private static final String USER_ID_CLAIM = "userId";

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Override
    public String generateAccessToken(Long userId) {
        return generateToken(Token.ACCESS_TOKEN, userId);
    }

    @Override
    public String generateRefreshToken(Long userId) {
        return generateToken(Token.REFRESH_TOKEN, userId);
    }

    private String generateToken(Token token, Long userId) {
        return JWT.create()
                .withSubject(token.name())
                .withExpiresAt(token.getExpirationTimeDate())
                .withClaim(USER_ID_CLAIM, userId)
                .sign(Algorithm.HMAC512(secretKey));
    }
}
