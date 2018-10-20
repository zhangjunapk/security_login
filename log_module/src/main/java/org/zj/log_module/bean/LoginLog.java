package org.zj.log_module.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:59
 * @Description:
 */
@Data
@Document(indexName = "login_test",type = "loginlog")
public class LoginLog {
    @Id
    private String id;
    @Field
    private String ip;
    @Field
    private Integer userId;
    @Field
    private Date loginDate;
}
