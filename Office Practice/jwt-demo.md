# jwt-demo


### File Structure
```pre
+ jwt-demo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\jwt\demo
		|---  JwtDemoApplication.java
	+ \src\main\java\com\suji\jwt\filter
		|---  SecurityFilter.java
	+ \src\test\java\com\suji\jwt\demo
		|---  JwtDemoApplicationTests.java
	+ \src\main\java\com\suji\jwt\demo\model
		|---  JwtUser.java
		|---  UserRequest.java
		|---  UserResponse.java
	+ \src\main\java\com\suji\jwt\demo\controller
		|---  UserRestController.java
	+ \src\main\java\com\suji\jwt\demo\service
		|---  IUserService.java
	+ \src\main\java\com\suji\jwt\demo\repository
		|---  UserRepository.java
	+ \src\main\java\com\suji\jwt\demo\config
		|---  AppConfig.java
		|---  InvalidUserAuthEntryPoint.java
		|---  SecurityConfig.java
	+ \src\main\java\com\suji\jwt\demo\util
		|---  Auth.java
		|---  JwtUtil.java
		|---  RolesUtil.java
	+ \src\main\java\com\suji\jwt\demo\service\impl
		|---  UserService.java
```
### Index
```pre
1. application.properties
2. model\JwtUser.java
3. model\UserRequest.java
4. model\UserResponse.java
5. controller\UserRestController.java
6. service\IUserService.java
7. repository\UserRepository.java
8. src\main\java\com\suji\jwt\demo\JwtDemoApplication.java
9. src\main\java\com\suji\jwt\demo\config\AppConfig.java
10. src\main\java\com\suji\jwt\demo\config\InvalidUserAuthEntryPoint.java
11. src\main\java\com\suji\jwt\demo\config\SecurityConfig.java
12. src\main\java\com\suji\jwt\demo\service\impl\UserService.java
13. src\main\java\com\suji\jwt\demo\util\Auth.java
14. src\main\java\com\suji\jwt\demo\util\JwtUtil.java
15. src\main\java\com\suji\jwt\demo\util\RolesUtil.java
16. src\main\java\com\suji\jwt\filter\SecurityFilter.java
17. src\test\java\com\suji\jwt\demo\JwtDemoApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



# User define values
jwt.secret ="sujith@123"


# Data Source
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# JPA Props
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto = create
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.defer-datasource-initialization=true


spring.h2.console.enabled=true



```

---

### 2. JwtUser.java

#### model\JwtUser.java

```java

package com.suji.jwt.demo.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class JwtUser {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String username;
	private String password;
	
	@ElementCollection
	@CollectionTable(
			name = "rolestab",
			joinColumns = @JoinColumn(name = "id")
			
			)
	@Column(name = "roles")
	private Set<String> roles;

	public JwtUser(String name, String username, String password, Set<String> roles) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	

}

```

---

### 3. UserRequest.java

#### model\UserRequest.java

```java

package com.suji.jwt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	private String username;
	private String password;
	
}

```

---

### 4. UserResponse.java

#### model\UserResponse.java

```java

package com.suji.jwt.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private String token;
	private String message;
	
}

```

---

### 5. UserRestController.java

#### controller\UserRestController.java

```java

package com.suji.jwt.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.jwt.demo.model.JwtUser;
import com.suji.jwt.demo.model.UserRequest;
import com.suji.jwt.demo.model.UserResponse;
import com.suji.jwt.demo.service.IUserService;
import com.suji.jwt.demo.util.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private IUserService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	// Save POST: /login

	@PostMapping("/login")
	public ResponseEntity<UserResponse> getUser(@RequestBody UserRequest userReq) {
		
		String token = jwtUtil.generateToken(userReq.getUsername());
		String message = "Success";
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userReq.getUsername(), userReq.getPassword());
		
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		
		
		UserResponse ur = new UserResponse(token, message);
		
		return new ResponseEntity<UserResponse>(ur, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<JwtUser> saveUser(@RequestBody JwtUser user) {
		JwtUser saveUser = service.saveUser(user);
		return new ResponseEntity<JwtUser>(saveUser, HttpStatus.OK);
	}
	
	@GetMapping(path = {"/",""})
	public ResponseEntity<List<JwtUser>> getAllUsers() {
		return new ResponseEntity<List<JwtUser>>(service.getAllUsers(), HttpStatus.OK);
	}
	
}

```

---

### 6. IUserService.java

#### service\IUserService.java

```java

package com.suji.jwt.demo.service;

import java.util.List;
import java.util.Optional;

import com.suji.jwt.demo.model.JwtUser;

public interface IUserService {
	
	// TODO: Encode Password
	JwtUser saveUser(JwtUser user);
	Optional<JwtUser> findByUsername(String username);
	List<JwtUser> getAllUsers();
}

```

---

### 7. UserRepository.java

#### repository\UserRepository.java

```java

package com.suji.jwt.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.jwt.demo.model.JwtUser;

public interface UserRepository extends JpaRepository<JwtUser, Integer> {

	Optional<JwtUser> findByUsername(String username);
	
}

```

---

### 8. JwtDemoApplication.java

#### src\main\java\com\suji\jwt\demo\JwtDemoApplication.java

