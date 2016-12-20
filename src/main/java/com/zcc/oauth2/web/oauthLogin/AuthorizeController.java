package com.zcc.oauth2.web.oauthLogin;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zcc.oauth2.OAuth2.authorizeServer.issuer.MD5Generator;
import com.zcc.oauth2.OAuth2.authorizeServer.issuer.OAuthIssuerImpl;
import com.zcc.oauth2.OAuth2.authorizeServer.request.OAuthAuthzRequest;
import com.zcc.oauth2.OAuth2.authorizeServer.response.OAuthASResponse;
import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.error.OAuthError;
import com.zcc.oauth2.OAuth2.common.error.OAuthErrorCode;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.OAuth2.common.message.OAuthResponse;
import com.zcc.oauth2.OAuth2.common.message.types.ResponseType;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;
import com.zcc.oauth2.entity.UserAuthorizeBean;
import com.zcc.oauth2.entity.UserBean;
import com.zcc.oauth2.service.ClientService;
import com.zcc.oauth2.service.Oauth2Service;
import com.zcc.oauth2.service.UserAuthorizeService;
import com.zcc.oauth2.service.UserService;
import com.zcc.oauth2.web.ErrorMessage;


@Controller
public class AuthorizeController {
	
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private Oauth2Service oauth2Service;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAuthorizeService   userAuthorizeService;
	@SuppressWarnings("rawtypes")
	@RequestMapping("/authorize")
	public Object Oauth2authorize(Model model, HttpServletRequest request)throws URISyntaxException, OAuthSystemException{
		ModelAndView mav = new ModelAndView();
		try{
			
			
			
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
			System.out.println("clientId:"+oauthRequest.getClientId());
			if(!oauth2Service.checkClientId(oauthRequest.getClientId())){
				
				OAuthResponse  response=OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.CodeResponse.INVALID_CLIENT)
                        .setErrorDescription(ErrorMessage.INVALID_MESSAGE)
                        .setErrorCode(OAuthErrorCode.CodeResponse.INVALID_CLIENT)
                        .buildJSONMessage();
				
				return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
			}
			if(!oauth2Service.checkRedirectUri(oauthRequest.getRedirectURI())){
				
				return new ResponseEntity<>("OAuth callback url is error!!!", HttpStatus.NOT_FOUND);
			}
			
			  if(check(request)){
				  if(!login(request)){
					   model.addAttribute("client",clientService.findByClientId(oauthRequest.getClientId()));
					  return "oauth2login";
				  }
			  }
			  System.out.println("clientId:");
			  if(checkAuthorization(request,oauthRequest)){
				 if(authrizationApp(request,oauthRequest))
					 model.addAttribute("client",clientService.findByClientId(oauthRequest.getClientId()));
					 return "authorization";
				  
			  }
			      
			    String username=(String)request.getSession().getAttribute("username");
			    String authorizationCode = null;
			    System.out.println("clientId:"+username);
	            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
	            System.out.println("responseType:"+responseType);
	            if (responseType.equals(ResponseType.CODE.toString())) {
	            	System.out.println("dddddd:");
	                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
	                authorizationCode = oauthIssuerImpl.authorizationCode();
	                oauth2Service.addAuthCode(authorizationCode, username);
	            }

	            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
	                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
	            builder.setCode(authorizationCode);          
	            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);
	            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();
	            HttpHeaders headers = new HttpHeaders();
	            headers.setLocation(new URI(response.getLocationUri()));
	            System.out.println("LocationUri："+response.getLocationUri());
	         //   System.out.println("ResponseEntity:"+new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus())));
	            
	           mav.addObject(OAuth.OAUTH_CODE, authorizationCode);
	    	   mav.setViewName("redirect:"+redirectURI);
	    		System.out.println(redirectURI);
	    		return mav;
	        //  return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
	            
		}catch(OAuthProblemException e){
			  
			   System.out.println("aaaaaaaa:");
			   String redirectUri =request.getParameter("redirect_uri");
			   System.out.println("redirectUri:"+redirectUri);
	            if (OAuthUtils.isEmpty(redirectUri)) {
	               
	                return new ResponseEntity<>("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
	            }

	            e.setRedirectUri(redirectUri);
	            final OAuthResponse response =
	                    OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
	                            .error(e).setErrorUri(redirectUri).buildJSONMessage();
	           // HttpHeaders headers = new HttpHeaders();
	            //headers.setLocation(new URI(response.getLocationUri()));
	            //System.out.println("headers:"+headers);
	            System.out.println("headers:"+HttpStatus.valueOf(response.getResponseStatus()));
	            return new ResponseEntity<>(response.getBody(),HttpStatus.valueOf(response.getResponseStatus()));
		}
			
		
	}
	
	public	boolean check( HttpServletRequest request){
		   
	String username= (String)request.getSession().getAttribute("username");
	    if(!StringUtils.isEmpty(username))
	    {
	    	return false;
	    }
	    	return true;
	}
	
	public boolean  checkAuthorization(HttpServletRequest request,OAuthAuthzRequest oauthRequest){
		 String client_id= request.getParameter("client_id");
		 UserBean userBean=(UserBean)request.getSession().getAttribute("userBean");
		 if(userAuthorizeService.findOne(client_id, userBean.getId())!=null){
			
			 
			 return false;
		}
		 
		 String flag=request.getParameter("authorizeFlag");
		 
		 if(flag!=null){
	    	 
	    	// UserBean userBean=(UserBean)request.getSession().getAttribute("userBean");
	    	 UserAuthorizeBean userAuthorizeBean=new UserAuthorizeBean();
	    	 userAuthorizeBean.setAppkey(oauthRequest.getClientId());
	    	 userAuthorizeBean.setUser_id(userBean.getId());
	    	 userAuthorizeBean.setClientName(clientService.findByClientId(oauthRequest.getClientId()).getClientName());
	    	 userAuthorizeBean.setDescription(clientService.findByClientId(oauthRequest.getClientId()).getDescription());
	    	 userAuthorizeService.createUserAuthorizeBean(userAuthorizeBean);
	    	 
	    	 return false;
	     }
		 
		 
		return true;
	}
	
	public boolean  authrizationApp(HttpServletRequest request,OAuthAuthzRequest oauthRequest){
	     String flag=request.getParameter("authorizeFlag");
	     if(flag!=null){
	    	/* 
	    	 UserBean userBean=(UserBean)request.getSession().getAttribute("userBean");
	    	 UserAuthorizeBean userAuthorizeBean=new UserAuthorizeBean();
	    	 userAuthorizeBean.setAppkey(oauthRequest.getClientId());
	    	 userAuthorizeBean.setUser_id(userBean.getId());
	    	 userAuthorizeBean.setClientName(clientService.findByClientId(oauthRequest.getClientId()).getClientName());
	    	 userAuthorizeService.createUserAuthorizeBean(userAuthorizeBean);
	    	 */
	    	 return false;
	     }
		
		return true;
	}
	
	public boolean login(HttpServletRequest request){
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
		UserBean userBean=userService.login(username, password);
		 if(userBean!=null)
		 {
			 HttpSession session= request.getSession();
			 session.setAttribute("username", username);
			 session.setAttribute("userBean", userBean);
			 return true;
		 }

		return false;
			
	}
	
}
