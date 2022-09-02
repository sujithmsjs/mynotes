# swagger3-security


### File Structure
```pre
+ swagger3-security\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\swaggersec
		|---  Swagger3SecurityApplication.java
	+ \src\test\java\com\suji\swaggersec
		|---  Swagger3SecurityApplicationTests.java
	+ \src\main\java\com\suji\swaggersec\controller
		|---  HelloWorldController.java
	+ \src\main\java\com\suji\swaggersec\security
		|---  JwtAuthenticationEntryPoint.java
		|---  SecurityConfig.java
```
### Index
```pre
1. application.properties
2. controller\HelloWorldController.java
3. src\main\java\com\suji\swaggersec\Swagger3SecurityApplication.java
4. src\main\java\com\suji\swaggersec\security\JwtAuthenticationEntryPoint.java
5. src\main\java\com\suji\swaggersec\security\SecurityConfig.java
6. src\test\java\com\suji\swaggersec\Swagger3SecurityApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

#
# spring.security.user.name=sujith
# spring.security.user.password=suji@123
#



```

---

### 2. HelloWorldController.java

#### controller\HelloWorldController.java

```java

package com.suji.swaggersec.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "basic-scheme")
public class HelloWorldController {

	@GetMapping({ "/hello" })
	public ResponseEntity<Map<String, String>> hello(HttpServletRequest request) {

		var hashMap = new HashMap<String, String>();

		hashMap.put("Hello", "This is working fine!!!");
		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String header = names.nextElement();
			hashMap.put(header, request.getHeader(header));
		}
		return new ResponseEntity<Map<String, String>>(hashMap, HttpStatus.OK);
	}

}

```

---

### 3. Swagger3SecurityApplication.java

#### src\main\java\com\suji\swaggersec\Swagger3SecurityApplication.java

```java

package com.suji.swaggersec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
@SecurityScheme(name = "jwtapi", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
@SecurityScheme(name = "basic-scheme", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class Swagger3SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Swagger3SecurityApplication.class, args);
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
}

```

---

### 4. JwtAuthenticationEntryPoint.java

#### src\main\java\com\suji\swaggersec\security\JwtAuthenticationEntryPoint.java

```java

package com.suji.swaggersec.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		log.info("Inside commence of AuthenticationEntryPoint");
		response.getWriter().write(authException.getMessage());
	}
	
	
	public void commence2(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {

		log.info("Inside commence of AuthenticationEntryPoint");
		log.error("Error: " + authException.getMessage());

		try {
			// response.setStatus(HttpStatus.UNAUTHORIZED.value());
			// response.resetBuffer();
			// response.getWriter().write(authException.getMessage());

			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}

```

---

### 5. SecurityConfig.java

#### src\main\java\com\suji\swaggersec\security\SecurityConfig.java

```java

package com.suji.swaggersec.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("sujith")
		.password(passwordEncoder().encode("1234"))
		.authorities("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		
				// Exclude Swagger URL's
				.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
				
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```

---

### 6. Swagger3SecurityApplicationTests.java

#### src\test\java\com\suji\swaggersec\Swagger3SecurityApplicationTests.java

```java

package com.suji.swaggersec;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Swagger3SecurityApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