```java

package com.suji.jwt.demo;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.jwt.demo.model.JwtUser;
import com.suji.jwt.demo.service.impl.UserService;
import com.suji.jwt.demo.util.Auth;

@SpringBootApplication
public class JwtDemoApplication implements ApplicationRunner {

	@Autowired
	private UserService service;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
		System.out.println("JWT-DEMO Project staretd...");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		JwtUser user = service.saveUser(new JwtUser("Sujith","sujith@gmail.com","sujith@123", Set.of(Auth.ADMIN,Auth.EMP)));
		Optional.of(user);
	}
}

```

---

### 9. AppConfig.java

#### src\main\java\com\suji\jwt\demo\config\AppConfig.java

```java

package com.suji.jwt.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

```

---

### 10. InvalidUserAuthEntryPoint.java

#### src\main\java\com\suji\jwt\demo\config\InvalidUserAuthEntryPoint.java

```java

package com.suji.jwt.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InvalidUserAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		log.debug("commence method, validating user...");
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User");

	}

}

```

---

### 11. SecurityConfig.java

#### src\main\java\com\suji\jwt\demo\config\SecurityConfig.java

```java

package com.suji.jwt.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationEntryPoint entryPoint;
	
	@Autowired
	private UserDetailsService uds;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uds).passwordEncoder(passwordEncoder);
	}
	
	

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		
		.authorizeRequests()
		.antMatchers("/users/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(entryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		//TODO : verify user from 2nd req onwords...
		;
		
		
		
	}

}

```

---

### 12. UserService.java

#### src\main\java\com\suji\jwt\demo\service\impl\UserService.java

```java

package com.suji.jwt.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suji.jwt.demo.model.JwtUser;
import com.suji.jwt.demo.repository.UserRepository;
import com.suji.jwt.demo.service.IUserService;

@Service
public class UserService implements IUserService, UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Override
	public JwtUser saveUser(JwtUser user) {
		
		// Encode Password before saving into DB.
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		
		return repo.save(user);
	}

	@Override
	public List<JwtUser> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public Optional<JwtUser> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		JwtUser user = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		//Set<String> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map( SimpleGrantedAuthority:: new).collect(Collectors.toList());
		

		
		User springUser = new User(user.getUsername(),user.getPassword(),authorities);	
		
		return springUser;
	}

}

```

---

### 13. Auth.java

#### src\main\java\com\suji\jwt\demo\util\Auth.java

```java

package com.suji.jwt.demo.util;

public class Auth {
	public static final String MRNG = "Manger";
	public static final String TL = "Team Leader";
	public static final String ADMIN = "Admin";
	public static final String EMP = "Employee";
}

```

---

### 14. JwtUtil.java

#### src\main\java\com\suji\jwt\demo\util\JwtUtil.java

```java

package com.suji.jwt.demo.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	
	// Generate Token
	public String generateToken(String id, String subject, String key) {

		byte[] secretKey = Base64.getEncoder().encode(key.getBytes());

		JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuer("Sujith")
				.setIssuedAt(new Date(System.nanoTime()))
				.setExpiration(new Date(System.nanoTime() + TimeUnit.MINUTES.toMillis(10)))
				.signWith(SignatureAlgorithm.HS256, secretKey);

		return builder.compact();
	}

	// Get Claims
	public Claims getClaims(String secretKey, String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

		return claims;
	}
	
	public String getUsername(String token) {
		return getClaims(secret, token).getSubject();
	}

	public void printClaims(Claims claims) {

		System.out.println(claims.getId());
		System.out.println(claims.getSubject());
		System.out.println(claims.getIssuer());
		System.out.println(claims.getIssuedAt());
		System.out.println(claims.getExpiration());
	}

	public String getSubject(String secretKey, String token) {
		return getClaims(secretKey, token).getSubject();
	}

	public boolean isTokenExpired(String secretKey, String token) {
		return getClaims(secretKey, token).getExpiration().after(new Date(System.currentTimeMillis()));
	}

	public String generateToken(String username) {
		return generateToken(username, "Demo", "Sujith@123");
	}

	public void validateToken(String token, String username) {
		
		
	}
}

```

---

### 15. RolesUtil.java

#### src\main\java\com\suji\jwt\demo\util\RolesUtil.java

```java

package com.suji.jwt.demo.util;

import java.util.List;

public class RolesUtil {
	public static List<String> getAllRoles(){
		return List.of(Auth.ADMIN, Auth.EMP, Auth.MRNG, Auth.TL);
	}
}

```

---

### 16. SecurityFilter.java

#### src\main\java\com\suji\jwt\filter\SecurityFilter.java

```java

package com.suji.jwt.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.suji.jwt.demo.util.JwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. Read token from Auth header
		Optional<String> token = Optional.ofNullable(request.getHeader("Authorization"));
		if (token.isPresent()) {
			String username = jwtUtil.getUsername(token.get());
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				//TODO Validate Token and it returns boolean value
				boolean isValid = true;
				
				if(isValid) {
					UsernamePasswordAuthenticationToken a = new UsernamePasswordAuthenticationToken(username, userDetails.getPassword());
					
					a.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				}
			}
			
			
		}
		filterChain.doFilter(request, response);

	}

}

```

---

### 17. JwtDemoApplicationTests.java

#### src\test\java\com\suji\jwt\demo\JwtDemoApplicationTests.java

```java

package com.suji.jwt.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

