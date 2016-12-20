package com.zcc.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class Oauth2ServiceImpl implements Oauth2Service{
	
	private Cache cache;
	@Autowired
	private ClientService  clientService;
	
	@Autowired
	public Oauth2ServiceImpl(CacheManager cacheManager){
		this.cache=cacheManager.getCache("code-cache");
	}
	
	public void addAuthCode(String authCode,String username){
		
		   cache.put(authCode, username);
	}

	public void addAccessToken(String accessToken,String username){
		
		  cache.put(accessToken, username);
	}

   public	boolean checkAuthCode(String authCode){
	   
	     return   cache.get(authCode)!=null;
		
	}

    public	boolean checkAccessToken(String accessToken){
		
		   return  cache.get(accessToken)!=null;
	}

	public String getUsernameByAuthCode(String authCode){
		
		  return  (String)cache.get(authCode).get();
	}
	public String getUsernameByAccessToken(String accessToken){
		  
		return (String)cache.get(accessToken).get();
	}

	public long getExpireIn(){
	
		   return 3600L;
	}

	public boolean checkClientId(String clientId){
		
      return  clientService.findByClientId(clientId)!=null;

	}

	public boolean checkClientSecret(String clientSecret){
		
		return clientService.findByClientSecret(clientSecret)!=null;
		    
	}

	@Override
	public boolean checkRedirectUri(String redirectUri) {
		// TODO Auto-generated method stub
		
		return clientService.findByRedirect(redirectUri)!=null;
	}
	
	
}
