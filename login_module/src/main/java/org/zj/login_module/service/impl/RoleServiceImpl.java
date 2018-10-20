package org.zj.login_module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zj.login_module.bean.Role;
import org.zj.login_module.dao.RoleMapper;
import org.zj.login_module.service.IRoleService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:18
 * @Description:
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getRoles(Integer userId) {
        return roleMapper.selectRoleByUserId(userId);
    }
}
