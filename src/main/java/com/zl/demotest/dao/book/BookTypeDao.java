package com.zl.demotest.dao.book;

        import com.zl.demotest.pojo.book.BookType;
        import org.apache.ibatis.annotations.Mapper;

        import java.util.List;

@Mapper
public interface BookTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BookType record);

    int insertSelective(BookType record);

    BookType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);

    List<BookType> getAllBookType();
}