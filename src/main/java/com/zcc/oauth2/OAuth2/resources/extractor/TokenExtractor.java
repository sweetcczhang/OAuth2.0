package com.zcc.oauth2.OAuth2.resources.extractor;

import javax.servlet.http.HttpServletRequest;

public interface TokenExtractor {
	
	public String getAccessToken(HttpServletRequest request);
	
	public String getAccessToken(HttpServletRequest request,String tokenName);

}
