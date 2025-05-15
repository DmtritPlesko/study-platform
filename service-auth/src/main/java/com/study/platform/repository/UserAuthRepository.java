package com.study.platform.repository;

import com.study.platform.entity.LoginInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<LoginInformation, String> {

    boolean isExistUserByEmail(String email);

    Optional<LoginInformation> findByEmail(String email);

    Optional<LoginInformation> findByRefreshTokenHash(String token);
}
