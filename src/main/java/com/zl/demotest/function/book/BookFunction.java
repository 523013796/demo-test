/**
 * User: zlin
 * Date: 2019/8/1
 * Time: 9:13
 **/

package com.zl.demotest.function.book;

import com.zl.demotest.controller.book.BookAo;
import com.zl.demotest.pojo.book.BookType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BookFunction {

    @Transactional
    List<BookAo> getAllBooks() throws Exception;

    @Transactional
    List<BookAo> queryBooks(String key) throws Exception;

    @Transactional
    List<BookType> getAllBookType() throws Exception;

    @Transactional
    BookAo getThisBook(Integer id) throws Exception;

    @Transactional
    Integer updateBook(BookAo ao) throws Exception;

    @Transactional
    Integer addBook(BookAo ao) throws Exception;

    @Transactional
    Integer deleteThisBook(Integer id)throws Exception;

    @Transactional
    Integer addBookType(String book_type)throws Exception;

    Integer addBorrowBook(Integer user, Integer book, Date time)throws Exception;


}
