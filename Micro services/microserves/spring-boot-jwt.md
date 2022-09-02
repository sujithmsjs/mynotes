# spring-boot-jwt


### File Structure
```pre
+ spring-boot-jwt\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\javainuse
		|---  SpringBootHelloWorldApplication.java
	+ \src\main\java\com\javainuse\model
		|---  JwtRequest.java
		|---  JwtResponse.java
		|---  JwtUser.java
		|---  Role.java
	+ \src\main\java\com\javainuse\controller
		|---  HelloWorldController.java
		|---  JwtAuthenticationController.java
		|---  UserController.java
	+ \src\main\java\com\javainuse\service
		|---  JwtUserDetailsService.java
	+ \src\main\java\com\javainuse\repository
		|---  UserRepository.java
	+ \src\main\java\com\javainuse\advice
		|---  AuthenticationAdvice.java
	+ \src\main\java\com\javainuse\config
		|---  JwtAuthenticationEntryPoint.java
		|---  JwtRequestFilter.java
		|---  JwtTokenUtil.java
		|---  WebSecurityConfig.java
	+ \src\main\java\com\javainuse\exception
		|---  CustomExceptionDetails.java
	+ \src\main\java\com\javainuse\reference
		|---  App2.java
		|---  TokenGenerator.java
	+ \src\main\java\com\javainuse\util
		|---  Constants.java
```
### Index
```pre
1. application.properties
2. model\JwtRequest.java
3. model\JwtResponse.java
4. model\JwtUser.java
5. model\Role.java
6. controller\HelloWorldController.java
7. controller\JwtAuthenticationController.java
8. controller\UserController.java
9. service\JwtUserDetailsService.java
10. repository\UserRepository.java
11. src\main\java\com\javainuse\SpringBootHelloWorldApplication.java
12. src\main\java\com\javainuse\advice\AuthenticationAdvice.java
13. src\main\java\com\javainuse\config\JwtAuthenticationEntryPoint.java
14. src\main\java\com\javainuse\config\JwtRequestFilter.java
15. src\main\java\com\javainuse\config\JwtTokenUtil.java
16. src\main\java\com\javainuse\config\WebSecurityConfig.java
17. src\main\java\com\javainuse\exception\CustomExceptionDetails.java
18. src\main\java\com\javainuse\reference\App2.java
19. src\main\java\com\javainuse\reference\TokenGenerator.java
20. src\main\java\com\javainuse\util\Constants.java

```

---

### 1. application.properties

#### application.properties

```properties

jwt.secret=javainuse
jwt.get.token.uri=/authenticate

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

# spring.jpa.defer-datasource-initialization=true


spring.h2.console.enabled=true


# Swagger
# To disable Swagger default Pet-Store URL
springdoc.swagger-ui.disable-swagger-default-url=true

# To Change the Doc path 
#springdoc.api-docs.path = /javainuse-openap
```

---

### 2. JwtRequest.java

#### model\JwtRequest.java

```java

package com.javainuse.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest{
	
	private String username;
	private String password;

}
```

---

### 3. JwtResponse.java

#### model\JwtResponse.java

```java

package com.javainuse.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
```

---

### 4. JwtUser.java

#### model\JwtUser.java

```java

package com.javainuse.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JwtUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String username;

	private String password;
	

	@JoinColumn(name = "roleId")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();
	
	private boolean enabled;


	public JwtUser(String name, String username, String password, Set<String> roles, boolean enabled) {
		super();
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.roles = roles.stream().map(Role::new).collect(Collectors.toSet());
		this.enabled = enabled;
		log.info("Created: "+this);
	}
	

}

```

---

### 5. Role.java

#### model\Role.java

```java

package com.javainuse.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	public Role(String name) {
		super();
		this.name = name;
	}
	
	
}

```

---

### 6. HelloWorldController.java

#### controller\HelloWorldController.java

```java

package com.javainuse.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin()
@SecurityRequirement(name = "jwtapi")
public class HelloWorldController {

	
	@GetMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping({ "/time" })
	public String time() {
		return "Hello Time";
	}
	
	@GetMapping({ "/check" })
	public String check() {
		return "Value "+(1/0);
	}

}

```

---

### 7. JwtAuthenticationController.java

#### controller\JwtAuthenticationController.java

```java

package com.javainuse.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.model.JwtRequest;
import com.javainuse.model.JwtResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		log.info("Welcome to : /authenticate");
		log.info("JwtRequest: "+authenticationRequest);
		

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		log.info("Generated new token for you: "+token);
		log.info("Sending response back...");
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		log.info("Inside authenticate({}, {}) "+username, password);
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		authenticationManager.authenticate(authenticationToken);
	}
}

```

