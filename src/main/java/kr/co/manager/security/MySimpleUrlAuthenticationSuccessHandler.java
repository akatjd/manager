package kr.co.manager.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.domain.jpa.Factory;
import kr.co.manager.domain.jpa.Machine;
import kr.co.manager.exception.rdb.AccountBcryptRepository;
import kr.co.manager.exception.rdb.FactoryRepository;
import kr.co.manager.exception.rdb.MachineRepository;
import kr.co.manager.service.MachineService;

@Component
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(MySimpleUrlAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    AccountBcryptRepository accountBcryptRepository;

    @Autowired
    MachineRepository machineRepository;

    @Autowired
    MachineService machineService;

    @Autowired
    FactoryRepository factoryRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        HttpSession session = request.getSession();
        AccountBcrypt account = accountBcryptRepository.findByAccountId(authentication.getName());
        List<Machine> machineList = machineRepository.findAll();
        String allowFactory = account.getAllowFactory();
        session.setAttribute("allowFactory", allowFactory);
        String[] allowFactoryId = allowFactory.split(",");
        if(allowFactoryId.length > 0){
            String factoryId = allowFactoryId[0];
            if(!"0".equals(factoryId)){
                Factory factory = factoryRepository.findById(Integer.valueOf(factoryId)).get();
                session.setAttribute("factory", factory);
            }
        }
        session.setAttribute("userName", account.getUserName());
        session.setAttribute("account", account);
        session.setAttribute("machines", machineList);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                return "/main/qgMonitoring";
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                return "/main/qgMonitoring";
            } else if (grantedAuthority.getAuthority().equals("ROLE_RESEARCHER")) {
                return "/main/qgMonitoring";
            } else if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
                return "/main/qgMonitoring";
            }
        }
        return "/main/qgMonitoring";
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
