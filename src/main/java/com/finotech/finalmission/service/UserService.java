package com.finotech.finalmission.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.finotech.finalmission.dto.user.UserRequestDto;
import com.finotech.finalmission.jwt.JwtTokenProvider;
import com.finotech.finalmission.dto.ResponseDto;
import com.finotech.finalmission.mapper.UserMapper;
import com.finotech.finalmission.model.User;
import com.finotech.finalmission.security.UserDetailsImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    // 회원가입
    public ResponseDto signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        if (userMapper.findByUsername(username) != null) {
            return new ResponseDto("failed", "중복된 아이디가 있습니다");
        }
        String encodedPwd = passwordEncoder.encode(userRequestDto.getPassword());
        userMapper.save(username, encodedPwd);
        return new ResponseDto("success", "회원가입 성공");
    }

    // 로그인
    public ResponseDto login(String username, String password, HttpServletResponse response) {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            return new ResponseDto("failed", "계정이 존재하지않습니다.");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseDto("failed", "비밀번호가 틀렸습니다.");
        }
        String token = jwtTokenProvider.createToken(username);
        response.setHeader("Authorization", token);
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return new ResponseDto("success", "로그인 성공했습니다.");
    }

    public ResponseDto getInfo(UserDetailsImpl userDetails) {
        return new ResponseDto("success", userDetails.getUsername());
    }

    public ResponseDto editInfo(UserRequestDto userRequestDto, UserDetailsImpl userDetails,  HttpServletResponse response) {
        if (!passwordEncoder.matches(userRequestDto.getPassword(), userDetails.getPassword())) {
            return new ResponseDto("failed", "비밀번호가 틀렸습니다.");
        }
        userMapper.update(userRequestDto.getUsername(), userDetails.getPassword());

        String token = jwtTokenProvider.createToken(userRequestDto.getUsername());
        response.setHeader("Authorization", token);
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new ResponseDto("success", "수정 성공했습니다.");
    }


    public ResponseDto deleteInfo(UserDetailsImpl userDetails, String password) {
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return new ResponseDto("failed", "비밀번호가 틀렸습니다.");
        }

        userMapper.delete(userDetails.getPassword());
        return new ResponseDto("success", "삭제 성공했습니다.");
    }
}
