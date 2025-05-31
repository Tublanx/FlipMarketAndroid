package com.lgh.FlipMarketBackend.dto;

public class RegisterRequest {

	private String username;
	
	private String password;
	
	private String name;
	
	private int age;
	
	private String phoneNum;
	
	private String role;
	
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
}
