/**
 * User: zlin
 * Date: 2019/8/7
 * Time: 17:26
 **/

package com.zl.demotest.controller.book;

public class BorrowBookAo {
    private Integer id;

    private String user;

    private Integer book;

    private String book_name;

    private String time;

    private Integer booknumber;

    private Boolean flag;

    private String rTime;

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

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    @Override
    public String toString() {
        return "BorrowBookAo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", book=" + book +
                ", book_name='" + book_name + '\'' +
                ", time='" + time + '\'' +
                ", booknumber=" + booknumber +
                ", flag=" + flag +
                ", rTime='" + rTime + '\'' +
                '}';
    }
}
