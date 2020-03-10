/**
 * User: zlin
 * Date: 2019/8/12
 * Time: 9:55
 **/

package com.zl.demotest.controller.login;

public class UserAo {
    private Integer id;

    private String name;

    private Boolean sex;

    private Byte age;

    private String idnumber;

    private String phone;

    private String qq;

    private String wechat;

    private String registertime;

    private String major;

    private String rank;

    private String studentnumber;

    private String password;

    private  int rank_t;

    public UserAo() {
    }

    public UserAo(Integer id, String name, Boolean sex, Byte age, String idnumber, String phone,
                  String qq, String wechat, String registertime, String major, String rank,
                  String studentnumber, String password,int rank_t) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.idnumber = idnumber;
        this.phone = phone;
        this.qq = qq;
        this.wechat = wechat;
        this.registertime = registertime;
        this.major = major;
        this.rank = rank;
        this.studentnumber = studentnumber;
        this.password = password;
        this.rank_t = rank_t;
    }

    @Override
    public String toString() {
        return "UserAo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", idnumber='" + idnumber + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", registertime=" + registertime +
                ", major='" + major + '\'' +
                ", rank='" + rank + '\'' +
                ", studentnumber='" + studentnumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int  getRank_t() {
        return rank_t;
    }

    public void setRank_t(int  rank_t) {
        this.rank_t = rank_t;
    }

}
