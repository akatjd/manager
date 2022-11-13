package kr.co.manager.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.manager.exception.rdb.FactoryRepository;
import kr.co.manager.exception.rdb.MachineRepository;
import kr.co.manager.service.MachineService;

@Component
public class MySimpleUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(MySimpleUrlAuthenticationFailureHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    MachineRepository machineRepository;

    @Autowired
    MachineService machineService;

    @Autowired
    FactoryRepository factoryRepository;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        handle(request, response, authenticationException);
        clearAuthenticationAttributes(request);
    }
    
    protected void handle(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        String targetUrl = determineTargetUrl(authenticationException);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    protected String determineTargetUrl(AuthenticationException authenticationException) {
        if(authenticationException instanceof BadCredentialsException){
            return "/main/login?error="+1130;
        }else if(authenticationException instanceof AuthenticationServiceException){
            String errMsg = authenticationException.getMessage();
            return "/main/login?error="+errMsg;
        }
        return "";
    }
    
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
