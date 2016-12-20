package com.zcc.oauth2.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.oauth2.entity.InnerInfoBean;
import com.zcc.oauth2.DAO.InnerInfoBeanDao;

@Service
public class InnerInfoServiceImpl implements InnerInfoService{
   
	@Autowired
	private InnerInfoBeanDao  InnerInfoBeanDao;
	
	@Override
	public InnerInfoBean createAppInfoBean(InnerInfoBean appInfoBean) {
		// TODO Auto-generated method stub
		appInfoBean.setAppId(UUID.randomUUID().toString());
		appInfoBean.setAppSecret(UUID.randomUUID().toString());
		   Date date=new Date();
		   SimpleDateFormat fs=new SimpleDateFormat("yyyy-MM-dd");
		 appInfoBean.setCreateTime(fs.format(date));
		return InnerInfoBeanDao.createAppInfoBean(appInfoBean);
	}
	
	@Override
	public InnerInfoBean updateAppInfoBean(InnerInfoBean appInfoBean) {
		// TODO Auto-generated method stub
		return InnerInfoBeanDao.updateAppInfoBean(appInfoBean);
	}

	@Override
	public InnerInfoBean findByAppId(String appId) {
		// TODO Auto-generated method stub
		return InnerInfoBeanDao.findByAppId(appId);
	}

	@Override
	public InnerInfoBean findBySecret(String appSecret) {
		// TODO Auto-generated method stub
		return InnerInfoBeanDao.findBySecret(appSecret);
	}

	@Override
	public InnerInfoBean findById(Long id) {
		// TODO Auto-generated method stub
		return InnerInfoBeanDao.findById(id);
	}

	@Override
	public List<InnerInfoBean> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return InnerInfoBeanDao.findByUserId(userId);
	}

	@Override
	public void deleteAppInfoBean(Long id) {
		// TODO Auto-generated method stub
		InnerInfoBeanDao.deleteAppInfoBean(id);
	}

	@Override
	public List<InnerInfoBean> findAll() {
		// TODO Auto-generated method stub
		return InnerInfoBeanDao.findAll();
	}

	

}
