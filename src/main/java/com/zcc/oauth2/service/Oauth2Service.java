package com.zcc.oauth2.service;

public interface Oauth2Service {

		
public void addAuthCode(String authCode,String username);

public void addAccessToken(String accessToken,String username);

public boolean checkAuthCode(String authCode);

public boolean checkAccessToken(String accessToken);

public String getUsernameByAuthCode(String authCode);

public String getUsernameByAccessToken(String accessToken);

public long getExpireIn();

public boolean checkClientId(String clientId);

public boolean checkClientSecret(String clientSecret);

public boolean checkRedirectUri(String redirectUri);
}
