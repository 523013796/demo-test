/**
 * User: zlin
 * Date: 2019/8/2
 * Time: 18:11
 **/

package com.zl.demotest.controller.book;

public class BookAo {
    private Integer id;

    private String name;

    private String author;

    private Double price;

    private String type;

    private String buytime;

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
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuytime() {
        return buytime;
    }

    public void setBuytime(String buytime) {
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
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "BookAo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", buytime='" + buytime + '\'' +
                ", numberremaining=" + numberremaining +
                ", number=" + number +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
