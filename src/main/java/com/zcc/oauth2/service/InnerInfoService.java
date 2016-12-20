package com.zcc.oauth2.service;

import java.util.List;

import com.zcc.oauth2.entity.InnerInfoBean;

public interface InnerInfoService {

	public InnerInfoBean createAppInfoBean(InnerInfoBean appInfoBean);
	
    public InnerInfoBean updateAppInfoBean(InnerInfoBean appInfoBean);
	
	public InnerInfoBean findByAppId(String appId);
	
	public InnerInfoBean findBySecret(String appSecret); 
	
	public InnerInfoBean findById(Long id);
	
	public List<InnerInfoBean> findByUserId(Long userId);
	
	public void deleteAppInfoBean(Long id);
	
	public List<InnerInfoBean>  findAll();
	
}
