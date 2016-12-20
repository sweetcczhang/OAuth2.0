package com.zcc.oauth2.OAuth2.common.parameters;

import java.util.Map;

import org.apache.oltu.oauth2.common.utils.JSONUtils;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthMessage;

public class JSONBodyParametersApplier implements OAuthParametersApplier{

	@Override
	public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params)
			throws OAuthSystemException {
		// TODO Auto-generated method stub
		  String json=null;
		  try{
			  
			  json=JSONUtils.buildJSON(params);
			  System.out.println("json:"+json);
			  message.setBody(json);
			  return message;
		  }catch(Throwable e){
			  throw new OAuthSystemException(e);
		  }	
	}

}
