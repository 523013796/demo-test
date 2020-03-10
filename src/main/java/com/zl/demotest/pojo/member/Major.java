package com.zl.demotest.pojo.member;

public class Major {
    private Integer id;

    private String majorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}