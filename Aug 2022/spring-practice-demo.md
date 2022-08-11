# spring-practice-demo


### File Structure
```pre
+ spring-practice-demo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  index.html
		|---  signup.html
		|---  test.html
		|---  update.html
	+ \src\main\resources\templates\error
		|---  404.html
	+ \src\main\resources\templates\gamer
		|---  index.html
		|---  signup.html
		|---  test.html
		|---  update.html
	+ \src\main\resources\static\css
		|---  basics.css
	+ \src\main\java\com\suji\practice
		|---  SpringPracticeDemoApplication.java
	+ \src\main\java\com\suji\practice\model
		|---  Gamer.java
		|---  Student.java
	+ \src\main\java\com\suji\practice\controller
		|---  DemoController.java
		|---  GamerController.java
	+ \src\main\java\com\suji\practice\service
		|---  StudentService.java
	+ \src\main\java\com\suji\practice\repository
		|---  GamerRepository.java
		|---  StudentRepository.java
```
### Index
```pre
1. application.properties
2. model\Gamer.java
3. model\Student.java
4. controller\DemoController.java
5. controller\GamerController.java
6. service\StudentService.java
7. repository\GamerRepository.java
8. repository\StudentRepository.java
9. src\main\java\com\suji\practice\SpringPracticeDemoApplication.java
10. resources\templates\error\404.html
11. resources\templates\gamer\index.html
12. resources\templates\gamer\signup.html
13. resources\templates\gamer\test.html
14. resources\templates\gamer\update.html
15. resources\templates\index.html
16. resources\templates\signup.html
17. resources\templates\test.html
18. resources\templates\update.html
19. static\css\basics.css

```

---

### 1. application.properties

#### application.properties

```properties


# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/std
spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Logging Settings
logging.level.root=INFO
logging.level.springframework.context=debug
logging.level.com.suji.practice=DEBUG

# DateTimeFormatters
spring.mvc.format.date=yyyy-MM-dd
spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
spring.mvc.format.time=HH:mm:ss

```

---

### 2. Gamer.java

#### model\Gamer.java

```java

package com.suji.practice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gamers")
public class Gamer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@Pattern(regexp =  "[a-zA-Z ]+")
	private String name;
	
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private LocalDate dob;
	
	
	@Column(nullable = false)
	private Integer games;
	
	@Column(nullable = false)
	private String color;
	
	@Size(max = 10)
	@Column(nullable = false)
	private String status;
	

}

```

---

### 3. Student.java

#### model\Student.java

```java

package com.suji.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 4)
	private String name;
	
	@Size(min = 4)
	private String school;
	
	
	
	public Student(String name, String school) {
		super();
		this.name = name;
		this.school = school;
	}
	
}

```

---

### 4. DemoController.java

#### controller\DemoController.java

```java

package com.suji.practice.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.suji.practice.model.Student;
import com.suji.practice.repository.StudentRepository;

@Controller
public class DemoController {

	
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);

	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping(path = {"/","","/index"})
	public String index(Model model) {
		log.debug("Index Page called.");
		model.addAttribute("students", studentRepository.findAll());
		return "index";
	}
	
	
	@GetMapping(path= "/signup")
	public String singup(Model model) {
	
		// <form th:object="${student}" >
		model.addAttribute("student", new Student());
		
		return "signup";
	}
	
	// <form th:action="@{/save}" >
	@PostMapping(path= "/save")
	public String save(@Valid Student student, BindingResult bindingResult) {
	
		if(bindingResult.hasErrors()) {
			
			log.error("Invalid data subbmited, redirecting to Singup again.");
			return "signup";
		}
		
		org.thymeleaf.spring5.expression.Fields f;
		
		studentRepository.save(student);
		log.debug("Object has been saved: "+student);
		
		return "redirect:/index";
	}
	
	// <form th:action="@{/save}" >
		@PostMapping(path= "/update")
		public String update(@Valid Student student, BindingResult bindingResult) {
		
			if(bindingResult.hasErrors()) {
				
				log.error("Invalid data subbmited, redirecting to Singup again.");
				return "update";
			}
			
			org.thymeleaf.spring5.expression.Fields f;
			
			studentRepository.save(student);
			log.debug("Object has been saved: "+student);
			
			return "redirect:/index";
		}
	
	@GetMapping(path = {"/test"})
	public String test(Model model) {
		return "test";
	}
	
	@GetMapping(path = "student/update/{id}")
	public String update(@PathVariable int id, Model model) {
		log.debug("Promt update id: "+id);
		
		Student student = studentRepository.getById(id);
		model.addAttribute("student", student);
		
		return "update";
	}
	
	@GetMapping(path = "student/delete/{id}")
	public String delete(@PathVariable int id) {
		log.debug("Promt delete id: "+id);
		studentRepository.deleteById(id);
		
		return "redirect:/index";
	}
	
	
	
	
	
	
}

```

