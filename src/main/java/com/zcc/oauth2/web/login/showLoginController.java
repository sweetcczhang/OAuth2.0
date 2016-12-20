package com.zcc.oauth2.web.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.service.UserService;

@Controller
public class showLoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public Object showloginForm(HttpServletRequest request){
		
		return "index";
	}
	
	@RequestMapping("/test")
	public String showAuthorization(HttpServletRequest request){
		
		return "authorization";
	}

}
