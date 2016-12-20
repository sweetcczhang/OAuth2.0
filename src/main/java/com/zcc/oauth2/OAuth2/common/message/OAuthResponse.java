package com.zcc.oauth2.OAuth2.common.message;

import java.util.HashMap;
import java.util.Map;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.error.OAuthError;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.parameters.BodyURLEncodedParametersApplier;
import com.zcc.oauth2.OAuth2.common.parameters.FragmentParametersApplier;
import com.zcc.oauth2.OAuth2.common.parameters.JSONBodyParametersApplier;
import com.zcc.oauth2.OAuth2.common.parameters.OAuthParametersApplier;
import com.zcc.oauth2.OAuth2.common.parameters.QueryParameterApplier;
import com.zcc.oauth2.OAuth2.common.parameters.WWWAuthHeaderParametersApplier;

public class OAuthResponse implements OAuthMessage{
	 
	 protected int responseStatus;
	 protected String body;
	 protected String uri;
	 
	 protected Map<String,String> headers=new HashMap<String,String>();
	 
     protected OAuthResponse(String uri,int responseStatus){
    	 this.uri=uri;
    	 this.responseStatus=responseStatus;
     }
     
     public static OAuthResponseBuilder status(int code){
    	 return new OAuthResponseBuilder(code);
     }
     
     public static OAuthErrorResponseBuilder errorResponse(int code){
    	  return new OAuthErrorResponseBuilder(code);
     }
     @Override
     public String getLocationUri(){
    	 return uri;
     }
     @Override
     public void setLocationUri(String uri){
    	 this.uri=uri;
     }
     @Override
     public String getBody(){
    	 return body;
     }
     @Override
     public void  setBody(String body){
    	 this.body=body;
     }
     @Override
     public String getHeader(String name){
    	 return headers.get(name);
     }
     @Override
     public Map<String ,String> getHeader(){
    	 return headers;
     }
     @Override
     public void setHeader(Map<String,String>headers){
    	 this.headers=headers;
     }
     @Override
     public void addHeader(String name,String header){
    	 this.headers.put(name, header);
     }
     
      public int getResponseStatus(){
    	  return responseStatus;
      }
     
     
     
     public static class OAuthResponseBuilder{
    	
    	 protected OAuthParametersApplier applier;
    	 protected Map<String,Object> parameters=new HashMap<String, Object>();
    	 protected int responseCode;
    	 protected String location;
    	 
    	 public OAuthResponseBuilder(int responseCode){
    		 this.responseCode=responseCode;
    	 }
    	 
    	 public OAuthResponseBuilder location(String location){
    		 this.location=location;
    		 return this;
    	 }
    	 
    	 public OAuthResponseBuilder setScope(String value){
    		 this.parameters.put(OAuth.OAUTH_SCOPE, value);
    		 return this;
    	 }
    	 
    	 public OAuthResponseBuilder setParam(String key,String value){
    		 this.parameters.put(key, value);
    		 return this; 
    	 }
    	 
    	 public OAuthResponse buildQueryMessage() throws OAuthSystemException{
    		 OAuthResponse msg=new OAuthResponse(location,responseCode);
    	     this.applier=new QueryParameterApplier();
    	     if(parameters.containsKey(OAuth.OAUTH_ACCESS_TOKEN)){
    	    	 this.applier=new FragmentParametersApplier();
    	     }else{
    	    	 this.applier=new QueryParameterApplier();
    	     }
    	      return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    	 }
         
    	 public OAuthResponse buildBodyMessage()throws OAuthSystemException{
    		 OAuthResponse msg=new OAuthResponse(location,responseCode);
    		 this.applier=new BodyURLEncodedParametersApplier();
    		 
    		 return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    	 }
    	 
    	 public OAuthResponse buildJSONMessage()throws OAuthSystemException{
    		OAuthResponse msg=new OAuthResponse(location,responseCode);
    		this.applier=new JSONBodyParametersApplier();
    		return(OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    	 }
    	 
    	 public OAuthResponse buildHeaderMessage()throws OAuthSystemException{
    		 OAuthResponse msg=new OAuthResponse(location,responseCode);
    		 this.applier=new WWWAuthHeaderParametersApplier();
    		 return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    	 }
    	 
     } 
    	 public static class OAuthErrorResponseBuilder extends OAuthResponseBuilder{
    		 
    		 public OAuthErrorResponseBuilder(int responseCode){
    			 super(responseCode);
    		 }
    		 
    		 public OAuthErrorResponseBuilder error(OAuthProblemException ex){
    			this.parameters.put(OAuthError.OAUTH_ERROR, ex.getError());
    			this.parameters.put(OAuthError.OAUTH_ERROR_DESCRIPTION, ex.getDescription());
    			this.parameters.put(OAuthError.OAUTH_ERROR_URI, ex.getUri());
    			this.parameters.put(OAuth.OAUTH_STATE, ex.getState());
    			this.parameters.put(OAuthError.OAUTH_ERROR_CODE,ex.getErrorCode());
    			return this;
    		 }
    		 
    		 public OAuthErrorResponseBuilder setError(String error){
    			 this.parameters.put(OAuthError.OAUTH_ERROR, error);
    			 return this;	 
    		 }
    		 
    		 public OAuthErrorResponseBuilder setErrorDescription(String description){
    			 this.parameters.put(OAuthError.OAUTH_ERROR_DESCRIPTION, description);
    			 return this;
    		 }
    		 
    		 public OAuthErrorResponseBuilder setErrorCode(String code){
    			 
    			 this.parameters.put(OAuthError.OAUTH_ERROR_CODE, code);
    			 return this;
    		 }
    		 
    		 public OAuthErrorResponseBuilder setErrorUri(String uri){
    			 this.parameters.put(OAuthError.OAUTH_ERROR_URI, uri);
    			 return this;
    		 }
    		 
    		 public OAuthErrorResponseBuilder setState(String state){
    			 this.parameters.put(OAuth.OAUTH_STATE, state);
    			 return this;
    		 }
    		 
    		 public OAuthErrorResponseBuilder setRealm(String realm){
    			 this.parameters.put(OAuth.WWWAuthHeader.REALM, realm);
    			 return this;
    		 }
    		 
    		 public OAuthErrorResponseBuilder location(String location){
    			 this.location=location;
    			 return this;
    		 }
    	 }	 
   
}
