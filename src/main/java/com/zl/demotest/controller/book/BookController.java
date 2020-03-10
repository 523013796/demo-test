/**
 * User: zlin
 * Date: 2019/8/3
 * Time: 21:35
 **/

package com.zl.demotest.controller.book;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zl.demotest.controller.login.UserAo;
import com.zl.demotest.function.book.BookFunction;
import com.zl.demotest.pojo.book.BookType;
import com.zl.demotest.utils.LayuiResultUtil;
import com.zl.demotest.utils.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookFunction fun;

    /**
     * 获得所以图书信息，使用了PageHelper分页插件
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAllbook")
    @ResponseBody
    JSONObject getAllbook(@RequestParam("page") String page, @RequestParam("limit") String limit) throws Exception {
        Page pages = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        List<BookAo> blist = fun.getAllBooks();
        JSONObject obj = new JSONObject();
        obj.put("code",200);
        obj.put("msg","");
        obj.put("count",pages.getTotal());
        obj.put("data",blist);
        return obj;
    }

    /**
     * 跳转page/qrybook页面
     * @param mv
     * @return
     * @throws Exception
     */
    @RequestMapping("/qrybook")
    ModelAndView qrybook(ModelAndView mv) throws Exception {
        List<BookType> allBookType = fun.getAllBookType();
        mv.addObject("btlist",allBookType);
        mv.setViewName("page/book/qrybook");
        return mv;
    }

    /**
     * 模糊查询图书
     * @param key
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectLikeBook")
    @ResponseBody
    JSONObject selectLikeBook(@RequestParam("key")String key,
                        @RequestParam("page") String page,@RequestParam("limit") String limit)throws Exception{
        Page pages = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
        List<BookAo> blist = fun.queryBooks(key);
        JSONObject obj = new JSONObject();
        LayuiResultUtil.putJSON(obj,pages.getTotal(),blist);
        return obj;
    }

    /**
     * 获得所选图书信息，再次用于展示给用户，以便于修改
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/getThisBook")
    @ResponseBody
    JSONObject getThisBook(@RequestParam("id") String id) throws Exception {
        BookAo bookAo = fun.getThisBook(Integer.valueOf(id));
        //request.setAttribute("bookType",Integer.valueOf(bookAo.getType()));
        JSONObject obj = new JSONObject();
        MyResult.MyResultUtil(obj,200,"",bookAo);
        return obj;
    }

    /**
     * 获取所有图书类别
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAllBookType")
    @ResponseBody
    String getAllBookType() throws Exception {
        List<BookType> allBookType = fun.getAllBookType();
        JSONObject obj = new JSONObject();
        obj.put("status",200);
        obj.put("message","");
        obj.put("data",allBookType);
        //MyResult.MyResultUtil(obj,200,"",allBookType);
        System.out.println(obj.toJSONString());
        return obj.toJSONString();
    }

    /**
     * 添加图书
     * @param ao
     * @return
     */
    @RequestMapping("/addBook")
    @ResponseBody
    JSONObject addBook(BookAo ao){
        try{
            Integer i = fun.addBook(ao);
            if (i==1) {
                return MyResult.result(200,"",i);
                //mv.addObject("msg","添加成功");
            }else {
                return MyResult.result(0,"",0);
                //mv.addObject("msg","添加失败,请你正确填写哦！");
            }
        }catch (Exception e){
            return MyResult.result(0);
            //mv.addObject("msg","添加失败,请你正确填写哦！");
        }
    }

    /**
     * 删除所选图书
     * @param id
     * @return
     */
    @RequestMapping("/deleteThisBook")
    @ResponseBody
    JSONObject deleteThisBook(String id){
        JSONObject obj = new JSONObject();
        try {
            Integer i = fun.deleteThisBook(Integer.valueOf(id));
            MyResult.MyResultUtil(obj,200,"Y",i);
        } catch (Exception e) {
            MyResult.MyResultUtil(obj,200,"N","网络异常！");
        }
        return obj;
    }

    /**
     * 修改图书信息
     * @param oldNumber
     * @param ao
     * @return
     */
    @RequestMapping("/updateBook")
    @ResponseBody
    JSONObject updateBook(String oldNumber,BookAo ao){
        JSONObject obj = new JSONObject();
        Integer cz = ao.getNumber()-Integer.valueOf(oldNumber);
        ao.setNumberremaining(ao.getNumberremaining()+cz);
        try {
            Integer i = fun.updateBook(ao);
            if (i == 1) {
                MyResult.MyResultUtil(obj,200,"Y",i);
            }
        } catch (Exception e) {
            //老子就是不处理！
        }
        return obj;
    }

    /**
     * 添加一个图书类别
     * @param book_type
     * @return
     */
    @RequestMapping("/addBookType")
    @ResponseBody
    JSONObject add_book_type(String book_type){
        JSONObject obj = new JSONObject();
        try {
            MyResult.MyResultUtil(obj,200,"Y",fun.addBookType(book_type));
        } catch (Exception e) {
            MyResult.MyResultUtil(obj,200,"",null);
        }
        return obj;
    }

    /**
     * 添加一条借书记录，一次借一本书
     * @param user
     * @param book
     * @return
     * @throws Exception
     */
    @RequestMapping("/addBorrowBook")
    @ResponseBody
    JSONObject borrow_book(Integer user, Integer book) throws Exception {
        JSONObject o = new JSONObject();
        Integer integer = fun.addBorrowBook(user, book, new Date());
        //System.out.println("user="+user+"\nbook="+book+"\ntime="+ StringAndDateUtil.DateTimeToString(time));
        MyResult.MyResultUtil(o,200,"",integer);
        return o;
    }

    @RequestMapping("getSessionUserId")
    @ResponseBody
    JSONObject getSessionUserId(HttpServletRequest request){
        UserAo userAo= (UserAo)request.getSession().getAttribute("user");
        System.out.println(userAo.getId());
        return MyResult.MyResultUtil(200,"",userAo.getId());
    }

}
