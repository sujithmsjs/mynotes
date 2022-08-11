# spring-z1-security-inmemory


### File Structure
```pre
+ spring-z1-security-inmemory\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  admin.html
		|---  home.html
		|---  login.html
		|---  manager.html
		|---  signup.html
		|---  userdetails.html
	+ \src\main\resources\templates\fragments
		|---  details.html
		|---  foot.html
		|---  head.html
		|---  nav.html
	+ \src\main\java\com\suji\security
		|---  SpringZ1SecurityInmemoryApplication.java
	+ \src\test\java\com\suji\security
		|---  SpringZ1SecurityInmemoryApplicationTests.java
	+ \src\main\java\com\suji\security\model
		|---  MyUserDetails.java
		|---  User.java
	+ \src\main\java\com\suji\security\controller
		|---  RolesController.java
		|---  WelcomeController.java
	+ \src\main\java\com\suji\security\service
		|---  UserDetailsServiceImpl.java
		|---  UserService.java
	+ \src\main\java\com\suji\security\repository
		|---  UserRepository.java
	+ \src\main\java\com\suji\security\configuration
		|---  WebSecurityConfig.java
```
### Index
```pre
1. application.properties
2. model\MyUserDetails.java
3. model\User.java
4. controller\RolesController.java
5. controller\WelcomeController.java
6. service\UserDetailsServiceImpl.java
7. service\UserService.java
8. repository\UserRepository.java
9. src\main\java\com\suji\security\SpringZ1SecurityInmemoryApplication.java
10. src\main\java\com\suji\security\configuration\WebSecurityConfig.java
11. resources\templates\admin.html
12. resources\templates\fragments\details.html
13. resources\templates\fragments\foot.html
14. resources\templates\fragments\head.html
15. resources\templates\fragments\nav.html
16. resources\templates\home.html
17. resources\templates\login.html
18. resources\templates\manager.html
19. resources\templates\signup.html
20. resources\templates\userdetails.html
21. src\test\java\com\suji\security\SpringZ1SecurityInmemoryApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


# Datasource

spring.datasource.url=jdbc:mysql://localhost:3306/secdemo
spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Settings

spring.jpa.hibernate.ddl-auto=update
#spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true


# Logging Settings
logging.level.root=INFO
logging.level.springframework.context=debug
logging.level.com.suji.security=DEBUG

#Spring Security Credintials
spring.security.user.name=sujith
spring.security.user.password=suji@123
```

---

### 2. MyUserDetails.java

#### model\MyUserDetails.java

```java

package com.suji.security.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private User user;

	public MyUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
```

---

### 3. User.java

#### model\User.java

```java

package com.suji.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

// For Spring Security util
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
	@Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String role;
    private boolean enabled;
    
    // username, password, role
    
	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
		enabled = true;
	}
}

```

---

### 4. RolesController.java

#### controller\RolesController.java

```java

package com.suji.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RolesController {

	@GetMapping("/profile")
	private String showProfile() {
		return "userdetails";
	}
	
	@GetMapping("/manager")
	private String showManager() {
		return "manager";
	}
	
	@GetMapping("/admin")
	private String showAdmin() {
		return "admin";
	}
	
	
}

```

---

### 5. WelcomeController.java

#### controller\WelcomeController.java

```java

package com.suji.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suji.security.model.User;
import com.suji.security.service.UserService;

@Controller
public class WelcomeController {

	
	@Autowired
	private UserService userService;
	
	// Init Logger
	private static final Logger log = LoggerFactory.getLogger(WelcomeController.class);

	
	
	// Homepage setup
	@RequestMapping(path = { "", "/", "/home" })
	public String showHome() {
		return "home";
	}
	
	@RequestMapping(path = {"/userdetails" })
	public String showUserDetails() {
		return "userdetails";
	}

	// Login page
	@RequestMapping(path = { "/login" })
	public String showLogin() {
		return "login";
	}

	// Singnup page
	@RequestMapping(path = { "/signup" })
	public String showSignup(/*@ModelAttribute User user*/ Model model) {
		model.addAttribute("user", new User("sujith","sujith@123","MANAGER"));
		return "signup";
	}

	// Process Signup
	@RequestMapping(path = { "/process-signup" }, method = RequestMethod.POST)
	public String processSignup(User user) {
		
		// Saving User Details
		User savedUser = userService.registerNewUserAccount(user);

		log.info("Saved user: "+savedUser);
		return "home";
	}

}

```

---

### 6. UserDetailsServiceImpl.java

#### service\UserDetailsServiceImpl.java

```java

package com.suji.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.suji.security.model.MyUserDetails;
import com.suji.security.model.User;
import com.suji.security.repository.UserRepository;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
    
    
    
    
 
}
```

---

### 7. UserService.java

#### service\UserService.java

```java

package com.suji.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suji.security.model.User;
import com.suji.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	public User registerNewUserAccount(User user){
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
	
	public User saveUser(User user) {
		return repository.save(user);
	}
}

```

---

### 8. UserRepository.java

#### repository\UserRepository.java

```java

package com.suji.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.suji.security.model.User;
 
public interface UserRepository extends CrudRepository<User, Long> {
 
    //@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}

```

---

### 9. SpringZ1SecurityInmemoryApplication.java

#### src\main\java\com\suji\security\SpringZ1SecurityInmemoryApplication.java