---

### 8. UserController.java

#### controller\UserController.java

```java

package com.javainuse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.model.JwtUser;
import com.javainuse.repository.UserRepository;
import com.javainuse.util.Constants;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "jwtapi")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/profile")
	public JwtUser getProfile(HttpServletRequest request) {
		String token = request.getHeader("Authorization").substring(6);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		log.info("Retrived Username: " + username);
		JwtUser user = repository.findByUsername(username).orElse(null);
		log.info("Retrived JwtUser: " + user);
		return user;
	}
	
	@PostMapping("/")
	public JwtUser save(@RequestBody JwtUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		log.info("Jwt User prompted to save {}", user);
		return repository.save(user);
	}
	
	@GetMapping("/")
	public List<JwtUser> findAll() {
	 	return repository.findAll();
	}
	
	
	
	@GetMapping("/admin/")
	public Map<String, String> admin() {
	 	var map = new HashMap<String, String>();
	 	map.put("Admin", "This is Admin content... only visible to Admins");
		return map;
	}
	
	@GetMapping("/tl/")
	public Map<String, String> tl() {
	 	var map = new HashMap<String, String>();
	 	map.put(Constants.TL, "This is TL content... only visible to TLS");
		return map;
	}
	
	@GetMapping("/employee/")
	public Map<String, String> employee() {
	 	var map = new HashMap<String, String>();
	 	map.put(Constants.EMPLOYEE, "This is EMPLOYEE content... only visible to EMPLOYEEs");
		return map;
	}
	
	@GetMapping("/ceo/")
	public Map<String, String> ceo() {
	 	var map = new HashMap<String, String>();
	 	map.put(Constants.CEO, "This is ceo content... only visible to Ceos");
		return map;
	}

	
	
	
}

```

---

### 9. JwtUserDetailsService.java

#### service\JwtUserDetailsService.java

```java

package com.javainuse.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javainuse.model.JwtUser;
import com.javainuse.model.Role;
import com.javainuse.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	//	String encode = encoder.encode("sujith@123");
	//	log.debug("loadUserByUsername({})", username);

		Optional<JwtUser> optional = repository.findByUsername(username);

		log.info("Username is Present?: "+optional.isPresent());
	//	log.info("JwtUsername: "+optional.get());

		
		JwtUser jwtUser = optional.orElseThrow( () -> new UsernameNotFoundException(username));
		log.info("Throws error: "+optional.get());
		

		Set<SimpleGrantedAuthority> roles = toSimpleGrantedAuthority(jwtUser.getRoles());
		
		log.info("Roles: "+roles);
		User user = new User(username, encoder.encode(jwtUser.getPassword()),roles);
		log.info("Spring User: " + user);
		return user;
	}

	private Set<SimpleGrantedAuthority> toSimpleGrantedAuthority(Set<Role> roles) {
	
		return roles.stream().map(Role::getName).map(SimpleGrantedAuthority:: new).collect(Collectors.toSet());

		
	}



}
```

---

### 10. UserRepository.java

#### repository\UserRepository.java

```java

package com.javainuse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.model.JwtUser;

public interface  UserRepository extends JpaRepository<JwtUser, Integer>{

	Optional<JwtUser> findByUsername(String username);



	
	
}

```

---

### 11. SpringBootHelloWorldApplication.java

#### src\main\java\com\javainuse\SpringBootHelloWorldApplication.java

```java

package com.javainuse;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javainuse.model.JwtUser;
import com.javainuse.repository.UserRepository;
import com.javainuse.util.Constants;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
@SecurityScheme(name = "jwtapi", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
public class SpringBootHelloWorldApplication implements ApplicationRunner {

	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
		log.info("Application get started");	
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		JwtUser u1 = new JwtUser("Sujith", "sujithm","suji@123", Set.of(Constants.CEO, Constants.TL), true);
		JwtUser u2 = new JwtUser("Vineeth", "vin", "vin@123", Set.of(Constants.ADMIN), true);
		JwtUser u3 = new JwtUser("Pranisha", "pranisha", "prani@123", Set.of(Constants.TL), true);
		JwtUser u4 = new JwtUser("Swathi", "swathi", "swathi@123", Set.of(Constants.MANAGER), true);
		JwtUser u5 = new JwtUser("Susmitha", "susmitha", "ammu@123", Set.of(Constants.EMPLOYEE), true);
		
		repository.save(u1);
		repository.save(u2);
		repository.save(u3);
		repository.save(u4);
		repository.save(u5);
	}
}
```

