package com.nean.note.chat.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nean.note.chat.common.RestResponse;
import com.nean.note.chat.user.domain.dto.LoginUserDto;
import com.nean.note.chat.user.domain.dto.UserDto;
import com.nean.note.chat.user.domain.pojo.User;

public interface UserService extends IService<User> {

    RestResponse<Object> register(UserDto userDto);

    RestResponse<Object> login(LoginUserDto loginUserDto);
}
