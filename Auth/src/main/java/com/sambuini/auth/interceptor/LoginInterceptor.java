package com.sambuini.auth.interceptor;

import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.service.SessionTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String HEADER = "auth-token";

    private final SessionTokenService sessionTokenService;

    public LoginInterceptor(SessionTokenService sessionTokenService) {
        this.sessionTokenService = sessionTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(StringUtils.isBlank(request.getHeader(HEADER)))
            throw new RuntimeException("jejeje");
        SessionToken sessionToken = new SessionToken(request.getHeader(HEADER));
        if(sessionToken.hasExpired())
            throw new RuntimeException("jijiji");
        if (!sessionTokenService.exists(sessionToken))
            throw new RuntimeException("jujujuju");
        request.getSession().setAttribute("token", sessionToken);
        return true;
    }
}
