package com.lj.springboot_shiro_jwt.shiro.realms;

import com.lj.springboot_shiro_jwt.Utils.JWTUtils;
import com.lj.springboot_shiro_jwt.shiro.token.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

//自定义Realm
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("正在授权");
        String role = jwtUtils.getTokenInfo(principalCollection.toString(), "role");
        //创建权限对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(role);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("正在认证");
         JWTToken jwtToken = (JWTToken) token;
        try {
            jwtUtils.verify(jwtToken.getTokenString());
            return new SimpleAuthenticationInfo(jwtToken.getPrincipal(),jwtToken.getCredentials(),this.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
