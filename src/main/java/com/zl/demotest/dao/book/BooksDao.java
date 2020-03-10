package com.zl.demotest.dao.book;

import com.zl.demotest.pojo.book.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BooksDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKeyWithBLOBs(Books record);

    int updateByPrimaryKey(Books record);

    List<Books> getAllBooks();

    List<Books> selectLike(@Param("key") String key);

    Books selectByKey(@Param("key")String key,@Param("value")String value);

    Integer updateNumberRemainingAddOne(@Param("id") Integer id);

    //List<Books> selectByKey(@Param("key")String key,@Param("value")String value);
}