---

### 12. AuthenticationAdvice.java

#### src\main\java\com\javainuse\advice\AuthenticationAdvice.java

```java

package com.javainuse.advice;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javainuse.exception.CustomExceptionDetails;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice

public class AuthenticationAdvice { // extends ResponseEntityExceptionHandler {

	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		log.info("Inside initBinder");
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		log.info("Inside initBinder, Date Format prompted:{} ",dateFormat);
		binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(dateFormat, true));
		
	}

	// Handling Generic Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomExceptionDetails> handleException(Exception ex) {
		log.info("Inside handleException(Exception ex) nsg:{}",ex.getMessage());
		
		// Adding details about exception
		CustomExceptionDetails customEx = new CustomExceptionDetails(ex, HttpStatus.INTERNAL_SERVER_ERROR,"handle by Exception");

		// Returning ResponseEntity with Exception details.
		return new ResponseEntity<CustomExceptionDetails>(customEx, HttpStatus.NOT_FOUND);
	}

	// EMPLOYEE NOT FOUND EXPECTION HANDLER
	@ResponseBody
	@ExceptionHandler(io.jsonwebtoken.SignatureException.class)
	// @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<CustomExceptionDetails> employeeNotFoundHandler(io.jsonwebtoken.SignatureException ex) {
		log.info("Inside employeeNotFoundHandler(io.jsonwebtoken.SignatureException ex) nsg:{}",ex.getMessage());
		// Adding details about exception
		CustomExceptionDetails customEx = new CustomExceptionDetails();

		customEx.setStatus(HttpStatus.NOT_FOUND.value());
		customEx.setMessage(ex.getMessage());
		customEx.setExceptionClass(ex.getClass());
		customEx.setTimestamp(LocalDateTime.now());

		// Returning ResponseEntity with Exception details.
		return new ResponseEntity<CustomExceptionDetails>(customEx, HttpStatus.NOT_FOUND);
	}

	// EMPLOYEE NOT FOUND EXPECTION HANDLER
	@ResponseBody
	@ExceptionHandler(BadCredentialsException.class)
	// @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<CustomExceptionDetails> handleBadCredentialsException(BadCredentialsException ex) {
		log.error("Inside handleBadCredentialsException(BadCredentialsException ex) msg:{}",ex.getMessage());
		// Adding details about exception
		CustomExceptionDetails customEx = new CustomExceptionDetails();

		customEx.setStatus(HttpStatus.UNAUTHORIZED.value());
		customEx.setCustomMessage("Check your Username and Password, and try again.");
		customEx.setMessage(ex.getMessage());
		customEx.setExceptionClass(ex.getClass());
		customEx.setTimestamp(LocalDateTime.now());

		// Returning ResponseEntity with Exception details.
		return new ResponseEntity<CustomExceptionDetails>(customEx, HttpStatus.UNAUTHORIZED);
	}

	// Handle MalformedJwtException

	@ResponseBody
	@ExceptionHandler(MalformedJwtException.class)
	// @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<CustomExceptionDetails> handleMalformedJwtException(MalformedJwtException ex) {
		log.error("Inside  handleMalformedJwtException(MalformedJwtException ex) msg:{}",ex.getMessage());
		// Adding details about exception
		CustomExceptionDetails customEx = new CustomExceptionDetails();

		customEx.setStatus(HttpStatus.NOT_FOUND.value());
		customEx.setCustomMessage("Token has issues, please check");
		customEx.setMessage(ex.getMessage());
		customEx.setExceptionClass(ex.getClass());
		customEx.setTimestamp(LocalDateTime.now());

		// Returning ResponseEntity with Exception details.
		return new ResponseEntity<CustomExceptionDetails>(customEx, HttpStatus.BAD_REQUEST);
	}

}

```

---

### 13. JwtAuthenticationEntryPoint.java

#### src\main\java\com\javainuse\config\JwtAuthenticationEntryPoint.java

```java

package com.javainuse.config;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.javainuse.exception.CustomExceptionDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		log.info("Inside commence of AuthenticationEntryPoint");
		
		CustomExceptionDetails ced = new CustomExceptionDetails();
		ced.setCustomMessage("Via AuthenticationEntryPoint");
		ced.setMessage(authException.getMessage());
		ced.setExceptionClass(authException.getClass());
		ced.setTimestamp(LocalDateTime.now());
		ced.setStatus(response.getStatus());
		
		String string = mapper.writeValueAsString(ced);
		response.getWriter().write(string);
		response.setContentType("application/json");
		
		response.setStatus(HttpStatus.FORBIDDEN.value());
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

### 14. JwtRequestFilter.java

#### src\main\java\com\javainuse\config\JwtRequestFilter.java

```java

