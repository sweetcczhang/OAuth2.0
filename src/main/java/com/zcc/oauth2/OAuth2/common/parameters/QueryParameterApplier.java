package com.zcc.oauth2.OAuth2.common.parameters;

import java.util.Map;

import com.zcc.oauth2.OAuth2.common.message.OAuthMessage;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;

public class QueryParameterApplier implements OAuthParametersApplier{

	@Override
	public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params)
		           {
		// TODO Auto-generated method stub
		 String messageUrl=message.getLocationUri();
		 if(messageUrl!=null){
			 boolean containsQuestionMark=messageUrl.contains("?");
			 StringBuffer url=new StringBuffer(messageUrl);
			 StringBuffer query=new StringBuffer(OAuthUtils.format(params.entrySet(), "UTF-8"));
			 if(!OAuthUtils.isEmpty(query.toString())){
				 if(containsQuestionMark){
					 url.append("&").append(query);
				 }else{
					 url.append("?").append(query);
				 }
			 }
			 message.setLocationUri(url.toString());
		 }
		return message;
	}

}
