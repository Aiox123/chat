package com.nean.note.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nean.note.chat.common.RestResponse;
import com.nean.note.chat.domain.dto.LoginUserDto;
import com.nean.note.chat.domain.dto.UserDto;
import com.nean.note.chat.domain.pojo.User;
import com.nean.note.chat.mapper.UserMapper;
import com.nean.note.chat.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public RestResponse<Object> register(UserDto userDto) {
        User user = query().eq("email", userDto.getEmail()).one();
        if(null != user){
            return RestResponse.success("该邮箱已被注册!");
        }
        // dto -> po
        User registerUser = dtoToPo(userDto);
        try {
            boolean isSave = save(registerUser);
            if(isSave){
                return RestResponse.success("注册成功，快去登录吧!");
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResponse.success("注册失败!");
    }

    @Override
    public RestResponse<Object> login(LoginUserDto loginUserDto) {
        User loginUser = query().eq("email", loginUserDto.getEmail()).eq("password", loginUserDto.getPassword()).one();
        return RestResponse.success(Objects.requireNonNullElse(loginUser, "error: 账号密码有误"));
    }

    private User dtoToPo(UserDto userDto){
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .avatar(userDto.getAvatar())
                .status(1)
                .createTime(new Date())
                .build();
    }
}
