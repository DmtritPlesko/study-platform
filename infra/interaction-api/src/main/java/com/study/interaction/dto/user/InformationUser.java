package com.study.interaction.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InformationUser {

    String username;

    String groupName;

    String email;

    String password;

    String role;

}
