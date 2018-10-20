package org.zj.login_module.controller;

import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zj.login_module.bean.Result;
import org.zj.login_module.bean.User;
import org.zj.login_module.service.IUserService;

/**
 * @Author: ZhangJun
 * @Date: 2018/10/20 9:21
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class MainController {

    @Autowired
    IUserService userService;

    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping
    public ResponseEntity<Result> register(User user){
        try {
            //这是注册的代码逻辑
            if (user == null || user.getEmail() == null || user.getEmail().equals("") || user.getPassword() == null || user.getPassword().equals(""))
                return new ResponseEntity<>(new Result(500, "输入错误请正确输入", null), HttpStatus.OK);

            //接下来获得用户
            boolean exist = userService.isExist(user.getEmail());
            if (exist)
                return new ResponseEntity<>(new Result(500, "邮件已经被占用", null), HttpStatus.OK);
            //接下来就注册

            //发送注册信息

            Message message = new ActiveMQMessage();
            message.setStringProperty("email", user.getEmail());
            message.setStringProperty("password", user.getPassword());
            jmsTemplate.convertAndSend(new ActiveMQTopic("register"),message);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Result(500,"服务器错误",null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //接下来是登录的方法
    @PostMapping("/login")
    public ResponseEntity<Result> login(User user){
        //先创建token
        try {
            //通过主题来和shiro进行交互,<门面模式>
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken();
            token.setUsername(user.getEmail());
            token.setPassword(user.getPassword().toCharArray());
            subject.login(token);
            Message message=new ActiveMQMessage();
            message.setStringProperty("ip","localhost:8080");

            User userr= (User) SecurityUtils.getSubject().getPrincipal();

            message.setIntProperty("id",userr.getId());
            message.setIntProperty("status",userr.getStatus());
            jmsTemplate.convertAndSend("login",message);
            System.out.println("消息发送了");
            return new ResponseEntity<>(new Result(200,"okkk",null), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new Result(500,"失败",null), HttpStatus.OK);
        }
    }

    @GetMapping("/aa")
    @RequiresPermissions("user_sasdfasdf")
    public void s(){
        System.out.println("走了");
    }

}
