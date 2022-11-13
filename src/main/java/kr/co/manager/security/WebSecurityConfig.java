package kr.co.manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;
    
    @Autowired
    MySimpleUrlAuthenticationFailureHandler mySimpleUrlAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) {
    	try {
    		
    		http.authorizeRequests()
    			.antMatchers("/main/**").permitAll()
    			.antMatchers("/signUpApi/**").permitAll()
    			.antMatchers("/signUp/**").permitAll()
    			.antMatchers("/login/**").permitAll()
	    		.antMatchers("/mes/**").permitAll()
		        .antMatchers("/upload/**").permitAll()
		        .antMatchers("/").hasAnyRole("USER","ADMIN","MANAGER","MEMBER")
		        .antMatchers("/qgMonitoringApi/**").hasAnyRole("USER","ADMIN","MANAGER","MEMBER")
		        .antMatchers("/machine/**").hasAnyRole("USER","ADMIN","MANAGER","MEMBER")
		        .antMatchers("/admin/**").hasAnyRole("USER","ADMIN","MANAGER","MEMBER")
		        .antMatchers("/statistic/**").hasRole("ADMIN")
		        .antMatchers("/members/**").hasRole("ADMIN")
		        .anyRequest().authenticated();
    		
    		http.formLogin()
    			.loginPage("/main/login")
    			.usernameParameter("userId")
    			.passwordParameter("password")
    			.failureHandler(mySimpleUrlAuthenticationFailureHandler)
    			.successHandler(mySimpleUrlAuthenticationSuccessHandler)
    			.permitAll();
    		
    		http.logout()
    			.logoutSuccessUrl("/main/login")
    			.permitAll();
    		
    		// 나중에 보안을 위해 HTTPS SSL 적용해야함
//    		http.httpBasic();
    		
    		http.csrf().disable();
    	
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/scripts/**", "/styles/**", "/images/**", "/vendor/**", "/icons/**", "/fonts/**", "/favicon.ico", "/external/**",
                "/v2/api-docs", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/focus/**", "/falcon/**"); //하단은 Swagger에서 사용되는 것을 추가
    }

}
