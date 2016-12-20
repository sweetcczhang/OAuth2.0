package com.zcc.oauth2.entity;

public class UserAuthorizeBean {
	
	private String appkey;//第三方应用的唯一标识，即appKey
	private Long   User_id;//用户Id，即用户唯一的表示
	private String clientName;//客户端名称
	private String create_time;//授权时间
	private Long id;//主键
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public Long getUser_id() {
		return User_id;
	}
	public void setUser_id(Long user_id) {
		User_id = user_id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	

}
