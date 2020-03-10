/**
 * User: zlin
 * Date: 2019/8/14
 * Time: 20:42
 **/

package com.zl.demotest.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器：
         *          anon：无需认证（登陆）可以访问
         *          authc：必须认证才可以访问
         *          user：如果使用rememberMe的功能可以直接访问
         *          perms：该资源必须得到资源权限才可以访问
         *          role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filerMap = new LinkedHashMap<>();
        /**
         * 放行登陆注册请求
         */
        filerMap.put("/login","anon");
        filerMap.put("/logon","anon");
        filerMap.put("/member/logon","anon");
        filerMap.put("/to_logon","anon");

        /**
         * 放行静态资源
         */
        filerMap.put("/css/**","anon");
        filerMap.put("/js/**","anon");
        filerMap.put("/layui/**","anon");
        filerMap.put("/img/**","anon");

        /**
         * 实现退出功能
         */
        filerMap.put("/logout","logout");
        filerMap.put("/**","authc");
        //filerMap.put("/*/*","authc");



        //修改默认登陆页面
        shiroFilterFactoryBean.setLoginUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filerMap);



        return shiroFilterFactoryBean;
    }
    /**
     * 创建ShiroWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("UserRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean(name = "UserRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
