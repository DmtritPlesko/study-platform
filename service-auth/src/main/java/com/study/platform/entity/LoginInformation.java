package com.study.platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.checkerframework.common.value.qual.StringVal;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "login_information")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "role")
    String role;

    @Column(name = "refresh_token_hash")
    String refreshTokenHash;

    @Column(name = "refresh_token_expiry")
    Date tokenExpire;
}

