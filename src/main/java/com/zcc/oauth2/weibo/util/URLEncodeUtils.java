package com.zcc.oauth2.weibo.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.BitSet;
/**
 * @author sinaWeibo
 * 
 */
public class URLEncodeUtils {

	static BitSet dontNeedEncoding;

	static {


		dontNeedEncoding = new BitSet(256);
		int i;
		for (i = 'a'; i <= 'z'; i++) {
			dontNeedEncoding.set(i);
		}
		for (i = 'A'; i <= 'Z'; i++) {
			dontNeedEncoding.set(i);
		}
		for (i = '0'; i <= '9'; i++) {
			dontNeedEncoding.set(i);
		}
		dontNeedEncoding.set(' '); /*
									 * encoding a space to a + is done in the
									 * encode() method
									 */
		dontNeedEncoding.set('-');
		dontNeedEncoding.set('_');
		dontNeedEncoding.set('.');
		dontNeedEncoding.set('*');

		dontNeedEncoding.set('+');
		dontNeedEncoding.set('%');

	}

	/**
	 * 判断段落文本是否被urlencode过
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isURLEncoded(String str) {
		if (str==null||"".equals(str)) {
			return false;
		}
		char[] chars = str.toCharArray();
		boolean containsPercent = false;
		for (char c : chars) {
			if (Character.isWhitespace(c)) {
				return false;
			}
			if (!dontNeedEncoding.get(c)) {
				return false;
			}
			if(c == '%'){
				containsPercent = true;
			}
		}
		if(!containsPercent){
			return false;
		}
		return true;
	}

	public static final String encodeURL(String str) {
		try {
			return URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	public static final String decodeURL(String str) {
		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
