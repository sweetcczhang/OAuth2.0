package com.zcc.oauth2.web.weiboLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.entity.UserBean;
import com.zcc.oauth2.service.UserService;
import com.zcc.oauth2.weibo.http.AccessToken;
import com.zcc.oauth2.weibo.model.User;
import com.zcc.oauth2.weibo.model.WeiboException;
import com.zcc.oauth2.weibo.util.WeiboConfig;
import com.zcc.oauth2.weibo.weibo4j.Oauth;
import com.zcc.oauth2.weibo.weibo4j.Users;

@Controller
public class weiboController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/happy")
	public  Object weibo(Model model ,HttpServletRequest request){
		
		//WeiboConfig weibo=new WeiboConfig();
		
		Oauth oauth=new Oauth();
		String code=request.getParameter("code");
		System.out.println("code:"+code);
		HttpSession session= request.getSession();
		try{
			AccessToken accessToken=oauth.getAccessTokenByCode(code);
			System.out.println("acessToken:"+accessToken);
			String  access_Token=accessToken.getAccessToken();
			System.out.println("access_Token:"+access_Token);
			String   uid=accessToken.getUid();
			UserBean userBean=userService.findByUid(uid);
			if(userBean!=null){
				
				session.setAttribute("username", userBean.getNickName());
				request.getSession().setAttribute("nickName", userBean.getNickName());
				session.setAttribute("uid", uid);
				session.setAttribute("flag", true);
				userBean=new UserBean();
				Users um=new Users(access_Token);
	          	User user=um.showUserById(uid);
	          	session.setAttribute("avatar",user.getAvatarLarge());
			}else{
			userBean=new UserBean();
			Users um=new Users(access_Token);
          	User user=um.showUserById(uid);
          	String screename=user.getScreenName();
          	userBean.setAddress(user.getLocation());
          	userBean.setSex(user.getGender());
          	userBean.setNickName(user.getScreenName());
          	userBean.setUid(uid);
          	userService.createUser(userBean);
       //   System.out.println("screename:"+screename);
       //   model.addAttribute("screename", screename);
          	session.setAttribute("uid", uid);
          	session.setAttribute("username", screename);
          	request.getSession().setAttribute("nickName", userBean.getNickName());
          	session.setAttribute("flag", true);
          	session.setAttribute("avatar",user.getAvatarLarge());
			}
		}catch(WeiboException e){
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}

}
