package com.zcc.oauth2.OAuth2.authorizeServer.request;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;
import com.zcc.oauth2.OAuth2.common.validators.OAuthValidator;

public abstract class AbstractOAuthTokenRequest extends OAuthRequest{
	
     protected AbstractOAuthTokenRequest(HttpServletRequest request) throws OAuthProblemException,OAuthSystemException{
    	 super(request);
     }
     protected OAuthValidator<HttpServletRequest> initValidator()throws OAuthProblemException,OAuthSystemException{
    	final String requestTypeValue=getParam(OAuth.OAUTH_GRANT_TYPE); 
    	if(OAuthUtils.isEmpty(requestTypeValue)){
    		throw OAuthUtils.handleOAuthProblemException("Missing grant_type parameter value");
    	}
    	final Class<?extends OAuthValidator<HttpServletRequest>> clazz=validators.get(requestTypeValue);
        if(clazz==null){
        	throw OAuthUtils.handleOAuthProblemException("Invalid grant_type parameter value");	
        }
        return OAuthUtils.instantiateClass(clazz);
     }
     
   /*  public String getPassword(){
    	 return getParam(OAuth.OAUTH_PASSWORD);
     }
     
     public String getUsername(){
    	 return getParam(OAuth.OAUTH_USERNAME);
     }
     */
     public String getCode(){
    	 return getParam(OAuth.OAUTH_CODE);
     }
     
     public String getGrantType(){
    	 return getParam(OAuth.OAUTH_GRANT_TYPE);
     }
     
     public String getRefreshToken(){
    	 return getParam(OAuth.OAUTH_REFRESH_TOKEN);
     }
     
}
