/**
 * User: zlin
 * Date: 2019/7/31
 * Time: 15:20
 **/

package com.zl.demotest.function.login;

import com.zl.demotest.controller.login.UserAo;
import org.springframework.transaction.annotation.Transactional;

public interface LoginFunction {

    @Transactional
    UserAo login(String phone, String password)throws  Exception;




}
