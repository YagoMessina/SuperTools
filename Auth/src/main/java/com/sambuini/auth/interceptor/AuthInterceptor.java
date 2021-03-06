package com.sambuini.auth.interceptor;

import com.sambuini.auth.entity.SessionToken;
import com.sambuini.auth.service.SessionTokenService;
import com.sambuini.error.validator.ClientValidate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String HEADER = "Auth-Token";

    private final SessionTokenService sessionTokenService;

    public AuthInterceptor(SessionTokenService sessionTokenService) {
        this.sessionTokenService = sessionTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(HEADER);
        ClientValidate.notBlank(token, "Missing Auth-Token.");

        SessionToken sessionToken = sessionTokenService.find(token);
        ClientValidate.isTrue(!sessionToken.hasExpired(), "The session already expired.");

        request.getSession().setAttribute(SessionToken.TOKEN, sessionToken);
        return true;
    }
}
