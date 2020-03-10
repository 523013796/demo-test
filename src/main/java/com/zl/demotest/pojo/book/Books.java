package com.zl.demotest.pojo.book;

import java.util.Date;

public class Books {
    private Integer id;

    private String name;

    private String author;

    private Double price;

    private Integer type;

    private Date buytime;

    private Integer numberremaining;

    private Integer number;

    private String introduce;

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
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public Integer getNumberremaining() {
        return numberremaining;
    }

    public void setNumberremaining(Integer numberremaining) {
        this.numberremaining = numberremaining;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", buytime=" + buytime +
                ", numberremaining=" + numberremaining +
                ", number=" + number +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}