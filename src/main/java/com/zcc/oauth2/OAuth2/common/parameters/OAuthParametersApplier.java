package com.zcc.oauth2.OAuth2.common.parameters;

import java.util.Map;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthMessage;

public interface OAuthParametersApplier {

	   OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params)throws
	   OAuthSystemException;
}
