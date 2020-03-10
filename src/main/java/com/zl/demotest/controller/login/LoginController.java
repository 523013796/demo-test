/**
 * User: zlin
 * Date: 2019/8/3
 * Time: 18:40
 **/

package com.zl.demotest.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.zl.demotest.function.login.LoginFunction;
import com.zl.demotest.function.member.UserFunction;
import com.zl.demotest.pojo.member.Major;
import com.zl.demotest.utils.MyResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    LoginFunction fun;
    @Autowired
    UserFunction userFunction;

    @RequestMapping("/index")
    String index(){
        return "login";
    }
    @RequestMapping("/login")
    @ResponseBody
    JSONObject login(@RequestParam("phone") String phone, @RequestParam("password") String password,
                     HttpServletRequest request) throws Exception {
        //获取Subject
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(phone,password);

        //执行登陆方法
        try {
            subject.login(token);
            //登陆成功
            UserAo userAo = fun.login(phone, password);
            userAo.setPassword("");
            request.getSession().setAttribute("user",userAo);
            return MyResult.result(200,"",null);
        }catch (Exception e){
            //登陆失败
            return MyResult.result(0,"",null);
        }
    }
    @RequestMapping("welcome")
    String welcome(){
        return "page/welcome";
    }
    @RequestMapping("logout")
    String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
    @RequestMapping("main")
    String main(HttpServletRequest request) throws ParseException {
        UserAo userAo = (UserAo) request.getSession().getAttribute("user");
        UserAo ao = userFunction.getUserByid(userAo.getId());
        request.getSession().setAttribute("user",ao);
        return "page/main";
    }

    @RequestMapping("my_message")
    ModelAndView myMseeage(ModelAndView mv,Integer id) throws ParseException {
        List<Major> majorList = userFunction.getAllMajor();
        mv.addObject("major_list",majorList);
        UserAo user = userFunction.getUserByid(id);
        mv.addObject("u",user);
        mv.setViewName("page/my");
        return mv;
    }
    @RequestMapping("to_logon")
    ModelAndView to_logon(ModelAndView mv){
        List<Major> majorList = userFunction.getAllMajor();
        mv.addObject("major_list",majorList);
        mv.setViewName("page/logon");
        return mv;
    }

    @RequestMapping("update_password")
    @ResponseBody
    JSONObject update_password(HttpServletRequest request,String password){
        UserAo userAo = (UserAo) request.getSession().getAttribute("user");
        Integer i = userFunction.updatePassword(userAo.getId(),password);
        if (i == 1) {
            return MyResult.result(200,i);
        }else{
            return null;
        }
    }

}
