package com.lgh.flipmarketandroid.dto.user;

public class RegisterRequest {

    private String username;
    private String password;
    private String name;
    private int age;
    private String phoneNum;

    private String role;

    public RegisterRequest() {

    }

    public RegisterRequest(String username, String password, String name, int age, String phoneNum, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNum = phoneNum;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getRole() {
        return role;
    }
}