package com.javainuse.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javainuse.advice.AuthenticationAdvice;
import com.javainuse.service.JwtUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationAdvice advice;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		log.info("Inside doFilterInternal() method");

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		log.info("Received token: {}", requestTokenHeader);

		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the
		// Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(6);

			log.info("Token is found starts with token={}", jwtToken);
			log.info("Getting username from the token...");

			// Error! is raising in the component level.
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				log.info("Extracted username from the token is {}", username);
			} catch (Exception e) {
				log.error(" Error at Decoding token:{} ", e.getMessage());

				// response.sendError(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
				response.getWriter().write(e.getMessage());
				response.setStatus(HttpStatus.BAD_REQUEST.value());

				// doFilter(request, response, chain);
				log.info(" After handling by Advice! ");
			}

			log.info(" Check... after try-catch error! ");

			log.info("Your're username is :" + username);


		} else {
			// logger.warn("JWT Token does not begin with Bearer String");
			log.info("Token is null;");
		}

		// Once we get the token validate it.
		if (username == null)
			log.info("Can't retrive Username!");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null)
			log.info("Authentication : " + authentication);

		if (username != null && authentication == null) {

			log.info("Validation started.");

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication

			Boolean validateToken = jwtTokenUtil.validateToken(jwtToken, userDetails);

			if (validateToken) {
				log.info("Token validation Successful");
				log.info("Validation jwt :" + validateToken);

				var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the Spring Security
				// Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				log.info("Authentication : " + SecurityContextHolder.getContext().getAuthentication());
				log.info("Hurry!!! Your're authenticated...");
				
				
			} else {
				log.info("Token validation Failed");
				
				response.getWriter().write("JWT Token validation failed.");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			}
		}
		log.info("OncePerRequestFilter: Doing Filter Chain");
		chain.doFilter(request, response);
		
		
	}

}

```

---

### 15. JwtTokenUtil.java

#### src\main\java\com\javainuse\config\JwtTokenUtil.java

```java

package com.javainuse.config;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	
	public static final long JWT_TOKEN_VALIDITY = 5*60;

	@Value("${jwt.secret}")
	private String secret;

	public String getUsernameFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		
		byte[] encode = Base64.getEncoder().encode(secret.getBytes());
		
		return Jwts.parser().setSigningKey(encode).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secret.getBytes())).compact();
		
	
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}

```

---

### 16. WebSecurityConfig.java

#### src\main\java\com\javainuse\config\WebSecurityConfig.java

```java

package com.javainuse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.javainuse.util.Constants;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter filter;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info("Inside configureGlobal(AuthenticationManagerBuilder)");
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("Inside passwordEncoder()");
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		log.info("Inside authenticationManagerBean()");
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		log.info("Inside configure(HttpSecurity httpSecurity)");
		// We don't need CSRF for this example
		httpSecurity.csrf().disable().addFilterBefore(filter, LogoutFilter.class)
				// dont authenticate this particular request
		
				.authorizeRequests()
				.antMatchers("/authenticate","/h2-console/**").permitAll()
				
				// allow Swagger-UI and OpenApi
				.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
				
				
				.antMatchers("/users/admin/**").hasAnyAuthority(Constants.ADMIN)
				.antMatchers("/users/tl/**").hasAnyAuthority(Constants.TL)
				.antMatchers("/users/ceo/**").hasAnyAuthority(Constants.CEO)
				.antMatchers("/users/employee/**").hasAnyAuthority(Constants.EMPLOYEE)
				
				// all other requests need to be authenticated
				.anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}

```

---

### 17. CustomExceptionDetails.java

#### src\main\java\com\javainuse\exception\CustomExceptionDetails.java

```java

package com.javainuse.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionDetails {

	private int status;
	private String customMessage;
	private String message;
	private Class exceptionClass;
	private LocalDateTime timestamp;

	public CustomExceptionDetails(Exception ex, HttpStatus status, String customMessage) {

		this.status = status.value();
		this.message = ex.getMessage();
		this.customMessage = customMessage;
		this.exceptionClass = ex.getClass();
		this.timestamp = LocalDateTime.now();
		log.error(this.toString());
	}

}

```

---

### 18. App2.java

#### src\main\java\com\javainuse\reference\App2.java

```java

package com.javainuse.reference;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;

public class App2 {

