package com.lgh.flipmarketandroid.dto.product;

public class User {

    private Long num;

    private String email;

    private String pwd;

    private String name;

    private int age;

    private String phoneNum;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String email, String pwd, String name, int age, String phoneNum) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
    }

    public Long getNum() {
        return num;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

}
