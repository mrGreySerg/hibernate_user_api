package org.serg.spring.hibernate_user_api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PostRequestDto {

    private String title;
    private Date date;
    private String text;

}
