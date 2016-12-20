package com.zcc.oauth2.qq.api.qzone;

import com.zcc.oauth2.qq.QQConnect;
import com.zcc.oauth2.qq.QQConnectException;
import com.zcc.oauth2.qq.javabeans.qzone.UserInfoBean;
import com.zcc.oauth2.qq.utils.QQConnectConfig;
import com.zcc.oauth2.qq.utils.http.PostParameter;

/**
 * 
 * 说明： QQ用户信息 api
 * 
 * @author zhengxg @date 2013-9-12
 */
public class UserInfo extends QQConnect {
	private static final long serialVersionUID = -6124397423510235640L;

	public UserInfo(String token, String openID) {
		super(token, openID);
	}

	private UserInfoBean getUserInfo(String openid) throws QQConnectException {
		return new UserInfoBean(
				this.client.get(
						QQConnectConfig.getValue("getUserInfoURL"),
						new PostParameter[] { new PostParameter("openid", openid),
								new PostParameter("oauth_consumer_key", QQConnectConfig.getValue("app_ID")),
								new PostParameter("access_token", this.client.getToken()),
								new PostParameter("format", "json") }).asJSONObject());
	}

	public UserInfoBean getUserInfo() throws QQConnectException {
		return getUserInfo(this.client.getOpenID());
	}
}