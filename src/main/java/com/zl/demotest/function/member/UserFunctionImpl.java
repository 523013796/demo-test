/**
 * User: zlin
 * Date: 2019/8/12
 * Time: 14:22
 **/

package com.zl.demotest.function.member;

import com.zl.demotest.controller.login.UserAo;
import com.zl.demotest.dao.member.MajorDao;
import com.zl.demotest.dao.member.UserDao;
import com.zl.demotest.dao.member.UserRankDao;
import com.zl.demotest.pojo.member.Major;
import com.zl.demotest.pojo.member.User;
import com.zl.demotest.pojo.member.UserRank;
import com.zl.demotest.utils.StringAndDateUtil;
import com.zl.demotest.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserFunctionImpl implements UserFunction {
    @Autowired
    UserDao userDao;
    @Autowired
    MajorDao majorDao;
    @Autowired
    UserRankDao userRankDao;
    @Override
    public List<UserAo> getAllUser() throws Exception {
        List<User> userList = userDao.selectAllUser();
        List<UserAo> aoList = new ArrayList<>();
        for (User u:userList) {
            aoList.add(getUserAO(u));
        }
        return aoList;
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Major> getAllMajor() {
        return majorDao.selectAllMajor();
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer insertUser(User user) {
        user.setId(UUIDUtils.getUUIDAsInt());
        byte a = 5;
        user.setRank(a);
        user.setRegistertime(new Date());
        if (user.getPassword()==null){
            user.setPassword(user.getStudentnumber());
        }
        return userDao.insertSelective(user);
    }

    @Override
    public UserAo getUserByid(Integer id) throws ParseException {
        User user = userDao.selectByPrimaryKey(id);
        user.setPassword("");
        UserAo userAO = getUserAO(user);
        return userAO;
    }

    @Override
    public Integer updatePassword(Integer id,String password) {
        Integer i = userDao.updatePassword(password,id);
        System.out.println(i);
        return i;
    }

    @Override
    public List<UserAo> getAllAuthority() throws ParseException {
        List<User> userList = userDao.selectAllUser();
        List<UserAo> aoList = new ArrayList<>();
        for (User u:userList) {
            if (u.getRank()==5) continue;
            aoList.add(getUserAO(u));
        }
        return aoList;
    }

    @Override
    public List<UserRank> getAllRank() {
        return majorDao.selectAllRank();
    }

    @Override
    public Integer updateUser(String studentnumber, Byte no) {

        return userDao.updateRankByStudent_number(studentnumber,no);
    }


    private UserAo getUserAO(User u ) throws ParseException {
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
