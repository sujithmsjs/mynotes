package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

	/**
	 * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
	 * to move towards a component-based security configuration. It is recommended to create a bean
	 * of type SecurityFilterChain for security related configurations.
	 *
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

		http.cors().configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setMaxAge(3600L);
						return config;
					}
				}).and().authorizeHttpRequests((auth) -> auth
						.antMatchers("/myAccount").hasRole("USER")
						.antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
						.antMatchers("/myLoans").authenticated()
						.antMatchers( "/myCards").hasAnyRole("USER","ADMIN")
						.antMatchers("/notices", "/contact").permitAll())
				.csrf().disable()
				.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);

		/**
		 * This configuration is needed to view the /h2-console with out any issues.
		 * Since H2 Console uses frames to display the UI, we need to allow the frames.
		 * Otherwise since by default Spring Security consider X-Frame-Options: DENY
		 * to avoid Clickjacking attacks, the /h2-console will not work properly.
		 * More details can be found at 
		 * https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/headers.html#headers-frame-options
		 */
		http.headers().frameOptions().sameOrigin();
		return http.build();
	}

}
