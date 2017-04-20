package com.zcc.oauth2.web.register;

import com.zcc.oauth2.qq.QQConnectException;
import com.zcc.oauth2.qq.javabeans.AccessToken;
import com.zcc.oauth2.qq.oauth.Oauth;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserRegisterController {

    public String  qqAuthrization(HttpServletRequest request) throws QQConnectException {


        AccessToken accessTokenObj=(new Oauth()).getAccessTokenByRequest(request);
        String accessToken=null;
        String openID=null;
        Long expiredIn=0L;
        return  null;
    }
}
