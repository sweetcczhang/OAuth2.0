package com.zcc.oauth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.oauth2.DAO.UserBeanDao;
import com.zcc.oauth2.OAuth2.authorizeServer.issuer.MD5Generator;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;
import com.zcc.oauth2.entity.UserBean;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private  UserBeanDao userDao;
	
	public UserBean createUser(UserBean user){
		//user.getPassword();
		try {
		user.setPassword(new MD5Generator().generateValue(user.getPassword()));
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDao.createUser(user);
	}
	public UserBean updateUser(UserBean user){
		
		return userDao.updateUser(user);
	}
	public void deleteUser(Long id){
		userDao.deleteUser(id);
	}
	public UserBean  findOne(Long id){
		
		return userDao.findOne(id);
	}
	public List<UserBean> findAll(){
		
		return userDao.findAll();
	}
	
	public UserBean findByUsername(String username){
		
		 return userDao.findByUsername(username);
	}
	public UserBean login(String username,String password){
		String newPassword=null;
		try {
			 newPassword=new MD5Generator().generateValue(password);
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userDao.login(username, newPassword);
	}
	
	@Override
	public UserBean findByUid(String uid) {
		// TODO Auto-generated method stub
		
		return userDao.findByUid(uid);
	}

}
