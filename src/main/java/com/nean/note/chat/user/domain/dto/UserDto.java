package com.nean.note.chat.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Email(message = "邮箱格式不对")
    private String email;

    @Length(min = 8, max = 16, message = "密码长度不符合 8 ~ 16 位")
    private String password;

    @Length(max = 16, message = "昵称过长")
    private String nickname;

    @Length(max = 100, message = "头像 url 不符合要求")
    private String avatar;
}
