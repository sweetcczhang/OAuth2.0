package com.zcc.oauth2.DAO;

import java.util.List;

import com.zcc.oauth2.entity.DeveloperBean;

public interface DeveloperBeanDao {
	
	public  DeveloperBean createDeveloperBean(DeveloperBean developerBean);
	
	public  DeveloperBean updateDeveloperBean(DeveloperBean developerBean);
	
    public DeveloperBean getDeveloperBeanByUserId(Long userId);
    
    public List<DeveloperBean> findAll();
}
