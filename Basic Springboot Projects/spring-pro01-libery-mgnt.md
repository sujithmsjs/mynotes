# spring-pro01-libery-mgnt


### File Structure
```pre
+ spring-pro01-libery-mgnt\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  dashboard.html
		|---  hello.html
		|---  signup.html
		|---  update.html
	+ \src\main\java\tech\suji\libmgnt
		|---  SpringPro01LiberyMgntApplication.java
	+ \src\test\java\tech\suji\libmgnt
		|---  SpringPro01LiberyMgntApplicationTests.java
	+ \src\main\java\tech\suji\libmgnt\model
		|---  Person.java
		|---  Student.java
	+ \src\main\java\tech\suji\libmgnt\controller
		|---  PersonController.java
		|---  StudentController.java
	+ \src\main\java\tech\suji\libmgnt\service
		|---  DisplayUtil.java
		|---  StudentService.java
	+ \src\main\java\tech\suji\libmgnt\repository
		|---  StudentRepository.java
	+ \src\main\java\tech\suji\libmgnt\validator
		|---  UniqueEmailConstraint.java
		|---  UniqueEmailValidator.java
```
### Index
```pre
1. application.properties
2. model\Person.java
3. model\Student.java
4. controller\PersonController.java
5. controller\StudentController.java
6. service\DisplayUtil.java
7. service\StudentService.java
8. repository\StudentRepository.java
9. src\main\java\tech\suji\libmgnt\SpringPro01LiberyMgntApplication.java
10. src\main\java\tech\suji\libmgnt\validator\UniqueEmailConstraint.java
11. src\main\java\tech\suji\libmgnt\validator\UniqueEmailValidator.java
12. resources\templates\dashboard.html
13. resources\templates\hello.html
14. resources\templates\signup.html
15. resources\templates\update.html
16. src\test\java\tech\suji\libmgnt\SpringPro01LiberyMgntApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
logging.level.com.suji.demo = DEBUG


#driverClassName: com.mysql.cj.jdbc.Driver
#dialect: org.hibernate.dialect.MySQL8Dialect

```

---

### 2. Person.java

#### model\Person.java

```java

package tech.suji.libmgnt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pin;
	
	private String name;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	@Column(nullable = false,unique = true)
	private String mobile;
	
	
	public Person(String name, String email, String mobile) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
}

```

---

### 3. Student.java

#### model\Student.java

```java

package tech.suji.libmgnt.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.suji.libmgnt.validator.UniqueEmailConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pin;
	
	@Pattern(regexp = "[a-zA-Z ]{4,20}", message = "Invalid Name")
	private String name;
	
	
	// @Column(nullable = false,unique = true)
	@Pattern(regexp = "^[a-z0-9]+@[a-z0-9]{1,5}[.][a-z0-9]{1,5}$", message = "Invalied email address")
	@UniqueEmailConstraint
	private String email;
	
	@Column(nullable = false,unique = true)
	@Pattern(regexp = "^[7-9]{1}[0-9]{9}$", message="Invalid mobile number")
	private String mobile;
	
	@Past
	private LocalDate dob;
	
	
	public Student(String name, String email, String mobile) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}


	public Student(String name, String email, String mobile, @Past LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
	}
	
	
}

```

---

### 4. PersonController.java

#### controller\PersonController.java

```java

package tech.suji.libmgnt.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tech.suji.libmgnt.model.Person;
import tech.suji.libmgnt.model.Student;
import tech.suji.libmgnt.service.DisplayUtil;
import tech.suji.libmgnt.service.StudentService;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private StudentService studentService;

	// to View Singup form
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
//	public String singupStudent(@ModelAttribute("students") Person person, Model model) {
	public String singupStudent(@ModelAttribute("student3345") Student person,Model model) {

		System.out.println("-----BEFORE SINUP PAVE VIEWED-----");
		System.out.println(person);
		DisplayUtil.displayModelData(model);

		return "signup";
	}

	

	@ModelAttribute
	public void justTest(Model model) {
		model.addAttribute("msg", "This is @ModelAttribute");
		System.out.println("This is ModelAttribute just check line.");
	}
	
	

	@ModelAttribute
	public Student getStudent() {
		return new Student("Sujith", "sujithmsjs@gamil.com", "8008188022", LocalDate.of(1994, 9, 2));

	}
	
	@ModelAttribute("student2")
	public Student getStudent2() {
		return new Student("Vineeth", "vin@gamil.com", "9676068006", LocalDate.of(1997, 7, 16));

	}

	// to Save Signup details

	// to View Login form
	// to Check credintials for login

	// to View Dashboard

	// to View Update form
	// to Save Update details.

	// to Delete the Person.

	// to view Welcome page.

}

```

