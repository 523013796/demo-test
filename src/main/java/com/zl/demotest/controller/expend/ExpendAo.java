/**
 * User: zlin
 * Date: 2019/8/10
 * Time: 9:21
 **/

package com.zl.demotest.controller.expend;

public class ExpendAo {

    private Integer id;

    private String user;

    private Double amount;

    private String time;

    private String uses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    @Override
    public String toString() {
        return "ExpendAo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                ", uses='" + uses + '\'' +
                '}';
    }
}
