package com.finotech.finalmission.dto.comment;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Long id; // post_id 또는 comment_id
    private String comment;
}
