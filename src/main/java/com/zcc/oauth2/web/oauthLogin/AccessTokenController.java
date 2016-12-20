package com.zcc.oauth2.web.oauthLogin;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zcc.oauth2.OAuth2.authorizeServer.issuer.MD5Generator;
import com.zcc.oauth2.OAuth2.authorizeServer.issuer.OAuthIssuer;
import com.zcc.oauth2.OAuth2.authorizeServer.issuer.OAuthIssuerImpl;
import com.zcc.oauth2.OAuth2.authorizeServer.request.OAuthTokenRequest;
import com.zcc.oauth2.OAuth2.authorizeServer.response.OAuthASResponse;
import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.error.OAuthError;
import com.zcc.oauth2.OAuth2.common.error.OAuthErrorCode;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthResponse;
import com.zcc.oauth2.OAuth2.common.message.types.GrantType;
import com.zcc.oauth2.service.Oauth2Service;
import com.zcc.oauth2.service.UserService;
import com.zcc.oauth2.web.ErrorMessage;

@RestController
public class AccessTokenController {
     
	  @Autowired
	  private  Oauth2Service oauth2Service;
	  @Autowired
	  private  UserService   userService;
	  
	  @RequestMapping("/accessToken")
	  public HttpEntity accessToken(HttpServletRequest request)throws URISyntaxException, OAuthSystemException{
		  
		  try{
			     OAuthTokenRequest  oauthRequest=new OAuthTokenRequest(request);
			     
			     if(!oauth2Service.checkClientId(oauthRequest.getClientId())){
			    	 OAuthResponse response=OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
			    			 .setError(OAuthError.TokenResponse.INVALID_CLIENT)
			    			 .setErrorDescription(ErrorMessage.INVALID_MESSAGE)
			    			 .setErrorCode(OAuthErrorCode.TokenResponse.INVALID_CLIENT)
			    			 .buildJSONMessage();
			    	 return new ResponseEntity<>(response.getBody(),HttpStatus.valueOf(response.getResponseStatus()));
			    	 
			     }
			     
			     if(!oauth2Service.checkClientSecret(oauthRequest.getClientSecret())){
			    	 OAuthResponse response=OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
			    			   .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
			    			   .setErrorDescription(ErrorMessage.INVALID_MESSAGE)
			    			   .setErrorCode(OAuthErrorCode.TokenResponse.INVALID_CLIENT)
			    			   .buildJSONMessage();
			    	 return new ResponseEntity<>(response.getBody(),HttpStatus.valueOf(response.getResponseStatus()));
			    	 
			     }
			     
			     String authCode=oauthRequest.getParam(OAuth.OAUTH_CODE);
			     if(oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())){
			           if(!oauth2Service.checkAuthCode(authCode)){
			    	        OAuthResponse response=OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
			    			    .setError(OAuthError.TokenResponse.INVALID_GRANT)
			    			    .setErrorDescription(ErrorMessage.INVALID_MESSAGE)
			    			    .setErrorCode(OAuthErrorCode.TokenResponse.INVALID_GRANT)
			    			    .buildJSONMessage();
			    	        return new ResponseEntity<>(response.getBody(),HttpStatus.valueOf(response.getResponseStatus()));
			    	 
			              }
			     }
			  
			     OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
			     final String accessToken=oauthIssuerImpl.accessToken();
			     oauth2Service.addAccessToken(accessToken, oauth2Service.getUsernameByAuthCode(authCode));
			     
			     OAuthResponse response=OAuthASResponse
			    		 .tokenResponse(HttpServletResponse.SC_OK)
			    		 .setAccessToken(accessToken)
			    		 .setExpiresIn(String.valueOf(oauth2Service.getExpireIn()))
			    		 .buildJSONMessage();
			  
			     System.out.println("responseBody:"+response.getBody());
			  
			  return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
			  
		  }catch(OAuthProblemException e){
			  System.out.println("缺少授权类型");
			   OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
	                    .buildJSONMessage();
	            return new ResponseEntity<>(res.getBody(), HttpStatus.valueOf(res.getResponseStatus()));
			  
		  }
		  
		  
		  
		  
		 
	  }
	
}
