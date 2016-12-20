package com.zcc.oauth2.service;

import java.util.List;

import com.zcc.oauth2.entity.UserAuthorizeBean;

public interface UserAuthorizeService {

	public  UserAuthorizeBean createUserAuthorizeBean(UserAuthorizeBean userAuthorizeBean);
	
	public  List<UserAuthorizeBean> findAll(Long user_id);
	
	public  UserAuthorizeBean   findOne(String appkey,Long user_id);
	
	public void deleleUserAuthorizeBean(String appkey,Long user_id);
	
	public void deleteUserAuthorizaBean(Long id);
	
}
