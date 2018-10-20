package org.zj.log_module.mq;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.zj.log_module.bean.LoginLog;
import org.zj.log_module.service.LogRepository;
import sun.rmi.runtime.Log;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 10:09
 * @Description:
 */
@Component
public class LogConsumer {

    @Autowired
    LogRepository logRepository;

    public LogConsumer(){
        System.out.println("我被spring 初始化了");
    }

    @JmsListener(destination = "login")
    public void handleMsg(Message message){
        try{
            System.out.println("日志模块收到消息");
            int id = message.getIntProperty("id");
            String ip = message.getStringProperty("ip");
            LoginLog loginLog= new LoginLog();
            loginLog.setId(UUID.randomUUID().toString());
            loginLog.setUserId(id);
            loginLog.setIp(ip);
            logRepository.save(loginLog);
            System.out.println("日志插入");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
