package com.zcc.oauth2.web.oauthLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zcc.oauth2.OAuth2.authorizeServer.response.OAuthASResponse;
import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.error.OAuthError;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthResponse;
import com.zcc.oauth2.OAuth2.common.message.types.ParameterStyle;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;
import com.zcc.oauth2.OAuth2.resources.request.OAuthAccessResourceRequest;
import com.zcc.oauth2.OAuth2.resources.response.OAuthRSResponse;
import com.zcc.oauth2.entity.UserBean;
import com.zcc.oauth2.service.Oauth2Service;
import com.zcc.oauth2.service.UserService;
import com.zcc.oauth2.web.ErrorMessage;

@RestController
public class UserResourceController {
     @Autowired
	private Oauth2Service oauth2Service;
    @Autowired
    private UserService  userService;
	@RequestMapping("/userInfo")
	public HttpEntity  userinformation(HttpServletRequest request)throws OAuthSystemException{
		
		try{
			 OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
			 
			 String accessToken=oauthRequest.getAccessToken();
			 if(!oauth2Service.checkAccessToken(accessToken)){
				 
				 OAuthResponse oauthResponse=OAuthASResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						 .setRealm("")
						 .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
						 .buildHeaderMessage();
				 HttpHeaders headers = new HttpHeaders();
	             headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				 return new ResponseEntity<>(headers,HttpStatus.UNAUTHORIZED);
			 }
			 String username=oauth2Service.getUsernameByAccessToken(accessToken);
			 
			 UserBean userbean=userService.findByUsername(username);
			 
			// OAuthRSResponse responses=(OAuthRSResponse) OAuthRSResponse.status(HttpServletResponse.SC_OK).setParam("userName", "admin").setParam("sex", "man")
			//		 .buildJSONMessage();
		    OAuthResponse responses=OAuthResponse.status(HttpServletResponse.SC_OK)
		    		.setParam("userName",userbean.getUsername())
		    		.setParam("sex",userbean.getSex())
		    		.setParam("nickName", userbean.getNickName())
		    		.setParam("address", userbean.getAddress())
		    		.setParam("id", String.valueOf(userbean.getId()))
				    .buildJSONMessage();
		//	 String usernames="admin";
			 // OAuthUtils.percentEncode(usernames);
			 
			 
			 return new ResponseEntity<>(OAuthUtils.percentEncode(responses.getBody()),HttpStatus.OK);
			
		}catch(OAuthProblemException e){
			
			 String errorCode = e.getError();
	            if (OAuthUtils.isEmpty(errorCode)) {
	                OAuthResponse oauthResponse = OAuthRSResponse
	                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
	                        .setRealm(ErrorMessage.RESOURCE_SERVER_NAME)
	                        .buildHeaderMessage();

	                HttpHeaders headers = new HttpHeaders();
	                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
	                return new ResponseEntity<Object>(headers, HttpStatus.UNAUTHORIZED);
	            }

	            OAuthResponse oauthResponse = OAuthRSResponse
	                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
	                    .setRealm(ErrorMessage.RESOURCE_SERVER_NAME)
	                    .setError(e.getError())
	                    .setErrorDescription(e.getDescription())
	                    .setErrorUri(e.getUri())
	                    .buildHeaderMessage();

	            HttpHeaders headers = new HttpHeaders();
	            headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
	            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
			
		}
		
		
	}
	
	
	
	
}
