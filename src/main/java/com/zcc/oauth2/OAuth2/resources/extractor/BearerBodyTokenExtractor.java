package com.zcc.oauth2.OAuth2.resources.extractor;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.common.OAuth;

public class BearerBodyTokenExtractor implements TokenExtractor{

	@Override
	public String getAccessToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token=request.getParameter(OAuth.OAUTH_BEARER_TOKEN);
		if(token==null){
			token=request.getParameter(OAuth.OAUTH_TOKEN);
		}
		return token;
	}

	@Override
	public String getAccessToken(HttpServletRequest request, String tokenName) {
		// TODO Auto-generated method stub
		String token=request.getParameter(tokenName);
		
		return token;
	}

}
