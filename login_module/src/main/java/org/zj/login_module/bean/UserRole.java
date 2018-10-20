package org.zj.login_module.bean;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:05
 * @Description:
 */
@Data
@Table(name = "t_user_role")
public class UserRole {
    @Id
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
