package com.study.platform.controller;

import com.study.interaction.dto.user.InformationUser;
import com.study.interaction.dto.user.UserDto;
import com.study.platform.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String userId) {
        return null;
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDto> UpdateUser(@PathVariable("id") String userId
            ,@RequestBody InformationUser userDto) {
        return null;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam(name = "name") String username) {

    }
}
