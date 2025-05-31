package com.lgh.FlipMarketBackend.dto;

public class RegisterResponse {

	private boolean isSuccess;
	
	private String message;
	
	public RegisterResponse(boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

}
