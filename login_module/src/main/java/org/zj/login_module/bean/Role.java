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
@Table(name = "t_role")
public class Role {
    @Id
    private Integer id;
    private String name;
    private String code;
}
