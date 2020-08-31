package com.lj.springboot_shiro_jwt.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "lj.jwt")
public class JWTUtils {

    private String SING = "我是签名";
    private int date = 7;

    /**
     * 生成Token
     */
    public String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,date);//设置过期时间为7天

        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((K,V)->{
            builder.withClaim(K,V);
        });

        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));//签名

        return token;
    }

    /**
     * 验证token
     * 签名有错会抛出异常
     */
    public void verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取token中的信息
     */
    public String getTokenInfo(String token,String info){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify.getClaim(info).asString();
    }

}
