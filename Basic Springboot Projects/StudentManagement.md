# StudentManagement


### File Structure
```pre
+ StudentManagement\ 
	|---  pom.xml
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  error.html
		|---  index.html
		|---  new.html
		|---  update.html
	+ \src\main\resources\static\styles
		|---  basicstyles.css
	+ \src\main\java\com\suji\stdmgnt
		|---  StudentManagementApplication.java
	+ \src\test\java\com\suji\stdmgnt
		|---  StudentManagementApplicationTests.java
	+ \src\main\java\com\suji\stdmgnt\model
		|---  Student.java
	+ \src\main\java\com\suji\stdmgnt\controller
		|---  StudentController.java
	+ \src\main\java\com\suji\stdmgnt\repository
		|---  StudentRepository.java
```
### Index
```pre
1. application.properties
2. pom.xml
3. model\Student.java
4. controller\StudentController.java
5. repository\StudentRepository.java
6. src\main\java\com\suji\stdmgnt\StudentManagementApplication.java
7. resources\templates\error.html
8. resources\templates\index.html
9. resources\templates\new.html
10. resources\templates\update.html
11. static\styles\basicstyles.css
12. src\test\java\com\suji\stdmgnt\StudentManagementApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


# Data Source
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sujith
spring.datasource.password=suji@1234

# JPA Props
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto = create
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.defer-datasource-initialization=true

spring.h2.console.enabled=true

server.error.include-exception=true
server.error.include-stacktrace=always
```

---

### 2. pom.xml

#### pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>tech.suji</groupId>
	<artifactId>StudentManagement</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>StudentManagement</name>
	<description>StudentManagement</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```

---

### 3. Student.java

#### model\Student.java

```java

package com.suji.stdmgnt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sno;
	private String name;
	private String branch;
	private double cgpa;
	
}

```

---

### 4. StudentController.java

#### controller\StudentController.java

```java

package com.suji.stdmgnt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suji.stdmgnt.model.Student;
import com.suji.stdmgnt.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentRepository repository;

	// students/new -> Create form backing form -> Show new.html
	
	@GetMapping("/new")
	public ModelAndView showCreateNew() {

		// Create form backing object
		Student std = new Student();

		ModelAndView mav = new ModelAndView();

		// Note: Add form backing object to the model, this object will be rendered by view.
		mav.addObject("student", std);

		// Set view
		mav.setViewName("new");
		return mav;
	}

	// students/index -> Show index.html

	@GetMapping("/index")
	public ModelAndView showAllStudent() {

		List<Student> list = repository.findAll();
		ModelAndView mav = new ModelAndView();

		// Adding a model object
		mav.addObject("students", list);

		mav.setViewName("index");

		return mav;
	}

	// students/edit -> Get object from Repository -> Show edit.html
	
	@GetMapping("/edit/{sno}")
	public ModelAndView editStudent(@PathVariable int sno) {
		Optional<Student> optional = repository.findById(sno);
		Student student = optional.get();

		ModelAndView mav = new ModelAndView();

		// Note: Add form backing object to the model, this object will be rendered by view.
		mav.addObject("student", student);

		// Set view
		mav.setViewName("update");

		return mav;
	}

	// students/save -> Save the data into Repository -> redirect to:/students/index
	
	@PostMapping("/save")
	public String saveStudent(Student student) {
		// Save Student
		log.info("saveStudent(Student) {}",student);
		repository.save(student);

		// Redirect to /students/index
		return "redirect:/students/index";
	}
	
	// students/delete -> Delete the Record -> redirect to:/students/index
	
	@GetMapping("/delete/{sno}")
	public String deleteStudent(@PathVariable int sno) {
		// Delete Student
		repository.deleteById(sno);

		// Redirect to /students/index
		return "redirect:/students/index";
	}

}

```

---

### 5. StudentRepository.java

#### repository\StudentRepository.java

```java

package com.suji.stdmgnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.stdmgnt.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

```

---

### 6. StudentManagementApplication.java

#### src\main\java\com\suji\stdmgnt\StudentManagementApplication.java

```java

package com.suji.stdmgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}

