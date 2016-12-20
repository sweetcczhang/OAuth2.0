package com.zcc.oauth2.OAuth2.common.exception;

import java.util.HashMap;
import java.util.Map;

import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;


public class OAuthProblemException extends Exception{

	  private String error;//错误
	  private String description;//错误描述
	  private String uri;//查看错误的地址
	  private String scope;//权限
	  private String state;//状态
	  private String redirectUri;//重定向地址 
	  private int responseStatus;//错误返回码
	  private String errorCode;
	  
	  private Map<String, String> parameters = new HashMap<String, String>();
	  
	  public OAuthProblemException(String error){
		  
		  this(error,"");
		  
	  }
	  
	  public OAuthProblemException(String error ,String description){
		  
		  super(error+" "+description);
		  this.error=error;
		  this.description=description;
		  
	  }
	  
	  public static OAuthProblemException error(String error){
		  
		  return new OAuthProblemException(error);
	  }
	  
	  public static OAuthProblemException error(String error,String description){
		  
		  return new OAuthProblemException(error,description);
	  }
	  
	  public OAuthProblemException description(String description){
		  
		  this.description=description;
		  return this;  
	  }
	  public OAuthProblemException errorCode(String errorCode){
		  this.errorCode=errorCode;
		  return this;
	  }
	  
	  public OAuthProblemException uri(String uri){
		  
		  this.uri=uri;
		  return this;
	  }
	  
	  public OAuthProblemException state(String state) {
	        this.state = state;
	        return this;
	    }

	    public OAuthProblemException scope(String scope) {
	        this.scope = scope;
	        return this;
	    }

	    public OAuthProblemException responseStatus(int responseStatus) {
	        this.responseStatus = responseStatus;
	        return this;
	    }
	   
	    public OAuthProblemException setParameter(String name, String value) {
	        parameters.put(name, value);
	        return this;
	    }

	    public String getError() {
	        return error;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public String getUri() {
	        return uri;
	    }

	    public String getState() {
	        return state;
	    }

	    public String getScope() {
	        return scope;
	    }

	    public int getResponseStatus() {
	        return responseStatus == 0 ? 400 : responseStatus;
	    }
	    
	    public String getErrorCode(){
	    	return errorCode;
	    }

	    public String get(String name) {
	        return parameters.get(name);
	    }

	    public Map<String, String> getParameters() {
	        return parameters;
	    }

	    public String getRedirectUri() {
	        return redirectUri;
	    }

	    public void setRedirectUri(String redirectUri) {
	        this.redirectUri = redirectUri;
	    }

	    @Override
	    public String getMessage() {
	        StringBuilder b = new StringBuilder();
	        if (!OAuthUtils.isEmpty(error)) {
	            b.append(error);
	        }

	        if (!OAuthUtils.isEmpty(description)) {
	            b.append(", ").append(description);
	        }
	        


	        if (!OAuthUtils.isEmpty(uri)) {
	            b.append(", ").append(uri);
	        }


	        if (!OAuthUtils.isEmpty(state)) {
	            b.append(", ").append(state);
	        }

	        if (!OAuthUtils.isEmpty(scope)) {
	            b.append(", ").append(scope);
	        }
            
	        if(!OAuthUtils.isEmpty(errorCode)){
	        	b.append(", ").append(errorCode);
	        }
	        
	        return b.toString();
	    }

	    @Override
	    public String toString() {
	        return "OAuthProblemException{" +
	                "error='" + error + '\'' +
	                ", description='" + description + '\'' +
	                ", uri='" + uri + '\'' +
	                ", state='" + state + '\'' +
	                ", scope='" + scope + '\'' +
	                ", errorCode='" +errorCode + '\''+ 
	                ", redirectUri='" + redirectUri + '\'' +
	                ", responseStatus=" + responseStatus +
	                ", parameters=" + parameters +
	                '}';
	    }
	
}
