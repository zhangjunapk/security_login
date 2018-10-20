package org.zj.do_login_module.mq;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.zj.do_login_module.bean.User;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:36
 * @Description:
 */
@Component
public class LoginConsumer {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;


    public LoginConsumer(){
        System.out.println("我被spring初始化了---------------------");
    }

    @JmsListener(destination = "login")
    public void login(Message message){
        try {
            System.out.println("登录收到消息");
            String email = message.getStringProperty("email");
            String password = message.getStringProperty("password");
            int id = message.getIntProperty("id");
            int status = message.getIntProperty("status");
            String ip = message.getStringProperty("ip");
            //把结果放到redis
            User user=new User(id,email,password,status);
            redisTemplate.opsForValue().set(ip,user,1000*60*60*24);
            System.out.println("登录成功吧用户放到redis");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("遇到错误");
        }
    }
}
