# spring-data-rest-demo


### File Structure
```pre
+ spring-data-rest-demo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\datarest
		|---  SpringDataRestDemoApplication.java
	+ \src\test\java\com\suji\datarest
		|---  SpringDataRestDemoApplicationTests.java
	+ \src\main\java\com\suji\datarest\model
		|---  ErrorDetails.java
		|---  Student.java
	+ \src\main\java\com\suji\datarest\controller
		|---  StudentController.java
	+ \src\main\java\com\suji\datarest\repository
		|---  StudentRepository.java
	+ \src\main\java\com\suji\datarest\handler
		|---  StudentExceptionHandler.java
```
### Index
```pre
1. application.properties
2. model\ErrorDetails.java
3. model\Student.java
4. controller\StudentController.java
5. repository\StudentRepository.java
6. src\main\java\com\suji\datarest\SpringDataRestDemoApplication.java
7. src\main\java\com\suji\datarest\handler\StudentExceptionHandler.java
8. src\test\java\com\suji\datarest\SpringDataRestDemoApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



# Database Setup

spring.datasource.url=jdbc:mysql://localhost:3306/demo2
spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver


# Hibernate Data Setup

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql: true

logging.level.org.springframework.context=DEBUG



```

---

### 2. ErrorDetails.java

#### model\ErrorDetails.java

```java

package com.suji.datarest.model;

import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ErrorDetails {
	private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private String error;
    private Integer count;
    private List<String> errors;
}

```

---

### 3. Student.java

#### model\Student.java

```java

package com.suji.datarest.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Pattern(regexp = "[a-zA-Z ]{3}",message = "Hey, check your name, Man!")
	private String name;
	private LocalDate dob; // "dob": "2000-09-16"
	
	public Student(String name, LocalDate dob) {
		super();
		this.name = name;
		this.dob = dob; 
	}
	
	 
}

```

---

### 4. StudentController.java

#### controller\StudentController.java

```java

package com.suji.datarest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		System.out.println("@InitBinder called");
	}

}

```

---

### 5. StudentRepository.java

#### repository\StudentRepository.java

```java

package com.suji.datarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.suji.datarest.model.Student;

@RepositoryRestResource(collectionResourceRel = "students",path = "students")
public interface StudentRepository extends JpaRepository<Student, Integer> {

}

```

---

### 6. SpringDataRestDemoApplication.java

#### src\main\java\com\suji\datarest\SpringDataRestDemoApplication.java

```java

package com.suji.datarest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.datarest.model.Student;
import com.suji.datarest.repository.StudentRepository;

@SpringBootApplication
public class SpringDataRestDemoApplication implements ApplicationRunner {

	public static void main(String[] args)  {
		SpringApplication.run(SpringDataRestDemoApplication.class, args);
	}
	
	@Autowired
	StudentRepository repository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//repository.save(new Student("Sujith",LocalDate.of(1992, 9, 2)));
		
	}

}

```

---

### 7. StudentExceptionHandler.java

#### src\main\java\com\suji\datarest\handler\StudentExceptionHandler.java

```java

package com.suji.datarest.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.suji.datarest.model.ErrorDetails;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		ErrorDetails ed = new ErrorDetails();
		ed.setCount(ex.getBindingResult().getErrorCount());
        ed.setStatus(HttpStatus.BAD_REQUEST);
        ed.setError("Validation failed");
        
        // Adding list of errors
        List<String> errors = new ArrayList<>();
        
        
        BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> allErrors = bindingResult.getAllErrors();
      
		for (ObjectError objectError : allErrors) {
			errors.add(objectError.getCode());
		}
		
		ed.setErrors(errors);
		
		return new ResponseEntity<>(ed, HttpStatus.BAD_REQUEST);
	}

	
	
}

```

---

### 8. SpringDataRestDemoApplicationTests.java

#### src\test\java\com\suji\datarest\SpringDataRestDemoApplicationTests.java

```java

package com.suji.datarest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataRestDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

