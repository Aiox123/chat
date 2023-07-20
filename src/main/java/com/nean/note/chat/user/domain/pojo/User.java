package com.nean.note.chat.user.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String avatar;

    private Integer status;

    private Date createTime;

}
