# spring-security-demo


### File Structure
```pre
+ spring-security-demo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  admin.html
		|---  emp.html
		|---  header.html
		|---  home.html
		|---  inbox.html
		|---  post.html
		|---  register.html
		|---  tasks.html
		|---  test.html
	+ \src\main\resources\templates\employee
		|---  register.html
	+ \src\main\resources\templates\error
		|---  404.html
	+ \src\main\resources\templates\fragments
		|---  forms.html
	+ \src\main\java\com\suji\sec
		|---  SpringSecurityDemoApplication.java
	+ \src\test\java\com\suji\sec
		|---  SpringSecurityDemoApplicationTests.java
	+ \src\main\java\com\suji\sec\model
		|---  Employee.java
		|---  Post.java
		|---  User.java
	+ \src\main\java\com\suji\sec\controller
		|---  EmployeeController.java
		|---  HomeController.java
		|---  PostController.java
		|---  UserController.java
		|---  UserRestController.java
	+ \src\main\java\com\suji\sec\service
		|---  UserService.java
	+ \src\main\java\com\suji\sec\repository
		|---  PostRepository.java
		|---  UserRepository.java
	+ \src\main\java\com\suji\sec\config
		|---  AppConfig.java
		|---  SecurityConfig.java
	+ \src\main\java\com\suji\sec\util
		|---  Auth.java
		|---  RolesUtil.java
	+ \src\main\java\com\suji\sec\service\impl
		|---  UserServiceImpl.java
```
### Index
```pre
1. application.properties
2. model\Employee.java
3. model\Post.java
4. model\User.java
5. controller\EmployeeController.java
6. controller\HomeController.java
7. controller\PostController.java
8. controller\UserController.java
9. controller\UserRestController.java
10. service\UserService.java
11. repository\PostRepository.java
12. repository\UserRepository.java
13. src\main\java\com\suji\sec\SpringSecurityDemoApplication.java
14. src\main\java\com\suji\sec\config\AppConfig.java
15. src\main\java\com\suji\sec\config\SecurityConfig.java
16. src\main\java\com\suji\sec\service\impl\UserServiceImpl.java
17. src\main\java\com\suji\sec\util\Auth.java
18. src\main\java\com\suji\sec\util\RolesUtil.java
19. resources\templates\admin.html
20. resources\templates\emp.html
21. resources\templates\employee\register.html
22. resources\templates\error\404.html
23. resources\templates\fragments\forms.html
24. resources\templates\header.html
25. resources\templates\home.html
26. resources\templates\inbox.html
27. resources\templates\post.html
28. resources\templates\register.html
29. resources\templates\tasks.html
30. resources\templates\test.html
31. src\test\java\com\suji\sec\SpringSecurityDemoApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties




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

### 2. Employee.java

#### model\Employee.java

```java

package com.suji.sec.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	
	private LocalDate doj;
	
	
}
```

---

### 3. Post.java

#### model\Post.java

```java

package com.suji.sec.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String about;

	private String post;

	public Post(String about, String post) {
		this.about = about;
		this.post = post;
	}
	
	

}
```

---

### 4. User.java

#### model\User.java

```java

package com.suji.sec.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "user_passwd")
	private String password;

	@Column(name = "user_email")
	private String email;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "user_role")
	private List<String> roles;

	public User(String name, String email, String password, List<String> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	
	
}
```

---

### 5. EmployeeController.java

#### controller\EmployeeController.java

```java

package com.suji.sec.controller;

import java.time.LocalDate;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suji.sec.model.Employee;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	// Show Register form
	@GetMapping("/signup")
	public String showEmployeeRegister(Model model) {
		
 		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null,null);
 		authenticationToken.setDetails(null);
		
		
		model.addAttribute("employee", new Employee(191,"Sujith",LocalDate.now()));
		return "employee/register";
	}
	
	
	
	@PostMapping("/signup")
	@ResponseBody
	public Employee showEmployee(Employee employee) {
		return employee;
	}
	
	@PostMapping(path ="/v2/signup")
	@ResponseBody
	public Employee showEmployeeV2(int id, String name,String doj) {
		return new Employee(id,doj,null);
	}
	
}

```

---

### 6. HomeController.java

#### controller\HomeController.java

```java

package com.suji.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.suji.sec.model.Post;
import com.suji.sec.model.User;
import com.suji.sec.service.UserService;
import com.suji.sec.util.RolesUtil;

@Controller
public class HomeController {
	
	/*
	 * GET:	/register - To show register page
	 * POST:/register - To save Form backing Object. 
	 */

