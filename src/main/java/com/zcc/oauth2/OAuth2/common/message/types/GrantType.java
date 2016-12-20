package com.zcc.oauth2.OAuth2.common.message.types;

public enum GrantType {

	/*
	AUTHORIZATION_CODE("authorization_code"),
	IMPLICIT("implicit"),
	PASSWORD("password"),
	REFRESH_TOKEN("refresh_token"),
	CLIENT_CREDENTIALS("client_credentials"),
	JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer");
	*/
	AUTHORIZATION_CODE("authorization_code"),
	REFRESH_TOKEN("refresh_token"),
	JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer");	
	private String grantType;
	
	GrantType(String grantType){
		this.grantType=grantType;
	}
	@Override
	public String toString(){
		return grantType;
	}
}
