package org.zj.login_module.bean;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:06
 * @Description:
 */
@Data
@Table(name = "t_role_permission")
public class RolePermission {
    @Id
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
}
