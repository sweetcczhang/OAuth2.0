package com.zcc.oauth2.web.qqLogin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.qq.oauth.Oauth;

@Controller
public class QQOAuth2Controller {
	
	@RequestMapping("/qqconnect")
	public void  qqOAuth( HttpServletRequest request,HttpServletResponse response) throws IOException, Exception{
		
		 Oauth oauth=new Oauth();
		 
		 response.sendRedirect(oauth.getAuthorizeURL(request));
		
	}

}
