package com.study.platform.entity;

import com.study.platform.auth.dto.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "login_information")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginInformation {

    @Id
    @Column(name = "user_id")
    Long id;

    @Email
    @Column(name = "email")
    String email;

    @Column(name = "password_hash")
    String password;

    @Column(name = "is_active")
    boolean active;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "role_id")
    UserRole role;
}

