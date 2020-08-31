package com.lj.springboot_shiro_jwt.server.impl;

import com.lj.springboot_shiro_jwt.Utils.JWTUtils;
import com.lj.springboot_shiro_jwt.dto.User;
import com.lj.springboot_shiro_jwt.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServerImpl implements UserServer {

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public String login(User user) {
        //去数据库里查该用户
        User dbUser = new User();
        dbUser.setUsername("luojing");
        dbUser.setPassword("612612");
        //判断用户密码是否正确
        if (dbUser.getUsername().equals(user.getUsername())&&
                dbUser.getPassword().equals(user.getPassword())){

            HashMap<String, String> map = new HashMap<>();
            map.put("username", user.getUsername());
            map.put("role","admin");
            String jwt = jwtUtils.getToken(map);
            return jwt;
        }

        return null;
    }
}
