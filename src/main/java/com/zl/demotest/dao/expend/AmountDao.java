/**
 * User: zlin
 * Date: 2019/8/11
 * Time: 10:46
 **/

package com.zl.demotest.dao.expend;

import com.zl.demotest.pojo.expend.Amount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AmountDao {

    @Select("select * from amount ")
    Amount selectAllAmount();

    @Update("UPDATE  `mangeteam`.`amount`  SET  `all_amount` = #{all_amount}  WHERE `id` = #{id} ")
    Integer updateAllAmoubt(Amount amo);
}
