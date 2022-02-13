package com.finotech.finalmission.dto.post;

import lombok.Data;

@Data
public class PostEditRequestDto {
    private Long id;
    private String title;
    private String post;
}
