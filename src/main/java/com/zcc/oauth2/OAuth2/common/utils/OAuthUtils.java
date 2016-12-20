package com.zcc.oauth2.OAuth2.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.error.OAuthError;
import com.zcc.oauth2.OAuth2.common.error.OAuthErrorCode;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;

public class OAuthUtils {
	private static final String ENCODING="UTF-8";
    private static final String PARAMETER_SEPARATOR="&";
    private static final String NAME_VALUE_SEPARATOR = "=";
    
    public static final String AUTH_SCHEME =OAuth.OAUTH_HEADER_NAME;
    private static final Pattern OAUTH_HEADER = Pattern.compile("\\s*(\\w*)\\s+(.*)");
    private static final Pattern NVP = Pattern.compile("(\\S*)\\s*\\=\\s*\"([^\"]*)\"");

    public static final String MULTIPART = "multipart/";

    private static final String DEFAULT_CONTENT_CHARSET = ENCODING;
    
    
   
    /**
     * 把参数格式化，即把参数格式化成
     * client_id="ddddd"&client_secret="xxxxxx"&name="xxxxx"这个形式
     * @param parameters
     * @param encoding
     * @return
     */
    public static String format(
    		final Collection<? extends Map.Entry<String, Object>> parameters,
    				final String encoding){
    	    final StringBuilder  result=new StringBuilder();
    	    for(final Map.Entry<String, Object> parameter : parameters){
    	    	String value=parameter.getValue()==null? null : String.valueOf(parameter.getValue());
    	    	if(!OAuthUtils.isEmpty(parameter.getKey())
    	    			&&!OAuthUtils.isEmpty(value)){
    	    		final String encodedName=encode(parameter.getKey(),encoding);
    	    		final String encodeValue=value!=null ? encode(value,encoding) : "";
    	    		if(result.length()>0){
    	    			result.append(PARAMETER_SEPARATOR);
    	    		}
    	    		   result.append(encodedName);
    	    		   result.append(NAME_VALUE_SEPARATOR);
    	    		   result.append(encodeValue);
    	    	}
    	    }
    		
    	return result.toString();
    	
    }
    /**
     * 把字符串编码，默认的编码方式是UTF-8
     * @param content(内容)
     * @param encoding(编码方式)
     * @return
     */
    private static String encode(final String content,final String encoding){
    	   try{
    		   return URLEncoder.encode(content,encoding !=null? encoding : "UTF-8");
    	   }catch(UnsupportedEncodingException problem){
    		   throw new IllegalArgumentException(problem);
    	   }
    }
     /**
      * 把输入流转化成字符串
      * @param is
      * @return
      * @throws IOException
      */
    public static String saveStreamAsString(InputStream is)throws IOException{
    	
    	return toString(is,ENCODING);
    }
    /**
     * 把输入流转化成字符串
     * @param is
     * @param defaultCharset
     * @return
     * @throws IOException
     */
    public static String toString(
    		final InputStream is, final String defaultCharset)throws IOException{
    	 if(is==null){
    		  throw new IllegalArgumentException("InputStream may not be null");
    	 }
    	 String charset=defaultCharset;
    	 if(charset==null){
    		 charset=DEFAULT_CONTENT_CHARSET;
    	 }
    	 Reader reader=new InputStreamReader(is,charset);
    	 StringBuilder sb=new StringBuilder();
    	 int l;
    	 try{
    		 char[] tmp=new char[4096];
    		 while((l=reader.read(tmp)) !=-1){
    			 sb.append(tmp,0,l);
    		 }
    		 
    	 }finally{
    		 reader.close();
    		 
    	 }
   	
    	return sb.toString();
    }
    /**
     * 处理OAuthProblemException类型的异常
     * @param message错误消息描述
     * @return
     */
  public static OAuthProblemException handleOAuthProblemException(String message){
	  
      System.out.println("傻逼啊怎么回事啊");
	  return OAuthProblemException.error(OAuthError.CodeResponse.INVALID_REQUEST)
	        		.description(message)
	        		.errorCode(OAuthErrorCode.CodeResponse.INVALID_REQUEST);
	  
  }
    /**
     * 处理参数缺失错误
     * @param missingParams缺失的参数
     * @return OAuthProblemException
     */
   public static OAuthProblemException handleMissingParameters(Set<String>missingParams){
	        StringBuffer sb=new StringBuffer("Missing parameters:");
	        if(!OAuthUtils.isEmpty(missingParams)){
	        	for(String missingParam : missingParams){
	        		sb.append(missingParam).append(" ");
	        	}
	        }
	   return handleOAuthProblemException(sb.toString().trim());
   }
    /**
     * 处理不正确的请求类容类型异常
     * @param expectedContentType
     * @return
     */
   public static OAuthProblemException handleBadContentTypeException(String expectedContentType){
	        StringBuilder errorMsg=new StringBuilder("Bad request content type. Excepting:").append(expectedContentType);
	        return handleOAuthProblemException(errorMsg.toString());
   }
    /**
     * 处理不允许出现参数异常的请求
     * @param notAllowedParams
     * @return
     */
   public static OAuthProblemException handleNotAllowedParametersOAuthException(
		           List<String> notAllowedParams){
	      StringBuffer sb=new StringBuffer("Not allowed parameters: ");
	      if(notAllowedParams!=null){
	    	  for(String notAllowed : notAllowedParams){
	    		  sb.append(notAllowed).append(" ");
	    	  }
	      }
	   return handleOAuthProblemException(sb.toString().trim());
   }
    
