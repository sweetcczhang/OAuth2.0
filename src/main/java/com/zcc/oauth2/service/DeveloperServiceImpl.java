package com.zcc.oauth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.oauth2.DAO.DeveloperBeanDao;
import com.zcc.oauth2.entity.DeveloperBean;

@Service
public class DeveloperServiceImpl implements DeveloperService{
    
	@Autowired
	private DeveloperBeanDao developerBeanDao;
	
	@Override
	public DeveloperBean createDeveloperBean(DeveloperBean developerBean) {
		// TODO Auto-generated method stub
		return developerBeanDao.createDeveloperBean(developerBean);
	}

	@Override
	public DeveloperBean updateDeveloperBean(DeveloperBean developerBean) {
		// TODO Auto-generated method stub
		return developerBeanDao.updateDeveloperBean(developerBean);
	}

	@Override
	public List<DeveloperBean> findAll() {
		// TODO Auto-generated method stub
		return developerBeanDao.findAll();
	}

	@Override
	public DeveloperBean findDeveloperBeanByUserId(Long userId) {
		// TODO Auto-generated method stub
		return developerBeanDao.getDeveloperBeanByUserId(userId);
	}

}
