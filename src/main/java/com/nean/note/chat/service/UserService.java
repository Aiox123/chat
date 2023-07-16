package com.nean.note.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nean.note.chat.common.RestResponse;
import com.nean.note.chat.domain.dto.LoginUserDto;
import com.nean.note.chat.domain.dto.UserDto;
import com.nean.note.chat.domain.pojo.User;

public interface UserService extends IService<User> {

    RestResponse<Object> register(UserDto userDto);

    RestResponse<Object> login(LoginUserDto loginUserDto);
}
