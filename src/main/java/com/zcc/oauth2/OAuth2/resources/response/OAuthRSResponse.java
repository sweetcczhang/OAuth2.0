package com.zcc.oauth2.OAuth2.resources.response;

import com.zcc.oauth2.OAuth2.common.message.OAuthResponse;

public class OAuthRSResponse extends OAuthResponse{

	
	protected OAuthRSResponse(String uri, int responseStatus) {
		super(uri, responseStatus);
		// TODO Auto-generated constructor stub
	}
    
	public static class OAuthRSResponseBuilder extends OAuthResponse.OAuthResponseBuilder{

		public OAuthRSResponseBuilder(int responseCode) {
			super(responseCode);
			// TODO Auto-generated constructor stub
		}
		
	}
}
