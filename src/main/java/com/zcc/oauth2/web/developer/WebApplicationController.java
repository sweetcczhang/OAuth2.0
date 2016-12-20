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
import com.zcc.oauth2.entity.ClientBean;
import com.zcc.oauth2.entity.InnerInfoBean;
import com.zcc.oauth2.service.AppInfoService;
import com.zcc.oauth2.service.ClientService;
import com.zcc.oauth2.service.InnerInfoService;
import com.zcc.oauth2.service.UserService;
/**
 * 
 * @author 张城城
 *
 */
@Controller
@RequestMapping("/application")
public class WebApplicationController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ClientService clientService;
	
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create_web",method=RequestMethod.GET)
	public String createWebApplication(Model model){
		
		if(!model.containsAttribute("clientBean")){
			model.addAttribute("clientBean", new ClientBean());
			
		}
		model.addAttribute("op", "创建");
		return "form_web";
	}
	
	@RequestMapping(value="/create_web", method=RequestMethod.POST)
	public String saveWebApplication(HttpServletRequest request,@Valid @ModelAttribute("clientBean") ClientBean clientBean, 
	
			BindingResult result,Model model)throws NoSuchAlgorithmException{
		  
		   
		  if(result.hasErrors()){
			  
		    	return createWebApplication(model);
		    }
		    String uid=(String)request.getSession().getAttribute("uid");   
		    if(uid!=null){
		    	clientBean.setUser_id(userService.findByUid(uid).getId());
		    	
		    }else{
		    	String username=(String)request.getSession().getAttribute("username");
				clientBean.setUser_id(userService.findByUsername(username).getId());
		    }
		   clientService.createClient(clientBean);
		   
		return "redirect:/change/managerController";
	}
	
	@RequestMapping(value="{id}/update" , method=RequestMethod.GET)
	public String showWebUpdateForm(@PathVariable Long id, Model model){
		     model.addAttribute("clientBean", clientService.findOne(id));
		     model.addAttribute("op", "修改");
		return "form_web";
	}
	
	@RequestMapping(value="{id}/update", method=RequestMethod.POST)
	public String updateWeb(@ModelAttribute ClientBean clientBean){
		   clientService.updateClient(clientBean);
		return "redirect:/change/managerController";
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String showwebDeleteForm(@PathVariable Long id, Model model){
		  model.addAttribute("clientBean", clientService.findOne(id));
		  model.addAttribute("op", "删除");
		return "form_web";
	}
	@RequestMapping(value="{id}/delete", method=RequestMethod.POST)
	public String  deleteWeb(@PathVariable Long id){
		  clientService.deleteClient(id);
		return "redirect:/change/managerController";
	}
	
	@RequestMapping(value="{id}/show")
	public String showDetail(@PathVariable Long id, Model model){
		
		model.addAttribute("clientBean", clientService.findOne(id));
		
		return "detail_web";
	}
	
}
