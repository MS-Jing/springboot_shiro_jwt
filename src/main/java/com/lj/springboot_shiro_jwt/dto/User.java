package com.lj.springboot_shiro_jwt.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class User {
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
