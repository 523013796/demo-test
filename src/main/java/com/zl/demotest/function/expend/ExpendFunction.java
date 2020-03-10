/**
 * User: zlin
 * Date: 2019/8/9
 * Time: 18:54
 **/

package com.zl.demotest.function.expend;

import com.zl.demotest.controller.expend.ExpendAo;
import com.zl.demotest.pojo.expend.Amount;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExpendFunction {

    @Transactional
    List<ExpendAo> selectAllExpend()throws Exception;

    @Transactional
    Integer updateByIdActive(ExpendAo expendAo)throws Exception;

    @Transactional
    Integer deleteById(Integer id)throws Exception;

    @Transactional
    Integer insertExpend(ExpendAo ao)throws Exception;

    @Transactional
    List<ExpendAo> selectExpendLikeLdOrUserOrUses(String key)throws Exception;

    @Transactional
    List<ExpendAo> selectExpendByUser(Integer id)throws Exception;

    Amount selectAllAmount();

    double sumExpend_out();

    double sumExpend_in();

    List<ExpendAo> selectAllExpend_in()throws Exception;

    @Transactional
    Integer updateExpend_inById(ExpendAo expendAo)throws Exception;

    @Transactional
    Integer insertExpend_in(ExpendAo ao)throws Exception;

    @Transactional
    Integer deleteExpend_inById(Integer id)throws Exception;

    @Transactional
    List<ExpendAo> selectExpend_inLikeLdOrUserOrUses(String key)throws Exception;
}
