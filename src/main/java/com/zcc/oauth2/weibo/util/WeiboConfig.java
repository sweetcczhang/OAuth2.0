package com.zcc.oauth2.weibo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WeiboConfig {
	public WeiboConfig(){}
	private static Properties props = new Properties(); 
	static{
		try {
			String temp = WeiboConfig.class.getClassLoader().getResource("")
					.toString().substring(6);
			String path = temp.substring(0, 3) + "/" + temp.substring(3);
            String filePath=path+"config.properties";
            System.out.println("filePath:"+filePath);
            System.out.println("Thread.currentThread:"+Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
