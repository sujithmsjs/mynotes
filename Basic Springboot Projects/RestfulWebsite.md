# RestfulWebsite


### File Structure
```pre
+ RestfulWebsite\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\restful
		|---  MyRunner.java
		|---  RestfulWebsiteApplication.java
	+ \src\test\java\com\suji\restful
		|---  RestfulWebsiteApplicationTests.java
	+ \src\main\java\com\suji\restful\model
		|---  Cat.java
		|---  Person.java
		|---  Student.java
	+ \src\main\java\com\suji\restful\controller
		|---  CatController.java
		|---  PersonController.java
		|---  StudentController.java
	+ \src\main\java\com\suji\restful\service
		|---  PersonService.java
	+ \src\main\java\com\suji\restful\repository
		|---  CatDao.java
		|---  PersonRepository.java
		|---  StudentDao.java
	+ \src\main\java\com\suji\restful\controller\advice
		|---  MyExceptionHandler.java
```
### Index
```pre
1. application.properties
2. model\Cat.java
3. model\Person.java
4. model\Student.java
5. controller\CatController.java
6. controller\PersonController.java
7. controller\StudentController.java
8. service\PersonService.java
9. repository\CatDao.java
10. repository\PersonRepository.java
11. repository\StudentDao.java
12. src\main\java\com\suji\restful\MyRunner.java
13. src\main\java\com\suji\restful\RestfulWebsiteApplication.java
14. src\main\java\com\suji\restful\controller\advice\MyExceptionHandler.java
15. src\test\java\com\suji\restful\RestfulWebsiteApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


# Datasource

spring.datasource.url=jdbc:mysql://localhost:3306/nitro
spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Settings

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


# Logging Settings
logging.level.root=INFO
logging.level.springframework.context=debug
logging.level.com.suji.crudrepo=DEBUG


# DateTimeFormatters

spring.mvc.format.date=yyyy-MM-dd
spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
spring.mvc.format.time=HH:mm:ss


# Email

spring.main.banner-mode=off

spring.mail.protocol=smtp
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=sujithmsjs@gmail.com
spring.mail.password=jSyr3nf2my#Lf7-6
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


#
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.path=/swagger-ui-custom.html
#












```

---

### 2. Cat.java

#### model\Cat.java

```java

package com.suji.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "cats")
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String color;
}

```

---

### 3. Person.java

#### model\Person.java

```java

package com.suji.restful.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
@Table(name = "persons")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "uname", nullable = false)
	private String username;

	@Column(name = "pw", nullable = false)
	private String password;

	
	@Column(name = "sex")
	private char gender;

	@Column(name = "height")
	private double height;

	@Column(name = "ncc")
	private boolean hasNCC;

//	@Column(name = "date_of_birth")
//	private LocalDate dob;
//
//	@Column(name = "registered_on")
//	@CreationTimestamp
//	private LocalDateTime registered;
//
//	@Column(name = "last_update")
//	@LastModifiedDate
//	private LocalDateTime lastUpdate;
}

```

---

### 4. Student.java

#### model\Student.java

```java

package com.suji.restful.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "stds ")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@NotBlank
	@Column(name = "uname",unique = true ,nullable = false)
	private String username;

	@NotBlank
	@Column(name = "pwd", nullable = false)
	private String password;
	
	private LocalDate dob;

	@CreationTimestamp
	private LocalDateTime registered;

	@LastModifiedDate
	private LocalDateTime lastUpdate;

	public Student(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}

```

---

### 5. CatController.java

#### controller\CatController.java

