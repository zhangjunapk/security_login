package org.zj.login_module.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zj.login_module.bean.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:12
 * @Description:
 */
public interface RoleMapper extends Mapper<Role> {
    @Select("select * from t_role where id in(select role_id from t_user_role where user_id=#{userId})")
    List<Role> selectRoleByUserId(@Param("userId") Integer userId);

}
