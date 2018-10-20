package org.zj.login_module.service;

import org.zj.login_module.bean.Permission;

import java.util.List;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:16
 * @Description:
 */
public interface IPermissionService {
    List<Permission> getPermissions(Integer roleId);
}
