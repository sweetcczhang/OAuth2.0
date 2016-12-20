package com.zcc.oauth2.qq.javabeans;

import java.io.Serializable;

/**
 * 说明： QQ 头像实体类
 * 
 * @author zhengxg @date 2013-9-12
 */
public class Avatar implements Serializable {
	private static final long serialVersionUID = -8402565179459840811L;
	/** 30像素 */
	private String avatarURL30 = "";
	/** 50像素 */
	private String avatarURL50 = "";
	/** 100像素 */
	private String avatarURL100 = "";

	public Avatar(String avatarURL) {
		if (avatarURL.equals("")) {
			return;
		}
		this.avatarURL30 = (avatarURL + "/30");
		this.avatarURL50 = (avatarURL + "/50");
		this.avatarURL100 = (avatarURL + "/100");
	}

	public Avatar(String avatarURL30, String avatarURL50, String avatarURL100) {
		this.avatarURL30 = avatarURL30;
		this.avatarURL50 = avatarURL50;
		this.avatarURL100 = avatarURL100;
	}

	public String getAvatarURL30() {
		return this.avatarURL30;
	}

	public String getAvatarURL50() {
		return this.avatarURL50;
	}

	public String getAvatarURL100() {
		return this.avatarURL100;
	}

	public int hashCode() {
		// int prime = 31;
		int result = 1;
		result = 31 * result + (this.avatarURL30 == null ? 0 : this.avatarURL30.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avatar other = (Avatar) obj;
		if (getAvatarURL30() == null) {
			if (other.getAvatarURL30() != null)
				return false;
		} else if (!getAvatarURL30().equals(other.getAvatarURL30()))
			return false;
		return true;
	}

	public String toString() {
		return "AvatarInfo [figureurl30 : " + getAvatarURL30() + " , " + "figureurl50 : " + getAvatarURL50() + " , "
				+ "figureurl100 : " + getAvatarURL100() + " , " + "]";
	}
}