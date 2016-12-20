package com.zcc.oauth2.OAuth2.common.parameters;

import java.util.Map;

import org.apache.oltu.oauth2.common.utils.OAuthUtils;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthMessage;

public class WWWAuthHeaderParametersApplier implements OAuthParametersApplier{

	@Override
	public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params)
			throws OAuthSystemException {
		// TODO Auto-generated method stub
		String header=OAuthUtils.encodeOAuthHeader(params);
		message.addHeader(OAuth.HeaderType.WWW_AUTHENTICATE, header);
		return message;
	}

}
