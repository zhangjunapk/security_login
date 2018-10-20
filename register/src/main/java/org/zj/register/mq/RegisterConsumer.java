package org.zj.register.mq;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.zj.register.bean.User;
import org.zj.register.service.IUserService;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:48
 * @Description:
 */
@Component
public class RegisterConsumer {

    @Autowired
    IUserService userService;

    @JmsListener(destination = "register")
    public void handleMsg(Message message){
        try {
            System.out.println("注册模块收到消息了");
            String email = message.getStringProperty("email");
            String password = message.getStringProperty("password");
            User user=new User(email,password,0);
            userService.addUser(user);
            System.out.println("注册模块注册成功了");
        }catch (Exception e){

        }
    }
}
