package org.zj.login_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("org.zj.login_module.dao")
@SpringBootApplication
public class LoginModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginModuleApplication.class, args);
    }
}
