package org.zj.log_module.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.zj.log_module.bean.LoginLog;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 10:01
 * @Description:
 */
@Component
public interface LogRepository extends ElasticsearchRepository<LoginLog,String> {
}
