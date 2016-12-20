package com.zcc.oauth2.qq.javabeans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zcc.oauth2.qq.QQConnectException;
import com.zcc.oauth2.qq.QQConnectResponse;
import com.zcc.oauth2.qq.utils.http.Response;
import com.zcc.oauth2.weibo.org.json.JSONException;
import com.zcc.oauth2.weibo.org.json.JSONObject;

/**
 * 说明： AccessToken 请求处理类
 * 
 * @author zhengxg @date 2013-9-12
 */
@SuppressWarnings("unused")
public class AccessToken extends QQConnectResponse implements Serializable {

	private static final long serialVersionUID = -5499186506535919974L;

	private String accessToken = "";
	private String expireIn = "";
	private String refreshToken = "";
	private String openid;

	public AccessToken() {
	}

	public AccessToken(Response res) throws QQConnectException {
		super(res);
		JSONObject json = null;
		String result = "";
		try {
			json = res.asJSONObject();
			try {
				this.accessToken = json.getString("access_token");
				this.expireIn = json.getString("expires_in");
				this.refreshToken = json.getString("refresh_token");
				this.openid = json.getString("openid");
			} catch (JSONException je) {
				throw new QQConnectException(je.getMessage() + ":" + json.toString(), je);
			}
		} catch (Exception e) {
			result = res.asString();
			Matcher m = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(result);
			if (m.find()) {
				this.accessToken = m.group(1);
				this.expireIn = m.group(2);
				this.refreshToken = m.group(3);
			} else {
				Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(result);
				if (m2.find()) {
					this.accessToken = m2.group(1);
					this.expireIn = m2.group(2);
				}
			}
		}
	}

	AccessToken(String res) throws QQConnectException, JSONException {
		JSONObject json = new JSONObject(res);
		this.accessToken = json.getString("access_token");
		this.expireIn = json.getString("expires_in");
		this.refreshToken = json.getString("refresh_token");
		this.openid = json.getString("openid");
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public long getExpireIn() {
		return Long.valueOf(this.expireIn).longValue();
	}

	public int hashCode() {
		// int prime = 31;
		int result = 1;
		result = 31 * result + (this.accessToken == null ? 0 : this.accessToken.hashCode());

		result = 31 * result + (this.expireIn == null ? 0 : this.expireIn.hashCode());

		result = 31 * result + (this.refreshToken == null ? 0 : this.refreshToken.hashCode());

		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessToken other = (AccessToken) obj;
		if (this.accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!this.accessToken.equals(other.accessToken))
			return false;
		if (this.expireIn == null) {
			if (other.expireIn != null)
				return false;
		} else if (!this.expireIn.equals(other.expireIn))
			return false;
		if (this.refreshToken == null) {
			if (other.refreshToken != null)
				return false;
		} else if (!this.refreshToken.equals(other.refreshToken))
			return false;
		return true;
	}

	public String toString() {
		return "AccessToken [accessToken=" + this.accessToken + ", expireIn=" + this.expireIn + "]";
	}
}
