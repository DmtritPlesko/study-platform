package com.study.platform.repository;

import com.study.platform.entity.LoginInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginInformationRepository extends JpaRepository<LoginInformation, String> {

    Optional<LoginInformation> findByEmail(String email);

    Optional<LoginInformation> findByRefreshTokenHash(String token);
}
