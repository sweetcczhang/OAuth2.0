package com.zcc.oauth2.web.changer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;
import com.zcc.oauth2.entity.DeveloperBean;
import com.zcc.oauth2.entity.UserBean;
import com.zcc.oauth2.service.AppInfoService;
import com.zcc.oauth2.service.ClientService;
import com.zcc.oauth2.service.DeveloperService;
import com.zcc.oauth2.service.InnerInfoService;
import com.zcc.oauth2.service.UserService;

@Controller
@RequestMapping("/change")
public class ChangerController {
    
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private UserService  userService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private InnerInfoService innerInfoService;
	
	@RequestMapping("/indexController")
	 public String changeToIndex(HttpServletRequest request){
		 
		 return "redirect:/";
	 }
	
	
	@RequestMapping("/documentController")
	public String changeToDocuments(HttpServletRequest request){
		
		return "documents";
	}
	
	
	@RequestMapping("/managerController")
	public String changeToManager(HttpServletRequest request,Model model){
       
		if(!checkLogin(request)){
            	return "login";
            }
		 Long userId;
		  String uid=(String)request.getSession().getAttribute("uid");
		  if(uid!=null){
			  userId=userService.findByUid(uid).getId();
		  }else{
			   String username=(String)request.getSession().getAttribute("username");
			   userId=userService.findByUsername(username).getId();
		  }
            DeveloperBean developerBean=developerService.findDeveloperBeanByUserId(userId);
            System.out.println(developerBean);
           if(developerBean==null){  
        	 return "redirect:/developer/developer_registers";
            }
	     
            model.addAttribute("developerBean", developerBean);
            model.addAttribute("clientList", clientService.findByUserId(userId));
            model.addAttribute("appInfoList", appInfoService.findByUserId(userId));
            model.addAttribute("innerInfoList", innerInfoService.findByUserId(userId));
		
		return "manager";
	}
	
	
	@RequestMapping("/accountCenterController")
	public String ChangeToAccountCenter(Model model, HttpServletRequest request){
		
		if(!checkLogin(request)){
        	return "login";
        }
		
		String uid=(String)request.getSession().getAttribute("uid");
		 Long  userId;
		   if(uid!=null){
			   model.addAttribute("userBean", userService.findByUid(uid));
			   userId=userService.findByUid(uid).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
			   
		   }else{
			   
			   String username=(String)request.getSession().getAttribute("username");
			   model.addAttribute("userBean", userService.findByUsername(username));
			   userId=userService.findByUsername(username).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
		   }
		
		return "account_center";
		
	}
	
	@RequestMapping("/accountCenterController1")
	public String ChangeToAccountCenter1(Model model, HttpServletRequest request){
		
		if(!checkLogin(request)){
        	return "login";
        }
		
		String uid=(String)request.getSession().getAttribute("uid");
		 Long  userId;
		   if(uid!=null){
			   model.addAttribute("userBean", userService.findByUid(uid));
			   userId=userService.findByUid(uid).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
			   
		   }else{
			   
			   String username=(String)request.getSession().getAttribute("username");
			   model.addAttribute("userBean", userService.findByUsername(username));
			   userId=userService.findByUsername(username).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
		   }
		
		return "account_center1";
		
	}
	
	
	
	
	
	@RequestMapping(value="/updateUserInfo")
	public String updateUserInfo(@ModelAttribute UserBean userBean){
		   
		userService.updateUser(userBean);
		
		return "redirect:/change/accountCenterController";
	}
	
	
	@RequestMapping("/accountCenterController2")
	public String ChangeToAccountCenter2(Model model, HttpServletRequest request){
		
		if(!checkLogin(request)){
        	return "login";
        }
		
		String uid=(String)request.getSession().getAttribute("uid");
		 Long  userId;
		   if(uid!=null){
			   model.addAttribute("userBean", userService.findByUid(uid));
			   userId=userService.findByUid(uid).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
			   
		   }else{
			   
			   String username=(String)request.getSession().getAttribute("username");
			   model.addAttribute("userBean", userService.findByUsername(username));
			   userId=userService.findByUsername(username).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
		   }
		
		return "account_center2";
		
	}
	
	
	
	
	
	
	@RequestMapping("/updateDveloper")
	public String updateDevelpoper(@ModelAttribute DeveloperBean developerBean ){
		
		developerService.updateDeveloperBean(developerBean);
		System.out.println("开发者资料怎么没有修改呢？");
	
		return "redirect:/change/accountCenterController3";
	}
	
	
	@RequestMapping("/accountCenterController3")
	public String ChangeToAccountCenter3(Model model, HttpServletRequest request){
		
		if(!checkLogin(request)){
        	return "login";
        }
		
		String uid=(String)request.getSession().getAttribute("uid");
		 Long  userId;
		   if(uid!=null){
			   model.addAttribute("userBean", userService.findByUid(uid));
			   userId=userService.findByUid(uid).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
			   
		   }else{
			   
			   String username=(String)request.getSession().getAttribute("username");
			   model.addAttribute("userBean", userService.findByUsername(username));
			   userId=userService.findByUsername(username).getId();
			   model.addAttribute("developerBean", developerService.findDeveloperBeanByUserId(userId));
		   }
		
		return "account_center3";
		
	}
	
	
	
	
	
	public  boolean checkLogin(HttpServletRequest request){
		
		   String username=(String)request.getSession().getAttribute("username");
		   if(OAuthUtils.isEmpty(username)){
			   
			   return false;
		   }
		return true;
	}
	
	
}
