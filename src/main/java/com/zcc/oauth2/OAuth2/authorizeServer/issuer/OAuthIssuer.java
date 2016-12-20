package com.zcc.oauth2.OAuth2.authorizeServer.issuer;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;

public interface OAuthIssuer {
	
	public String accessToken()throws OAuthSystemException;
	
	public String authorizationCode()throws OAuthSystemException;
	
	public String refreshToken()throws OAuthSystemException;
	

}
