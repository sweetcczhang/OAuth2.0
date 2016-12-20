package com.zcc.oauth2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.oauth2.DAO.AppInfoBeanDao;
import com.zcc.oauth2.entity.AppInfoBean;

@Service
public class AppInfoServiceImpl implements AppInfoService{
    
	@Autowired
	private AppInfoBeanDao appInfoBeanDao;
	
	@Override
	public AppInfoBean createAppInfoBean(AppInfoBean appInfoBean) {
		// TODO Auto-generated method stub
		   appInfoBean.setAppId(UUID.randomUUID().toString());
		   appInfoBean.setAppSecret(UUID.randomUUID().toString());
		   Date date=new Date();
		   SimpleDateFormat fs=new SimpleDateFormat("yyyy-MM-dd");
		   appInfoBean.setCreateTime(fs.format(date));
		return appInfoBeanDao.createAppInfoBean(appInfoBean);
	}

	@Override
	public AppInfoBean updateAppInfoBean(AppInfoBean appInfoBean) {
		// TODO Auto-generated method stub
		return appInfoBeanDao.updateAppInfoBean(appInfoBean);
	}

	@Override
	public AppInfoBean findByAppId(String appId) {
		// TODO Auto-generated method stub
		return appInfoBeanDao.findByAppId(appId);
	}

	@Override
	public AppInfoBean findBySecret(String appSecret) {
		// TODO Auto-generated method stub
		return appInfoBeanDao.findBySecret(appSecret);
	}

	@Override
	public AppInfoBean findById(Long id) {
		// TODO Auto-generated method stub
		return appInfoBeanDao.findById(id);
	}

	@Override
	public List<AppInfoBean> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return appInfoBeanDao.findByUserId(userId);
	}

	@Override
	public void deleteAppInfoBean(Long id) {
		// TODO Auto-generated method stub
		appInfoBeanDao.deleteAppInfoBean(id);
		
	}

	@Override
	public List<AppInfoBean> findAll() {
		// TODO Auto-generated method stub
		return appInfoBeanDao.findAll();
	}

}
