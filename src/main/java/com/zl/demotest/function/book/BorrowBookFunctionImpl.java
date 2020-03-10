/**
 * User: zlin
 * Date: 2019/8/7
 * Time: 16:20
 **/

package com.zl.demotest.function.book;

import com.zl.demotest.controller.book.BorrowBookAo;
import com.zl.demotest.dao.book.BooksDao;
import com.zl.demotest.dao.book.BorrowBooksDao;
import com.zl.demotest.dao.member.UserDao;
import com.zl.demotest.pojo.book.Books;
import com.zl.demotest.pojo.book.BorrowBooks;
import com.zl.demotest.pojo.member.User;
import com.zl.demotest.utils.StringAndDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BorrowBookFunctionImpl implements BorrowBookFunction {
    @Autowired
    private BorrowBooksDao borrowBooksDao;
    @Autowired
    private BooksDao booksDao;
    @Autowired
    private UserDao userDao;
    @Override
    public List<BorrowBookAo> getAllBorrowBook() throws Exception {
        List<BorrowBooks> allBorrowBook = borrowBooksDao.getAllBorrowBook();
        List<BorrowBookAo> aoList = new ArrayList<>();
        for (BorrowBooks borrowBooks:allBorrowBook) {
            BorrowBookAo ao = getAo(borrowBooks);
            aoList.add(ao);
        }
        return aoList;
    }

    @Override
    public Integer selectByUserOrBook(String key) throws ParseException {
        Books books = booksDao.selectByKey("name", key.trim());
        if (books != null) {
            return books.getId();
        }else {
            User u = userDao.selectByKey("name", key.trim());
            if (u != null) {
                return u.getId();
            }else {
                return null;
            }
        }
    }

    @Override
    public List<BorrowBookAo> getByUserOrBook(Integer key) throws Exception {
        List<BorrowBookAo> aoList = new ArrayList<>();
        List<BorrowBooks> borrowBookList = borrowBooksDao.selectByBookOrUserId(Integer.valueOf(key));
        for (BorrowBooks borrowBooks:borrowBookList) {
            BorrowBookAo ao = getAo(borrowBooks);
            aoList.add(ao);
        }
        return aoList;
    }

    @Override
    public Integer deleteBorrowBookRecordById(String id) throws Exception {
        return borrowBooksDao.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public Integer updateBookActive(Books books) throws Exception {
        return booksDao.updateByPrimaryKeySelective(books);
    }

    @Override
    public Books selectBookById(Integer id) throws Exception {
        return booksDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer guiHuanBook(Integer id, Date rTime,Integer book) throws Exception {
        if(booksDao.updateNumberRemainingAddOne(book)==1){
            return borrowBooksDao.guiHuanBook(rTime,id);

        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<BorrowBookAo> getMyBorrowBook(Integer id) throws ParseException {
        List<BorrowBooks> allBorrowBook = borrowBooksDao.getMyBorrowBook(id);
        List<BorrowBookAo> aoList = new ArrayList<>();
        for (BorrowBooks borrowBooks:allBorrowBook) {
            BorrowBookAo ao = getAo(borrowBooks);
            aoList.add(ao);
        }
        return aoList;
    }

    private BorrowBookAo getAo(BorrowBooks b) throws ParseException {
        BorrowBookAo ao = new BorrowBookAo();
        ao.setBook(b.getBook());
        ao.setBook_name(booksDao.selectByPrimaryKey(b.getBook()).getName());
        ao.setBooknumber(b.getBooknumber());
        ao.setFlag(b.getFlag());
        ao.setId(b.getId());
        ao.setrTime(StringAndDateUtil.DateTimeToString(b.getrTime()));
        ao.setTime(StringAndDateUtil.DateTimeToString(b.getTime()));
        ao.setUser(userDao.selectNameById(b.getUser()));
        return ao;
    }
}
