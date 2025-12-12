package org.serg.spring.hibernate_user_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDto {

    private String username;
    private int age;

}
