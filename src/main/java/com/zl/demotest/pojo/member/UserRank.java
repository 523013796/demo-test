package com.zl.demotest.pojo.member;

public class UserRank {
    private Byte no;

    private String rank;

    public Byte getNo() {
        return no;
    }

    public void setNo(Byte no) {
        this.no = no;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }
}