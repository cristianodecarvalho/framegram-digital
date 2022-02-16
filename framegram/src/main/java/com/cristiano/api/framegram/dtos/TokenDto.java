package com.cristiano.api.framegram.dtos;

public class TokenDto {

	private String token;
	private String type;
	private Long loggedUserId;
	
	public TokenDto(String token, String type, Long loggedUserId) {
		this.token = token;
		this.type = type;
		this.loggedUserId = loggedUserId;
	}
	
	public String getToken() {
		return token;
	}
	
	public String getType() {
		return type;
	}

	public Long getLoggedUserId() {
		return loggedUserId;
	}

}