```java

package com.suji.restful.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.restful.model.Cat;
import com.suji.restful.repository.CatDao;



@RestController
@RequestMapping("/cat")
public class CatController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CatController.class);

	
	@Autowired
	private CatDao dao;
	
	@PostMapping("/save")
	public Cat saveCat(@RequestBody Cat cat) {
		LOG.info("/save/"+cat);
		return dao.save(cat);
	}
	
	@GetMapping("/read/{id}")
	public Cat getCat(@PathVariable int id) {
		LOG.info("/cat/"+id);
		//Cat cat = dao.getById(id);
		//Cat cat = dao.findById(id).get();
		Optional<Cat> cat = dao.findById(id);
		
		LOG.info("/cat/ Object got:"+cat);
		return cat.orElse(new Cat(-1,"Not Found","Not Found"));
	}
	
	@PutMapping("/update")
	public Cat updateCat(@RequestBody Cat cat) {
		LOG.info("/update/"+cat);
		return dao.save(cat);
	}
	
	@DeleteMapping("/delete/{id}")
	public Cat deleteCat(@PathVariable int id) {
		LOG.info("/cat/delete/"+id);
		Optional<Cat> optCat = dao.findById(id);
		Cat cat = optCat.orElse(null);
		
		if(cat !=null) {
			dao.deleteById(id);
			cat = new Cat(id, "Deleted", "");
		}else {
			cat = new Cat(id, "Not Found", "");
		}
		LOG.info("cat/delete/ Object got:");
		
		return cat;
	}
	
	
	
	
	
}


```

---

### 6. PersonController.java

#### controller\PersonController.java

```java

package com.suji.restful.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.suji.restful.model.Person;
import com.suji.restful.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping("/all")
	public List<Person> getAll() {
		LOG.debug("/all called...");
		return personService.findAll();
	}
	


	@RequestMapping("/id/{id}")
	public Person getPersonById(@PathVariable("id") int id) {
		Person p = personService.getById(id);
		LOG.info("/{id} called: "+p);
		
		
		return p;
	}
	
	@RequestMapping("/name/{name}")
	public Person getPersonByName(@PathVariable("id") String name) {
		LOG.info("/{name} caled...");
		return personService.getByName(name);
	}
	
	@RequestMapping("/{username}")
	public Person getPersonByUsername(@PathVariable("id") String username) {
		LOG.info("/{name} caled...");
		return personService.getByUsername(username);
	}
	
	
}

```

---

### 7. StudentController.java

#### controller\StudentController.java

```java

package com.suji.restful.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.restful.model.Student;
import com.suji.restful.repository.StudentDao;



@RestController
@RequestMapping("/std")
public class StudentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

	
	@Autowired
	private StudentDao dao;
	
	@PostMapping("/")
	public Student saveStudent(@Valid @RequestBody Student student) {
		LOG.warn("SAVING DATA... First line");
		student.setRegistered(LocalDateTime.now());
		student.setLastUpdate(LocalDateTime.now());
		LOG.warn("BEFORE SAVING DATA...");
		Student saved = dao.save(student);
		LOG.warn("AFTER SAVGING DATA..");
		
		
		return saved;
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable int id) {
		
		LOG.info("/student/"+id);
		Optional<Student> student = dao.findById(id);
		
		LOG.info("/student/ Object got:"+student);
		return student.orElse(new Student(-1,"Not Found","Not Found"));
	}
	
	
	@GetMapping("/all")
	public List<Student> getStudent() {
		
		return dao.findAll();
	}
	
	@PutMapping("/")
	public Student updateStudent(@RequestBody Student student) {
		student.setLastUpdate(LocalDateTime.now());
		LOG.info("/update/"+student);
		return dao.save(student);
	}
	
	@DeleteMapping("/{id}")
	public Student deleteStudent(@PathVariable int id) {
		LOG.info("/student/delete/"+id);
		Optional<Student> optStudent = dao.findById(id);
		Student student = optStudent.orElse(null);
		
		if(student !=null) {
			dao.deleteById(id);
			student = new Student(id, "Deleted", "");
		}else {
			student = new Student(id, "Not Found", "");
		}
		LOG.info("student/delete/ Object got:");
		
		return student;
	}
	
//	
//	@ExceptionHandler({DataIntegrityViolationException.class,ConstraintViolationException.class})
//	public ResponseEntity<Object> ex4(Exception e) {
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("Response From", "Student Controller class");
//		map.put("Cause", e.getCause()!=null?e.getCause().toString():"Null");
//		map.put("Class", e.getClass().getCanonicalName());
//		map.put("Error Message", e.getMessage());
//		
//		return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
	
	
}


```

