package com.zcc.oauth2.OAuth2.authorizeServer.issuer;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;

public interface ValueGenerator {
	
	public String generateValue() throws OAuthSystemException;
	
	public String generateValue(String param) throws OAuthSystemException;

}