	@Autowired
	private UserService userService;

	// VIEW Welcome Page
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}

	@GetMapping("/register")
	public String showRegister(@ModelAttribute("userObj") User user, Model model) {

		user.setName("Test name");

		model.addAttribute("roles", RolesUtil.getAllRoles());

		return "register";
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

	@PostMapping("/register")
	public String saveUser(@ModelAttribute User user, Model model) {

		System.out.println(user);

		Integer id = userService.saveUser(user);
		String message = "User '" + id + "' saved successfully !";

		// From backing Objects
		model.addAttribute("userObj", new User());
		model.addAttribute("msg", message);
		model.addAttribute("roles", RolesUtil.getAllRoles());

		return "register";

	}

}

```

---

### 7. PostController.java

#### controller\PostController.java

```java

package com.suji.sec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suji.sec.model.Post;
import com.suji.sec.repository.PostRepository;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	// Show POST Form GET:/form
	@GetMapping("/form")
	public String showPost(Model model) {
		model.addAttribute("post", new Post(1, "Hey this is header", "This is actual body."));
		return "post";
	}

	// Find All Posts GET:/all
	@GetMapping(path = "/all")
	public List<Post> findAllPosts() {
		return postRepository.findAll();
	}

	// Save Post POST:/save
	@PostMapping(path = { "/save" })
	public String post(Post post) {
		postRepository.save(post);
		return "redirect:/post/form";
	}
}
```

---

### 8. UserController.java

#### controller\UserController.java

```java

package com.suji.sec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suji.sec.model.User;
import com.suji.sec.service.UserService;
import com.suji.sec.util.Auth;
import com.suji.sec.util.RolesUtil;

@Controller
@RequestMapping("/reg")
public class UserController {

	@Autowired
	private UserService userService;

	// Go to Registration Page
	@GetMapping("/test")
	public String test(Model model) {

		model.addAttribute("userObj", new User());

		model.asMap().entrySet().forEach(s -> System.out.print(s.getKey() + " : " + s.getValue()));

		return "test";
	}

	// Go to Registration Page
	@GetMapping("/register")
	public String register(Model model) {
		User user = new User();
		user.setRoles(List.of(Auth.MRNG, Auth.TL));
		model.addAttribute("userObj");

		model.asMap().entrySet().forEach(s -> System.out.print(s.getKey() + " : " + s.getValue()));

		return "register";
	}

	// Read Form data to save into DB
	//@PostMapping("/saveUser")
	@PostMapping("/register")
	public String saveUser(@ModelAttribute User user, Model model) {

			System.out.println(user);

			Integer id = userService.saveUser(user);
			String message = "User '" + id + "' saved successfully !";
			
			// From backing Objects
			model.addAttribute("userObj", new User());
			model.addAttribute("msg", message);
			model.addAttribute("roles", RolesUtil.getAllRoles());
			
			return "register";

	}

	/*
	 * TEST CODE
	 *
	 * // Read Form data to save into DB
	 * 
	 * @PostMapping("/saveUser")
	 * 
	 * @ResponseBody public ResponseEntity<User> saveUser(@ModelAttribute User user,
	 * Model model) {
	 * 
	 * System.out.println(user);
	 * 
	 * // Integer id = userService.saveUser(user); // String message = "User '" + id
	 * + "' saved successfully !"; // model.addAttribute("msg", message); return new
	 * ResponseEntity<User>(user, HttpStatus.OK);
	 * 
	 * }
	 */

}
```

---

### 9. UserRestController.java

#### controller\UserRestController.java

```java

package com.suji.sec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.sec.model.User;
import com.suji.sec.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	// Go to Registration Page
	@GetMapping(path = {"/",""})
	public List<User> getAll() {
		
		return userService.getAllUsers();
	}

	// Read Form data to save into DB
	
	@PostMapping(path = {"/",""})
	public String saveUser(@RequestBody User user) {
		
		Integer id = userService.saveUser(user);
		return "SUCCESS: User id '"+id+"' has been saved successfully.";
		
	}
}
```

---

### 10. UserService.java

#### service\UserService.java

```java

package com.suji.sec.service;

import java.util.List;

import com.suji.sec.model.User;

public interface UserService {
	public Integer saveUser(User user);
	public List<User> getAllUsers();

}
```

---

### 11. PostRepository.java

#### repository\PostRepository.java

```java

package com.suji.sec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.sec.model.Post;
import com.suji.sec.model.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
```

---

### 12. UserRepository.java

#### repository\UserRepository.java

```java

