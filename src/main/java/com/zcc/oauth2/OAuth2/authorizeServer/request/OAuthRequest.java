package com.zcc.oauth2.OAuth2.authorizeServer.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.validators.OAuthValidator;


/**
 * 
 * @author 张城城
 *
 */
public abstract class OAuthRequest {
	
	private Logger log=LoggerFactory.getLogger(OAuthRequest.class);
	
	protected HttpServletRequest request;
	protected OAuthValidator<HttpServletRequest> validator;
	protected Map<String,Class<? extends OAuthValidator<HttpServletRequest>>> validators=
			new HashMap<String, Class<? extends OAuthValidator<HttpServletRequest>>>();

	public OAuthRequest(HttpServletRequest request) throws OAuthSystemException,OAuthProblemException{
		this.request=request;
		validate();
	}
	public OAuthRequest(){
		
	}
	protected void validate()throws OAuthSystemException,OAuthProblemException{
		//try{
			System.out.println("哈哈哈哈哈大学33");
			validator=initValidator();
			validator.validateMethod(request);
			validator.validateContentType(request);
			validator.validateRequiredParameters(request);
			validator.validateClientAuthenticationCredentials(request);
			System.out.println("哈哈哈哈哈大学22");
		
	    /*  }catch(OAuthProblemException e){
			try{
				System.out.println("哈哈哈哈哈大学11");
				String redirectUri=request.getParameter(OAuth.OAUTH_REDIRECT_URI);
				if(!OAuthUtils.isEmpty(redirectUri)){
					e.setRedirectUri(redirectUri);
				}
				
				throw e;
				
			}catch(Exception ex){
				System.out.println("哈哈哈哈哈大学11");
				if(log.isDebugEnabled()){
					log.debug("Cannot read redirect_url from the request: {}",new String[] {ex.getMessage()});
				}
			}
			
			
		}*/
	}
	/**
	 * 
	 * @return
	 * @throws OAuthProblemException
	 * @throws OAuthSystemException
	 */
	protected abstract OAuthValidator<HttpServletRequest> initValidator() throws OAuthProblemException,
	OAuthSystemException;
	
	public String getParam(String name){
		return request.getParameter(name);
	}
	
	public String getClientId(){
		String[] creds=OAuthUtils.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION));
		if(creds!=null){
			return creds[0];
		}
		return getParam(OAuth.OAUTH_CLIENT_ID);
	}
	
	public String getRedirectURI(){
		
		return getParam(OAuth.OAUTH_REDIRECT_URI);
		
	}
	/**
	 * 
	 * @return
	 */
	public String getClientSecret(){
		String[] creds=OAuthUtils.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION));
		if(creds!=null){
			return creds[1];
		}
		return getParam(OAuth.OAUTH_CLIENT_SECRET);
	}
	
	/**
	 * 
	 */
	public boolean isClientAuthHeaderUsed(){
		return OAuthUtils.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION))!=null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<String> getScopes(){
		String scopes=getParam(OAuth.OAUTH_SCOPE);
		return OAuthUtils.decodeScopes(scopes);
	}
}
