# spring-basics-intibind


### File Structure
```pre
+ spring-basics-intibind\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\tech\suji
		|---  SpringBasicsIntibindApplication.java
	+ \src\test\java\tech\suji
		|---  SpringBasicsIntibindApplicationTests.java
	+ \src\main\java\tech\suji\model
		|---  Employee.java
	+ \src\main\java\tech\suji\controller
		|---  EmployeeController.java
	+ \src\main\java\tech\suji\repository
		|---  EmployeeRepository.java
	+ \src\main\java\tech\suji\config
		|---  AppConfig.java
		|---  LocalDateFormat.java
```
### Index
```pre
1. application.properties
2. model\Employee.java
3. controller\EmployeeController.java
4. repository\EmployeeRepository.java
5. src\main\java\tech\suji\SpringBasicsIntibindApplication.java
6. src\main\java\tech\suji\config\AppConfig.java
7. src\main\java\tech\suji\config\LocalDateFormat.java
8. src\test\java\tech\suji\SpringBasicsIntibindApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. Employee.java

#### model\Employee.java

```java

package tech.suji.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	private int id;
	private String name;
	
	@JsonFormat(pattern="ddMMyyyy")
	private LocalDate dob;
	
	
}

```

---

### 3. EmployeeController.java

#### controller\EmployeeController.java

```java

package tech.suji.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.suji.model.Employee;
import tech.suji.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;
	
	// GET:/employees
	@GetMapping(path = {"","/"})
	public List<Employee> findAll(){
		return repo.findAll();
	}
	
	// POST :/employees
	@PostMapping(path = {"","/"})
	public Employee save(@RequestBody Employee emp){
		return repo.save(emp);
	}
	
	
	
	
}

```

---

### 4. EmployeeRepository.java

#### repository\EmployeeRepository.java

```java

package tech.suji.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tech.suji.model.Employee;

@Repository
public class EmployeeRepository {

	private List<Employee> list = new ArrayList<>();
	private int index = 0;
	
	{
		list.add(new Employee(1, "Sujith", LocalDate.of(1995,9 , 2)));
		list.add(new Employee(2, "Anusha", LocalDate.of(1998,12 , 7)));
		list.add(new Employee(3, "Jeevana", LocalDate.of(1995,6 , 22)));
	}
	
	public Employee save(Employee emp) {
		emp.setId(list.size() + 1);
		list.add(emp);
		return emp;
	}

	public List<Employee> findAll() {
		return list;
	}
	

	
	
}

```

---

### 5. SpringBasicsIntibindApplication.java

#### src\main\java\tech\suji\SpringBasicsIntibindApplication.java

```java

package tech.suji;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.datetime.DateFormatter;

@SpringBootApplication
public class SpringBasicsIntibindApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(SpringBasicsIntibindApplication.class, args);
		
		DateFormatter df = new DateFormatter("ddMMyyyy");
		Date date = df.parse("02091994", Locale.ENGLISH);
		System.out.println(date);
	}

}

```

---

### 6. AppConfig.java

#### src\main\java\tech\suji\config\AppConfig.java

```java

package tech.suji.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class AppConfig {

	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		System.out.println("@InitBinder called");
		
	}

}

```

---

### 7. LocalDateFormat.java

#### src\main\java\tech\suji\config\LocalDateFormat.java

```java

package tech.suji.config;

public class LocalDateFormat {

}

```

---

### 8. SpringBasicsIntibindApplicationTests.java

#### src\test\java\tech\suji\SpringBasicsIntibindApplicationTests.java

```java

package tech.suji;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBasicsIntibindApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

