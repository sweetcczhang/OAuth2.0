package com.zcc.oauth2.service;

import java.util.List;

import com.zcc.oauth2.entity.UserBean;

public interface UserService {
	
	public UserBean createUser(UserBean user);
	public UserBean updateUser(UserBean user);
	public void deleteUser(Long id);
	public UserBean  findOne(Long id);
	public List<UserBean> findAll();
	public UserBean findByUsername(String username); 
	public UserBean login(String username,String password);
	public UserBean findByUid(String uid);
}
