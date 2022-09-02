# restful-validation-demo


### File Structure
```pre
+ restful-validation-demo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  index.html
	+ \src\main\java\com\example\demo
		|---  Demo5Application.java
	+ \src\main\java\com\example\demo\model
		|---  Employee.java
	+ \src\main\java\com\example\demo\advice
		|---  EmployeeControllerAdvice.java
	+ \src\main\java\com\example\demo\controller
		|---  EmployeeController.java
		|---  EmployeeRestController.java
		|---  TestController.java
	+ \src\main\java\com\example\demo\excep
		|---  CustomExceptionDetails.java
		|---  EmployeeNotFoundException.java
	+ \src\main\java\com\example\demo\repository
		|---  EmployeeRepository.java
	+ \src\main\java\com\example\demo\service
		|---  EmployeeService.java
	+ \src\main\java\com\example\demo\service\impl
		|---  EmployeeServiceImpl.java
```
### Index
```pre
1. resources\application.properties
2. resources\templates\index.html
3. \model\Employee.java
4. \src\main\java\com\example\demo\advice\EmployeeControllerAdvice.java
5. \src\main\java\com\example\demo\controller\EmployeeController.java
6. \src\main\java\com\example\demo\controller\EmployeeRestController.java
7. \src\main\java\com\example\demo\controller\TestController.java
8. \src\main\java\com\example\demo\Demo5Application.java
9. \src\main\java\com\example\demo\excep\CustomExceptionDetails.java
10. \src\main\java\com\example\demo\excep\EmployeeNotFoundException.java
11. \src\main\java\com\example\demo\repository\EmployeeRepository.java
12. \src\main\java\com\example\demo\service\EmployeeService.java
13. \src\main\java\com\example\demo\service\impl\EmployeeServiceImpl.java

```

---

### 1. application.properties

#### resources\application.properties

```properties



# Data Source
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# To Standard Output
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


spring.jpa.defer-datasource-initialization=true

spring.jpa.hibernate.ddl-auto = update

spring.h2.console.enabled=true

### OPEN API URL ###
# Available at http://localhost:8080/api-docs/
springdoc.api-docs.path=/api-docs

# SWAGGER
# Available at http://localhost:8080/swagger-ui-custom.html
springdoc.swagger-ui.path=/swagger-ui-custom.html
```

---

### 2. index.html

#### resources\templates\index.html

```html

<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<h2>HTML Table</h2>
	<table>
		<tr>
			<th>Employee Id</th>
			<th>Name</th>
			<th>Salary</th>
			<th>Actions</th>
		</tr>

		<tr th:each="emp : ${employees}">
		
			<td th:text="${emp.id}"></td>
			<td th:text="${emp.name}"></td>
			<td th:text="${emp.salary}"></td>

			<td><a th:href="@{/emp/edit/{sno}(sno=${emp.id})}">Edit</a> <a
				th:href="@{/emp/delete/{sno}(sno=${emp.id})}">Delete</a></td>

		</tr>
	</table>
</body>
</html>


```

---

### 3. Employee.java

#### \model\Employee.java

```java

package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 4, max = 20)
	private String name;
	
	@Positive
	private double salary;
	
	@Pattern(regexp = "[A-Z]{2,}", message = "Hey, please check the pattern and try again.")
	private String role;
	
	@Past(message = "Invalid Joining date, please check and try again.")
	private LocalDate joinDate = LocalDate.now();
	
	
}

```

---

### 4. EmployeeControllerAdvice.java

#### \src\main\java\com\example\demo\advice\EmployeeControllerAdvice.java

```java

package com.example.demo.advice;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.excep.CustomExceptionDetails;
import com.example.demo.excep.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeControllerAdvice { // extends ResponseEntityExceptionHandler {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		System.out.println("Date Format prompted: "+dateFormat);
		binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(dateFormat, true));

	}

	// EMPLOYEE NOT FOUND EXPECTION HANDLER
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<CustomExceptionDetails> employeeNotFoundHandler(EmployeeNotFoundException ex) {

		// Adding details about exception
		CustomExceptionDetails customEx = new CustomExceptionDetails();

		customEx.setStatus(HttpStatus.NOT_FOUND.value());
		customEx.setMessage(ex.getMessage());
		customEx.setExceptionClass(ex.getClass());
		customEx.setTimestamp(LocalDateTime.now());

		// Returning ResponseEntity with Exception details.
		return new ResponseEntity<CustomExceptionDetails>(customEx, HttpStatus.NOT_FOUND);
	}

	// GENERIC EXCEPTION HANDLER
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomExceptionDetails> badRequestHandler(Exception ex) {

		// Adding details about exception
		CustomExceptionDetails customEx = new CustomExceptionDetails();
		customEx.setStatus(HttpStatus.BAD_REQUEST.value());
		customEx.setMessage(ex.getMessage());
		customEx.setExceptionClass(ex.getClass());
		customEx.setTimestamp(LocalDateTime.now());

		// Returning ResponseEntity with Exception details.
		return new ResponseEntity<CustomExceptionDetails>(customEx, HttpStatus.BAD_REQUEST);
	}

	// Hibernate Validaiton Check

	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		// Creating a HashMap
		Map<String, String> errors = new LinkedHashMap<>();

		// Adding details about exception
		errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
		errors.put("exception", ex.getClass().getCanonicalName());
		errors.put("timestamp", LocalDateTime.now().toString());

		List<ObjectError> list = ex.getBindingResult().getAllErrors();

		ex.getBindingResult().getAllErrors().forEach((error) -> {

			String fieldName = ((FieldError) error).getField();

			String errorMessage = error.getDefaultMessage();

			errors.put(fieldName, errorMessage);

			System.out.println(fieldName + " : " + errorMessage);

		});

		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);

	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		
//		Map<String, Object> body = new LinkedHashMap<>();
//		
//		
//		body.put("timestamp", LocalDate.now());
//		body.put("status", status.value());
//
//		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
//				.collect(Collectors.toList());
//
//		body.put("errors", errors);
//		System.out.println("MethodArgumentNotValidException Data: " + body);
//
//		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//	}

}

```

