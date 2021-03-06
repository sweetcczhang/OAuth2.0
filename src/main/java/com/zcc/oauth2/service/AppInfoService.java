package com.zcc.oauth2.service;

import java.util.List;

import com.zcc.oauth2.entity.AppInfoBean;

public interface AppInfoService {
	
public AppInfoBean createAppInfoBean(AppInfoBean appInfoBean);
	
	public AppInfoBean updateAppInfoBean(AppInfoBean appInfoBean);
	
	public AppInfoBean findByAppId(String appId);
	
	public AppInfoBean findBySecret(String appSecret); 
	
	public AppInfoBean findById(Long id);
	
	public List<AppInfoBean> findByUserId(Long userId);
	
	public void deleteAppInfoBean(Long id);
	
	public List<AppInfoBean>  findAll();

}
