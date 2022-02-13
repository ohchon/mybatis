package com.finotech.finalmission.controller;

import javax.servlet.http.HttpServletResponse;

import com.finotech.finalmission.dto.ResponseDto;
import com.finotech.finalmission.dto.user.UserRequestDto;
import com.finotech.finalmission.security.UserDetailsImpl;
import com.finotech.finalmission.service.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseDto signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }

    @PostMapping("/users/login")
    public ResponseDto login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
        return userService.login(userRequestDto.getUsername(), userRequestDto.getPassword(), response);
    }

    @GetMapping("/users")
    public ResponseDto getInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getInfo(userDetails);
    }

    @PutMapping("/users")
    public ResponseDto editInfo(@RequestBody UserRequestDto userRequestDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                HttpServletResponse response) {
        return userService.editInfo(userRequestDto, userDetails, response);
    }

    @DeleteMapping("/users")
    public ResponseDto deleteInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody String password) {
        return userService.deleteInfo(userDetails, password);
    }

}
