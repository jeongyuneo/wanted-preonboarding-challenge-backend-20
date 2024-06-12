package com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.service;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.token.TokenManager;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.authentication.token.TokenPayload;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.entity.User;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.service.UserReadService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserReadService userReadService;
    private final TokenManager tokenManager;

    public AuthenticationService(UserReadService userReadService, TokenManager tokenManager) {
        this.userReadService = userReadService;
        this.tokenManager = tokenManager;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReadService.findByUsername(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public TokenPayload generateToken(Long userId) {
        return new TokenPayload(
                tokenManager.generateAccessToken(userId),
                tokenManager.generateRefreshToken(userId)
        );
    }
}
