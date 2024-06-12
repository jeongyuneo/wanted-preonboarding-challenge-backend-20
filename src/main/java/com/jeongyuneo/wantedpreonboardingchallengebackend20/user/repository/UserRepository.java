package com.jeongyuneo.wantedpreonboardingchallengebackend20.user.repository;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
