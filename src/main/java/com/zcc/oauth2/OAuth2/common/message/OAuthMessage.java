package com.zcc.oauth2.OAuth2.common.message;

import java.util.Map;

public interface OAuthMessage {
      
	public String getLocationUri();
	
	public void   setLocationUri(String uri);
	
    public String getBody();
    
    public void   setBody(String body);
    
    public String getHeader(String name);
    
    public void addHeader(String name,String header);
    
    public Map<String, String> getHeader();
    
    public void   setHeader(Map<String,String> name);
    
}
