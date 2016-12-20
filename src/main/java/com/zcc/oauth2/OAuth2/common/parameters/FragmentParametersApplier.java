package com.zcc.oauth2.OAuth2.common.parameters;

import java.util.Map;

import org.apache.oltu.oauth2.common.utils.OAuthUtils;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthMessage;

public class FragmentParametersApplier implements OAuthParametersApplier{

	@Override
	public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params)
			throws OAuthSystemException {
		    String messageUrl=message.getLocationUri();
		    if(messageUrl!=null){
		    	StringBuilder url=new StringBuilder(messageUrl);
		    	if(params.containsKey(OAuth.OAUTH_REFRESH_TOKEN)){
		    		params.remove(OAuth.OAUTH_REFRESH_TOKEN);
		    	}
		    	String fragmentQuery=OAuthUtils.format(params.entrySet(), "UTF-8");
		    	if(!OAuthUtils.isEmpty(fragmentQuery)){
		    		if(params.size()>0){
		    			url.append("#").append(fragmentQuery);
		    		}
		    	}
		    	message.setLocationUri(url.toString());
		    }
		// TODO Auto-generated method stub
		return message;
	}

	
}
