package com.zcc.oauth2.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zcc.oauth2.entity.UserBean;
import com.zcc.oauth2.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginContoller")
	public ModelAndView  checkLogin(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        UserBean userBean=userService.login(username, password);
		if(userBean!=null){
			
			HttpSession session=request.getSession();
			session.setAttribute("userBean1", userBean);
			session.setAttribute("username", username);
			session.setAttribute("nickName",userBean.getNickName());
			session.setAttribute("flag", true);
			mav.setViewName("redirect:/");
			return mav;
		}
        mav.setViewName("login");		
		return mav;
	}
	@RequestMapping("/logout")
	public String  checkLogut(HttpServletRequest request){
		
		   request.getSession().invalidate();
		   
		   
		return "redirect:/";
	}

}
