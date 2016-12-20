package com.zcc.oauth2.web.developer;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zcc.oauth2.entity.AppInfoBean;
import com.zcc.oauth2.service.AppInfoService;
import com.zcc.oauth2.service.UserService;

@Controller
@RequestMapping("/appInfo")
public class AppInfoController {

	@Autowired
	private UserService userService;
	@Autowired
	private AppInfoService  appInfoService;
	
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create_app", method=RequestMethod.GET)
	public String createApplication(Model model){
		
		if(!model.containsAttribute("appInfoBean")){
		  model.addAttribute("appInfoBean", new AppInfoBean());
		}
		
		model.addAttribute("op", "&nbsp;&nbsp;创建&nbsp;&nbsp;");
		
		return "form";
	}
	
	@RequestMapping(value="/create_app", method=RequestMethod.POST)
	public String saveApplication(@Valid @ModelAttribute("appInfoBean") AppInfoBean appInfoBean,BindingResult result,Model model,HttpServletRequest request)throws NoSuchAlgorithmException{
		
		if(result.hasErrors()){
			return createApplication(model);
		}
		
		String uid=(String)request.getSession().getAttribute("uid");
		 if(uid!=null){
			 appInfoBean.setUser_id(userService.findByUid(uid).getId());
		 }else{
			 
         	 String username=(String)request.getSession().getAttribute("username");
			 appInfoBean.setUser_id(userService.findByUsername(username).getId());
		 }
		 
		 appInfoService.createAppInfoBean(appInfoBean);
		 return "redirect:/change/managerController";
	}
	
	@RequestMapping(value="{id}/update", method=RequestMethod.GET)
	public String showUpdateApplication(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("appInfoBean", appInfoService.findById(id));
		model.addAttribute("op", "修改");
		return "form";
	}
	
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String updateAppInfo(@ModelAttribute AppInfoBean appInfoBean){
		 appInfoService.updateAppInfoBean(appInfoBean);
		 return "redirect:/change/managerController";
	}
	@RequestMapping(value="{id}/delete", method=RequestMethod.GET)
	public String showDeleteForm(@PathVariable Long id, Model model){
		model.addAttribute("appInfoBean", appInfoService.findById(id));
		model.addAttribute("op", "删除");
		return "form";
	}
	@RequestMapping(value="{id}/delete", method=RequestMethod.POST)
	public String deleteAppInfo(@PathVariable Long id){
		  appInfoService.deleteAppInfoBean(id);
		 return "redirect:/change/managerController";
	}
	
	@RequestMapping(value="{id}/show")
	public String showDetail(@PathVariable Long id,Model model){
		model.addAttribute("appInfoBean",appInfoService.findById(id));
		
		return "detail_app";
	}
	
}
