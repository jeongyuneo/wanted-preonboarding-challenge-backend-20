package com.jeongyuneo.wantedpreonboardingchallengebackend20.user.entity;

import com.jeongyuneo.wantedpreonboardingchallengebackend20.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user SET deleted=true WHERE user_id=?")
@SQLRestriction(value = "deleted=false")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20, updatable = false, unique = true)
    private String username;

    @Column(length = 50)
    private String password;

    @Column(length = 20)
    private String name;
}
