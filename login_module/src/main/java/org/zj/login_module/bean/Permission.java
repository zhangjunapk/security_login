package org.zj.login_module.bean;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:07
 * @Description:
 */
@Data
@Table(name = "t_permission")
public class Permission {
    @Id
    private Integer id;
    private String name;
    private String code;
}
