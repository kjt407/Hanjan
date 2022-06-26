package com.jongtk.hanjan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jongtk.hanjan.security.filter.ApiCheckFilter;
import com.jongtk.hanjan.security.filter.ApiLoginFilter;
import com.jongtk.hanjan.security.handler.ApiLoginFailHandler;
import com.jongtk.hanjan.security.handler.ApiLoginSuccessfulHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.csrf().disable();
        http.logout();
        http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService);

        // 인증을 진행하는 필터보다 먼저 실행되도록 설정        
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    

	@Bean
	public ApiCheckFilter apiCheckFilter() {
		return new ApiCheckFilter("/api/group/*");
	}
	
	@Bean
	public ApiLoginFilter apiLoginFilter() throws Exception{
		ApiLoginFilter apiLoginFilter =  new ApiLoginFilter("/api/login");
		apiLoginFilter.setAuthenticationManager(authenticationManager());	//WebSecurityConfigurerAdapter
		apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
		apiLoginFilter.setAuthenticationSuccessHandler(new ApiLoginSuccessfulHandler());
		
		return apiLoginFilter; 
	}
}
