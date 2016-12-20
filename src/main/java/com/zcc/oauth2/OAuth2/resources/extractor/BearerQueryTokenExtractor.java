package com.zcc.oauth2.OAuth2.resources.extractor;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.common.OAuth;
import static com.zcc.oauth2.OAuth2.resources.ResourceServer.getQueryParameterValue;

public class BearerQueryTokenExtractor implements TokenExtractor{

	@Override
	public String getAccessToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token=getQueryParameterValue(request,OAuth.OAUTH_BEARER_TOKEN);
		if(token==null){
			token=getQueryParameterValue(request,OAuth.OAUTH_TOKEN);
		}
		return token;
	}

	@Override
	public String getAccessToken(HttpServletRequest request, String tokenName) {
		// TODO Auto-generated method stub
	String	token=getQueryParameterValue(request,tokenName);
		
	   return token;
	}

}
