package com.zcc.oauth2.OAuth2.resources;



import com.zcc.oauth2.OAuth2.common.message.types.ParameterStyle;
import com.zcc.oauth2.OAuth2.resources.extractor.BearerBodyTokenExtractor;
import com.zcc.oauth2.OAuth2.resources.extractor.BearerQueryTokenExtractor;
import com.zcc.oauth2.OAuth2.resources.validator.BearerBodyOAuthValidator;
import com.zcc.oauth2.OAuth2.resources.validator.BearerQueryOAuthValidator;

public class BearerResourceServer extends ResourceServer{
	
	public BearerResourceServer(){
		     extractors.put(ParameterStyle.BODY, BearerBodyTokenExtractor.class);
		     extractors.put(ParameterStyle.QUERY, BearerQueryTokenExtractor.class);
		     
		     validators.put(ParameterStyle.BODY, BearerBodyOAuthValidator.class);
		     validators.put(ParameterStyle.QUERY, BearerQueryOAuthValidator.class);
	}

}
