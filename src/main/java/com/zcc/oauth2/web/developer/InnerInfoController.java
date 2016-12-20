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

import com.zcc.oauth2.entity.InnerInfoBean;
import com.zcc.oauth2.service.InnerInfoService;
import com.zcc.oauth2.service.UserService;
/**
 * 
 * @author 张城城
 *
 */
@Controller
@RequestMapping("/innerInfo")
public class InnerInfoController {

	@Autowired
	private UserService userService;
	@Autowired
	private InnerInfoService innerInfoService;
	

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create_inner", method=RequestMethod.GET)
	public String createInner(Model model){
		if(!model.containsAttribute("innerInfoBean"))
		model.addAttribute("innerInfoBean", new InnerInfoBean());
		model.addAttribute("op", "创建");
		return "form_app";
	}
	
	@RequestMapping(value="/create_inner", method=RequestMethod.POST)
	public String saveInner(@Valid @ModelAttribute("innerInfoBean") InnerInfoBean innerInfoBean,
			BindingResult result,Model model,HttpServletRequest request)throws NoSuchAlgorithmException{
		
		if(result.hasErrors()){
			return createInner(model);
		}
		String uid=(String)request.getSession().getAttribute("uid");
		if(uid!=null){
			
			innerInfoBean.setUser_id(userService.findByUid(uid).getId());
			
		}else{
			
			 String username=(String)request.getSession().getAttribute("username");
			 innerInfoBean.setUser_id(userService.findByUsername(username).getId());
		} 
		
		 innerInfoService.createAppInfoBean(innerInfoBean);
		
		 return "redirect:/change/managerController";
		
	}
	
	@RequestMapping(value="{id}/update", method=RequestMethod.GET)
	public String  showUpdateForm(@PathVariable Long id, Model model){
		model.addAttribute("appInfoBean", innerInfoService.findById(id));
		model.addAttribute("op", "修改");
		return "form_app";
	}
	
	@RequestMapping(value="{id}/update", method=RequestMethod.POST)
	public String updateForm(@ModelAttribute InnerInfoBean innerInfoBean ){
		innerInfoService.updateAppInfoBean(innerInfoBean);
		 
		return "redirect:/change/managerController";
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String showDeleteForm(@PathVariable Long id, Model model){
		model.addAttribute("innerInfoBean", innerInfoService.findById(id));
		model.addAttribute("op", "删除");
		return "form_app";
	}
	@RequestMapping(value="{id}/delete",method=RequestMethod.POST)
	public String deleteInnerInfo(@PathVariable Long id){
		 innerInfoService.deleteAppInfoBean(id);
		return "redirect:/change/managerController";
	}
	
	@RequestMapping(value="{id}/show")
	public String showDetail(@PathVariable Long id, Model model){
		
		model.addAttribute("innerInfoBean", innerInfoService.findById(id));
		return "detail";
	}

}
