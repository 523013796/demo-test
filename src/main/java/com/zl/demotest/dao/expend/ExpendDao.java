package com.zl.demotest.dao.expend;

import com.zl.demotest.controller.expend.ExpendAo;
import com.zl.demotest.pojo.expend.Expend;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Expend record);

    int insertSelective(Expend record);

    Expend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Expend record);

    int updateByPrimaryKey(Expend record);

    /**
     * 基于注解的mybatis，数据库命名必须严格按照下划线转换驼峰命名规则，
     * 否则必须自己配置,例如下
     *          @Results({
     *              @Result(property = "id", column = "id"),
     *              @Result(property = "user", column = "user"),
     *              @Result(property = "amount", column = "amount"),
     *              @Result(property = "time", column = "time"),
     *              @Result(property = "uses", column = "uses"),
     *          })
     * @return
     */
    @Select("SELECT * FROM expend order by time desc")
    List<Expend> selectAllExpend();

    @Select("select * from expend where id like '%${key}%' or uses like '%${key}%' " +
            "or user = (SELECT id FROM USER WHERE NAME LIKE #{key}) order by time desc")
    List<Expend> selectExpendLikeIdOrUsesOrUser(String key);

    @Select("select * from expend where user = #{id} order by time desc")
    List<Expend> selectExpendByuser(Integer id);

    @Select("SELECT expend.id,expend.amount,expend.time,expend.uses,user.`name` AS USER FROM expend, USER WHERE expend.user=user.id")
    List<ExpendAo>testSelect();

    @Select("select amount from expend where id = #{id}")
    double selectAmountByid(Integer id);

    @Update("UPDATE \n" +
            "  `mangeteam`.`expend_in` \n" +
            "SET\n" +
            "  `amount` = #{amount},\n" +
            "  `time` = #{time},\n" +
            "  `uses` = #{uses} \n" +
            "WHERE `id` = #{id} ")
    Integer updateExpend_in(Expend expend);

    @Insert("INSERT INTO `mangeteam`.`expend_in` ( `id`,  `user`,  `amount`,  `time`,  `uses`) VALUES\n" +
            "  (\n" +
            "    #{id},\n" +
            "    #{user},\n" +
            "    #{amount},\n" +
            "    #{time},\n" +
            "    #{uses}\n" +
            "  ) ;")
    Integer insertExpend_in(Expend expend);

    /**
     * 支出金额求和
     * @return
     */
    @Select("select sum(amount) from expend")
    double sumExpend_out();

    /**
     * 收入经额求和
     * @return
     */
    @Select("select sum(amount) from expend_in ")
    double sumExpend_in();

    @Select("select * from expend_in order by time desc")
    List<Expend> selectAllExpend_in();

    @Select("select * from expend_in where id like '%${key}%' or uses like '%${key}%' " +
            "or user = (SELECT id FROM USER WHERE NAME = #{key}) order by time desc")
    List<Expend> selectExpend_inLikeIdOrUsesOrUser(String key);

    @Delete("delete from expend_in where id = #{id}")
    Integer deleteExpend_inByid(Integer id);

    @Select("select amount from expend_in where id = #{id}")
    double selectAmount_inByid(Integer id);





}