---

### 5. EmployeeController.java

#### \src\main\java\com\example\demo\controller\EmployeeController.java

```java

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	
	private final EmployeeRepository repository;

	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	

	// Read all the Employees
	@GetMapping(path = {"","/"})
	@ResponseBody
	public List<Employee> getAll(){
		return repository.findAll();
	}
	
	@PostMapping("/")
	@ResponseBody
	public Employee saveEmployee(@RequestBody Employee employee){
		return repository.save(employee);
	}
	
	@PutMapping("/{empId}")
	@ResponseBody
	public Employee updateEmployee(@PathVariable long empId,@RequestBody Employee employee){
		
		Optional<Employee> id = repository.findById(empId);
		
		if(id.isPresent()) {	
			employee.setId(empId);
		}
		
		
		
		return repository.save(employee);
	}

	
	@GetMapping("/home")
	public ModelAndView showHome() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("employees", repository.findAll());
		return mav;
	}
	
}

```

---

### 6. EmployeeRestController.java

#### \src\main\java\com\example\demo\controller\EmployeeRestController.java

```java

package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.excep.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("employees")
public class EmployeeRestController {

	private final EmployeeRepository repository;

	public EmployeeRestController(EmployeeRepository repository) {
		this.repository = repository;
	}

	// GET all Employees
	@GetMapping(path = {"/",""})
	public List<Employee> all() {
		return repository.findAll();
	}

	// SAVE an Employee
	@PostMapping(path = {"/",""})
	public Employee newEmployee(@Valid @RequestBody Employee newEmployee) {
		System.out.println("Prompt to save:  " + newEmployee);
		return repository.save(newEmployee);
	}

	// GET Single Employee
	@GetMapping("/{id}")
	Employee one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	// Update an Employee
	@PutMapping("/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		return repository.findById(id).map(employee -> {
			System.out.println("PutMapping: Id found. ");
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			employee.setSalary(newEmployee.getSalary());
			employee.setJoinDate(newEmployee.getJoinDate());
			
			return repository.save(employee);
		}).orElseGet(() -> {
			System.out.println("PutMapping: Id not found. ");
			newEmployee.setId(id);
			return repository.save(newEmployee);
		});
	}

	// Delete an Employee
	@DeleteMapping("/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}

```

---

### 7. TestController.java

#### \src\main\java\com\example\demo\controller\TestController.java

```java

package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/hello")
	public String hi() {
		return "Hello World";
	}
	
	@GetMapping("/{date}")
	public LocalDate test(@PathVariable LocalDate date) {
		System.out.println("Local date value: "+date);
		return date;
	}
	
}

```

---

### 8. Demo5Application.java

#### \src\main\java\com\example\demo\Demo5Application.java

```java

package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class Demo5Application  implements ApplicationRunner{

	// AutoWire Employee Repository
	@Autowired
	private EmployeeRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Demo5Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Data started to load from main method.");
		repository.save(new Employee(11,"Vineeth",55000,"MNG",LocalDate.of(2000, 9, 2)));
	}
}

```

---

### 9. CustomExceptionDetails.java

#### \src\main\java\com\example\demo\excep\CustomExceptionDetails.java

```java

package com.example.demo.excep;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionDetails {

	private int status;
	private String message;
	private Class exceptionClass;
	private LocalDateTime timestamp;
	
}

```

---

### 10. EmployeeNotFoundException.java

#### \src\main\java\com\example\demo\excep\EmployeeNotFoundException.java

```java

package com.example.demo.excep;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(long id) {
		super("Employee Id not found - "+id);
	}

}

```

---

### 11. EmployeeRepository.java

#### \src\main\java\com\example\demo\repository\EmployeeRepository.java

```java

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

```

---

### 12. EmployeeService.java

#### \src\main\java\com\example\demo\service\EmployeeService.java

```java

package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	//CREATE
	Employee saveEmployee(Employee emp);
	
	// READ
	List<Employee>	getAllEmployees();
	Employee findEmployeeById(long id);
	
	// UPDATE
	Employee updateEmployee(Employee emp);
	
	// DELETE
	void	deleteEmployeeById(long id);
	
}

```

---

### 13. EmployeeServiceImpl.java

#### \src\main\java\com\example\demo\service\impl\EmployeeServiceImpl.java

```java

package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.excep.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository repository;
	
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		return repository.save(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Employee findEmployeeById(long id) {
		return repository.findById(id).orElseThrow( () -> new EmployeeNotFoundException(id));
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		return null;
	}

	@Override
	public void deleteEmployeeById(long id) {
		 repository.deleteById(id);
	}
	
}

```

---

