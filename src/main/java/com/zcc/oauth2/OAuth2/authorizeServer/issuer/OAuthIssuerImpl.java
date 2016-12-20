package com.zcc.oauth2.OAuth2.authorizeServer.issuer;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;

public class OAuthIssuerImpl implements OAuthIssuer{
	
	private ValueGenerator vg;

	public OAuthIssuerImpl(ValueGenerator vg){
		this.vg=vg;
	}
	@Override
	public String accessToken() throws OAuthSystemException {
		// TODO Auto-generated method stub
		return vg.generateValue();
	}

	@Override
	public String authorizationCode() throws OAuthSystemException {
		// TODO Auto-generated method stub
		return vg.generateValue();
	}

	@Override
	public String refreshToken() throws OAuthSystemException {
		// TODO Auto-generated method stub
		return vg.generateValue();
	}

}
