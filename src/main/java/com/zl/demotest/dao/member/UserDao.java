package com.zl.demotest.dao.member;

import com.zl.demotest.pojo.member.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByKey(@Param("key") String key, @Param("value") String value);

    @Select("select name from user where id = #{id}")
    String selectNameById(Integer id);

    @Select("select id from user where name = #{name}")
    Integer selectIdByName(String name);

    @Select("select * from user ")
    List<User> selectAllUser();

    @Update("update user set password = #{password} where id = #{id}")
    int updatePassword(String password,Integer id);

    @Update("update user set rank = #{no} where studentNumber = #{studentnumber}")
    Integer updateRankByStudent_number(String studentnumber, Byte no);
}