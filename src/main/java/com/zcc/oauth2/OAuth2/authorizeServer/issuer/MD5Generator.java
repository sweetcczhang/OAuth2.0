package com.zcc.oauth2.OAuth2.authorizeServer.issuer;

import java.security.MessageDigest;
import java.util.UUID;

import com.zcc.oauth2.OAuth2.common.exception.OAuthSystemException;

public class MD5Generator implements ValueGenerator{

	private static final char[] hexCode="0123456789abcdef".toCharArray();
	
	public static String toHexString(byte[] data){
		if(data==null){
			return null;
		}
		StringBuilder r=new StringBuilder(data.length*2);
		for(byte b : data){
			r.append(hexCode[(b>>4)&0xF]);
			r.append(hexCode[(b & 0xF)]);
		}
		return r.toString();
	}
	
	@Override
	public String generateValue() throws OAuthSystemException {
		// TODO Auto-generated method stub
		return generateValue(UUID.randomUUID().toString());
	}

	@Override
	public String generateValue(String param) throws OAuthSystemException {
		// TODO Auto-generated method stub
		try{
			MessageDigest algorithm=MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(param.getBytes());
			byte[] messageDigest=algorithm.digest();
			return toHexString(messageDigest);
			
		}catch(Exception e){
			throw new OAuthSystemException("OAuth Token cnnot be generated.",e);
		}
		
	}

}
