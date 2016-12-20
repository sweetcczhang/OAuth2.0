package com.zcc.oauth2.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

public class UserBean implements Serializable{
	
	private  Long id;//主键
	@Email(message="用户名填写错误")
	private  String username;//用户名
	@Pattern(regexp="^[0-9a-zA-Z]{6,16}$",message="密码长度必须是6到16位字母数字组合")
	private  String password;//密码
	@Pattern(regexp="^[男女]$",message="性别填写不正确")
	private  String sex;//性别
	
	private  String nickName;//电子邮箱
	
	private  String address;//地址
	
	private  String uid;//用户uid,针对使用第三方登录的用户
//	private  String salt;//加密 盐
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	*/
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		UserBean User = (UserBean) obj;

		if (id != null ? !id.equals(User.id) : User.id != null)
			return false;

		return true;
	}
   
	@Override
	  public int hashCode(){
		   
		   return id!=null ? id.hashCode():0;
	  }
}