---

### 5. GamerController.java

#### controller\GamerController.java

```java

package com.suji.practice.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suji.practice.model.Gamer;
import com.suji.practice.model.Student;
import com.suji.practice.repository.StudentRepository;

@Controller
@RequestMapping("/gamer")
public class GamerController {

	
	private static final Logger log = LoggerFactory.getLogger(GamerController.class);

	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping(path = {"/","","/index"})
	public String index(Model model) {
		return "/gamer/index";
	}
	
	
	@GetMapping(path= "/signup")
	public String singup(Model model) {
	
		model.addAttribute("gamer", new Gamer());
		
		return "/gamer/signup";
	}
	
	// <form th:action="@{/save}" >
	@PostMapping(path= "/save")
	
	public String save(@Valid Gamer gamer, BindingResult bindingResult) {
	
		if(bindingResult.hasErrors()) {
			
			log.error("Invalid data subbmited, redirecting to Singup again.");
			return "/gamer/signup";
		}
		
		
		return "/gamer/index";
	}
	
	}

```

---

### 6. StudentService.java

#### service\StudentService.java

```java

package com.suji.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.practice.model.Student;

@Service
public class StudentService {

	
}

```

---

### 7. GamerRepository.java

#### repository\GamerRepository.java

```java

package com.suji.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.practice.model.Gamer;
import com.suji.practice.model.Student;

public interface GamerRepository extends JpaRepository<Gamer, Integer> {

	
}

```

---

### 8. StudentRepository.java

#### repository\StudentRepository.java

```java

package com.suji.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.practice.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}

```

---

### 9. SpringPracticeDemoApplication.java

#### src\main\java\com\suji\practice\SpringPracticeDemoApplication.java

```java

package com.suji.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPracticeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPracticeDemoApplication.class, args);
	}

}

```

---

### 10. 404.html

#### resources\templates\error\404.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all" th:href="@{/css/basics.css}" />

</head>
<body>

	<h1>Resource not found.</h1>
	<a th:href="@{/}">Back to Home</a>

</body>
</html>
```

---

### 11. index.html

#### resources\templates\gamer\index.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>
	<h1>Index</h1>
	<hr />

	<a th:href="@{/gamer/signup}">Signup</a>
</body>
</html>
```

---

### 12. signup.html

#### resources\templates\gamer\signup.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>


	<!-- id, name, email,dob,games, color, status -->
	<form th:object="${gamer}" th:action="@{/gamer/save}" th:method="POST">

		First Name <input type="text" placeholder="Name" th:field="*{name}">
		<p th:if="${  #fields.hasErrors('name') }" th:errors="*{name}" class="error"></p>
		
		<br>
		<br> 
		Email Id <input type="email" placeholder="Email" th:field="*{email}"><br>
		<p th:if="${  #fields.hasErrors('email') }" th:errors="*{email}" class="error"></p>
		<br> 
		Date of birth <input type="date" th:field="*{dob}"><br>
		<p th:if="${  #fields.hasErrors('dob') }" th:errors="*{dob}" class="error"></p>
		<br> 
		Games <input type="number" placeholder="Number" th:field="*{games}"><br>
		<p th:if="${  #fields.hasErrors('games') }" th:errors="*{games}" class="error"></p>
		<br> 
		Profile color <input type="color" th:field="*{color}"><br>
		<p th:if="${  #fields.hasErrors('color') }" th:errors="*{color}" class="error"></p>
		<br>

		<textarea class="form-control" rows="12" th:inline="text">
			[[*{status}]]
		</textarea>
		<p th:if="${  #fields.hasErrors('status') }" th:errors="*{status}" class="error"></p>
		
		<br> <br> <input type="submit" value="Submit">
	</form>

</body>
</html>
```

---

### 13. test.html

#### resources\templates\gamer\test.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>
	<h1 th:if=true th:text=" hellow world">Hellow</h1>
</body>
</html>
```

---

### 14. update.html

