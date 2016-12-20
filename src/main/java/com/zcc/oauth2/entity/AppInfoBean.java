package com.zcc.oauth2.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 张城城
 *
 */
public class AppInfoBean {

    private Long id;
    
    private Long user_id;
    @NotEmpty(message="不能为空")
    private String appName;
    
    private String description;
    @NotEmpty(message="应用地址不能为空")
	@Pattern(regexp="^(http(s)?:\\/\\/)?(www\\.)?[\\w-]+\\.[\\w-]+\\.\\w{2,4}(\\/)?$",message="{url.format.not}")
	private String appUrl;
    @NotEmpty(message="回调地址不能为空")
	@Pattern(regexp="^(http(s)?:\\/\\/)?(www\\.)?[\\w-]+\\.[\\w-]+\\.\\w{2,4}(\\/)?$",message="url地址不正确")
	private String redirectUrl;
	
	private String appId;
	
	private String appSecret;
	
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	

}
