package com.lj.springboot_shiro_jwt;

import com.lj.springboot_shiro_jwt.Utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroJwtApplicationTests {

    @Autowired
    JWTUtils jwtUtils;
    @Test
    void contextLoads() {
        System.out.println(jwtUtils.getSING());
    }

}
