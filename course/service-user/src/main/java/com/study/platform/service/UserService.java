package com.study.platform.service;

import com.study.interaction.dto.user.InformationUser;
import com.study.interaction.dto.user.UserDto;

public interface UserService {

    UserDto getAllInformationUser(String username);

    UserDto getUser(String userId);

    UserDto updateUser(String id,InformationUser userInformation);

    void deleteUser(String username);
}
