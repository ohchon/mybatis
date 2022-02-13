package com.finotech.finalmission.service;

import com.finotech.finalmission.mapper.UserMapper;
import com.finotech.finalmission.model.User;
import com.finotech.finalmission.security.UserDetailsImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    // 로그인할 때 들어온 username으로 DB에서 정보 찾기
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Can't find " + username);
        }
        return new UserDetailsImpl(user);
    }
}
