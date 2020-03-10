/**
 * User: zlin
 * Date: 2019/8/11
 * Time: 10:44
 **/

package com.zl.demotest.pojo.expend;

public class Amount {
    private String id;
    private double all_amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAllAmount() {
        return all_amount;
    }

    public void setAllAmount(double allAmount) {
        this.all_amount = allAmount;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "id='" + id + '\'' +
                ", allAmount=" + all_amount +
                '}';
    }
}
