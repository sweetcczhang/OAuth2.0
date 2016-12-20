package com.zcc.oauth2.service;

import java.util.List;

import com.zcc.oauth2.entity.DeveloperBean;

public interface DeveloperService {
	
	public DeveloperBean createDeveloperBean(DeveloperBean developerBean);
	
	public DeveloperBean updateDeveloperBean(DeveloperBean developerBean);
	
	public List<DeveloperBean> findAll();
	
    public DeveloperBean findDeveloperBeanByUserId(Long userId);

}
