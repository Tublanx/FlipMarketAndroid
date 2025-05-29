package com.lgh.flipmarketandroid.dto.user;

public class RegisterRequest {

    private String username;
    private String password;
    private String name;
    private int age;
    private String phoneNum;

    public RegisterRequest(String username, String password, String name, int age, String phoneNum) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
    }

}
