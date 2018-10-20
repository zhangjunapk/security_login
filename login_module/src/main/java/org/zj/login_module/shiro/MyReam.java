package org.zj.login_module.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.zj.login_module.bean.Permission;
import org.zj.login_module.bean.Result;
import org.zj.login_module.bean.Role;
import org.zj.login_module.bean.User;
import org.zj.login_module.service.IPermissionService;
import org.zj.login_module.service.IRoleService;
import org.zj.login_module.service.IUserService;

import java.util.List;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 10:23
 * @Description:
 */
@Component
public class MyReam extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IPermissionService permissionService;


    //这里对用户进行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权界面");
        try {
            AuthorizationInfo result = new SimpleAuthorizationInfo();

            User user= (User) principalCollection.getPrimaryPrincipal();
            System.out.println("授权界面"+user);
            System.out.println("用户id" + user.getId());
            List<Role> roles = roleService.getRoles(user.getId());

            for (Role r : roleService.getRoles(user.getId())) {
                ((SimpleAuthorizationInfo) result).addRole(r.getCode());
                System.out.println("有角色:" + r.getCode());
                for (Permission p : permissionService.getPermissions(r.getId())) {
                    ((SimpleAuthorizationInfo) result).addStringPermission(p.getCode());
                    System.out.println("    有权限:" + p.getCode());
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //这里检测用户是否存在(认证)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken t= (UsernamePasswordToken) token;

        if (t == null || t.getUsername() == null || t.getUsername().equals("") || t.getPassword() == null || new String(t.getPassword()).equals(""))
            return null;

        if(!userService.isExist((t.getUsername())))
            return null;

        //走到这里说明用户没问题了

        return new SimpleAuthenticationInfo(userService.getUser(t.getUsername()),new String(t.getPassword()),this.getName());
    }
}