package com.suji.sec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.sec.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByEmail(String email);

}
```

---

### 13. SpringSecurityDemoApplication.java

#### src\main\java\com\suji\sec\SpringSecurityDemoApplication.java

```java

package com.suji.sec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.sec.model.User;
import com.suji.sec.service.UserService;
import com.suji.sec.util.Auth;

@SpringBootApplication
public class SpringSecurityDemoApplication implements ApplicationRunner {

	@Autowired
	private UserService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
		System.out.println("SpringSecurityDemoApplication has been started.");
	}
	
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<String> roals = List.of(Auth.ADMIN);
		
		User user = new User("Sujith","sujith@gmail.com","sujith@123",roals);
		
		Integer integer = service.saveUser(user);
		System.out.println("User id: "+integer+" saved successfully.");
		
	}

}

```

---

### 14. AppConfig.java

#### src\main\java\com\suji\sec\config\AppConfig.java

```java

package com.suji.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
```

---

### 15. SecurityConfig.java

#### src\main\java\com\suji\sec\config\SecurityConfig.java

```java

package com.suji.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.suji.sec.util.Auth;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserDetailsService uds;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(uds).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/h2-console/**","/register/**","/rest/users/**").permitAll()
				.antMatchers("/admin").hasAuthority(Auth.ADMIN)
				.antMatchers("/tasks").hasAnyAuthority(Auth.ADMIN, Auth.TL)
				.anyRequest().authenticated()
				.and()
				// the boolean flags force the redirection even though
				// the user requested a specific secured resource.
				.formLogin().defaultSuccessUrl("/home", true).permitAll()
				// .formLogin().successForwardUrl("/home").permitAll()
				.and().logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/home");

		// this will ignore only h2-console csrf, spring security 4+
		http.csrf().ignoringAntMatchers("/h2-console/**");
		// this will allow frames with same origin which is much more safe
		http.headers().frameOptions().sameOrigin();
		
	}

}
```

---

### 16. UserServiceImpl.java

#### src\main\java\com\suji\sec\service\impl\UserServiceImpl.java

```java

package com.suji.sec.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suji.sec.model.User;
import com.suji.sec.repository.UserRepository;
import com.suji.sec.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Integer saveUser(User user) {

		String passwd = user.getPassword();
		String encodedPasswod = passwordEncoder.encode(passwd);

		user.setPassword(encodedPasswod);
		user = userRepo.save(user);

		return user.getId();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> opt = userRepo.findUserByEmail(email);

		org.springframework.security.core.userdetails.User springUser = null;

		if (opt.isEmpty()) {
			throw new UsernameNotFoundException("User with email: " + email + " not found");
		} else {
			
			User user = opt.get();
			
			// Convert List<String> to Set<GrantedAuthority> without loops
			Set<SimpleGrantedAuthority> collect = user.getRoles().stream().map(SimpleGrantedAuthority :: new).collect(Collectors.toSet());
			//Set<SimpleGrantedAuthority> collect = user.getRoles().stream().map( s -> new SimpleGrantedAuthority(s)).collect(Collectors.toSet());
			
//			List<String> roles = user.getRoles();
//			Set<GrantedAuthority> ga = new HashSet<>();
//			
//			for (String role : roles) {
//				ga.add(new SimpleGrantedAuthority(role));
//			}

			springUser = new org.springframework.security.core.userdetails.User(email, user.getPassword(), collect);
			
		}

		return springUser;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
```

---

### 17. Auth.java

#### src\main\java\com\suji\sec\util\Auth.java

```java

package com.suji.sec.util;

public class Auth {
	public static final String MRNG = "Manger";
	public static final String TL = "Team Leader";
	public static final String ADMIN = "Admin";
	public static final String EMP = "Employee";
}

```

---

### 18. RolesUtil.java

#### src\main\java\com\suji\sec\util\RolesUtil.java

```java

package com.suji.sec.util;

import java.util.List;

public class RolesUtil {
	public static List<String> getAllRoles(){
		return List.of(Auth.ADMIN, Auth.EMP, Auth.MRNG, Auth.TL);
	}
}

```

---

### 19. admin.html

#### resources\templates\admin.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Admin Page')">
<meta charset="ISO-8859-1">

<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<div th:replace="header :: navbar"></div>




	<!-- The Spring Security Authentication interface exposes useful methods concerning the authenticated principal or authentication request. -->
	<div class="container">
		<h1>Admin!</h1>
		<table>
			<tr>
				<td>name</td>
				<td sec:authentication="name"></td>
			</tr>

			<tr>
				<td>principal.username</td>
				<td sec:authentication="principal.username"></td>
			</tr>

			<tr>
				<td>principal.authorities</td>
				<td sec:authentication="principal.authorities"></td>
			</tr>

			<tr>
				<td>principal</td>
				<td sec:authentication="principal"></td>
			</tr>

			<tr>
				<td>Authorities</td>
				<td sec:authentication="authorities"></td>
			</tr>

			<tr>
				<td>Details</td>
				<td sec:authentication="details"></td>
			</tr>

		</table>
	</div>


</body>
</html>
```

---

### 20. emp.html

#### resources\templates\emp.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Emp page')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
</body>
</html>
```

---

### 21. register.html

#### resources\templates\employee\register.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Employee Register')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
	
	<div class="container"> 
		<form th:object="${employee}" th:method="post" th:action="@{/employee/v2/signup}">

			<pre>
				<input type="number" th:field="*{id}" />
				<input type="text" th:field="*{name}"/>
				<input type="date" class="datepicker" th:field="*{doj}"/>
				<input type="submit" value="Register"/>
			</pre>
			<script>

				$('.datepicker').datepicker({
    				dateFormat: 'yyyy-mm-dd'
 				});
				
			</script>

			
		</form>
	
	</div>	
	
</body>
</html>
```

---

### 22. 404.html

#### resources\templates\error\404.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Error')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
	<div class="container">
		<h1>Requested URL not found.</h1>
	</div>
	
</body>
</html>
```

---

### 23. forms.html

#### resources\templates\fragments\forms.html

```html


<form class="form-signin" method="post" action="/logout">
	<h2 class="form-signin-heading">Are you sure you want to log out?</h2>
	<input name="_csrf" type="hidden"
		value="a79b863a-59db-4845-972d-fff09b072fe6" />
	<button class="btn btn-lg btn-primary btn-block" type="submit">Log
		Out</button>
</form>
```

---

### 24. header.html

#### resources\templates\header.html

```html

<!DOCTYPE html>
<html lang="en">
<head th:fragment="header-values(title)">
<title th:text="${title}"></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>



	<nav th:fragment="navbar" class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<ul class="navbar-nav">
				
				
				
				<li class="nav-item">
					<a class="nav-link"  sec:authentication="principal.username" th:href="@{/home}"></a>
				</li>
			
				<li class="nav-item">
					<a class="nav-link"  th:href="@{/post/form}">Post</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link"  href="/inbox">Inbox</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link"  href="/admin">Admin</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link"  href="/home">Home</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link"  href="/emp">Emp Details</a>
				</li>
				
				<li class="nav-item">
					<a  class="nav-link"  href="/tasks">Tasks</a>
				</li>
				
				<li class="nav-item">
					<a class="nav-link"  href="/logout">Logout</a>
				</li>
				
			</ul>
		</div>
	</nav>



</body>
</html>

```

---

### 25. home.html

#### resources\templates\home.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Home Page Edited')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
</body>
</html>
```

---

### 26. inbox.html

#### resources\templates\inbox.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Inbox')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
	
</body>
</html>
```

---

### 27. post.html

#### resources\templates\post.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Posts')">

</head>

<body>
	<div th:replace="header :: navbar"></div>

	<div class="container">

		<form th:fragment="post-form" th:object="${post}" th:action="@{/post/save}" th:method="post">

			<div class="form-group">
				<label for="usr">Name:</label> <input type="text"
					th:field="*{about}" class="form-control" id="usr" name="about">
			</div>

			<div class="form-group">
				<label for="comment">Comment:</label>
				<textarea class="form-control" th:field="*{post}" name="post" rows="5"
					id="comment"></textarea>
			</div>

			<button type="submit" class="btn btn-success">Submit</button>
			
			<!-- <input type="submit" class="btn btn-success" value="Submit Button"> -->

		</form>
	</div>
</body>
</html>
```

---

### 28. register.html

#### resources\templates\register.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Register')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
</body>
</html>
```

---

### 29. tasks.html

#### resources\templates\tasks.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Tasks')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
</body>
</html>
```

---

### 30. test.html

#### resources\templates\test.html

```html

<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<head th:replace="header :: header-values('Test page')">

</head>

<body>
	<div th:replace="header :: navbar"></div>
</body>
</html>
```

---

### 31. SpringSecurityDemoApplicationTests.java

#### src\test\java\com\suji\sec\SpringSecurityDemoApplicationTests.java

```java

package com.suji.sec;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