---

### 5. StudentController.java

#### controller\StudentController.java

```java

package tech.suji.libmgnt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tech.suji.libmgnt.model.Student;
import tech.suji.libmgnt.service.DisplayUtil;
import tech.suji.libmgnt.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;



	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String singupStudent(@ModelAttribute("student") Student student) {

		System.out.println("Model Attribute: "+student);
		return "signup";
	}
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String saveStudent(@Valid Student student, BindingResult bindingResult,Model model) {
		System.out.println("---- BEFORE SAVING DATA ----");
		DisplayUtil.displayModelData(model);
		
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		
		System.out.println("About to save: "+student);
		studentService.saveStudent(student);
		return "redirect:/student/dashboard";

	}
	
	@RequestMapping(value = "/edit-student", method = RequestMethod.POST)
	public String editStudent(Student student) {
		studentService.saveStudent(student);
		
		return "redirect:/student/dashboard";

	}

	@RequestMapping(value = "/dashboard")
	public ModelAndView showDashboard() {

		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("students", studentService.findAllStudents());

		return mv;
	}

	@RequestMapping(value = "/edit/{pin}")
	public ModelAndView editStudent(@PathVariable int pin) {

		Student student = studentService.findById(pin);
		System.out.println("Edit: " + pin + " " + student);

		ModelAndView mv = new ModelAndView("update");
		mv.addObject("student", student);

		return mv;
	}

	@RequestMapping(value = "/delete/{pin}")
	public String deleteStudent(@PathVariable("pin") int value) {
		System.out.println("Delete: " + value);
		studentService.deleteById(value);
		
		return "redirect:/student/dashboard";
	}

}

```

---

### 6. DisplayUtil.java

#### service\DisplayUtil.java

```java

package tech.suji.libmgnt.service;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.ui.Model;

public class DisplayUtil {
	
	public static void displayModelData(Model model) {
		// To display Model data
		Map<String, Object> map = model.asMap();
		Set<Entry<String, Object>> set = map.entrySet();
		System.out.println("Total Model Objects: " + set.size());
		for (Entry<String, Object> entry : set) {
			System.out.println("--> " + entry);
		}
	}
	
}

```

---

### 7. StudentService.java

#### service\StudentService.java

```java

package tech.suji.libmgnt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.suji.libmgnt.model.Student;
import tech.suji.libmgnt.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	public Student saveStudent(Student student) {
		return repository.save(student);
	}
	
	public Student findStudentById(int pin) {
		return repository.findById(pin).orElse(new Student());
	}
	
	
	
	public List<Student> findAllStudents(){
		return repository.findAll();
	}
	
	
	
	public Student findById(int id) {
		return repository.findById(id).orElse(new Student());
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
	
	
	
}

```

---

### 8. StudentRepository.java

#### repository\StudentRepository.java

```java

package tech.suji.libmgnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.suji.libmgnt.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {


	boolean existsByEmail(String emailField);

	
	
}

```

---

### 9. SpringPro01LiberyMgntApplication.java

#### src\main\java\tech\suji\libmgnt\SpringPro01LiberyMgntApplication.java

```java

package tech.suji.libmgnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPro01LiberyMgntApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringPro01LiberyMgntApplication.class, args);
		
		
	}

}

```

---

### 10. UniqueEmailConstraint.java

#### src\main\java\tech\suji\libmgnt\validator\UniqueEmailConstraint.java

```java

package tech.suji.libmgnt.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailConstraint {

	String message() default "Username already Exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}

```

---

### 11. UniqueEmailValidator.java

#### src\main\java\tech\suji\libmgnt\validator\UniqueEmailValidator.java

