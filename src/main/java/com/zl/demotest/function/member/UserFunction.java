/**
 * User: zlin
 * Date: 2019/8/12
 * Time: 14:22
 **/

package com.zl.demotest.function.member;

import com.zl.demotest.controller.login.UserAo;
import com.zl.demotest.pojo.member.Major;
import com.zl.demotest.pojo.member.User;
import com.zl.demotest.pojo.member.UserRank;

import java.text.ParseException;
import java.util.List;

public interface UserFunction {
    List<UserAo> getAllUser()throws Exception;

    Integer deleteUser(Integer id);

    List<Major> getAllMajor();

    Integer updateUser(User user);

    Integer insertUser(User user);

    UserAo getUserByid(Integer id) throws ParseException;

    Integer updatePassword(Integer id,String password);

    List<UserAo> getAllAuthority() throws ParseException;

    List<UserRank> getAllRank();

    Integer updateUser(String studentnumber, Byte no);
}
