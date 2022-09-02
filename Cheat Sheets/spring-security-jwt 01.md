# spring-security-jwt


### File Structure
```pre
+ spring-security-jwt\ 
	+ \src\main\java\com\suji\springsecurityjwt
		|---  SpringSecurityJwtApplication.java
	+ \src\test\java\com\suji\springsecurityjwt
		|---  SpringSecurityJwtApplicationTests.java
	+ \src\main\java\com\suji\springsecurityjwt\controller
		|---  AuthController.java
	+ \src\main\java\com\suji\springsecurityjwt\config
		|---  JWTAuthenticationEntryPoint.java
		|---  SecurityConfig.java
	+ \src\main\java\com\suji\springsecurityjwt\error
		|---  ErrorResponse.java
	+ \src\main\java\com\suji\springsecurityjwt\filter
		|---  JWTFilter.java
```
### Index
```pre
1. controller\AuthController.java
2. src\main\java\com\suji\springsecurityjwt\SpringSecurityJwtApplication.java
3. src\main\java\com\suji\springsecurityjwt\config\JWTAuthenticationEntryPoint.java
4. src\main\java\com\suji\springsecurityjwt\config\SecurityConfig.java
5. src\main\java\com\suji\springsecurityjwt\error\ErrorResponse.java
6. src\main\java\com\suji\springsecurityjwt\filter\JWTFilter.java
7. src\test\java\com\suji\springsecurityjwt\SpringSecurityJwtApplicationTests.java

```

---

### 1. AuthController.java

#### controller\AuthController.java

```java

package com.suji.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
public class AuthController {

	@GetMapping("/hi")
	public String sayHi() {
		log.info("inside sayHi method");
		return "Hello world..!";
	}
	
	@GetMapping("/authenticate")
	public String authenticate() {
		log.info("inside authenticate method");
		return "Hello You're authenticated!";
	}
	
}

```

---

### 2. SpringSecurityJwtApplication.java

#### src\main\java\com\suji\springsecurityjwt\SpringSecurityJwtApplication.java

```java

package com.suji.springsecurityjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}
}

```

---

### 3. JWTAuthenticationEntryPoint.java

#### src\main\java\com\suji\springsecurityjwt\config\JWTAuthenticationEntryPoint.java

```java

package com.suji.springsecurityjwt.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.suji.springsecurityjwt.error.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		String writeError = writeError(authException);
		response.getWriter().write(writeError);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		log.error("Error! " + authException.getLocalizedMessage());
	}

	private String writeError(AuthenticationException ex) throws JsonProcessingException {
		
		ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.UNAUTHORIZED);
		return writer.writeValueAsString(err);

	}

}

```

---

### 4. SecurityConfig.java

#### src\main\java\com\suji\springsecurityjwt\config\SecurityConfig.java

```java

package com.suji.springsecurityjwt.config;

import org.apache.catalina.filters.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.suji.springsecurityjwt.filter.JWTFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JWTAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private JWTFilter filter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable()

				.authorizeHttpRequests()

				.antMatchers("/authenticate").permitAll().anyRequest().authenticated()

				.and()

				.exceptionHandling().authenticationEntryPoint(entryPoint)

				.and()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}

```

---

### 5. ErrorResponse.java

#### src\main\java\com\suji\springsecurityjwt\error\ErrorResponse.java

```java

package com.suji.springsecurityjwt.error;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
	
	private HttpStatus status;
	private String message;
	
}

```

---

### 6. JWTFilter.java

#### src\main\java\com\suji\springsecurityjwt\filter\JWTFilter.java

```java

package com.suji.springsecurityjwt.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

	
		Optional<String> token = Optional.ofNullable(request.getHeader("Authorization"));

		if (token.filter(s -> s.equalsIgnoreCase("sujith")).isPresent()
				&& SecurityContextHolder.getContext().getAuthentication() == null) {

			// Create Authentication Object
			var authentication = new UsernamePasswordAuthenticationToken("sujith", "suji@1234",
					List.of(new SimpleGrantedAuthority("ADMIN")));

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		log.info("inside doFilterInternal before DO FILTER Request:{} ", request);
		filterChain.doFilter(request, response);
		log.info("inside doFilterInternal after DO FILTER Response:{} ", response);
	}

}

```

---

### 7. SpringSecurityJwtApplicationTests.java

#### src\test\java\com\suji\springsecurityjwt\SpringSecurityJwtApplicationTests.java

```java

package com.suji.springsecurityjwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityJwtApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

