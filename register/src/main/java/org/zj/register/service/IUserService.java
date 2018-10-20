package org.zj.register.service;


import org.zj.register.bean.User;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:13
 * @Description:
 */
public interface IUserService {
    User getUser(String email, String password);
    boolean addUser(User user);
    boolean isExist(String email);
}
