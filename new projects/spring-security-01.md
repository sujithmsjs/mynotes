# spring-security-01


### File Structure
```pre
+ spring-security-01\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  admin.html
		|---  emp.html
		|---  home.html
		|---  inbox.html
		|---  tasks.html
	+ \src\main\java\com\suji\security
		|---  SpringSecurity01Application.java
	+ \src\main\java\com\suji\security\model
		|---  Attempts.java
	+ \src\main\java\com\suji\security\configuration
		|---  AppConfig.java
		|---  ApplicationConfig.java
	+ \src\main\java\com\suji\security\controller
		|---  HomeController.java
	+ \src\main\java\com\suji\security\repository
		|---  AttemptsRepository.java
	+ \src\main\java\com\suji\security\util
		|---  Auth.java
```
### Index
```pre
1. resources\application.properties
2. resources\templates\admin.html
3. resources\templates\emp.html
4. resources\templates\home.html
5. resources\templates\inbox.html
6. resources\templates\tasks.html
7. \model\Attempts.java
8. \src\main\java\com\suji\security\configuration\AppConfig.java
9. \src\main\java\com\suji\security\configuration\ApplicationConfig.java
10. \src\main\java\com\suji\security\controller\HomeController.java
11. \src\main\java\com\suji\security\repository\AttemptsRepository.java
12. \src\main\java\com\suji\security\SpringSecurity01Application.java
13. \src\main\java\com\suji\security\util\Auth.java

```

---

### 1. application.properties

#### resources\application.properties

```properties


spring.security.user.name = sujith
spring.security.user.password = sujith@123

```

---

### 2. admin.html

#### resources\templates\admin.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin!</h1>
	
	<a href="/login">Login</a>
	<a href="/inbox">Inbox</a>
	<a href="/admin">Admin</a>
	<a href="/home">Home</a>
	<a href="/emp">Emp Details</a>
	<a href="/tasks">Tasks</a>
	<a href="/logout">Logout</a>
</body>
</html>
```

---

### 3. emp.html

#### resources\templates\emp.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Employee Details Page!</h1>
	
	<a href="/login">Login</a>
	<a href="/inbox">Inbox</a>
	<a href="/admin">Admin</a>
	<a href="/home">Home</a>
	<a href="/emp">Emp Details</a>
	<a href="/tasks">Tasks</a>
	<a href="/logout">Logout</a>
	
</body>
</html>
```

---

### 4. home.html

#### resources\templates\home.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome Home!</h1>
	<a href="/login">Login</a>
	<a href="/inbox">Inbox</a>
	<a href="/admin">Admin</a>
	<a href="/home">Home</a>
	<a href="/emp">Emp Details</a>
	<a href="/tasks">Tasks</a>
	<a href="/logout">Logout</a>
</body>
</html>
```

---

### 5. inbox.html

#### resources\templates\inbox.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Inbox!</h1>
	<a href="/login">Login</a>
	<a href="/inbox">Inbox</a>
	<a href="/admin">Admin</a>
	<a href="/home">Home</a>
	<a href="/emp">Emp Details</a>
	<a href="/tasks">Tasks</a>
	<a href="/logout">Logout</a>
</body>
</html>
```

---

### 6. tasks.html

#### resources\templates\tasks.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Tasks!</h1>
	<a href="/login">Login</a>
	<a href="/inbox">Inbox</a>
	<a href="/admin">Admin</a>
	<a href="/home">Home</a>
	<a href="/emp">Emp Details</a>
	<a href="/tasks">Tasks</a>
	<a href="/logout">Logout</a>
</body>
</html>
```

---

### 7. Attempts.java

#### \model\Attempts.java

```java

package com.suji.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class Attempts { 
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private int id;
   private String username; 
   private int attempts;

}
```

---

### 8. AppConfig.java

#### \src\main\java\com\suji\security\configuration\AppConfig.java

```java

package com.suji.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig  {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

```

---

### 9. ApplicationConfig.java

#### \src\main\java\com\suji\security\configuration\ApplicationConfig.java

```java

package com.suji.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.suji.security.util.Auth;

@Configuration
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder encoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("sujith").password(encoder.encode("sujith@123")).authorities(Auth.ADMIN);
		auth.inMemoryAuthentication().withUser("scott").password(encoder.encode("scott@123")).authorities(Auth.EMP);
		auth.inMemoryAuthentication().withUser("james").password(encoder.encode("james@123")).authorities(Auth.TL);
	
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/admin").hasAuthority(Auth.ADMIN)
		.antMatchers("/tasks").hasAnyAuthority(Auth.ADMIN, Auth.TL)
		.anyRequest().authenticated()
		.and()
		// the boolean flags force the redirection even though 
        // the user requested a specific secured resource.
        .formLogin().defaultSuccessUrl("/home", true).permitAll()
		//.formLogin().successForwardUrl("/home").permitAll()
		.and()
		.logout().permitAll();
	}

}
```

---

### 10. HomeController.java

#### \src\main\java\com\suji\security\controller\HomeController.java

```java

package com.suji.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// VIEW Welcome Page
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}

	// VIEW Welcome Page
	@GetMapping("/admin")
	public String showAdmin() {
		return "admin";
	}

	// VIEW Welcome Page
	@GetMapping("/tasks")
	public String showTasks() {
		return "tasks";
	}

	// VIEW Welcome Page
	@GetMapping("/inbox")
	public String showinbox() {
		return "inbox";
	}
	
	// VIEW Welcome Page
		@GetMapping("/emp")
		public String showEmployee() {
			return "emp";
		}

}

```

---

### 11. AttemptsRepository.java

#### \src\main\java\com\suji\security\repository\AttemptsRepository.java

```java

package com.suji.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suji.security.model.Attempts;

@Repository 
public interface AttemptsRepository extends JpaRepository<Attempts, Integer> { 
   Optional<Attempts> findAttemptsByUsername(String username); 
}
```

---

### 12. SpringSecurity01Application.java

#### \src\main\java\com\suji\security\SpringSecurity01Application.java

```java

package com.suji.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurity01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity01Application.class, args);
		System.out.println("SpringSecurity01Application has been started.");
	}

}

```

---

### 13. Auth.java

#### \src\main\java\com\suji\security\util\Auth.java

```java

package com.suji.security.util;

public class Auth {
	public static final String MRNG = "Manger";
	public static final String TL = "Team Leader";
	public static final String ADMIN = "Admin";
	public static final String EMP = "Employee";
}

```

---

