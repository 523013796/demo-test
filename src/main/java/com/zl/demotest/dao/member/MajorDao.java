package com.zl.demotest.dao.member;

import com.zl.demotest.pojo.member.Major;
import com.zl.demotest.pojo.member.UserRank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MajorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    @Select("select id,major_name as majorName from major")
    List<Major> selectAllMajor();

    @Select("select * from user_rank")
    List<UserRank> selectAllRank();
}