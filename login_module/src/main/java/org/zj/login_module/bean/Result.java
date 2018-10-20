package org.zj.login_module.bean;

import lombok.Data;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:22
 * @Description:
 */
@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
