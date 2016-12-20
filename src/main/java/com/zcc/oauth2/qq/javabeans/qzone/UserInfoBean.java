package com.zcc.oauth2.qq.javabeans.qzone;

import java.io.Serializable;

import com.zcc.oauth2.qq.QQConnectException;
import com.zcc.oauth2.qq.QQConnectResponse;
import com.zcc.oauth2.qq.javabeans.Avatar;
import com.zcc.oauth2.weibo.org.json.JSONException;
import com.zcc.oauth2.weibo.org.json.JSONObject;

/**
 * 说明： 用户信息实体类
 * 
 * @author zhengxg @date 2013-9-12
 */
public class UserInfoBean extends QQConnectResponse implements Serializable {
	private static final long serialVersionUID = 5606709876246698659L;
	/** 头像信息, 30/50/100 */
	private Avatar avatar = new Avatar("");
	/** QQ昵称 */
	private String nickname;
	/** 性别: 男/女 */
	private String gender;
	/**  省份  */
	private String province;
	

	/** 不常用属性 *************** */
	private boolean vip; // QQ会员
	private int level; // QQ空间等级
	private boolean yellowYearVip; // QQ空间黄钻
	private int ret; // 查询返回状态
	private String msg; // 错误信息

	/** 不常用属性 *************** */

	public String getNickname() {
		return this.nickname;
	}

	public String getGender() {
		return this.gender;
	}

	public boolean isVip() {
		return this.vip;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isYellowYearVip() {
		return this.yellowYearVip;
	}

	public int getRet() {
		return this.ret;
	}

	public String getMsg() {
		return this.msg;
	}

	public String getProvince() {
		return province;
	}

	public UserInfoBean(JSONObject json) throws QQConnectException {
		init(json);
	}

	private void init(JSONObject json) throws QQConnectException {
		if (json != null) {
			try{
				
			
			this.ret = json.getInt("ret");
			if (0 != this.ret) {
				this.msg = json.getString("msg");
			} else {
				this.msg = "";
				this.nickname = json.getString("nickname");
				this.province=json.getString("province");
				this.gender = json.getString("gender");
				this.vip = (json.getInt("vip") == 1);
				this.avatar = new Avatar(json.getString("figureurl"), json.getString("figureurl_1"),
						json.getString("figureurl_2"));
				this.level = json.getInt("level");
				this.yellowYearVip = (json.getInt("is_yellow_year_vip") == 1);
			}
			}catch(JSONException jsone){
				throw new QQConnectException(jsone.getMessage() + ":" + json.toString(), jsone);
				
			}
		}
	}

	public int hashCode() {
		// int prime = 31;
		int result = 1;
		result = 31 * result + (this.nickname == null ? 0 : this.nickname.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfoBean other = (UserInfoBean) obj;
		if (this.nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!this.nickname.equals(other.nickname))
			return false;
		return true;
	}

	public String toString() {
		return "UserInfo [nickname : " + this.nickname + " , " + "figureurl30 : " + this.avatar.getAvatarURL30()
				+ " , " + "figureurl50 : " + this.avatar.getAvatarURL50() + " , " + "figureurl100 : "
				+ this.avatar.getAvatarURL100() + " , " + "gender : " + this.gender + " , " + "vip : " + this.vip
				+ " , " + "level : " + this.level + " , " + "isYellowYeaarVip : " + this.yellowYearVip + "]";
	}
}