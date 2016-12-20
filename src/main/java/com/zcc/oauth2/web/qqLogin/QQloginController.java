package com.zcc.oauth2.web.qqLogin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.oauth2.entity.UserBean;
import com.zcc.oauth2.qq.QQConnectException;
import com.zcc.oauth2.qq.api.OpenID;
import com.zcc.oauth2.qq.api.qzone.UserInfo;
import com.zcc.oauth2.qq.javabeans.AccessToken;
import com.zcc.oauth2.qq.javabeans.qzone.UserInfoBean;
import com.zcc.oauth2.qq.oauth.Oauth;
import com.zcc.oauth2.service.UserService;

@Controller
public class QQloginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/qqhandle")
	public String  qqAuthrization(HttpServletRequest request) throws QQConnectException{
		
			
			AccessToken accessTokenObj=(new Oauth()).getAccessTokenByRequest(request);
			String accessToken=null;
			String openID=null;
			Long expiredIn=0L;
			
			if(accessTokenObj.getAccessToken().equals("")){
				System.out.println("没有响应参数");
			}else{
				
				accessToken=accessTokenObj.getAccessToken();
				expiredIn=accessTokenObj.getExpireIn();
				OpenID openIDObj=new OpenID(accessToken);
				openID=openIDObj.getUserOpenID();
				System.out.println("openID:"+openID);
				UserBean userBean=userService.findByUid(openID);
				if(userBean!=null){
					request.getSession().setAttribute("username", userBean.getNickName());
					request.getSession().setAttribute("nickName", userBean.getNickName());
					request.getSession().setAttribute("flag", true);
					request.getSession().setAttribute("uid",openID);
					UserInfo userInfoObj=new UserInfo(accessToken,openID);
				    UserInfoBean userInfoBean=userInfoObj.getUserInfo();
				    request.getSession().setAttribute("avatar",userInfoBean.getAvatar().getAvatarURL50());
				}else{
				
					UserInfo userInfoObj=new UserInfo(accessToken,openID);
				    UserInfoBean userInfoBean=userInfoObj.getUserInfo();
				    userBean=new UserBean();
				    userBean.setNickName(userInfoBean.getNickname());
				    userBean.setAddress(userInfoBean.getProvince());
				    userBean.setSex(userInfoBean.getGender());
				    userBean.setUid(openID);
				    userService.createUser(userBean);
				    request.getSession().setAttribute("username", userInfoBean.getNickname());
				    request.getSession().setAttribute("nickName", userBean.getNickName());
				    request.getSession().setAttribute("flag", true);
				    request.getSession().setAttribute("uid",openID);
				    request.getSession().setAttribute("avatar",userInfoBean.getAvatar().getAvatarURL50());
				}
				//userBean.
				
				
			}
			
			
		
		return "index";
		
	}

}
