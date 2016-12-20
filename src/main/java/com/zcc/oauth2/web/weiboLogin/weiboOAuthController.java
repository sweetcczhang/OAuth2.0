package com.zcc.oauth2.web.weiboLogin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.weibo.model.WeiboException;
import com.zcc.oauth2.weibo.weibo4j.Oauth;

@Controller
public class weiboOAuthController {

	
	@RequestMapping("/xweibo")
	public void weiboAuthorize(HttpServletRequest request ,HttpServletResponse response)throws WeiboException, IOException{
		
		Oauth  oauth=new Oauth();
		//BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		
	 	response.sendRedirect(oauth.authorize("code"));
	}
	
	
}
