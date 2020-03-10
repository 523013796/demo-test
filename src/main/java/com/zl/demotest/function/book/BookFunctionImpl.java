/**
 * User: zlin
 * Date: 2019/8/1
 * Time: 9:14
 **/

package com.zl.demotest.function.book;

import com.zl.demotest.controller.book.BookAo;
import com.zl.demotest.dao.book.BookTypeDao;
import com.zl.demotest.dao.book.BooksDao;
import com.zl.demotest.dao.book.BorrowBooksDao;
import com.zl.demotest.pojo.book.BookType;
import com.zl.demotest.pojo.book.Books;
import com.zl.demotest.pojo.book.BorrowBooks;
import com.zl.demotest.utils.StringAndDateUtil;
import com.zl.demotest.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class BookFunctionImpl implements BookFunction {

    @Autowired
    BooksDao booksDao;
    @Autowired
    BookTypeDao bookTypeDao;
    @Autowired
    BorrowBooksDao borrowBooksDao;

    @Override
    public List<BookAo> getAllBooks() throws Exception {
        List<Books> allBooks = booksDao.getAllBooks();
        return getWebBooks(allBooks, getBookTypeMap());
    }

    @Override
    public List<BookAo> queryBooks(String key) throws ParseException {
        return getWebBooks(booksDao.selectLike("%"+key+"%"),getBookTypeMap());
    }

    @Override
    public List<BookType> getAllBookType() {
        return bookTypeDao.getAllBookType();
    }

    @Override
    public BookAo getThisBook(Integer id) throws ParseException {
        return  getWebBook(booksDao.selectByPrimaryKey(id));
    }

    @Override
    public Integer updateBook(BookAo ao) throws Exception {
        Books book = booksDao.selectByPrimaryKey(ao.getId());
        int borrowNumber = book.getNumber()-book.getNumberremaining();
        book.setAuthor(ao.getAuthor());
        book.setBuytime(StringAndDateUtil.StringToDate(ao.getBuytime()));
        book.setIntroduce(ao.getIntroduce());
        book.setName(ao.getName());
        book.setNumber(ao.getNumber());
        book.setNumberremaining(ao.getNumber()-borrowNumber);
        book.setPrice(ao.getPrice());
        book.setType(Integer.valueOf(ao.getType()));
        return booksDao.updateByPrimaryKeySelective(book);
    }

    @Override
    public Integer addBook(BookAo ao) throws Exception {
        Books book = new Books();
        book.setAuthor(ao.getAuthor());
        book.setBuytime(StringAndDateUtil.StringToDate(ao.getBuytime()));
        book.setId(UUIDUtils.getUUIDAsInt());
        book.setIntroduce(ao.getIntroduce());
        book.setName(ao.getName());
        book.setNumber(ao.getNumber());
        book.setNumberremaining(ao.getNumber());
        book.setPrice(ao.getPrice());
        book.setType(Integer.valueOf(ao.getType()));
        return booksDao.insert(book);
    }

    @Override
    public Integer deleteThisBook(Integer id) throws Exception {
        return booksDao.deleteByPrimaryKey(id);
    }

    @Override
    public Integer addBookType(String book_type) throws Exception {
        BookType bt = new BookType();
        bt.setId(UUIDUtils.getUUIDAsInt());
        bt.setBooktypename(book_type.trim());
        return bookTypeDao.insert(bt);
    }

    @Override
    public Integer addBorrowBook(Integer user, Integer book, Date time) throws Exception {
        BorrowBooks borrowBooks = new BorrowBooks();
        borrowBooks.setBook(book);
        borrowBooks.setBooknumber(1);
        borrowBooks.setFlag(true);
        borrowBooks.setId(UUIDUtils.getUUIDAsInt());
        borrowBooks.setTime(time);
        borrowBooks.setrTime(null);
        borrowBooks.setUser(user);
        Integer i = borrowBooksDao.updateNumberRemainingOne(book);
        if (i==1){
            return borrowBooksDao.insertSelective(borrowBooks);
        }else {
            throw new RuntimeException();
        }
    }

    private Map<Integer,String> getBookTypeMap() {
        List<BookType> btlist = bookTypeDao.getAllBookType();
        Map<Integer,String> btmap = new HashMap<>();
        for (BookType bt: btlist) {
            btmap.put(bt.getId(),bt.getBooktypename());
        }
        return btmap;
    }
    private Map<String,Integer> getBookTypeKeyMap() {
        List<BookType> btlist = bookTypeDao.getAllBookType();
        Map<String,Integer> btmap = new HashMap<>();
        for (BookType bt: btlist) {
            btmap.put(bt.getBooktypename(),bt.getId());
        }
        return btmap;
    }

    private List<BookAo> getWebBooks(List<Books> bookList,Map<Integer,String> booktypemap) throws ParseException {
        List<BookAo> balist= new ArrayList<>();
        BookAo ba = null;
        for (Books b:bookList) {
            ba=new BookAo();
            ba.setAuthor(b.getAuthor());
            ba.setBuytime(StringAndDateUtil.DateToString(b.getBuytime()));
            ba.setId(b.getId());
            ba.setIntroduce(b.getIntroduce());
            ba.setName(b.getName());
            ba.setNumber(b.getNumber());
            ba.setNumberremaining(b.getNumberremaining());
            ba.setPrice(b.getPrice());
            ba.setType(booktypemap.get(b.getType()));
            balist.add(ba);
        }
        return balist;
    }

    private BookAo getWebBook(Books book) throws ParseException {
        BookAo ba = null;

            ba=new BookAo();
            ba.setAuthor(book.getAuthor());
            ba.setBuytime(StringAndDateUtil.DateToString(book.getBuytime()));
            ba.setId(book.getId());
            ba.setIntroduce(book.getIntroduce());
            ba.setName(book.getName());
            ba.setNumber(book.getNumber());
            ba.setNumberremaining(book.getNumberremaining());
            ba.setPrice(book.getPrice());
            ba.setType(String.valueOf(book.getType()));
        return ba;
    }
}
