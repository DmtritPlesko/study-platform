package com.study.platform.controller;

import com.study.interaction.dto.auth.LoginRequest;
import com.study.interaction.dto.user.InformationUser;
import com.study.interaction.dto.user.UserDto;
import com.study.platform.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN', 'TEACHER')")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String userId) {
        return null;
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDto> UpdateUser(@PathVariable("id") String userId
            ,@RequestBody InformationUser userDto) {

        return ResponseEntity.ok(userService.updateUser(userId,userDto));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@RequestParam(name = "name") String username) {
        userService.deleteUser(username);
    }

    @GetMapping("/me")
    public ResponseEntity<InformationUser> getUserInformation(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("access_token".equals(cookie.getName())) { // Ищем куку с именем "access_token"
                    String token = cookie.getValue();

                    InformationUser userInfo = userService.getAllInformationUser(token);
                    return ResponseEntity.ok(userInfo);
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Аксес токен не найден в куки");
    }
}
