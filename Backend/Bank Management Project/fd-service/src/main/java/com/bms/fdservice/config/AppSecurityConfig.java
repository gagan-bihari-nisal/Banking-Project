package com.bms.fdservice.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bms.fdservice.filter.TokenValidatorFilter;

@Configuration
public class AppSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Bean
	SecurityFilterChain defaultConfig(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.cors();
		
		
		
		http.authorizeRequests().antMatchers("/create").authenticated().anyRequest().permitAll();

		http.addFilterBefore(new TokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling().authenticationEntryPoint(entryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}


}