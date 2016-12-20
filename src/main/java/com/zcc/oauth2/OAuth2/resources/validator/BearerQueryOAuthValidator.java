package com.zcc.oauth2.OAuth2.resources.validator;

import static com.zcc.oauth2.OAuth2.resources.ResourceServer.getQueryParameterValues;

import javax.servlet.http.HttpServletRequest;

import com.zcc.oauth2.OAuth2.common.OAuth;
import com.zcc.oauth2.OAuth2.common.error.OAuthError;
import com.zcc.oauth2.OAuth2.common.exception.OAuthProblemException;
import com.zcc.oauth2.OAuth2.common.utils.OAuthUtils;
import com.zcc.oauth2.OAuth2.common.validators.AbstractValidator;
import com.zcc.oauth2.OAuth2.resources.ResourceServer;

public class BearerQueryOAuthValidator extends AbstractValidator<HttpServletRequest>{
	 @Override
	    public void validateContentType(HttpServletRequest request) throws OAuthProblemException {
	    }

	    @Override
	    public void validateMethod(HttpServletRequest request) throws OAuthProblemException {
	    }

	    @Override
	    public void validateRequiredParameters(HttpServletRequest request) throws OAuthProblemException {

	        String[] tokens = getQueryParameterValues(request, OAuth.OAUTH_BEARER_TOKEN);
	        if (OAuthUtils.hasEmptyValues(tokens)) {
	            tokens = getQueryParameterValues(request, OAuth.OAUTH_TOKEN);
	            if (OAuthUtils.hasEmptyValues(tokens)) {
	                throw OAuthProblemException.error(null, "Missing OAuth token.");
	            }
	        }

	        if (tokens != null && tokens.length > 1) {
	            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST, "Multiple tokens attached.");
	        }

	        String oauthVersionDiff = ResourceServer.getQueryParameterValue(request, OAuth.OAUTH_VERSION_DIFFER);
	        if (!OAuthUtils.isEmpty(oauthVersionDiff)) {
	            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST,
	                                              "Incorrect OAuth version. Found OAuth V1.0.");
	        }
	    }

}
