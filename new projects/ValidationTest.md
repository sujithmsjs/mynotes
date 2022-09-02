# ValidationTest


### File Structure
```pre
+ ValidationTest\ 
	+ \src\main\java\com\suji\validation
		|---  ValidationTestApplication.java
	+ \src\main\java\com\suji\validation\model
		|---  Employee.java
		|---  EmployeeSplit.java
	+ \src\main\java\com\suji\validation\controller
		|---  EmployeeController.java
	+ \src\main\java\com\suji\validation\exception
		|---  EmployeeNotFoundException.java
		|---  ExceptionHandlingConfig.java
	+ \src\main\java\com\suji\validation\repository
		|---  EmployeeRepository.java
```
### Index
```pre
1. \model\Employee.java
2. \model\EmployeeSplit.java
3. \src\main\java\com\suji\validation\controller\EmployeeController.java
4. \src\main\java\com\suji\validation\exception\EmployeeNotFoundException.java
5. \src\main\java\com\suji\validation\exception\ExceptionHandlingConfig.java
6. \src\main\java\com\suji\validation\repository\EmployeeRepository.java
7. \src\main\java\com\suji\validation\ValidationTestApplication.java

```

---

### 1. Employee.java

#### \model\Employee.java

```java

package com.suji.validation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[A-Z][a-z]+$", message = "Name should starts with captal letter followed by small letters.")
	private String name;
	
	@Past(message = "Joining date must be in past.")
	private LocalDate doj;
	
	@CreatedDate
	private LocalDateTime created;
	
	
	@LastModifiedDate
	private LocalDateTime lastModified;
	
	
	
}

```

---

### 2. EmployeeSplit.java

#### \model\EmployeeSplit.java

```java

package com.suji.validation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeSplit {
	private Long id;
	private String name;
}

```

---

### 3. EmployeeController.java

#### \src\main\java\com\suji\validation\controller\EmployeeController.java

```java

package com.suji.validation.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.validation.exception.EmployeeNotFoundException;
import com.suji.validation.model.Employee;
import com.suji.validation.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// GET all employees
	
	@GetMapping(path =  {"/",""})
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// GET single employee by ID
	
	@GetMapping("/{id}")
	public Employee getEmployeesById(@PathVariable Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	// GET employees by name
	
	@GetMapping("/name/{name}")
	public List<Employee> getEmployeesByName(@PathVariable String name) {
		return employeeRepository.findAllByNameLike(name);
	}

	// SAVE an employee
	
	@PostMapping("/")
	public Employee saveEmployee(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// UPDATE an employee by ID
	
	@PutMapping("/{id}")
	public Employee updateEmployee(Long id,@Valid  @RequestBody Employee newEmployeeDetails) {
		
		newEmployeeDetails.setId(id);
		
		return employeeRepository.save(newEmployeeDetails);
	}

	// Delete an employee
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deleteEmployeeById(@PathVariable Long id) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		employeeRepository.delete(employee);
		
		Map<String,String> map = new LinkedHashMap<>();
		map.put("Message", "Employee id '"+employee.getId()+"' deleted successfully.");
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.NO_CONTENT);
		
		
		
		
	}
}



```

---

### 4. EmployeeNotFoundException.java

#### \src\main\java\com\suji\validation\exception\EmployeeNotFoundException.java

```java

package com.suji.validation.exception;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(Long employeeId) {
		super("Employee with id '"+employeeId+"' is not found.");
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}	
}

```

---

### 5. ExceptionHandlingConfig.java

#### \src\main\java\com\suji\validation\exception\ExceptionHandlingConfig.java

```java

package com.suji.validation.exception;

import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ExceptionHandlingConfig   extends ResponseEntityExceptionHandler {

	// Handle Generic Exception
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map> handleGenericException(Exception ex){
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Response form", "handleGenericException");
		map.put("message", ex.getMessage());
		map.put("exception", ex.getClass().getSimpleName());
		return new ResponseEntity<Map>(map, HttpStatus.BAD_REQUEST);
		
	}
	
	// Handle MethodArgumentTypeMismatchException
	
	
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		Map<String, String> map = new LinkedHashMap<>();
		
		map.put("Response form", "handleMethodArgumentTypeMismatchException");
		map.put("message", ex.getMessage());
		map.put("exception", ex.getClass().getSimpleName());
		
		return new ResponseEntity<Map>(map, HttpStatus.BAD_REQUEST);
		
	}
	
	
	// Handle EmployeeNotFoundException

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<Map> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		Map<String, String> map = new LinkedHashMap<>();
		
		map.put("Response form", "handleEmployeeNotFoundException");
		map.put("message", ex.getMessage());
		map.put("exception", ex.getClass().getSimpleName());
		
		return new ResponseEntity<Map>(map, HttpStatus.NOT_FOUND);
	}
	
	// Handle Method Argument Not Valid Exception

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("status", status.value());
		map.put("Reason phrase", status.getReasonPhrase());
		map.put("Context Path", request.getContextPath());

		//groupingBy(classifier, mapFactory, downstream)
		Map<String, String> fieldErrorsMap = fieldErrors.stream().collect(Collectors.toMap( fe -> fe.getField(), fe ->  fe.getDefaultMessage()));
		
		//fieldErrors.stream().forEach( f -> map.put(f.getField(),f.getDefaultMessage()));
		map.put("Field Errors", fieldErrorsMap);
		
		return new ResponseEntity<Object>(map, status);
	}

	
	
	@ExceptionHandler(value = DateTimeParseException.class)
	public ResponseEntity<Map> handleGenericException(DateTimeParseException ex){
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Response form", "handle DateTimeParseException");
		map.put("message", ex.getMessage());
		map.put("exception", ex.getClass().getSimpleName());
		return new ResponseEntity<Map>(map, HttpStatus.BAD_REQUEST);
		
	}

}

```

---

### 6. EmployeeRepository.java

#### \src\main\java\com\suji\validation\repository\EmployeeRepository.java

```java

package com.suji.validation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.suji.validation.model.Employee;
import com.suji.validation.model.EmployeeSplit;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Optional<Employee> findByName(String name);
	
	List<Employee> findAllByNameLike(String expression);
	
	@Query("select new com.suji.validation.model.EmployeeSplit(e.id, e.name) from Employee e")
	public List<EmployeeSplit> findSurveyCount();

	
	
	
}

```

---

### 7. ValidationTestApplication.java

#### \src\main\java\com\suji\validation\ValidationTestApplication.java

```java

package com.suji.validation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.validation.repository.EmployeeRepository;

@SpringBootApplication
public class ValidationTestApplication  implements ApplicationRunner{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ValidationTestApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		List<?> surveyCount = employeeRepository.findSurveyCount();
		System.out.println(surveyCount);
		

		
	}
	
	
	
	
	
	
	

}

```

---

