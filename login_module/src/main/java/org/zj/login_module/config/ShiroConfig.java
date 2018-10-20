package org.zj.login_module.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.zj.login_module.shiro.MyCredentialsMatcher;
import org.zj.login_module.shiro.MyReam;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
    /**
     * 配置安全管理器，并且注入Realm域
     * @param realm
     * @return
     */
    @Bean
    public SecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     *  Credentials：凭证/证书 ---
     *
     * 配置Realm域，注入密码比较器
     * @param credentialsMatcher
     * @return
     */
    @Bean
    public MyReam realm(CredentialsMatcher credentialsMatcher){
        MyReam myReam = new MyReam();
        myReam.setCredentialsMatcher(credentialsMatcher);
        return myReam;
    }

    /**
     * 密码比较器
     *
     * @return
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
//    return new HashedCredentialsMatcher("MD5");
        return new MyCredentialsMatcher();
    }

    /**
     * 配置拦截路径和放行路径
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        System.out.println("ShiroConfiguration.shirFilter()");
        // shiro过滤器工厂类
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器----Map集合
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();


        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/login*", "anon");
        filterChainDefinitionMap.put("/user/login*", "anon");
        filterChainDefinitionMap.put("/validatecode.jsp*", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/data/**", "anon");

        //   /** 匹配所有的路径
        //  通过Map集合组成了一个拦截器链 ，自顶向下过滤，一旦匹配，则不再执行下面的过滤
        //  如果下面的定义与上面冲突，那按照了谁先定义谁说了算
        //  /** 一定要配置在最后
        filterChainDefinitionMap.put("/**", "authc");

        // 将拦截器链设置到shiro中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");


        return shiroFilterFactoryBean;
    }

    /**
     * 开启shiro aop注解支持
     * 使用代理方式;所以需要开启代码支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 开启cglib代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

}