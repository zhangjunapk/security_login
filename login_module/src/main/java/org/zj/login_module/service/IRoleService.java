package org.zj.login_module.service;

import org.zj.login_module.bean.Role;

import java.util.List;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:15
 * @Description:
 */
public interface IRoleService {
    List<Role> getRoles(Integer userId);
}