#### resources\templates\gamer\update.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>

	<h1>Update Data</h1>
	<hr />

	<form th:action="@{/update}" th:object="${student}" th:method="post">
		<input type="hidden" th:field="*{id}" />
		<!-- SIGH UP FORM  getErrorCount-->
		<p th:text=" 'Error Count: '  + ${#fields.allErrors.size}"></p>
		<p th:text=" 'Error Count: '  + ${#fields}"></p>
		<table>
			<tr> 
				<td>Username</td>
				<td><input type="text" placeholder="Name" th:field="*{name}" />
					<p th:if="${  #fields.hasErrors('name') }" th:errors="*{name}" class="error"></p></td>
			</tr>
			<tr>
				<td>School Name</td>

				<td><input type="text" placeholder="Schoolname"
					th:field="*{school}" />
					<p th:if="${ #fields.hasErrors( 'school' ) }" th:errors="*{school}"></p>
				</td>
			</tr>

			<tr>
				<td><input type="submit" value="Add" /></td>
				<td></td>
			</tr>
		</table>

	</form>

</body>
</html>
```

---

### 15. index.html

#### resources\templates\index.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>
	<h1>Hello, Sujith</h1>
	<hr />
	
	<a th:href="@{/signup}">Add Student</a>
	<a th:href="@{/test}">Test</a>
	
	<table>
		<tr>
			<th>Sno.</th>
			<th>Name</th>
			<th>School</th>
			<th>Actions</th>
		</tr>
		
		<tr th:each="student : ${students}">
			<td th:text="${student.id}"></td>
			<td th:text="${student.name}"></td>
			<td th:text="${student.school}"></td>
			<td>
				<a th:href="@{ student/delete/{id} (id = ${student.id}) }">Delete</a>
				<a th:href="@{ student/update/{id} (id = ${student.id}) }">Update</a>
			</td>
		</tr>
		
	</table>

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
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>

	<h1>Add new User</h1>
	<hr />

	<form th:action="@{/save}" th:object="${student}" th:method="post">
		<!-- SIGH UP FORM  getErrorCount-->
		<p th:text=" 'Error Count: '  + ${#fields.allErrors.size}"></p>
		<p th:text=" 'Error Count: '  + ${#fields}"></p>
		<table>
			<tr> 
				<td>Username</td>
				<td><input type="text" placeholder="Name" th:field="*{name}" />
					<p th:if="${  #fields.hasErrors('name') }" th:errors="*{name}" class="error"></p></td>
			</tr>
			<tr>
				<td>School Name</td>

				<td><input type="text" placeholder="Schoolname"
					th:field="*{school}" />
					<p th:if="${ #fields.hasErrors( 'school' ) }" th:errors="*{school}"></p>
				</td>
			</tr>

			<tr>
				<td><input type="submit" value="Add" /></td>
				<td></td>
			</tr>
		</table>

	</form>

</body>
</html>
```

---

### 17. test.html

#### resources\templates\test.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>
	<h1 th:if=true th:text=" hellow world">Hellow</h1>
</body>
</html>
```

---

### 18. update.html

#### resources\templates\update.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<title>Good Thymes Virtual Grocery</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="all"
	th:href="@{/css/basics.css}" />

</head>
<body>

	<h1>Update Data</h1>
	<hr />

	<form th:action="@{/update}" th:object="${student}" th:method="post">
		<input type="hidden" th:field="*{id}" />
		<!-- SIGH UP FORM  getErrorCount-->
		<p th:text=" 'Error Count: '  + ${#fields.allErrors.size}"></p>
		<p th:text=" 'Error Count: '  + ${#fields}"></p>
		<table>
			<tr> 
				<td>Username</td>
				<td><input type="text" placeholder="Name" th:field="*{name}" />
					<p th:if="${  #fields.hasErrors('name') }" th:errors="*{name}" class="error"></p></td>
			</tr>
			<tr>
				<td>School Name</td>

				<td><input type="text" placeholder="Schoolname"
					th:field="*{school}" />
					<p th:if="${ #fields.hasErrors( 'school' ) }" th:errors="*{school}"></p>
				</td>
			</tr>

			<tr>
				<td><input type="submit" value="Add" /></td>
				<td></td>
			</tr>
		</table>

	</form>

</body>
</html>
```

---

### 19. basics.css

#### static\css\basics.css

```css

@charset "ISO-8859-1";

h1{
	color: blue;
	text-align: center;
}


table {
  font-family: arial, sans-serif;
  border-collapse: collapse; 
  margin-left: auto; 
  margin-right: auto;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

a {
	font-size: 15px;
}

.error{
	font-style: italic;
	color: red;
}

.success{
	font-style: italic;
	color: green;	
}

```

---

