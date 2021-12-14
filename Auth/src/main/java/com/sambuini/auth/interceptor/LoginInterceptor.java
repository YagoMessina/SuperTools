package com.sambuini.auth.interceptor;

import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.service.SessionTokenService;
import com.sambuini.error.validator.ClientValidate;
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
        String token = request.getHeader(HEADER);
        ClientValidate.notBlank(token, "Missing auth-token.");

        SessionToken sessionToken = sessionTokenService.find(token);
        ClientValidate.isTrue(!sessionToken.hasExpired(), "The session already expired.");

        request.getSession().setAttribute("token", sessionToken);
        return true;
    }
}
