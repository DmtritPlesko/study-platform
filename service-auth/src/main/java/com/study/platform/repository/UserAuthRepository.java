package com.study.platform.repository;

import com.study.platform.entity.LoginInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<LoginInformation,Long> {

    boolean isExistUserByEmail(String email);
}
