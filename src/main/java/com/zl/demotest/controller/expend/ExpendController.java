/**
 * User: zlin
 * Date: 2019/8/8
 * Time: 19:41
 **/

package com.zl.demotest.controller.expend;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.demotest.function.expend.ExpendFunction;
import com.zl.demotest.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("expend")
public class ExpendController {

    @Autowired
    ExpendFunction fun;

    @RequestMapping("qry_expend")
    String goqryexpend(){
        return "page/expend/qryexpend_out";
    }
    @RequestMapping("all_expend")
    String all_expend(Model m){
        m.addAttribute("amount_y",fun.selectAllAmount().getAllAmount());
        return "page/expend/expend";
    }

    @RequestMapping("qry_expend_in")
    String goqryexpend_out(){
        return "page/expend/qryexpend_in";
    }

    @RequestMapping("all_amount")
    String goaddexpend(Model m){
        m.addAttribute("amount_y",fun.selectAllAmount().getAllAmount());
        m.addAttribute("amount_z",fun.sumExpend_in());
        m.addAttribute("amount_out",fun.sumExpend_out());
        return "page/expend/all_amount";
    }
    @RequestMapping("my_expend")
        String gomyexpend(){
            return "page/expend/myexpend";
        }

    @RequestMapping("selectAllExpend_out")
    @ResponseBody
    JSONObject selectAllExpend(Integer page,Integer limit) throws Exception {
        Page pages = PageHelper.startPage(page,limit);
        List<ExpendAo> expendList = fun.selectAllExpend();
        return MyResult.result(200,"",pages.getTotal(),expendList);
    }

    @RequestMapping("selectLikeKey_out")
    @ResponseBody
    JSONObject selectLikeKey(String key,Integer page ,Integer limit)throws Exception{
        Page pages = PageHelper.startPage(page,limit);
        List<ExpendAo> expendAoList = fun.selectExpendLikeLdOrUserOrUses(key);
        return MyResult.result(200,"",pages.getTotal(),expendAoList);
    }

    @RequestMapping("selectmyexpend")
    @ResponseBody
    JSONObject selectmyexpend(Integer id,Integer page ,Integer limit)throws Exception{
        Page pages = PageHelper.startPage(page,limit);
        List<ExpendAo> expendAoList = fun.selectExpendByUser(id);
        return MyResult.result(200,"",pages.getTotal(),expendAoList);
    }

    @RequestMapping("updateExpendAction_out")
    @ResponseBody
    JSONObject updateExpendAction(ExpendAo expendAo)throws Exception{
        Integer i = fun.updateByIdActive(expendAo);
        JSONObject o = new JSONObject();
        if (i==1){
            MyResult.MyResultUtil(o,200,"",null);
        }
        return o;
    }

    @RequestMapping("deleteExpend_out")
    @ResponseBody
    JSONObject deleteExpend(Integer id)throws Exception{
        JSONObject result = MyResult.result(200, "", fun.deleteById(id));
        return result;
    }

    @RequestMapping("insertExpend_out")
    @ResponseBody
    JSONObject insertExpend(ExpendAo ao)throws Exception{
            Integer i = fun.insertExpend(ao);
        return MyResult.result(200,"",i);
    }

    @RequestMapping("selectAllExpend_in")
    @ResponseBody
    JSONObject selectAllExpend_in(Integer page,Integer limit) throws Exception {
        Page pages = PageHelper.startPage(page,limit);
        List<ExpendAo> expendList = fun.selectAllExpend_in();
        return MyResult.result(200,"",pages.getTotal(),expendList);
    }

    @RequestMapping("updateExpend_in")
    @ResponseBody
    JSONObject updateExpend_in(ExpendAo expendAo)throws Exception{
        Integer i = fun.updateExpend_inById(expendAo);
        JSONObject o = new JSONObject();
        if (i==1){
            MyResult.MyResultUtil(o,200,"",null);
        }
        return o;
    }

    @RequestMapping("insertExpend_in")
    @ResponseBody
    JSONObject insertExpend_in(ExpendAo ao)throws Exception{
        Integer i = fun.insertExpend_in(ao);
        return MyResult.result(200,"",i);
    }

    @RequestMapping("deleteExpend_in")
    @ResponseBody
    JSONObject deleteExpend_in(Integer id)throws Exception{
        JSONObject result = MyResult.result(200, "", fun.deleteExpend_inById(id));
        return result;
    }

    @RequestMapping("selectLikeKey_in")
    @ResponseBody
    JSONObject selectLikeKey_in(String key,Integer page ,Integer limit)throws Exception{
        Page pages = PageHelper.startPage(page,limit);
        List<ExpendAo> expendAoList = fun.selectExpend_inLikeLdOrUserOrUses(key);
        return MyResult.result(200,"",pages.getTotal(),expendAoList);
    }

}
