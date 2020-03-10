/**
 * User: zlin
 * Date: 2019/8/9
 * Time: 18:56
 **/

package com.zl.demotest.function.expend;

import com.zl.demotest.controller.expend.ExpendAo;
import com.zl.demotest.dao.expend.AmountDao;
import com.zl.demotest.dao.expend.ExpendDao;
import com.zl.demotest.dao.member.UserDao;
import com.zl.demotest.pojo.expend.Amount;
import com.zl.demotest.pojo.expend.Expend;
import com.zl.demotest.utils.StringAndDateUtil;
import com.zl.demotest.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ExpendFunctionIml implements ExpendFunction {
    @Autowired
    ExpendDao expendDao;
    @Autowired
    UserDao userDao;
    @Autowired
    AmountDao amountDao;
    @Override
    public List<ExpendAo> selectAllExpend() throws Exception {
        List<Expend> expendList = expendDao.selectAllExpend();
        List<ExpendAo> expendAoList = new ArrayList<>();
        for (Expend expend:expendList) {
            ExpendAo expendAo = getExpendAo(expend);
            expendAoList.add(expendAo);
        }
        return expendAoList;
    }

    @Override
    public Integer updateByIdActive(ExpendAo expendAo) throws Exception {
        expendAo.setAmount(setAmountOut(expendAo.getAmount()));
        double v = expendDao.selectAmountByid(expendAo.getId());
        double c = expendAo.getAmount()-v;
        Amount amount = amountDao.selectAllAmount();
        amount.setAllAmount(amount.getAllAmount()+c);
        Integer i = amountDao.updateAllAmoubt(amount);
        if (i != 1) {
            throw new RuntimeException();
        }
        Expend expend = getExpend(expendAo);
        return expendDao.updateByPrimaryKeySelective(expend);
    }

    @Override
    public Integer deleteById(Integer id) throws Exception {
        double v = expendDao.selectAmountByid(id);
        Amount amount = amountDao.selectAllAmount();
        amount.setAllAmount(amount.getAllAmount()+(-v));
        Integer i = amountDao.updateAllAmoubt(amount);
        if (i != 1) {
            throw new RuntimeException();
        }
        return expendDao.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insertExpend(ExpendAo ao) throws Exception {
        ao.setAmount(setAmountOut(ao.getAmount()));
        Amount amount = amountDao.selectAllAmount();
        amount.setAllAmount(amount.getAllAmount()+ao.getAmount());
        Integer i = amountDao.updateAllAmoubt(amount);
        if (i != 1) {
            throw new RuntimeException();
        }
        Expend expend = getExpend(ao);
        expend.setId(UUIDUtils.getUUIDAsInt());
        Integer j = expendDao.insertSelective(expend);
        if (j == 1) {
            return j;
        }else {
            throw new RuntimeException("添加失败");
        }
    }

    @Override
    public List<ExpendAo> selectExpendLikeLdOrUserOrUses(String key) throws Exception {
        List<Expend> expendList = expendDao.selectExpendLikeIdOrUsesOrUser(key);
        List<ExpendAo> expendAoList = new ArrayList<>();
        for (Expend exp:expendList) {
            expendAoList.add(getExpendAo(exp));
        }
        return expendAoList;
    }

    @Override
    public List<ExpendAo> selectExpendByUser(Integer id) throws Exception {
        List<Expend> expendList = expendDao.selectExpendByuser(id);
        List<ExpendAo> expendAoList = new ArrayList<>();
        for (Expend e:expendList
             ) {
            expendAoList.add(getExpendAo(e));
        }
        return expendAoList;
    }

    @Override
    public Amount selectAllAmount() {
        return amountDao.selectAllAmount();
    }

    @Override
    public double sumExpend_out() {
        return expendDao.sumExpend_out();
    }

    @Override
    public double sumExpend_in() {
        return expendDao.sumExpend_in();
    }

    @Override
    public List<ExpendAo> selectAllExpend_in() throws Exception {
        List<Expend> expendList = expendDao.selectAllExpend_in();
        List<ExpendAo> expendAoList = new ArrayList<>();
        for (Expend expend:expendList) {
            ExpendAo expendAo = getExpendAo(expend);
            expendAoList.add(expendAo);
        }
        return expendAoList;
    }

    @Override
    public Integer updateExpend_inById(ExpendAo expendAo) throws Exception {
        double v = expendDao.selectAmount_inByid(expendAo.getId());
        double c = expendAo.getAmount()-v;
        Amount amount = amountDao.selectAllAmount();
        amount.setAllAmount(amount.getAllAmount()+c);
        Integer i = amountDao.updateAllAmoubt(amount);
        if (i != 1) {
            throw new RuntimeException();
        }
        Expend expend = getExpend(expendAo);
        return expendDao.updateExpend_in(expend);
    }

    @Override
    public Integer insertExpend_in(ExpendAo ao) throws Exception {
        Amount amount = amountDao.selectAllAmount();
        amount.setAllAmount(amount.getAllAmount()+ao.getAmount());
        Integer i = amountDao.updateAllAmoubt(amount);
        if (i != 1) {
            throw new RuntimeException();
        }
        Expend expend = getExpend(ao);
        expend.setId(UUIDUtils.getUUIDAsInt());
        Integer j = expendDao.insertExpend_in(expend);
        if (j == 1) {
            return j;
        }else {
            throw new RuntimeException("添加失败");
        }
    }

    @Override
    public Integer deleteExpend_inById(Integer id) throws Exception {
        double v = expendDao.selectAmount_inByid(id);
        Amount amount = amountDao.selectAllAmount();
        amount.setAllAmount(amount.getAllAmount()+(-v));
        Integer i = amountDao.updateAllAmoubt(amount);
        if (i != 1) {
            throw new RuntimeException();
        }
        return expendDao.deleteExpend_inByid(id);
    }

    @Override
    public List<ExpendAo> selectExpend_inLikeLdOrUserOrUses(String key) throws Exception {
        List<Expend> expendList = expendDao.selectExpend_inLikeIdOrUsesOrUser(key);
        List<ExpendAo> expendAoList = new ArrayList<>();
        for (Expend exp:expendList) {
            expendAoList.add(getExpendAo(exp));
        }
        return expendAoList;
    }

    private ExpendAo getExpendAo(Expend expend) throws ParseException {
        ExpendAo ao = new ExpendAo();
        ao.setAmount(expend.getAmount());
        ao.setId(expend.getId());
        ao.setTime(StringAndDateUtil.DateTimeToString(expend.getTime()));
        ao.setUser(userDao.selectNameById(expend.getUser()));
        ao.setUses(expend.getUses());
        return ao;
    }

    private Expend getExpend(ExpendAo expendAo)throws Exception{
        Expend expend = new Expend();
        expend.setId(expendAo.getId());
        expend.setAmount(expendAo.getAmount());
        expend.setTime(StringAndDateUtil.StringToDateTime(expendAo.getTime()));
        expend.setUser(userDao.selectIdByName(expendAo.getUser()));
        expend.setUses(expendAo.getUses());
        return expend;
    }

    double setAmountOut(double amount){
        if (amount <=0) {
            throw new RuntimeException("支出金额不能小于或等于0");
        }else {
            return  -amount;
        }
    }
}
