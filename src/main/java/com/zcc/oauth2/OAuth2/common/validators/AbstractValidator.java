package com.zcc.oauth2.OAuth2.common.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;

public abstract class AbstractValidator<T extends HttpServletRequest> implements OAuthValidator<T> {
      
	protected List<String> requiredParams=new ArrayList<String>();
	protected Map<String,String[]>optionalParams=new HashMap<String,String[]>();
	protected List<String> notAllowedParams=new ArrayList<String>();
	protected boolean enforceClientAuthentication;
	
	//验证http方法是否正确
	@Override
	public void validateMethod(T request) throws OAuthProblemException{
		if(!request.getMethod().equals(OAuth.HttpMethod.POST)){
			throw OAuthUtils.handleOAuthProblemException("Method not set to POST.");
		}
	}
	
   //检验ContentType类型是否正确
	@Override
	public void validateContentType(T request)throws OAuthProblemException{
		String contentType=request.getContentType();
		final String expectedContentType=OAuth.ContentType.URL_ENCODED;
		if(!OAuthUtils.hasContentType(contentType, expectedContentType)){
			throw OAuthUtils.handleBadContentTypeException(expectedContentType);
		}
	}
	
	//检验必传参数是否有问题
	 @Override
	 public void validateRequiredParameters(T request)throws OAuthProblemException{
		 final Set<String> missingParameters=new HashSet<String>();
		 for(String requiredParam : requiredParams){
			 String val=request.getParameter(requiredParam);
			 if(OAuthUtils.isEmpty(val)){
				 missingParameters.add(requiredParam);
			 }
		 }
		 if(!missingParameters.isEmpty()){
			 throw OAuthUtils.handleMissingParameters(missingParameters);
		 }
	 }
	 
	 //检验可选参数是否正确
	 @Override
	 public void validateOptionalParameters(T request) throws OAuthProblemException{
		 final Set<String>missingParameters=new HashSet<String>();
		 for(Map.Entry<String, String[]> requiredParam : optionalParams.entrySet()){
			 final String paramName=requiredParam.getKey();
			 String val=request.getParameter(paramName);
			 if(!OAuthUtils.isEmpty(val)){
				 String[] dependentParams=requiredParam.getValue();
				 if(!OAuthUtils.hasEmptyValues(dependentParams)){
					 for(String dependentParam : dependentParams){
						 val=request.getParameter(dependentParam);
						 if(OAuthUtils.isEmpty(val)){
							 missingParameters.add(dependentParam);
						 }
						 
					 }
				 }
			 }
			 if(!missingParameters.isEmpty()){
				 throw OAuthUtils.handleMissingParameters(missingParameters);
			 }
			 
		 }
	 }
	 
	 //检验不允许参数
	 @Override
	 public void validateNotAllowedParameters(T request) throws OAuthProblemException{
		 List<String> notAllowedParameters=new ArrayList<String>();
		 for(String requiredParam : notAllowedParams){
			 String val=request.getParameter(requiredParam);
			 if(!OAuthUtils.isEmpty(val)){
				 notAllowedParameters.add(requiredParam);
			 }
		 }
		 if(!notAllowedParameters.isEmpty()){
			 throw OAuthUtils.handleNotAllowedParametersOAuthException(notAllowedParameters);
		 }
	 }
	 //检验客户端证书是否正确
	 @Override
	public void validateClientAuthenticationCredentials(T request)throws OAuthProblemException{
		if(enforceClientAuthentication){
			Set<String>missingParameters=new HashSet<String>();
			String clientAuthHeader=request.getHeader(OAuth.HeaderType.AUTHORIZATION);
			String[] clientCreds=OAuthUtils.decodeClientAuthenticationHeader(clientAuthHeader);
			if(clientCreds==null||OAuthUtils.isEmpty(clientCreds[0])||OAuthUtils.isEmpty(clientCreds[1])){
				if(OAuthUtils.isEmpty(request.getParameter(OAuth.OAUTH_CLIENT_ID))){
					missingParameters.add(OAuth.OAUTH_CLIENT_ID);
				}
				if(OAuthUtils.isEmpty(request.getParameter(OAuth.OAUTH_CLIENT_SECRET))){
					missingParameters.add(OAuth.OAUTH_CLIENT_SECRET);
				}
			}
			if(!missingParameters.isEmpty()){
				throw OAuthUtils.handleMissingParameters(missingParameters);
			}
		}
	}
	 
	 //检验所有的类型是否正确
	public void performAllValidations(T request) throws OAuthProblemException{
		this.validateContentType(request);
		this.validateMethod(request);
		this.validateClientAuthenticationCredentials(request);
		this.validateNotAllowedParameters(request);
		this.validateOptionalParameters(request);
		this.validateRequiredParameters(request);
	}
	  
	 
	 
}
