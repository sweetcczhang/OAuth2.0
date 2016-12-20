package com.zcc.oauth2.OAuth2.authorizeServer.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.common.error.OAuthError;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.validators.AbstractValidator;

public class CodeTokenValidator extends AbstractValidator<HttpServletRequest>{
	
	public CodeTokenValidator(){
		requiredParams.add(OAuth.OAUTH_CLIENT_ID);
		requiredParams.add(OAuth.OAUTH_GRANT_TYPE);
		requiredParams.add(OAuth.OAUTH_REDIRECT_URI);
	}
 
	@Override
	public void validateMethod(HttpServletRequest request)throws OAuthProblemException{
		    String method=request.getMethod();
		    if(!OAuth.HttpMethod.GET.equals(method) && !OAuth.HttpMethod.POST.equals(method)){
		    	throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST)
		    	.description("Method not correct.");
		    }
	}
	
	@Override
	public void validateContentType(HttpServletRequest request)throws OAuthProblemException{
		
	}
	
	
	
	
}
