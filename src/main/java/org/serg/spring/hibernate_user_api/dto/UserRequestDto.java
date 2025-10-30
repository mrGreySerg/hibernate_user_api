package org.serg.spring.hibernate_user_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {

    private String username;
    private int age;

}
