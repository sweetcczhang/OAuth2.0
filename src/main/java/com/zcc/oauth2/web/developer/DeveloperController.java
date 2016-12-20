package com.zcc.oauth2.web.developer;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.entity.DeveloperBean;
import com.zcc.oauth2.service.DeveloperService;
import com.zcc.oauth2.service.UserService;

@Controller
@RequestMapping("/developer")
public class DeveloperController {
	
	@Autowired
	private DeveloperService developerService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/developer_registers")
	public String  createDeveloper(Model model){
		
		if(!model.containsAttribute("developerBean"))
		    model.addAttribute("developerBean", new DeveloperBean());
		
		return "developer_register";
	}
	@RequestMapping("/save_developer")
	public String saveDeveloper(@Valid @ModelAttribute("developerBean") DeveloperBean developerBean,
			BindingResult result,Model model,HttpServletRequest request)throws NoSuchAlgorithmException{
		
		    if(result.hasErrors()){
		    	return createDeveloper(model);
		    }
		 
		String uid=(String)request.getSession().getAttribute("uid");
		if(uid!=null){
			developerBean.setUserId(userService.findByUid(uid).getId());
		}else{
			String username=(String)request.getSession().getAttribute("username"); 
			developerBean.setUserId(userService.findByUsername(username).getId());
		}
		
		developerService.createDeveloperBean(developerBean);
		
		model.addAttribute("developerBean",developerBean);
		System.out.println("---------已经执行到这了-------------");
		return "redirect:/change/managerController";
	}
	
	

}
