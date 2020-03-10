package com.zl.demotest.dao.book;

import com.zl.demotest.pojo.book.BorrowBooks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface BorrowBooksDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowBooks record);

    int insertSelective(BorrowBooks record);

    BorrowBooks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowBooks record);

    int updateByPrimaryKey(BorrowBooks record);

    List<BorrowBooks> getAllBorrowBook();
    List<BorrowBooks> selectByBookId(Integer key);
    List<BorrowBooks> selectByBookOrUserId(@Param("key")Integer key);

    Integer updateNumberRemainingOne(@Param("id")Integer id);

    Integer guiHuanBook(@Param("rTime")Date rTime,@Param("id")Integer id);

    @Select("select * from borrow_books where user = #{id} ORDER BY time DESC")
    List<BorrowBooks> getMyBorrowBook(Integer id);
}