package com.zcc.oauth2.DAO;

import java.util.List;

import com.zcc.oauth2.entity.UserAuthorizeBean;

public interface UserAuthorizeBeanDao {
	
	
	public UserAuthorizeBean creatUserAuthorizeBean(UserAuthorizeBean userAuthorizeBean);
	
	public UserAuthorizeBean updateUserAuthorizeBean(UserAuthorizeBean userAuthorizeBean);
	
	public void deleteUserAuthorizeBean(String appkey,Long user_id);
	
	public List<UserAuthorizeBean> findAll(Long user_id);
	
	public UserAuthorizeBean findOne(String appkey,Long user_id);
	
	public void deleteUserAuthorizeBean(Long id);

	
	
}
