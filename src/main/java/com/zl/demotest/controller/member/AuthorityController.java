/**
 * User: zlin
 * Date: 2019/10/22
 * Time: 9:49
 **/

package com.zl.demotest.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.demotest.controller.login.UserAo;
import com.zl.demotest.function.member.UserFunction;
import com.zl.demotest.pojo.member.UserRank;
import com.zl.demotest.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("member")
public class AuthorityController {
    @Autowired
    UserFunction userFunction;


    @RequestMapping("toAuthority")
    ModelAndView toAuthority(ModelAndView mv){
        List<UserRank> userRanks = userFunction.getAllRank();
        mv.addObject("user_rank",userRanks);
        mv.setViewName("page/member/authority");
        return mv;
    }

    @RequestMapping("getAllAuthority")
    @ResponseBody
    JSONObject getAllAuthority(Integer page, Integer limit) throws Exception {
        Page p = PageHelper.startPage(page,limit);
        List<UserAo> userList = userFunction.getAllAuthority();
        return MyResult.result(200,"",p.getTotal(),userList);
    }

    @RequestMapping("insertAuthority")
    @ResponseBody
   JSONObject insertAuthority(String studentnumber ,Byte rank){

            Integer i = userFunction.updateUser(studentnumber, rank);
            if (i == 1) {
                return MyResult.result(200, "", i);
            }

        return null;
   }



    @RequestMapping("updateAuthority")
    @ResponseBody
    JSONObject updateAuthority(String studentnumber ,Byte rank){

        Integer i = userFunction.updateUser(studentnumber, rank);
        if (i == 1) {
            return MyResult.result(200, "", i);
        }

        return null;
    }


}
