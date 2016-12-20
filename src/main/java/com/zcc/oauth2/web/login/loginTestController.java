package com.zcc.oauth2.web.login;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zcc.oauth2.entity.UserBean;
import com.zcc.oauth2.service.UserService;
/**
 * 
 * @author 张城城
 *
 */
@Controller
public class loginTestController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String  loginTest(HttpServletRequest request){
		
		return "login";
	}
	@RequestMapping(value="/registers", method=RequestMethod.GET)
	public String register(Model model){
		
		if(!model.containsAttribute("userBean"))
		model.addAttribute("userBean", new UserBean());
		return "register";
	}
	
	@RequestMapping(value="/registers", method=RequestMethod.POST)
	public String saveRegister(@Valid @ModelAttribute("userBean") UserBean userBean,BindingResult result,
			Model model)throws NoSuchAlgorithmException{
		if(result.hasErrors()){
		  return register(model);
		}
		userService.createUser(userBean);
		return "redirect:/login";
	}
	
	
	
}
