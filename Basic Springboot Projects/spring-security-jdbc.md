# spring-security-jdbc


### File Structure
```pre
+ spring-security-jdbc\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  access-denied.html
		|---  home.html
		|---  manager.html
		|---  settings.html
		|---  signup.html
		|---  userdetails.html
	+ \src\main\resources\templates\error
		|---  404.html
	+ \src\main\java\com\suji\security
		|---  SpringSecurityJdbcApplication.java
	+ \src\test\java\com\suji\security
		|---  SpringSecurityJdbcApplicationTests.java
	+ \src\main\java\com\suji\security\controller
		|---  DemoController.java
	+ \src\main\java\com\suji\security\repository
		|---  UserRepository.java
	+ \src\main\java\com\suji\security\config
		|---  WebSecurityConfig.java
	+ \src\main\java\com\suji\security\entity
		|---  Authorities.java
		|---  User.java
	+ \src\main\java\com\suji\security\sercice
		|---  UserDetailsServiceImpl.java
		|---  UserService.java
	+ \src\main\java\com\suji\security\util
		|---  MyUserDetails.java
```
### Index
```pre
1. application.properties
2. controller\DemoController.java
3. repository\UserRepository.java
4. src\main\java\com\suji\security\SpringSecurityJdbcApplication.java
5. src\main\java\com\suji\security\config\WebSecurityConfig.java
6. src\main\java\com\suji\security\entity\Authorities.java
7. src\main\java\com\suji\security\entity\User.java
8. src\main\java\com\suji\security\sercice\UserDetailsServiceImpl.java
9. src\main\java\com\suji\security\sercice\UserService.java
10. src\main\java\com\suji\security\util\MyUserDetails.java
11. resources\templates\access-denied.html
12. resources\templates\error\404.html
13. resources\templates\home.html
14. resources\templates\manager.html
15. resources\templates\settings.html
16. resources\templates\signup.html
17. resources\templates\userdetails.html
18. src\test\java\com\suji\security\SpringSecurityJdbcApplicationTests.java

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
```

---

### 2. DemoController.java

#### controller\DemoController.java

```java

package com.suji.security.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suji.security.entity.User;
import com.suji.security.sercice.UserService;

@Controller
public class DemoController {
	
	
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	
	@Autowired
	private UserService service;

	@RequestMapping(path = {"","/"})
	public String home() {
		return "home";
	}
	
	@RequestMapping(path = {"/signup"})
	public String showSignupForm(Model model) {
		model.addAttribute("userdetails", new User("vin","vin@123","MANAGER"));
		log.error("Inside /signup");
		return "singup";
	}
	
	// /save-user
	@RequestMapping(path = {"/save-user"}, method = RequestMethod.POST)
	public String saveUserDetails(User user) {
		service.registerNewUserAccount(user);
		return "redirect:/home";
	}
	
	
	@RequestMapping("/manager")
	public String manager() {
		return "/manager";
	}
	
	@RequestMapping("/settings")
	public String settings() {
		return "/settings";
	}
	
	//Add request mappiing for /access-denied
	@RequestMapping("/access-denied")
	public String accessDenied(HttpSession session) {
		System.out.println(session.getId());
		return "access-denied";
	}
	
}

```

---

### 3. UserRepository.java

#### repository\UserRepository.java

```java

package com.suji.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.suji.security.entity.User;
 
public interface UserRepository extends CrudRepository<User, Long> {
 
    //@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}

```

---

### 4. SpringSecurityJdbcApplication.java

#### src\main\java\com\suji\security\SpringSecurityJdbcApplication.java

```java

package com.suji.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;

import com.suji.security.entity.User;
import com.suji.security.repository.UserRepository;
import com.suji.security.sercice.UserService;

@SpringBootApplication
public class SpringSecurityJdbcApplication  implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJdbcApplication.class, args);
	}

	@Autowired
	private UserService service;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
	//	service.registerNewUserAccount(new User("sujith","suji@123","ADMIN"));
	//	System.out.println("User added.");
	}

}

```

---

### 5. WebSecurityConfig.java

#### src\main\java\com\suji\security\config\WebSecurityConfig.java

```java

package com.suji.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.suji.security.sercice.UserDetailsServiceImpl;
 
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
            .formLogin().permitAll()
            .and()
            .logout().permitAll();
    }
}
```

---

### 6. Authorities.java

#### src\main\java\com\suji\security\entity\Authorities.java

```java

package com.suji.security.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table(name = "authorities")
public class Authorities {

	@Id
	private int id;
	@ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private String username;
	private String authority;
	
}

```

---

### 7. User.java

#### src\main\java\com\suji\security\entity\User.java

```java

package com.suji.security.entity;

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

### 8. UserDetailsServiceImpl.java

#### src\main\java\com\suji\security\sercice\UserDetailsServiceImpl.java

```java

package com.suji.security.sercice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.suji.security.entity.User;
import com.suji.security.repository.UserRepository;
import com.suji.security.util.MyUserDetails;
 
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

### 9. UserService.java

#### src\main\java\com\suji\security\sercice\UserService.java

```java

package com.suji.security.sercice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suji.security.entity.User;
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
}

```

---

### 10. MyUserDetails.java

#### src\main\java\com\suji\security\util\MyUserDetails.java

