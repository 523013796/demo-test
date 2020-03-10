package com.zl.demotest.dao.member;

import com.zl.demotest.pojo.member.UserRank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRankDao {
    int deleteByPrimaryKey(Byte no);

    int insert(UserRank record);

    int insertSelective(UserRank record);

    UserRank selectByPrimaryKey(Byte no);

    int updateByPrimaryKeySelective(UserRank record);

    int updateByPrimaryKey(UserRank record);
}