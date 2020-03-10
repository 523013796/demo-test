/**
 * User: zlin
 * Date: 2019/8/11
 * Time: 17:43
 **/

package com.zl.demotest.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.demotest.controller.login.UserAo;
import com.zl.demotest.function.member.UserFunction;
import com.zl.demotest.pojo.member.Major;
import com.zl.demotest.pojo.member.User;
import com.zl.demotest.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("member")
public class UserController {
    @Autowired
    UserFunction fun;

    @RequestMapping("llll")
    String test(){
        //throw new RuntimeException("我抛我抛我抛我抛我抛我抛");
        return "page/member/llll";
    }
    @RequestMapping("goUser")
    ModelAndView goUser(ModelAndView mv){
        List<Major> majorList = fun.getAllMajor();
        System.out.println(majorList);
        mv.addObject("major_list",majorList);
        mv.setViewName("page/member/user");
        return mv;
    }


    @RequestMapping("getAllUser")
    @ResponseBody
    JSONObject getAllUser(Integer page, Integer limit) throws Exception {
        Page p = PageHelper.startPage(page,limit);
        List<UserAo> userList = fun.getAllUser();
        return MyResult.result(200,"",p.getTotal(),userList);
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    JSONObject deleteUser(Integer id)throws Exception{
        Integer i = fun.deleteUser(id);
        if (i==1){
            return MyResult.result(200,"",i);
        }
        return null;
    }

    @RequestMapping("updateUser")
    @ResponseBody
    JSONObject updateUser(User user){
        Integer i = fun.updateUser(user);
        return MyResult.result(200,"",i);
    }

    @RequestMapping("insertUser")
    @ResponseBody
    JSONObject insertUser(User user){
        Integer i = fun.insertUser(user);
        return MyResult.result(200,"",i);
    }

    @RequestMapping("logon")
    @ResponseBody
    JSONObject logon(User user){
        Integer i = fun.insertUser(user);
        return MyResult.result(200,"",i);
    }
}
