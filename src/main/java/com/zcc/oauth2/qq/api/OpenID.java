package com.zcc.oauth2.qq.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zcc.oauth2.qq.QQConnect;
import com.zcc.oauth2.qq.QQConnectException;
import com.zcc.oauth2.qq.utils.QQConnectConfig;
import com.zcc.oauth2.qq.utils.http.PostParameter;

/**
 * 
 * 说明： OpenId api
 * 
 * @author zhengxg @date 2013-9-12
 */
public class OpenID extends QQConnect {
	private static final long serialVersionUID = 6913005509508673584L;

	public OpenID(String token) {
		this.client.setToken(token);
	}

	private String getUserOpenID(String accessToken) throws QQConnectException {
		String openid = "";
		String jsonp = this.client.get(QQConnectConfig.getValue("getOpenIDURL"),
				new PostParameter[] { new PostParameter("access_token", accessToken) }).asString();

		Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(jsonp);

		if (m.find())
			openid = m.group(1);
		else {
			throw new QQConnectException("server error!");
		}
		return openid;
	}

	public String getUserOpenID() throws QQConnectException {
		String accessToken = this.client.getToken();
		return getUserOpenID(accessToken);
	}
}