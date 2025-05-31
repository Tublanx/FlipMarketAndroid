package com.lgh.FlipMarketBackend.dto;

public class EmailCheckResponse {

	private boolean exists;
	
	public EmailCheckResponse(boolean exists) {
		this.exists = exists;
	}
	
	public boolean isExists() {
		return exists;
	}
	
}
