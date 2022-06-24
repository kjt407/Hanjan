package com.jongtk.hanjan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jongtk.hanjan.filter.ApiCheckFilter;
import com.jongtk.hanjan.filter.ApiLoginFilter;

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
        http.authorizeRequests()
                .antMatchers("/main").hasRole("USER");
        http.csrf().disable();
        http.logout();
        http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsService);

        // 인증을 진행하는 필터보다 먼저 실행되도록 설정        
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    public LoginSuccessHandler successHandler(){
//        return new LoginSuccessHandler(passwordEncoder());
//    }

	@Bean
	public ApiCheckFilter apiCheckFilter() {
		return new ApiCheckFilter("/api/group/*");
	}
	
	@Bean
	public ApiLoginFilter apiLoginFilter() throws Exception{
		ApiLoginFilter apiLoginFilter =  new ApiLoginFilter("/api/login");
		apiLoginFilter.setAuthenticationManager(authenticationManager());	//WebSecurityConfigurerAdapter
		
		return apiLoginFilter; 
	}
}
