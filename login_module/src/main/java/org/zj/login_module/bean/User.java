package org.zj.login_module.bean;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 8:57
 * @Description:
 */
@Data
@Table(name="t_user")
public class User {
    @Id
    private Integer id;
    private String email;
    private String password;
    private Integer status;
}
