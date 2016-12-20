package com.zcc.oauth2.qq;

import java.io.Serializable;

import com.zcc.oauth2.qq.utils.http.HttpClient;

/**
 * 
 * 说明： QQ链接超类
 * 
 * @author zhengxg @date 2013-9-12
 */
public class QQConnect implements Serializable {
	
	private static final long serialVersionUID = 2403532632395197292L;
	
	protected HttpClient client = new HttpClient();

	protected QQConnect() {
	}

	protected QQConnect(String token, String openID) {
		this.client.setToken(token);
		this.client.setOpenID(openID);
	}

	protected void setToken(String token) {
		this.client.setToken(token);
	}

	protected void setOpenID(String openID) {
		this.client.setOpenID(openID);
	}
}