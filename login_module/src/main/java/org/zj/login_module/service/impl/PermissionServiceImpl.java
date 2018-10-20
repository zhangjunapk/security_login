package org.zj.login_module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zj.login_module.bean.Permission;
import org.zj.login_module.bean.RolePermission;
import org.zj.login_module.dao.PermissionMapper;
import org.zj.login_module.service.IPermissionService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:19
 * @Description:
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissions(Integer roleId) {
        return permissionMapper.selectPermissionByRoleId(roleId);
    }
}
