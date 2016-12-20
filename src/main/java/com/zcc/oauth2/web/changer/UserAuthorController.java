package com.zcc.oauth2.web.changer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.service.UserAuthorizeService;
import com.zcc.oauth2.service.UserService;

@Controller
@RequestMapping("/userAuthor")
public class UserAuthorController {
    
	
	@Autowired
	private UserAuthorizeService   userAuthorizeService; 
	@Autowired
	private UserService userService;
	
	@RequestMapping("show")     
	public String showUserAuthor(HttpServletRequest request,Model model){
		   
		
		   String uid=(String)request.getSession().getAttribute("uid");
		   if(uid!=null){
			   
			  return "error";
			   
		   }else{
		   String username=(String)request.getSession().getAttribute("username");
		   model.addAttribute("userBean", userService.findByUsername(username));
		   Long userId=userService.findByUsername(username).getId();
		   model.addAttribute("userAuthorizeBean",userAuthorizeService.findAll(userId));
		   }
		   return"manageAuthorize";
	}
	
	@RequestMapping(value="{id}/delete")
	public String  manageUserAuthor(@PathVariable Long id){
		
		   userAuthorizeService.deleteUserAuthorizaBean(id);
		
		   return "redirect:/userAuthor/show";
	}
	
}
