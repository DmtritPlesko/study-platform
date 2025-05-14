package com.study.platform.service;

import com.study.interaction.user.dto.InformationUser;
import com.study.interaction.user.dto.UserDto;

public interface UserService {

    UserDto getAllInformationUser(String username);

    UserDto getUser(String userId);

    UserDto updateUser(String id,InformationUser userInformation);

    void deleteUser(String username);
}
