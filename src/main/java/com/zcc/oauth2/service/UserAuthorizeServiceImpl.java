package com.zcc.oauth2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.oauth2.DAO.UserAuthorizeBeanDao;
import com.zcc.oauth2.entity.UserAuthorizeBean;

@Service
public class UserAuthorizeServiceImpl implements UserAuthorizeService{
   
	@Autowired
   private UserAuthorizeBeanDao userAuthorizeBeanDao;
	
   public  UserAuthorizeBean createUserAuthorizeBean(UserAuthorizeBean userAuthorizeBean){   
	   
	      Date date=new Date();
		  SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		  userAuthorizeBean.setCreate_time(sf.format(date));
	   
	   return  userAuthorizeBeanDao.creatUserAuthorizeBean(userAuthorizeBean);
	   
   }
	
	public  List<UserAuthorizeBean> findAll(Long user_id){
		
		
		return userAuthorizeBeanDao.findAll(user_id);
		
	}
	
	public  UserAuthorizeBean  findOne(String appkey,Long user_id){
		
		
		return userAuthorizeBeanDao.findOne(appkey, user_id);
		
	}
	
	public void deleleUserAuthorizeBean(String appkey,Long user_id){
		
		userAuthorizeBeanDao.deleteUserAuthorizeBean(appkey, user_id);
		
	}

	@Override
	public void deleteUserAuthorizaBean(Long id) {
		// TODO Auto-generated method stub
		userAuthorizeBeanDao.deleteUserAuthorizeBean(id);
		
	}
}
