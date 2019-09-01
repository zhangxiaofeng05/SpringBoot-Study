package com.shiro.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //1.设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //2.设置Shiro内置过滤器，可以实现权限相关的拦截器
        //常用的过滤器
        //      anon:无需认证(登录)可以访问
        //      authc:必须认证才能访问
        //      user：如果使用remberme的功能可以直接访问
        //      perms：该资源必须得到资源权限才可以访问
        //      role：该资源必须得到角色权限才可以访问
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
//        filterMap.put("/add","authc");
//        filterMap.put("/update","authc");

        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/login","anon");

        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");

        filterMap.put("/*","authc");
        //3.修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSerurityManager
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(getRealm());
        return securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect,用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
