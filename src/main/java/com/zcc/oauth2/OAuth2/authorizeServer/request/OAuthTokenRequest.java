package com.zcc.oauth2.OAuth2.authorizeServer.request;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.authorizeServer.validator.AuthorizationCodeValidator;
import com.zcc.oauth2.OAuth2.authorizeServer.validator.RefreshTokenValidator;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.types.GrantType;
import com.zcc.oauth2.OAuth2.common.validators.OAuthValidator;

public class OAuthTokenRequest extends AbstractOAuthTokenRequest{

	public OAuthTokenRequest(HttpServletRequest request) throws OAuthProblemException, OAuthSystemException {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	protected OAuthValidator<HttpServletRequest> initValidator()throws OAuthProblemException,OAuthSystemException{
		validators.put(GrantType.AUTHORIZATION_CODE.toString(), AuthorizationCodeValidator.class);
		validators.put(GrantType.REFRESH_TOKEN.toString(), RefreshTokenValidator.class);
		return super.initValidator();
	}

}
