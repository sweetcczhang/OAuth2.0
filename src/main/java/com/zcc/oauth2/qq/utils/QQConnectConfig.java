package com.zcc.oauth2.qq.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 说明： QQ配置文件初始类
 * 
 * @author zhengxg @date 2013-9-12
 */
public class QQConnectConfig {

	private static Properties props = new Properties();

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}

	public static String getScheme() {
		String flag = getValue("useSSL");
		return flag.equals("true") ? "https://" : "http://";
	}

	public static String getRedirectUrl(String domainName) {
		String overwriteUrl = getValue("overwrite_redirect_URL");
		String redirectUrl = getScheme() + domainName + overwriteUrl;
		return redirectUrl;
	}

	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("qqconnectconfig.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}