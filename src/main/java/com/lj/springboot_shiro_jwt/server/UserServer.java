package com.lj.springboot_shiro_jwt.server;

import com.lj.springboot_shiro_jwt.dto.User;

public interface UserServer {

    String login(User user);
}
