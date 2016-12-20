package com.zcc.oauth2.OAuth2.authorizeServer.validator;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.validators.AbstractValidator;

public class AuthorizationCodeValidator extends AbstractValidator<HttpServletRequest>{
	
	public AuthorizationCodeValidator(){
		
		requiredParams.add(OAuth.OAUTH_CLIENT_ID);
		requiredParams.add(OAuth.OAUTH_GRANT_TYPE);
		requiredParams.add(OAuth.OAUTH_REDIRECT_URI);
		enforceClientAuthentication=true;
	}

}