	public static void main5(String[] args) {
		TokenGenerator util = new TokenGenerator("sujith@123");
		
		String token = util.generateToken("sujith");
		System.out.println(token);
		
		
		 String username = util.getUsernameFromToken(token);
		System.out.println(username);
		System.out.println(util.getCountDownSecounds(token));
		
		System.out.println("Is okay: "+util.validateToken("sujith", token));
		
	}

	public static void main4(String[] args) {
		TokenGenerator util = new TokenGenerator("Hellow@123");
		// String token = util.generateToken("sujith");
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWppdGgiLCJpYXQiOjE2NTYwNzYwMjAsImV4cCI6MTY1NjA3NjE0MH0.f4CKu3WMF1wB8zWNiJUO6jpSZjtbslUKkvRb9Y-j6-g";
		Claims claims = util.getClaims(token);
				

		System.out.println(token);
		System.out.println("Username: " + claims.getSubject());
		System.out.println("Has Time: " + util.getCountDownSecounds(token));
	}

	public static void main3(String[] args) {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime withMinute = LocalDateTime.now().plusMinutes(10);

		Duration between = Duration.between(now, withMinute);
		System.out.println(now);
		System.out.println(withMinute);
		System.out.println("Duration: " + between.getSeconds());

	}

	public static void main2(String[] args) {
		TokenGenerator util = new TokenGenerator("Hellow@123");
		String token = util.generateToken("sujith");
		System.out.println(token);

		// Claims claims =
		// util.getClaims("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWppdGgiLCJpYXQiOjY3OTI1NjUxNjc5MywiZXhwIjo2NzkyNTY1MTY4ODR9.K08kT-UIXMU0SkKnZXY0SQzPuQ_zgHI-S9B-VbE9Ek8");

		Claims claims = util.getClaims(token);
		System.out.println(TimeUnit.MINUTES.toMillis(1));
		System.out.println(claims.getExpiration());
		System.out.println(claims.getIssuedAt());
		System.out.println();

		// System.out.println( new
		// Date(System.currentTimeMillis()).getSeconds()-claims.getExpiration().getSeconds());

	}
}

```

---

### 19. TokenGenerator.java

#### src\main\java\com\javainuse\reference\TokenGenerator.java

```java

package com.javainuse.reference;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerator {

	private final String SECRET_KEY;
	private static final long EXP_TIME_IN_MINS = 2;

	public TokenGenerator(String secret) {
		SECRET_KEY = secret;
	}

	// Generating Token
	public String generateToken(String username) {

		// Issure Date: NOW
		LocalDateTime now = LocalDateTime.now();

		// Expire Date:
		LocalDateTime withMinute = LocalDateTime.now().plusMinutes(EXP_TIME_IN_MINS);

		JwtBuilder builder = Jwts.builder()
				// .setId("101")
				.setSubject(username)
				// .setIssuer("Sujith")
				.setIssuedAt(toDate(now)).setExpiration(toDate(withMinute))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(SECRET_KEY.getBytes()));

		return builder.compact();
	}

	public long getCountDownSecounds(String token) {
		Claims claims = getClaims(token);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expireAt = LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());

		Duration between = Duration.between(now, expireAt);

		return between.toSeconds();
	}

	// Get Claims
	public Claims getClaims(String token) {
		byte[] encode = Base64.getEncoder().encode(SECRET_KEY.getBytes());

		return Jwts.parser().setSigningKey(encode).parseClaimsJws(token).getBody();
	}

	public String getUsernameFromToken(String token) {
		return getClaims(token).getSubject();
	}

	// Print Claims
	public void printClaims(String token) {
		Claims claims = getClaims(token);

		System.out.println("Reading Claims for token: " + token);
		System.out.println(claims.getId());
		System.out.println(claims.getSubject());
		System.out.println(claims.getIssuer());
		System.out.println(claims.getIssuedAt());
		System.out.println(claims.getExpiration());

	}

	public boolean isTimeExpired(String token) {
		return getCountDownSecounds(token) < 0;
	}

	public boolean validateToken(String username, String token) {
		return getClaims(token).getSubject().equals(username) && !isTimeExpired(token);
	}

	private Date toDate(LocalDateTime dateTime) {
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}

```

---

### 20. Constants.java

#### src\main\java\com\javainuse\util\Constants.java

```java

package com.javainuse.util;

public class Constants {

	public static final String MANAGER = "MANAGER";
	public static final String TL = "TEAM LEADER";
	public static final String ADMIN = "ADMIN";
	public static final String CEO = "CEO";
	public static final String EMPLOYEE = "EMPLOYEE";
	public static final String SSL = "SERINIOR DEVELOPER";
	
}

```

---

