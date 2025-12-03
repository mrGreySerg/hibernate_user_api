package org.serg.spring.hibernate_user_api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PostResponseDto {

    private String title;
    private LocalDate date;
    private String text;

}