```java

package com.suji.security.util;

import com.suji.security.entity.User;

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

### 11. access-denied.html

#### resources\templates\access-denied.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Good Thymes Virtual Grocery</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>

	<h3>Access Denied</h3>
	<h2>Welcome</h2>
	<p>Spring Security Thymeleaf tutorial</p>
	
	<div sec:authorize="hasRole('USER')">Text visible to user.</div>
	<div sec:authorize="hasRole('ADMIN')">Text visible to admin.</div>
	<div sec:authorize="isAuthenticated()">Text visible only to
		authenticated users.</div>
	Authenticated username:
	<div sec:authentication="name"></div>
	Authenticated user roles:
	<div sec:authentication="principal.authorities"></div>
	<h1 sec:authentication="principal.authorities"></h1>
	

	<a th:href="@{/settings}">Settings</a>
	<a th:href="@{/manager}">Manager</a>
	<a th:href="@{/logout}">Logout</a>

</body>

</html>
```

---

### 12. 404.html

#### resources\templates\error\404.html

```html

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>404 Not Found</title>
        <meta charset="utf-8" />
    </head>
    <body>
    
    <!-- https://stackoverflow.com/questions/37398385/spring-boot-and-custom-404-error-page -->
    <!-- https://docs.spring.io/spring-boot/docs/1.4.3.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-error-handling-custom-error-pages -->
        <h3>404 Not Found</h3>
        <h1 th:text="${errorCode}">404</h1>
        <p th:utext="${errorMessage}">Error java.lang.NullPointerException</p>
        <a href="/" th:href="@{/}">Back to Home Page</a>
    </body>
    
</html>


```

---

### 13. home.html

#### resources\templates\home.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Good Thymes Virtual Grocery</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>

	<h3>HOME PAGE</h3>


	<a class="btn btn-primary" th:href="@{/login}" >Login</a>
	<a class="btn btn-primary" th:href="@{/signup}" >Signup</a>
	



</body>

</html>
```

---

### 14. manager.html

#### resources\templates\manager.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Good Thymes Virtual Grocery</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>

	<h3>Hello Manager!</h3>
	
	<div sec:authorize="hasRole('USER')">Text visible to user.</div>
	<div sec:authorize="hasRole('ADMIN')">Text visible to admin.</div>
	<div sec:authorize="isAuthenticated()">Text visible only to
		authenticated users.</div>
	Authenticated username:
	<div sec:authentication="name"></div>
	Authenticated user roles:
	<div sec:authentication="principal.authorities"></div>
	<h1 sec:authentication="principal.authorities"></h1>
	
	<a th:href="@{/settings}">Settings</a>
	<a th:href="@{/manager}">Manager</a>
	<a th:href="@{/logout}">Logout</a>

</body>

</html>
```

---

### 15. settings.html

#### resources\templates\settings.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Good Thymes Virtual Grocery</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>

	<h3>Hello Admin!</h3>
	<h2>Welcome</h2>
	<p>Spring Security Thymeleaf tutorial</p>
	
	<div sec:authorize="hasRole('USER')">Text visible to user.</div>
	<div sec:authorize="hasRole('ADMIN')">Text visible to admin.</div>
	<div sec:authorize="isAuthenticated()">Text visible only to
		authenticated users.</div>
	Authenticated username:
	<div sec:authentication="name"></div>
	Authenticated user roles:
	<div sec:authentication="principal.authorities"></div>
	<h1 sec:authentication="principal.authorities"></h1>

	<a th:href="@{/settings}">Settings</a>
	<a th:href="@{/manager}">Manager</a>
	<a th:href="@{/logout}">Logout</a>

</body>

</html>
```

---

### 16. signup.html

#### resources\templates\signup.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Good Thymes Virtual Grocery</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>


	<h2>Signup from</h2>
<!--
	<form th:action="@{/save-user}" th:object="${userdetails}">
		  username, password, role 
		<input type="text" name="username" th:field="*{username}"><br>

		<input type="password" name="password" th:field="*{password}"><br>

		<select name="roles" th:field="*{role}">
			<option value="USER">USER</option>
			<option value="ADMIN">ADMIN</option>
			<option value="MANAGER">MANAGER</option>
		</select> <br /> <br /> <input type="submit" value="Submit">
	</form>
	-->

</body>

</html>
```

---

### 17. userdetails.html

#### resources\templates\userdetails.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Good Thymes Virtual Grocery</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>

<body>

	<h3>User details</h3>

	<div sec:authorize="isAuthenticated()">Text visible only to
		authenticated users.</div>
		
		
	Username:
	
	<h1 sec:authentication="name"></h1>
	Authenticated user roles:
	<h1 sec:authentication="principal.authorities"></h1>

	<h1>Session id: <span th:text="${session.id}"></span></h1>

	<!-- Content for EMPLOYEE-->
	<div sec:authorize="hasRole('EMPLOYEE')">
		<a th:href="@{/logout}">Logout</a>
	</div>

	<!-- Content for ADMIN -->
	<div sec:authorize="hasRole('ADMIN')">
		<a th:href="@{/settings}">Settings</a>
	</div>

	<!-- Content for MANAGER -->
	<div sec:authorize="hasRole('MANAGER')">
		<a th:href="@{/manager}">Manager</a>
	</div>





</body>

</html>
```

---

### 18. SpringSecurityJdbcApplicationTests.java

#### src\test\java\com\suji\security\SpringSecurityJdbcApplicationTests.java

```java

package com.suji.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityJdbcApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

