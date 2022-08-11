# z01-spring-jwt


### File Structure
```pre
+ z01-spring-jwt\ 
	+ \src\main\java\com\suji\jwt
		|---  Z01SpringJwtApplication.java
	+ \src\test\java\com\suji\jwt
		|---  Z01SpringJwtApplicationTests.java
	+ \src\main\java\com\suji\jwt\model
		|---  JwtRequest.java
		|---  JwtResponse.java
	+ \src\main\java\com\suji\jwt\controller
		|---  AuthenticationController.java
		|---  HelloController.java
	+ \src\main\java\com\suji\jwt\service
		|---  ImplUserDetailsService.java
	+ \src\main\java\com\suji\jwt\configuration
		|---  SecurityConfiguration.java
```
### Index
```pre
1. model\JwtRequest.java
2. model\JwtResponse.java
3. controller\AuthenticationController.java
4. controller\HelloController.java
5. service\ImplUserDetailsService.java
6. src\main\java\com\suji\jwt\Z01SpringJwtApplication.java
7. src\main\java\com\suji\jwt\configuration\SecurityConfiguration.java
8. src\test\java\com\suji\jwt\Z01SpringJwtApplicationTests.java

```

---

### 1. JwtRequest.java

#### model\JwtRequest.java

```java

package com.suji.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
	private String username;
	private String password;
}

```

---

### 2. JwtResponse.java

#### model\JwtResponse.java

```java

package com.suji.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private String username;
	private String token;
}

```

---

### 3. AuthenticationController.java

#### controller\AuthenticationController.java

```java

package com.suji.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@GetMapping({"/athenticate"})
	public String sayHello(@) {
		
		return "Hello World!";
	}
	
}

```

---

### 4. HelloController.java

#### controller\HelloController.java

```java

package com.suji.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping({"","/"})
	public String sayHello() {
		return "Hello World!";
	}
	
}

```

---

### 5. ImplUserDetailsService.java

#### service\ImplUserDetailsService.java

```java

package com.suji.jwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImplUserDetailsService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		
		String encode = passwordEncoder.encode("password");
		System.out.println(" Encoded Password:  "+encode);
		
		
		if ("sujith".equals(username)) {
			user = new User("sujith","password",List.of(new SimpleGrantedAuthority("USER")));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return user;
	}

}

```

---

### 6. Z01SpringJwtApplication.java

#### src\main\java\com\suji\jwt\Z01SpringJwtApplication.java

```java

package com.suji.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Z01SpringJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Z01SpringJwtApplication.class, args);
	}
	
	@Bean
	protected BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

```

---

### 7. SecurityConfiguration.java

#### src\main\java\com\suji\jwt\configuration\SecurityConfiguration.java

```java

package com.suji.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.suji.jwt.service.ImplUserDetailsService;

@Configurable
@Component
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);//.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/**").authenticated();
	}

	
	
	
}

```

---

### 8. Z01SpringJwtApplicationTests.java

#### src\test\java\com\suji\jwt\Z01SpringJwtApplicationTests.java

```java

package com.suji.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Z01SpringJwtApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

