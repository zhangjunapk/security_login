package org.zj.login_module.shiro;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zj.login_module.bean.User;
import org.zj.login_module.service.IUserService;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 10:23
 * @Description:
 */
@Component
public class MyCredentialsMatcher implements CredentialsMatcher {

    @Autowired
    IUserService userService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        //用户输入的信息
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;

        //在这里判断密码
        //这里应该从数据库查找把
        User user = userService.getUser(token.getUsername());
        System.out.println("用户输入:");
        System.out.println(token.getUsername()+"<-       ->"+new String(token.getPassword()));

        System.out.println(user);

        //然后判断密码是否匹配
        if(!getMd5(new String(token.getPassword()),token.getUsername()).equals(user.getPassword()))
            return false;
        return true;
    }

    private String getMd5(String needMd5,String slat){
        String s = new Md5Hash(needMd5, slat).toString();
        System.out.println("加密后        ->"+s);
        return s;
    }

}
