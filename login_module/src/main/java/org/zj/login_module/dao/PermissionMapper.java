package org.zj.login_module.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zj.login_module.bean.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:13
 * @Description:
 */
public interface PermissionMapper extends Mapper<Permission> {
    @Select("select * from t_permission where id in(select permission_id from t_role_permission where role_id=#{roleId})")
    List<Permission> selectPermissionByRoleId(@Param("roleId") Integer roleId);
}
