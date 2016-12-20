package com.zcc.oauth2.qq.oauth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.qq.QQConnect;
import com.zcc.oauth2.qq.QQConnectException;
import com.zcc.oauth2.qq.javabeans.AccessToken;
import com.zcc.oauth2.qq.utils.QQConnectConfig;
import com.zcc.oauth2.qq.utils.RandomStatusGenerator;
import com.zcc.oauth2.qq.utils.http.PostParameter;

/**
 * 说明：Oauth类，为了能够兼容系统中有多个域名而重写，覆盖腾讯的部分代码
 * 
 * @author zhengxg @date 2013-9-11
 */
public class Oauth extends QQConnect {

	private static final long serialVersionUID = -7860508274941797293L;

	private String[] extractionAuthCodeFromUrl(String url) throws QQConnectException {
		if (url == null) {
			throw new QQConnectException("you pass a null String object");
		}
		Matcher m = Pattern.compile("code=(\\w+)&state=(\\w+)&?").matcher(url);
		String authCode = "";
		String state = "";
		if (m.find()) {
			authCode = m.group(1);
			state = m.group(2);
		}

		return new String[] { authCode, state };
	}

	public AccessToken getAccessTokenByRequest(ServletRequest request) throws QQConnectException {
		String queryString = ((HttpServletRequest) request).getQueryString();
		if (queryString == null) {
			return new AccessToken();
		}
		String state = (String) ((HttpServletRequest) request).getSession().getAttribute("qq_connect_state");
		if ((state == null) || (state.equals(""))) {
			return new AccessToken();
		}

		String[] authCodeAndState = extractionAuthCodeFromUrl(queryString);
		String returnState = authCodeAndState[1];
		String returnAuthCode = authCodeAndState[0];

		AccessToken accessTokenObj = null;

		if ((returnState.equals("")) || (returnAuthCode.equals(""))) {
			accessTokenObj = new AccessToken();
		} else if (!state.equals(returnState)) {
			accessTokenObj = new AccessToken();
		} else{
			//String domainName = ((HttpServletRequest) request).getServerName();
    		String redirectUrl = QQConnectConfig.getValue("redirect_URL");
			accessTokenObj = new AccessToken(this.client.post(QQConnectConfig.getValue("accessTokenURL"),
					new PostParameter[] { new PostParameter("client_id", QQConnectConfig.getValue("app_ID")),
							new PostParameter("client_secret", QQConnectConfig.getValue("app_KEY")),
							new PostParameter("grant_type", "authorization_code"),
							new PostParameter("code", returnAuthCode),
							new PostParameter("redirect_uri", redirectUrl) },
					Boolean.valueOf(false)));
		}
		return accessTokenObj;
	}

	/** @deprecated */
	public AccessToken getAccessTokenByQueryString(String queryString, String state) throws QQConnectException {
		if (queryString == null) {
			return new AccessToken();
		}
		String[] authCodeAndState = extractionAuthCodeFromUrl(queryString);
		String returnState = authCodeAndState[1];
		String returnAuthCode = authCodeAndState[0];

		AccessToken accessTokenObj = null;

		if ((returnState.equals("")) || (returnAuthCode.equals(""))) {
			accessTokenObj = new AccessToken();
		} else if (!state.equals(returnState)) {
			accessTokenObj = new AccessToken();
		} else
			accessTokenObj = new AccessToken(this.client.post(QQConnectConfig.getValue("accessTokenURL"),
					new PostParameter[] { new PostParameter("client_id", QQConnectConfig.getValue("app_ID")),
							new PostParameter("client_secret", QQConnectConfig.getValue("app_KEY")),
							new PostParameter("grant_type", "authorization_code"),
							new PostParameter("code", returnAuthCode),
							new PostParameter("redirect_uri", QQConnectConfig.getValue("redirect_URI")) },
					Boolean.valueOf(false)));

		return accessTokenObj;
	}

	/** @deprecated */
	public String getAuthorizeURL(String scope, String state) throws Exception {
		return QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
				+ QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri="
				+ QQConnectConfig.getValue("redirect_URI").trim() + "&response_type=" + "code" + "&state=" + state
				+ "&scope=" + scope;
	}

	/** @deprecated */
	// 2013年9月11日15:07:41 注销
	// public String getAuthorizeURL(String state) throws QQConnectException {
	// String scope = QQConnectConfig.getValue("scope");
	// if ((scope != null) && (!scope.equals(""))) {
	// return getAuthorizeURL("code", state, scope);
	// }
	// return QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
	// + QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri="
	// + QQConnectConfig.getValue("redirect_URI").trim() + "&response_type=" + "code" + "&state=" + state;
	// }

	/** @deprecated */
	public String getAuthorizeURLByScope(String scope, ServletRequest request) throws Exception {
		String state = RandomStatusGenerator.getUniqueState();
		((HttpServletRequest) request).setAttribute("qq_connect_state", state);

		return QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
				+ QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri="
				+ QQConnectConfig.getValue("redirect_URI").trim() + "&response_type=" + "code" + "&state=" + state
				+ "&scope=" + scope;
	}

	public String getAuthorizeURL(ServletRequest request) throws Exception {
		String state = RandomStatusGenerator.getUniqueState();
		((HttpServletRequest) request).getSession().setAttribute("qq_connect_state", state);
		String scope = QQConnectConfig.getValue("scope");
		//String domainName = ((HttpServletRequest) request).getServerName();
		//domainName="mfxuan.free.800m.net";
		/*
		 * 测试主机
		 */
		//System.out.println("-----domainName:"+domainName);
		String redirectUrl = QQConnectConfig.getValue("redirect_URL");
		if ((scope != null) && (!scope.equals(""))) {
			return getAuthorizeURL("code", state, scope, redirectUrl);
		}
		return QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
				+ QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri=" + redirectUrl.trim() + "&response_type="
				+ "code" + "&state=" + state;
	}

	// /** @deprecated */
	public String getAuthorizeURL(String response_type, String state, String scope, String redirectUrl)
			throws Exception {
		return QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
				+ QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri=" + redirectUrl.trim() + "&response_type="
				+ response_type + "&state=" + state + "&scope=" + scope;
	}
}