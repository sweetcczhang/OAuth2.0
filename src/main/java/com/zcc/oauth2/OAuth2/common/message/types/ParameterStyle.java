package com.zcc.oauth2.OAuth2.common.message.types;

public enum ParameterStyle {
    BODY("body"),
    QUERY("query"),
    HEADER("header");
	
	private String parameterStyle;
	
	ParameterStyle(String parameterStyle){
		this.parameterStyle= parameterStyle;
	}
	
  public String toString(){
		
		return parameterStyle;
	}
	
}
