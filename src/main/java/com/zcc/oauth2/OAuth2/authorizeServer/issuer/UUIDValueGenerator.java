package com.zcc.oauth2.OAuth2.authorizeServer.issuer;

import java.util.UUID;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;

public class UUIDValueGenerator implements ValueGenerator{

	
	
	@Override
	public String generateValue() throws OAuthSystemException {
		// TODO Auto-generated method stub
		
		return generateValue(UUID.randomUUID().toString());
	}

	@Override
	public String generateValue(String param) throws OAuthSystemException {
		// TODO Auto-generated method stub
		
		return UUID.fromString(UUID.nameUUIDFromBytes(param.getBytes()).toString()).toString();
	}

}
