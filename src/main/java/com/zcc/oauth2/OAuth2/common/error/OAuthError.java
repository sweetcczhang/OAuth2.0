package com.zcc.oauth2.OAuth2.common.error;

public abstract class OAuthError {
	
	    //错误
		public static final String OAUTH_ERROR="error";
		//错误描述
		public static final String OAUTH_ERROR_DESCRIPTION="error_description";
		//错误地址
		public static final String OAUTH_ERROR_URI="error_uri";
		//错误代码
		public static final String OAUTH_ERROR_CODE="error_code";
		
    //授权码错误类
	public static final class CodeResponse{
		
		//客户端ID不合法
		public static final String INVALID_CLIENT="invalid_client";
		//参数缺失错误，包括不合法参数，不支持参数
		public static final String  INVALID_REQUEST="invalid_request";
		//客户端使用这种获取授权码的方式未获得认证
		public static final String  UNAUTHORIZE_CLIENT="unauthorize_client";
		//资源拥有者或资源服务器拒绝
		public static final String  ACCESS_DENIED="access_denied";
		//获取的权限不合法
		public static final String  INVALID_SCOPE="invalid_scope";
		//不支持的授权类型
		public static final String  UNSUPPORTED_RESPONSE_TYPE="unsupported_response_type";
		//服务器错误
		public static final String  SERVER_ERROR="server_error";
		//服务器暂时无法处理请求
		public static final String TEMPORARILY_UNAVAILABLE = "temporarily_unavailable";
		
		
	  }
	//令牌错误类
	public static final class TokenResponse{
		
		public static final String INVALID_REQUEST="invalid_request";
		
		public static final String UNAUTHORIZED_CLIENT="unauthorzied_client";
		
		public static final String INVALID_SCOPE="invalid_scope";
		
		public static final String INVALID_CLIENT="invalid_client";
		
		public static final String UNSUPPORTED_GRANT_TYPE="unsupported_grant_type";
		
		public static final String INVALID_GRANT="invalid_grant";
				
		
	}
	//资源请求错误类
	public static final class ResourceResponse{
		
		public static final String INVALID_REQUEST="invalid_request";
		
		public static final String EXPIRED_TOKEN="expired_token";
		
		public static final String INSUFFICIENT_SCOPE = "insufficient_scope";
	        
	    public static final String INVALID_TOKEN = "invalid_token";
	}

}
