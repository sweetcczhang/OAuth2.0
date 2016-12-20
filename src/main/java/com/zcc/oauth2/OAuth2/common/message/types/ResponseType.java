package com.zcc.oauth2.OAuth2.common.message.types;

public enum ResponseType {

	CODE("code"),
	TOKEN("token");
	
	private String responseType;
	
	ResponseType(String responseType){
		this.responseType=responseType;
	}
	
	@Override
	public String toString(){
		return responseType;
	}
}
