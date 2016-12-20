package com.zcc.oauth2.OAuth2.authorizeServer.validator;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.validators.AbstractValidator;

public class RefreshTokenValidator extends AbstractValidator<HttpServletRequest>{
        
	   public RefreshTokenValidator(){
		   
		   requiredParams.add(OAuth.OAUTH_CODE);
		   requiredParams.add(OAuth.OAUTH_REFRESH_TOKEN);
		   
		   enforceClientAuthentication=true;
	   }
	
}