---

### 8. PersonService.java

#### service\PersonService.java

```java

package com.suji.restful.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.restful.model.Person;
import com.suji.restful.repository.PersonRepository;

@Service
public class PersonService {
	
	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
		
	}
	
	public Person addPerson(Person person) {
		return personRepository.save(person);
	}
	
	
	public Person update(Person person) {
		return personRepository.save(person);
	}
	
	public void deleteById(int id) {
			personRepository.deleteById(id);
	}
	
	public Person getById(int id) {
		return personRepository.getById(id);
	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}

	public Person getByName(String name) {
		return getByName(name);
	}
	
	public Person getByUsername(String username) {
		return getByName(username);
	}

	
	
	
	
}

```

---

### 9. CatDao.java

#### repository\CatDao.java

```java

package com.suji.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.restful.model.Cat;

public interface CatDao extends JpaRepository<Cat, Integer> {

}

```

---

### 10. PersonRepository.java

#### repository\PersonRepository.java

```java

package com.suji.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.restful.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	public Person getByName(String name);
	public Person getByUsername(String name);
}

```

---

### 11. StudentDao.java

#### repository\StudentDao.java

```java

package com.suji.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.restful.model.Cat;
import com.suji.restful.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer> {

}

```

---

### 12. MyRunner.java

#### src\main\java\com\suji\restful\MyRunner.java

```java

package com.suji.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.suji.restful.controller.PersonController;
import com.suji.restful.service.PersonService;

@Component
public class MyRunner  implements CommandLineRunner{
	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

	private final PersonService dao;

	public MyRunner(PersonService dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.warn("My Runner working fine....");
	}
}

```

---

### 13. RestfulWebsiteApplication.java

#### src\main\java\com\suji\restful\RestfulWebsiteApplication.java

```java

package com.suji.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebsiteApplication.class, args);
		System.out.println("RestfulWebsiteApplication get started...");
	}

}

```

---

### 14. MyExceptionHandler.java

#### src\main\java\com\suji\restful\controller\advice\MyExceptionHandler.java

```java

package com.suji.restful.controller.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	 @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No such Order")  // 404
	 public String ex6() {
		 return "This request is as bad as Poola Sony.";
	 }

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> ex3(Exception e) {
		Map<String, String> map = new HashMap<String, String>();
		
		if(e != null) {
			map.put("Response From", "ex3(Exception e)");
			map.put("Cause", e.getCause()!=null?e.getCause().toString():"Null");
			map.put("Class", e.getClass().getCanonicalName());
			map.put("Error Message", e.getMessage());
		}else {
			map.put("Eexception", "null");
		}
		
		return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	
	@ExceptionHandler({DataIntegrityViolationException.class,ConstraintViolationException.class})
	public ResponseEntity<Object> ex4(Exception e) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("Response From", "ex4(DataIntegrityViolationException e)");
		map.put("Cause", e.getCause()!=null?e.getCause().toString():"Null");
		map.put("Class", e.getClass().getCanonicalName());
		map.put("Error Message", e.getMessage());
		
		return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> errors = bindingResult.getFieldErrors();
		for (FieldError fieldError : errors) {
			String key =fieldError.getField();
			String value = fieldError.getDefaultMessage();
			map.put(key, value);
		}

		map.put("Cause", ex.getCause()!=null?ex.getCause().toString():"Null");
		map.put("Class", ex.getClass().getCanonicalName());
		map.put("Error Message", ex.getMessage());
			
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}

```

---

### 15. RestfulWebsiteApplicationTests.java

#### src\test\java\com\suji\restful\RestfulWebsiteApplicationTests.java

```java

package com.suji.restful;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestfulWebsiteApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

