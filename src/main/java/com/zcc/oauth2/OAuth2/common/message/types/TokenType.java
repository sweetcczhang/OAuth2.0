package com.zcc.oauth2.OAuth2.common.message.types;

public enum TokenType {
    
	BEARER("Bearer"),
	MAC("MAC");
	
	private String tokenType;
	
	TokenType(String tokenType){
		this.tokenType=tokenType;
	}
	@Override
	public String toString(){
		return tokenType;
	}
}
