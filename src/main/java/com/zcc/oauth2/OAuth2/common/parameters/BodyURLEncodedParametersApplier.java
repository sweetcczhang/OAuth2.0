package com.zcc.oauth2.OAuth2.common.parameters;

import java.util.Map;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthMessage;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;

public class BodyURLEncodedParametersApplier implements OAuthParametersApplier{

	public OAuthMessage applyOAuthParameters(OAuthMessage message,Map<String, Object> params)
	throws OAuthSystemException{
		String body=OAuthUtils.format(params.entrySet(), "UTF-8");
		message.setBody(body);
		return message;
		
		
	}
	
}
