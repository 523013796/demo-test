/**
 * User: zlin
 * Date: 2019/8/7
 * Time: 15:31
 **/

package com.zl.demotest.controller.book;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.demotest.function.book.BorrowBookFunction;
import com.zl.demotest.pojo.book.Books;
import com.zl.demotest.utils.LayuiResultUtil;
import com.zl.demotest.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/borrowBook/")
public class BorrowBookController {
    @Autowired
    BorrowBookFunction fun;

    @RequestMapping("record")
    String goRecord(){
        return "page/book/borrowbook_record";
    }

    @RequestMapping("myRecord")
    String goMyRecord(){
        return "page/book/myRecord";
    }

    @RequestMapping("getAllBorrowBook")
    @ResponseBody
    JSONObject getAllBorrowBook(String page,String limit) throws Exception {
        JSONObject o = new JSONObject();
        Page pages = PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<BorrowBookAo> aoList = fun.getAllBorrowBook();
        LayuiResultUtil.putJSON(o,pages.getTotal(),aoList);
        return o;
    }
    @RequestMapping("getMyBorrowBook")
    @ResponseBody
    JSONObject getMyBorrowBook(Integer id,String page,String limit) throws Exception {
        JSONObject o = new JSONObject();
        Page pages = PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<BorrowBookAo> aoList = fun.getMyBorrowBook(id);
        LayuiResultUtil.putJSON(o,pages.getTotal(),aoList);
        return o;
    }

    @RequestMapping("selectByUserOrBook")
    @ResponseBody
    JSONObject selectByUserOrBook(String key,String page,String limit) throws Exception {
        JSONObject o = new JSONObject();
            List<BorrowBookAo> aoList = null;
        Integer i = fun.selectByUserOrBook(key.trim());
        Page pages = PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        if (i == null) {
            aoList = fun.getByUserOrBook(Integer.valueOf(key));
        }else {
            aoList = fun.getByUserOrBook(i);
        }
        LayuiResultUtil.putJSON(o,pages.getTotal(),aoList);
        return o;
    }

    @RequestMapping("deleteBorrowBookRecord")
    @ResponseBody
    JSONObject deleteBorrowBookRecordById(String id ,boolean flag,Integer booknumber,Integer book) throws Exception {
        if(flag){
            Books b = fun.selectBookById(book);
            b.setNumberremaining(b.getNumberremaining()+booknumber);
            fun.updateBookActive(b);
        }
        //System.out.println("booknumber="+booknumber+"------flag="+flag);
        JSONObject o = new JSONObject();
            MyResult.MyResultUtil(o,200,"Y",fun.deleteBorrowBookRecordById(id));
        return o;
    }

    @RequestMapping("guiHuanBook")
    @ResponseBody
    JSONObject guiHuanBook(Integer id , Date rTime,Integer book) throws Exception {
        //System.out.println(id+"-----"+rTime);
        JSONObject o = new JSONObject();
            MyResult.MyResultUtil(o,200,"Y",fun.guiHuanBook(id,rTime,book));
        return o;
    }



}
