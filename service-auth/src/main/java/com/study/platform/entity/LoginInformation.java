package com.study.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "login_information")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginInformation {

    @Id
    @Column(name = "user_id")
    String id;

    @Email
    @Column(name = "email")
    String email;

    @Column(name = "password_hash")
    String password;

    @Column(name = "is_active")
    boolean active;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "role")
    UserRole role;

    @Column(name = "refresh_token_hash")
    String refreshTokenHash;

    @Column(name = "refresh_token_expiry")
    Date tokenExpire;
}

