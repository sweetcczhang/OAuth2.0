package com.zcc.oauth2.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 张城城
 *
 */
public class ClientBean {
	
	private Long id;//主键
	
	private Long  user_id;//用户id表示客户端是哪个用户创建的
	
	@NotEmpty(message="不能为空")
	private String clientName;//客户端名称
	
	private String description;//应用的描述
	@NotEmpty(message="应用地址不能为空")
	@Pattern(regexp="^(http(s)?:\\/\\/)?(www\\.)?[\\w-]+(:)?[0-9]+\\/\\w{2,10}(\\/)?([\\w-]+)?(\\/)?([\\w-]+)?$",message="{url.format.not}")
	private String webUrl;//应用地址^(http(s)?:\/\/)?(www\.)?[\w-]+\.\w{2,4}(\/)?$
	@NotEmpty(message="开心")
	@Pattern(regexp="^(http(s)?:\\/\\/)?(www\\.)?[\\w-]+(:)?[0-9]+\\/\\w{2,10}(\\/)?([\\w-]+)?(\\/)?([\\w-]+)?$",message="网址格式不正确")
	private String redirectUrl;//应用回调地址
	//"^(http(s)?:\\/\\/)?(www\\.)?[\\w-]+\\.[\\w-]+\\.\\w{2,4}(\\/)?$"
	private String clientId;//客户端id
	
	private String clientSecret;//客户端安全key
	
	private String createTime;//创建的时间
	
	
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirect_Url) {
		this.redirectUrl = redirect_Url;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(this==o) return true;
		if(o==null||getClass()!=o.getClass()) return false;
		
		ClientBean client=(ClientBean)o;
		if(id!=null? !id.equals(client.id):client.id!=null) return false;
		
		return true;
	}
   @Override
   public int hashCode(){
	   
	   return id!=null ? id.hashCode():0;
   }
   

}
