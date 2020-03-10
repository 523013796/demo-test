/**
 * User: zlin
 * Date: 2019/7/31
 * Time: 15:28
 **/

package com.zl.demotest.function.login;

import com.zl.demotest.controller.login.UserAo;
import com.zl.demotest.dao.member.MajorDao;
import com.zl.demotest.dao.member.UserDao;
import com.zl.demotest.dao.member.UserRankDao;
import com.zl.demotest.pojo.member.User;
import com.zl.demotest.utils.StringAndDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class LoginFunctionImpl implements LoginFunction{
    @Autowired
    UserDao um;
    @Autowired
    UserRankDao userRankDao;
    @Autowired
    MajorDao majorDao;

    @Override
    public UserAo login(String phone, String password) throws Exception {
        User u = um.selectByKey("phone", phone);
        if (u!=null){
            if (u.getPassword().equals(password.trim())){
                return getUserAO(u);
            }
        }
        return null;
    }


    UserAo getUserAO(User u ) throws ParseException {
        UserAo ao = new UserAo(u.getId(),u.getName(),u.getSex(),u.getAge(),
                u.getIdnumber(),u.getPhone(),u.getQq(),
                u.getWechat(),
                StringAndDateUtil.DateToString(u.getRegistertime()),
                majorDao.selectByPrimaryKey(u.getMajor()).getMajorName(),
                userRankDao.selectByPrimaryKey(u.getRank()).getRank(),
                u.getStudentnumber(),u.getPassword(),u.getRank());
        return ao;
    }
}
