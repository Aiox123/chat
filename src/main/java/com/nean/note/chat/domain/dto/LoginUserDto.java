package com.nean.note.chat.domain.dto;

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
public class LoginUserDto {

    @Email(message = "邮箱格式不对")
    private String email;

    @Length(min = 8, max = 16, message = "密码长度不符合 8 ~ 16 位")
    private String password;
}
