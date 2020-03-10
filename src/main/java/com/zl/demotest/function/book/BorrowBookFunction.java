/**
 * User: zlin
 * Date: 2019/8/7
 * Time: 16:18
 **/

package com.zl.demotest.function.book;

import com.zl.demotest.controller.book.BorrowBookAo;
import com.zl.demotest.pojo.book.Books;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BorrowBookFunction {

    @Transactional
    List<BorrowBookAo> getAllBorrowBook()throws Exception;
    @Transactional
    Integer selectByUserOrBook(String key)throws Exception;
    @Transactional
    List<BorrowBookAo> getByUserOrBook(Integer key)throws Exception;
    @Transactional
    Integer deleteBorrowBookRecordById(String id)throws Exception;
    @Transactional
    Integer updateBookActive(Books books)throws Exception;
    @Transactional
    Books selectBookById(Integer id)throws Exception;
    @Transactional
    Integer guiHuanBook(Integer id, Date rTime,Integer book)throws Exception;
    @Transactional
    List<BorrowBookAo> getMyBorrowBook(Integer id) throws Exception;
}