   /**
    * 解码规格化参数
    * @param form
    * @return 返回Map类型的数据
    */
   public static Map<String, Object> decodeForm(String form){
	   Map<String, Object> params=new HashMap<String,Object>();
	   if(!OAuthUtils.isEmpty(form)){
		    for(String nvp: form.split("\\&")){
		    	int equals=nvp.indexOf('=');
		    	String name,value;
		    	if(equals<0){
		    		name=decodePercent(nvp);
		    		value=null;
		    	}else{
		    		name=decodePercent(nvp.substring(0,equals));
		    		value=decodePercent(nvp.substring(equals+1));
		    	}
		    	params.put(name , value);
		    }
	   }
	       return params;
   }
   /**
    * 如果Content-Type header 已经被编码会返回true
    * @param contentType
    * @return
    */
   public static boolean isFormEncoded(String contentType){
	   if(contentType==null){
		   return false;
	   }
	   int semi=contentType.indexOf(";");
	   if(semi>=0){
		   contentType=contentType.substring(0,semi);
	   }
	   return OAuth.ContentType.URL_ENCODED.equalsIgnoreCase(contentType.trim());
   }
   /**
    * 解码：把字符串解码
    * @param s
    * @return
    */
   public static String decodePercent(String s){
	   try{
		   return URLDecoder.decode(s,ENCODING);
	   }catch(java.io.UnsupportedEncodingException wow){
		   throw new RuntimeException(wow.getMessage(),wow);
	   }
   }
   /**
    * 构造一个以&分割的字符串
    * @param values
    * @return
    */
   public static String percentEncode(Iterable values){
	   
	   StringBuilder p=new StringBuilder();
	   for(Object v : values){
		   String stringvalue=toString(v);
		   if(!isEmpty(stringvalue)){
			   if(p.length()>0){
				   p.append("&");
			   }
			   p.append(OAuthUtils.percentEncode(toString(v)));
		   }
		 
	   }
	   return p.toString();
   }
   /**
    * 编码字符串s并把特殊字符替换
    * @param s
    * @return
    */
   public static String percentEncode(String s){
	   if(s==null){
		   return "";
	   }
	   try{
		   return URLEncoder.encode(s,ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
		   
	   }catch(UnsupportedEncodingException wow){
		   
		   throw new RuntimeException(wow.getMessage(), wow);
	   }
   }
   /**
    * 把object类型转换成字符串
    * @param form
    * @return
    */
   private static final String toString(Object form){
	   return (form==null) ? null : form.toString();
   }
   /**
    * 判断集合是否为空
    * @param missingParams
    * @return
    */
   private static boolean isEmpty(Set<String> missingParams){
	    if(missingParams==null||missingParams.size()==0){
	    	return true;
	    }
	    return false;
   }
   /**
    * 实例化类
    * @param clazz
    * @return
    * @throws OAuthSystemException
    */
 public static <T> T instantiateClass(Class<T> clazz)throws OAuthSystemException{
	 
	 return instantiateClassWithParameters(clazz,null,null);
 }
  /**
   * 实例化带参数的类
   * @param clazz
   * @param paramsTypes
   * @param paramValues
   * @return
   * @throws OAuthSystemException
   */
    public static <T> T instantiateClassWithParameters(Class<T> clazz,Class<?>[] paramsTypes,
    		                                           Object[] paramValues)throws OAuthSystemException{
    	try{
    		if(paramsTypes!=null&&paramValues!=null){
    			if(!(paramsTypes.length==paramValues.length)){
    				throw new IllegalArgumentException("Number of types and values must be equal");
    			}
    			
    			if(paramsTypes.length==0&&paramValues.length==0){
    				return clazz.newInstance();
    			}
    			 Constructor<T> clazzConstructor = clazz.getConstructor(paramsTypes);
                 return clazzConstructor.newInstance(paramValues);
    		}
    		return clazz.newInstance();
    	}catch(NoSuchMethodException e){
    		throw new OAuthSystemException(e);
    	}catch(InstantiationException e){
    		throw new OAuthSystemException(e);
    	}catch(IllegalAccessException e){
    		throw new OAuthSystemException(e);
    	}catch(InvocationTargetException e){
    		throw new OAuthSystemException(e);
    	}
    }
 /**
  * 获取AuthHeader类容
  * @param authHeader
  * @return
  */
    public static String getAuthHeaderField(String authHeader){
    	if(authHeader!=null){  		
    		Matcher m=OAUTH_HEADER.matcher(authHeader);
    		if(m.matches()){
        		if(AUTH_SCHEME.equalsIgnoreCase(m.group(1))){
        			return m.group(2);
        		}
        	}
    	}
    	return null;
    }
    /**
     * 解码OAuthHeader
     * @param header
     * @return
     */
 public static Map<String, String> decodeOAuthHeader(String header){
	    Map<String, String> headerValues=new HashMap<String, String>();
	    if(header!=null){
	    	Matcher m=OAUTH_HEADER.matcher(header);
	    	if(m.matches()){
	    		if(AUTH_SCHEME.equalsIgnoreCase(m.group(1))){
	    			for(String nvp:m.group(2).split("\\s*,\\s*")){
	    			 m=NVP.matcher(nvp);
	    			 if(m.matches()){
	    				 String name=decodePercent(m.group(1));
	    				 String value=decodePercent(m.group(2));
	    				 headerValues.put(name, value);
	    			 }
	    			}
	    		}
	    	}
	    }
	    return headerValues;
 }
   /**
    * 解码客户端AuthenticationHeader
    * @param authenticationHeader
    * @return
    */
   public static String[] decodeClientAuthenticationHeader(String authenticationHeader){
	   if(authenticationHeader==null||"".equals(authenticationHeader)){
		     return null;
	   }
	   String[] tokens=authenticationHeader.split(" ");
	   if(tokens.length!=2){
		   return null;
	   }
	   if(tokens[0]!=null&&!"".equals(tokens[0])){
		   String authType=tokens[0];
		   if(!authType.equalsIgnoreCase("basic")){
			   return null;
		   }  
	   }
	   if(tokens[1]!=null&&!"".equals(tokens[1])){
		   String encodedCreds=tokens[1];
		   String decodedCreds=new String(Base64.decodeBase64(encodedCreds));
		   if(decodedCreds.contains(":")&&decodedCreds.split(":").length==2){
			   String[] creds=decodedCreds.split(":");
			   if(!OAuthUtils.isEmpty(creds[0])&&!OAuthUtils.isEmpty(creds[1])){
				   return decodedCreds.split(":");
			   }
		   }
	   }
	   return null;
   }
 /**
  * 编码OAuthHeader
  * @param entries
  * @return
  */
 public static String encodeOAuthHeader(Map<String,Object> entries){
	 StringBuffer sb=new StringBuffer();
	 sb.append(OAuth.OAUTH_HEADER_NAME).append(" ");
	 if(entries.get("realm")!=null){
		 String value=String.valueOf(entries.get("realm"));
		 if(!OAuthUtils.isEmpty(value)){
			 sb.append("realm=\"");
			 sb.append(value);
			 sb.append("\",");
		 }
		 entries.remove("realm");
	 }
	 for(Map.Entry<String, Object> entry:entries.entrySet()){
		 String value=entry.getValue()==null? null : String.valueOf(entry.getValue());
		 if(!OAuthUtils.isEmpty(entry.getKey())&&!OAuthUtils.isEmpty(value)){
			 
			 sb.append(entry.getKey());
			 sb.append("=\"");
			 sb.append(value);
			 sb.append("\",");
		 }
	 }
	  return sb.substring(0,sb.length()-1);
 }
 /**
  * 编码AuthorizationBearerHeader
  * @param entries
  * @return
  */
 public static String encodeAuthorizationBearerHeader(Map<String,Object>entries){
	 StringBuffer sb=new StringBuffer();
	 sb.append(OAuth.OAUTH_HEADER_NAME).append(" ");
	 for(Map.Entry<String, Object>entry : entries.entrySet()){
		 String value=entry.getValue()==null ? null : String.valueOf(entry.getValue());
		 if(!OAuthUtils.isEmpty(entry.getKey())&&!OAuthUtils.isEmpty(value)){
			 sb.append(value);
		 }
	 }
	 return sb.toString();
 }
 
   
   public static boolean isEmpty(String value){
	   return value==null||"".equals(value);
   }
   
   
   public static boolean hasEmptyValues(String[] array){
	   if(array==null||array.length==0){
		   return true;
	   }
	   for(String s : array){
		   if(isEmpty(s)){
			   return true;
		   }
	   }
	   return false;
   }
   
   public static String getAuthzMethod(String header){
	   
	   if(header!=null){
		   Matcher m=OAUTH_HEADER.matcher(header);
		   if(m.matches()){
			   return m.group(1);
		   }
	   }
	   return null;
   }
   
   public static Set<String> decodeScopes(String s){
	   Set<String> scopes=new HashSet<String>();
	   if(!OAuthUtils.isEmpty(s)){
		   StringTokenizer tokenizer=new StringTokenizer(s, " ");
		   while(tokenizer.hasMoreElements()){
			   scopes.add(tokenizer.nextToken());
		   }
	   }
	   return scopes;
   }
   
   public static String encodeScopes(Set<String> s){
	      StringBuffer scopes=new StringBuffer();
	      for(String scope : s){
	    	  scopes.append(scope).append(" ");
	      }
	      return scopes.toString().trim();
   }
    
   public static boolean isMultipart(HttpServletRequest request){
	   if(!"post".equals(request.getMethod().toLowerCase())){
		   return false;
	   }
	   
	   String contentType=request.getContentType();
	   if(contentType==null){
		   return false;
	   }
	   
	   if(contentType.toLowerCase().startsWith(MULTIPART)){
		   return true;
	   }
	   return false;
	   }
   
  
   
   public static boolean hasContentType(String requestContentType ,String requiredContentType){
	   if(OAuthUtils.isEmpty(requiredContentType)||OAuthUtils.isEmpty(requiredContentType)){
		   return false;
	   }
	   StringTokenizer tokenzier=new StringTokenizer(requestContentType,";");
	   while(tokenzier.hasMoreTokens()){
		   if(requiredContentType.equals(tokenzier.nextToken())){
			   return true;
		   }
	   }
	   return false;
	   
   }
   
}
