package com.jeongyuneo.wantedpreonboardingchallengebackend20.user.service;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.ApplicationException;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.exception.ApplicationExceptionStatus;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.entity.User;
import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserReadService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ApplicationExceptionStatus.USER_NOT_FOUND));
    }
}
