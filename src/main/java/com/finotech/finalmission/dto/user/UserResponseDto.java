package com.finotech.finalmission.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UserResponseDto {
    private String username;
    private List<Object> _links;
}
