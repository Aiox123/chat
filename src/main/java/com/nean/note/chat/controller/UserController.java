package com.nean.note.chat.controller;

import com.nean.note.chat.common.RestResponse;
import com.nean.note.chat.domain.dto.LoginUserDto;
import com.nean.note.chat.domain.dto.UserDto;
import com.nean.note.chat.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public RestResponse<Object> register(@Validated @RequestBody UserDto userDto){
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public RestResponse<Object> login(@Validated @RequestBody LoginUserDto loginUserDto){
        return userService.login(loginUserDto);
    }

}
