package com.zl.demotest.pojo.book;

import java.util.Date;

public class BorrowBooks {
    private Integer id;

    private Integer user;

    private Integer book;

    private Date time;

    private Integer booknumber;

    private Boolean flag;

    private Date rTime;

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

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getBooknumber() {
        return booknumber;
    }

    public void setBooknumber(Integer booknumber) {
        this.booknumber = booknumber;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Date getrTime() {
        return rTime;
    }

    public void setrTime(Date rTime) {
        this.rTime = rTime;
    }

    @Override
    public String toString() {
        return "BorrowBooks{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", time=" + time +
                ", booknumber=" + booknumber +
                ", flag=" + flag +
                ", rTime=" + rTime +
                '}';
    }
}