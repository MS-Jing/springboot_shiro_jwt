package com.lj.springboot_shiro_jwt.controller;

import com.lj.springboot_shiro_jwt.dto.User;
import com.lj.springboot_shiro_jwt.server.UserServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserServer userServer;

    @RequestMapping("/user")
    @RequiresAuthentication
    public String user() {
        return "我是user页面";
    }

    @RequestMapping("/index")
    @RequiresRoles("admin")
    public String index() {
        return "我是index页面";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/logout")
    @RequiresAuthentication
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "我是logout页面";
    }

    /**
     * 身份认证
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(User user, HttpServletResponse response) {
        try {
            String jwt = userServer.login(user);
            if (!StringUtils.isEmpty(jwt)) {
                response.setHeader("Authorization", jwt);
                response.setHeader("Access-control-Expose-Headers", "Authorization");
                return "认证成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "认证失败";
    }
}
