package org.serg.spring.hibernate_user_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private String username;
    private int age;

}
