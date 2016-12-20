package com.zcc.oauth2.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class DeveloperBean {
	
	private Long userId;//用户ID
	@NotEmpty(message="不能为空")
	private String developerType;//开发者类型
	
	private String companyName;//公司名称
	
	private String companyAddress;//公司地址
	@NotEmpty(message="不能为空")
	private String  linkMan;//联系人
	@Email(message="邮箱格式不正确")
	private String  email;//电子邮箱
	
	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message="手机号码格式不正确")
	private String telePhone;//手机号码
	@Pattern(regexp="^[1-9][0-9]{4,}$",message="QQ号码格式不正确")
	private String qq;//QQ号码
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDeveloperType() {
		return developerType;
	}

	public void setDeveloperType(String developerType) {
		this.developerType = developerType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
   
}