```

---

### 7. error.html

#### resources\templates\error.html

```html


<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Error Page</title>
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>
<body>
	<h2>
		<a th:href="@{/students/index}">Back to home</a>
	</h2>
	<h1>Error Page</h1>
	<table>
		<tr>
			<td>Date</td>
			<td th:text="${timestamp}" />
		</tr>
		<tr>
			<td>Path</td>
			<td th:text="${path}" />
		</tr>
		<tr>
			<td>Error</td>
			<td th:text="${error}" />
		</tr>
		<tr>
			<td>Status</td>
			<td th:text="${status}" />
		</tr>
		<tr>
			<td>Message</td>
			<td th:text="${message}" />
		</tr>
		<tr>
			<td>Exception</td>
			<td th:text="${exception}" />
		</tr>
	</table>
</body>
</html>

```

---

### 8. index.html

#### resources\templates\index.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body bgcolor="yellow">
	<div>

		<a th:href="@{/students/new}">Create New Student</a>

		<table>
			<tr>
				<th>Sno</th>
				<th>Name</th>
				<th>Branch</th>
				<th>CGPA</th>
				<th>Actions</th>
			</tr>

			<tr th:each=" s : ${students}">
				<td th:text=" ${ s.sno }"></td>
				<td th:text="${ s.name }"></td>
				<td th:text="${ s.branch }"></td>
				<td th:text="${ s.cgpa }"></td>

				<td><a th:href="@{/students/edit/{sno}(sno=${s.sno})}">Edit</a> <a
					th:href="@{/students/delete/{sno}(sno=${s.sno})}">Delete</a></td>

			</tr>
			
		</table>
	</div>
</body>
</html>

```

---

### 9. new.html

#### resources\templates\new.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Create New Student</title>
	<meta charset="UTF-8">
	<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body bgcolor="cyan">
	<div>
	<h3><a th:href="@{/students/index}">Back to home</a></h3>
		<h1>Create new student</h1>
		<!-- Form backing object "student" has to be sent from controller -->
		<form method="post"  th:action="@{/students/save}" th:object="${student}">

			<input type="hidden" th:field="*{sno}"><br />

			<label for="lname">Name:</label><br />
			<input type="text" th:field="*{name}"><br />

			<label for="lname">Brach:</label><br />
			<input type="text" th:field="*{branch}"><br />

			<label for="lname">CGPA:</label><br />
			<input type="text" th:field="*{cgpa}"><br />
			<br>
			<input type="submit" value="Add">
		</form>

	</div>
</body>

</html>

```

---

### 10. update.html

#### resources\templates\update.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Create New Student</title>
	<meta charset="UTF-8">
	<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body bgcolor="green">
	<div>
		<h1>Update student</h1>
		<!-- Form backing object "student" has to be sent from controller -->
		<p th:text=" 'Update student id: ' + ${sno} "></p>
		<form method="post"  th:action="@{/students/save}" th:object="${student}">


			<input type="hidden" th:field="*{sno}" ><br />

			<label for="lname">Name:</label><br />
			<input type="text" th:field="*{name}"><br />

			<label for="lname">Brach:</label><br />
			<input type="text" th:field="*{branch}"><br />

			<label for="lname">CGPA:</label><br />
			<input type="text" th:field="*{cgpa}"><br />
			<br>
			<input type="submit" value="Update">
		</form>

	</div>
</body>

</html>

```

---

### 11. basicstyles.css

#### static\styles\basicstyles.css

```css

div, form, table {
	margin: auto;
	width: 60%;
	/*  border: 3px solid rgb(0, 255, 64);*/
	padding: 10px;
}

form {
	text-align: center;
}

a, h1, h2, p {
	text-align: center;
	display: block;
}

table, th, td {
	margin: 10px;
	padding: 10px;
	border: 2px solid black;
	border-collapse: collapse;
	margin-left: auto;
	margin-right: auto;
}

```

---

### 12. StudentManagementApplicationTests.java

#### src\test\java\com\suji\stdmgnt\StudentManagementApplicationTests.java

```java

package com.suji.stdmgnt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

