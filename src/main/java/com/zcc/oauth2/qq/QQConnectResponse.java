package com.zcc.oauth2.qq;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.zcc.oauth2.qq.utils.http.Response;
import com.zcc.oauth2.weibo.org.json.JSONException;
import com.zcc.oauth2.weibo.org.json.JSONObject;

public class QQConnectResponse implements Serializable {

	private static final long serialVersionUID = 3519962197957449562L;

	public QQConnectResponse() {
	}

	public QQConnectResponse(Response res) {
	}

	protected static String getString(String name, JSONObject json, boolean decode) {
		String returnValue = null;
		try {
			returnValue = json.getString(name);
			if (decode)
				try {
					returnValue = URLDecoder.decode(returnValue, "UTF-8");
				} catch (UnsupportedEncodingException ignore) {
				}
		} catch (JSONException ignore) {
		}
		return returnValue;
	}

	protected static int getInt(String key, JSONObject json) throws JSONException {
		String str = json.getString(key);
		if ((null == str) || ("".equals(str)) || ("null".equals(str))) {
			return -1;
		}
		return Integer.parseInt(str);
	}
}