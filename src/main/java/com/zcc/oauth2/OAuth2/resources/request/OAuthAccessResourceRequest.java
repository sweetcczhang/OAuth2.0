package com.zcc.oauth2.OAuth2.resources.request;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.common.error.OAuthError;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.types.ParameterStyle;
import com.zcc.oauth2.OAuth2.common.message.types.TokenType;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;
import com.zcc.oauth2.OAuth2.common.validators.OAuthValidator;
import com.zcc.oauth2.OAuth2.resources.BearerResourceServer;
import com.zcc.oauth2.OAuth2.resources.ResourceServer;
import com.zcc.oauth2.OAuth2.resources.extractor.TokenExtractor;

public class OAuthAccessResourceRequest {

	private HttpServletRequest request;
	private ParameterStyle[] parameterStyles=new ParameterStyle[]{OAuth.DEFAULT_PARAMETER_STYLE};
	private TokenType[] tokenTypes=new TokenType[]{OAuth.DEFAULT_TOKEN_TYPE};
	private ParameterStyle usedParameterStyle;
	private ResourceServer usedResourceServer;
	
	protected static Map<TokenType,Class> tokens=new HashMap<TokenType,Class>();
	
	private TokenExtractor extractor;
	
	{
		tokens.put(TokenType.BEARER, BearerResourceServer.class);
	}
	
	public OAuthAccessResourceRequest(HttpServletRequest request)
	      throws OAuthSystemException,OAuthProblemException{
		this(request, new TokenType[]{OAuth.DEFAULT_TOKEN_TYPE},new ParameterStyle[]{OAuth.DEFAULT_PARAMETER_STYLE});
		
	}
	
	public OAuthAccessResourceRequest(HttpServletRequest request,ParameterStyle...parameterStyles)
	throws OAuthSystemException,OAuthProblemException{
		this(request,new TokenType[]{OAuth.DEFAULT_TOKEN_TYPE},parameterStyles);
	}
	
	public OAuthAccessResourceRequest(HttpServletRequest request,TokenType...tokenTypes)
	throws OAuthSystemException,OAuthProblemException{
		this(request,tokenTypes,new ParameterStyle[]{OAuth.DEFAULT_PARAMETER_STYLE});
		
	}
	
	
	public OAuthAccessResourceRequest(HttpServletRequest request,TokenType[] tokenTypes ,ParameterStyle[] parameterStyles)
	      throws OAuthSystemException,OAuthProblemException{
		this.request=request;
		this.tokenTypes=tokenTypes;
		this.parameterStyles=parameterStyles;
		this.validate();
	}
	
	public String getAccessToken() throws OAuthSystemException{
		
		return extractor.getAccessToken(request);
	}
	
	private void validate()throws OAuthSystemException,OAuthProblemException{
		
		int foundValidStyles=0;
		boolean lackAuthInfo=false;
		OAuthProblemException ex=null;
		String lackAuthReason="OAuth parameters were not found";
		for(TokenType tokenType : tokenTypes){
			
			ResourceServer resourceServer=instantiateResourceServer(tokenType);
			for(ParameterStyle style : parameterStyles){
				try{
					OAuthValidator validator=resourceServer.instantiateValidator(style);
					validator.validateContentType(request);
					validator.validateMethod(request);
					validator.validateRequiredParameters(request);
					usedParameterStyle=style;
					usedResourceServer=resourceServer;
					foundValidStyles++;
				}catch(OAuthProblemException e){
					if(OAuthUtils.isEmpty(e.getError())){
						lackAuthInfo=true;
						lackAuthReason=e.getDescription();
					}else{
						ex=OAuthProblemException.error(e.getError(),e.getDescription());
					}
					
				}
				
			}
			
		}
		if(foundValidStyles>1){
			throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST,
					"Found more than one mechanism for authenticating client");
		}
		if(ex!=null){
			throw ex;
		}
		
		if(foundValidStyles==0 && lackAuthInfo){
			throw OAuthProblemException.error(null, lackAuthReason);
		}
		
		if(foundValidStyles==0){
			throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST,
					"OAuth parameters were not found");
		}
		
		extractor=usedResourceServer.instantiateExtractor(usedParameterStyle);
		
	}
	public static ResourceServer instantiateResourceServer(TokenType tokenType) throws OAuthSystemException{
		Class clazz=tokens.get(tokenType);
		if(clazz==null){
			throw new OAuthSystemException("Cannot instantiate a resource server.");
		}
		return (ResourceServer)OAuthUtils.instantiateClass(clazz);
	}
	
	
}
