package org.generation.italy.demo.security;

import org.generation.italy.demo.serv.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConf {
	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/api/**").permitAll()
				.requestMatchers("/**").hasAuthority("ADMIN")
				//.requestMatchers(HttpMethod.POST, "/create", "/update", "/delete",
						//"/drink/create", "/drink/update", "/drink/delete",
						//"/ingredient/create", "/ingredient/update", "/ingredient/delete"		
						//).hasAuthority("ADMIN")
				//.requestMatchers("/admin", "/admin/**").hasAuthority("ADMIN")
				//.requestMatchers("/useradmin", "/useradmin/**").hasAnyAuthority("USER", "ADMIN")	
			.and().formLogin()
			.and().logout()
		;

		return http.build();
	}
	
	@Bean
	public UserDetailsService getuseDetailsService() {
		
		return new UserServ();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getAuthProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(getuseDetailsService());
		provider.setPasswordEncoder(getPasswordEncoder());
		
		return provider;
	}
}
