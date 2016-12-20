package com.zcc.oauth2.OAuth2.authorizeServer.request;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.authorizeServer.validator.CodeValidator;
import com.zcc.oauth2.OAuth2.authorizeServer.validator.TokenValidator;
import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.types.ResponseType;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;
import com.zcc.oauth2.OAuth2.common.validators.OAuthValidator;

public class OAuthAuthzRequest extends OAuthRequest{

	
	public  OAuthAuthzRequest(HttpServletRequest request)throws OAuthProblemException,OAuthSystemException{
		     super(request);
	}
	
	@Override
	protected OAuthValidator<HttpServletRequest> initValidator() throws OAuthProblemException, OAuthSystemException {
		// TODO Auto-generated method stub
		validators.put(ResponseType.CODE.toString(), CodeValidator.class);
		validators.put(ResponseType.TOKEN.toString(), TokenValidator.class);
		final String requestTypeValue=getParam(OAuth.OAUTH_RESPONSE_TYPE);
		System.out.println("这个验证其到底有没有执行    "+"requestTypeValue:"+requestTypeValue);
		System.out.println(OAuthUtils.isEmpty(requestTypeValue));
		if(OAuthUtils.isEmpty(requestTypeValue)){
			System.out.println("张城城参数缺失");
			throw OAuthUtils.handleOAuthProblemException("Missing response_type parameter value");
			
		}
		
		
		final Class<? extends OAuthValidator<HttpServletRequest>> clazz=validators.get(requestTypeValue);
		if(clazz==null){
			System.out.println("张城城参数错误");
			throw OAuthUtils.handleOAuthProblemException("Invalid response_type parameter value");
		}
		
		return OAuthUtils.instantiateClass(clazz);
	}

	public String getState(){
		return getParam(OAuth.OAUTH_STATE);
	}
	
	public String getResponseType(){
		return getParam(OAuth.OAUTH_RESPONSE_TYPE);
	}
	
}
