package com.zl.demotest.pojo.expend;

import java.util.Date;

public class Expend {
    private Integer id;

    private Integer user;

    private Double amount;

    private Date time;

    private String uses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses == null ? null : uses.trim();
    }

    @Override
    public String toString() {
        return "Expend{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", time=" + time +
                ", uses='" + uses + '\'' +
                '}';
    }
}