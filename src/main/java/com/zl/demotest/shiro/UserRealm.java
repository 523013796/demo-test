/**
 * User: zlin
 * Date: 2019/8/14
 * Time: 20:45
 **/

package com.zl.demotest.shiro;

import com.zl.demotest.dao.member.UserDao;
import com.zl.demotest.pojo.member.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserDao userDao;
    /**
     * 执行授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权认证");
        return null;
    }

    /**
     * 执行认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("登陆认证");

        //编写shiro判断逻辑，判断用户名和密码
        //判断用户名
        UsernamePasswordToken token1 = (UsernamePasswordToken)token;
        User user = userDao.selectByKey("phone", token1.getUsername());
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