```java

package com.suji.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringZ1SecurityInmemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZ1SecurityInmemoryApplication.class, args);
	}

}

```

---

### 10. WebSecurityConfig.java

#### src\main\java\com\suji\security\configuration\WebSecurityConfig.java

```java

package com.suji.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.suji.security.service.UserDetailsServiceImpl;

// https://www.codejava.net/frameworks/spring-boot/spring-boot-security-authentication-with-jpa-hibernate-and-mysql

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.authorizeRequests()
		.antMatchers("/role/profile/**").hasRole("USER")
		.antMatchers("/role/admin/**").hasRole("ADMIN")
		.antMatchers("/role/manager/**").hasRole("MANAGER")
		.and()
		.formLogin()
		// .loginPage("/login.html")
		// .loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/userdetails", true)
		.failureUrl("/login?error=true").permitAll()
        .and()
        .logout().permitAll();
		
	}
}
```

---

### 11. admin.html

#### resources\templates\admin.html

```html

<!DOCTYPE html>
<html lang="en">

<head th:replace="~{fragments/head::head('This is title')}"></head>

<body>

	<nav th:replace="~{fragments/nav :: navbar}"></nav>

	<div class="container">
		<h1>This is Admin view</h1>
		<div th:replace="~{fragments/details :: login-details}"></div>
	</div>

</body>
</html>
```

---

### 12. details.html

#### resources\templates\fragments\details.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
	<div th:fragment="login-details">
		<h1 sec:authentication="name"></h1>
		<h1 sec:authentication="principal.authorities"></h1>

		<div sec:authorize="isAuthenticated()">Text visible only to
			authenticated users.</div>
	</div>
</body>
</html>

```

---

### 13. foot.html

#### resources\templates\fragments\foot.html

```html

</body>

</html>
```

---

### 14. head.html

#### resources\templates\fragments\head.html

```html

<!DOCTYPE html>
<html lang="en">

<head th:fragment="head(title)">
  <title th:text="${title}"></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
</div>
```

---

### 15. nav.html

#### resources\templates\fragments\nav.html

```html

<nav th:fragment="navbar" class="navbar navbar-inverse">
	<div class="container-fluid">
		
		<div class="navbar-header">
			<a class="navbar-brand" sec:authentication="name" href="#"></a>
		</div>

		<!-- Navigation button on Left side  -->
		<ul class="nav navbar-nav">
			<li><a th:href="@{/role/profile}" class="active">Profile</a></li>
			<li><a th:href="@{/role/manager}">Manger</a></li>
			<li><a th:href="@{/role/admin}">Admin</a></li>	

		</ul>

		<!-- Navigation button on Right side  -->
		<ul class="nav navbar-nav navbar-right">
			<li><a th:href="@{/logout}" class="navbar-righ">Logout</a></li>
		</ul>
		
	</div>
</nav>




```

---

### 16. home.html

#### resources\templates\home.html

```html

<!DOCTYPE html>
<html lang="en">

<head th:replace="~{fragments/head::head('This is title')}"></head>

<body>

	<nav th:replace="~{fragments/nav :: navbar}"></nav>

	<div class="container">
		<h1>HOME PAGE</h1>
		<div th:replace="~{fragments/details :: login-details}"></div>
	</div>

</body>
</html>
```

---

### 17. login.html

#### resources\templates\login.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">



<body>

	<h3>Login Page</h3>


	<a class="btn btn-primary" th:href="@{/login}" >Login</a>
	<a class="btn btn-primary" th:href="@{/signup}" >Signup</a>
	
	


</body>

</html>
```

---

### 18. manager.html

#### resources\templates\manager.html

```html

<!DOCTYPE html>
<html lang="en">

<head th:replace="~{fragments/head::head('This is title')}"></head>

<body>

	<nav th:replace="~{fragments/nav :: navbar}"></nav>

	<div class="container">
		<h1>This is Manager view</h1>
		
		<div th:replace="~{fragments/details :: login-details}"></div>
	</div>

</body>
</html>
```

---

### 19. signup.html

#### resources\templates\signup.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head th:replace="~{fragments/head::head('This is title')}"></head>

<body>
	<nav th:replace="~{fragments/nav :: navbar}"></nav>	
	<h3>Signup Page</h3>
	
	<form th:method="post" th:action="@{/process-signup}" th:object="${user}">
	
		<input type="text"  th:field="*{username}"><br>

		<input type="text"  th:field="*{password}"><br>

		<select  th:field="*{role}">
			<option value="USER">USER</option>
			<option value="ADMIN">ADMIN</option>
			<option value="MANAGER">MANAGER</option>
		</select> <br /> <br /> <input type="submit" value="Submit">
	</form>
	


</body>

</html>
```

---

### 20. userdetails.html

#### resources\templates\userdetails.html

```html

<!DOCTYPE html>
<html lang="en">

<head th:replace="~{fragments/head::head('This is title')}"></head>

<body>

	<nav th:replace="~{fragments/nav :: navbar}"></nav>

	<div class="container">
		<h1>Home page</h1>
		<div th:replace="~{fragments/details :: login-details}"></div>
	</div>

</body>
</html>
```

---

### 21. SpringZ1SecurityInmemoryApplicationTests.java

#### src\test\java\com\suji\security\SpringZ1SecurityInmemoryApplicationTests.java

```java

package com.suji.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringZ1SecurityInmemoryApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