```java

package tech.suji.libmgnt.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import tech.suji.libmgnt.repository.StudentRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> { 
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public boolean isValid(String emailField, ConstraintValidatorContext context) {
		
		
		boolean isExisted = studentRepository.existsByEmail(emailField);
		System.out.println("isExisted: "+isExisted);
		return isExisted;
	}		
}





```

---

### 12. dashboard.html

#### resources\templates\dashboard.html

```html

<!DOCTYPE>
<html>
<head>

<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
}
</style>

</head>
<body>

	<h1>Dashboard</h1>
	<a th:href="@{/student/signup}">Back</a>
	<table>
		<tr>
			<th>Pin</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th></th>
			<th></th>
		</tr>
		<tr th:each="std : ${students}">
			<td th:text="${std.pin}"></td>
			<td th:text="${std.name}"></td>
			<td th:text="${std.email}"></td>
			<td th:text="${std.mobile}"></td>

			<td><a th:href="@{/student/edit/{sno}(sno=${std.pin})}">Edit</a></td>
			<td><a th:href="@{/student/delete/{sno}(sno=${std.pin})}">Delete</a></td>
		</tr>
	</table>
	<a th:href="@{/student/signup}">Back</a>

</body>
</html>










```

---

### 13. hello.html

#### resources\templates\hello.html

```html

<!DOCTYPE>
<html>
<head>

<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
}
</style>

</head>
<body>
	<h1>Welcome to my first spring demo</h1>
	<h1 th:text=" ${value} + ' Hey ' + (1+3) + ${value}  ">Ignore me</h1>

	<div th:object="${std}" style="color: blue;">

		<h1 th:text="*{pin}"></h1>
		<h1 th:text="*{name}"></h1>
		<h1 th:text="*{email}"></h1>
		<h1 th:text="*{mobile}"></h1>

	</div>

	<table>
		<tr>
			<th>Pin</th>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
			<th></th>
			<th></th>
		</tr>
		<tr th:each="std : ${students}">
			<td th:text="${std.pin}"></td>
			<td th:text="${std.name}"></td>
			<td th:text="${std.email}"></td>
			<td th:text="${std.mobile}"></td>
			
			<td> <a th:href="@{/update}">Update</a> </td>
			<td> <a th:href="@{/delete}">Delete</a> </td>
		</tr>
	</table>

</body>
</html>
```

---

### 14. signup.html

#### resources\templates\signup.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

	<h2>Signup Form</h2>
	
	<form th:action="@{/student/signup}" th:object="${student}" method="post">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" th:field="*{name}"></td>
				<td th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="email" name="email" th:field="*{email}"></td>
				<td th:if="${#fields.hasErrors('email')}" th:errors="*{email}"></td>
			</tr>
			<tr>
				<td>Mobile</td>
				<td><input type="tel" name="mobile" th:field="*{mobile}"></td>
				<td th:if="${#fields.hasErrors('mobile')}" th:errors="*{mobile}"></td>
			</tr>
			<tr>
				<td><input type="submit" value="signup"></td>
			</tr>
		</table>
	</form>
	<a th:href="@{/student/dashboard}">Dashboard</a>
	
	<p th:if="${#fields}" ></p>
</body>
</html>


```

---

### 15. update.html

#### resources\templates\update.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>

	<h2>Update Student</h2>

	<form th:action="@{/student/save-student}" th:object="${student}" method="post">
		<table>
			<tr>
				<td>Pin</td>
				<td><input type="number" name="pin" th:field="*{pin}" readonly></td>
			</tr>
			
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" th:field="*{name}"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="email" name="email" th:field="*{email}"></td>
			</tr>
			<tr>
				<td>Mobile</td>
				<td><input type="tel" name="mobile" th:field="*{mobile}"></td>
			</tr>
			<tr>
				<td><input type="submit" value="signup"></td>
			</tr>
		</table>
	</form>
	<a th:href="@{/student/dashboard}">Dashboard</a>
</body>
</html>


```

---

### 16. SpringPro01LiberyMgntApplicationTests.java

#### src\test\java\tech\suji\libmgnt\SpringPro01LiberyMgntApplicationTests.java

```java

package tech.suji.libmgnt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringPro01LiberyMgntApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

