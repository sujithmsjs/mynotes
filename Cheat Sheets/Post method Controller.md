## POST Method REST Controller Demo
---
### application.properties
```properties
# App config
server.port=8080
spring.application.name=BootMongo

# Open API Available at http://localhost:8080/api-docs/
springdoc.api-docs.path=/api-docs

# Swagger
# Default URL: http://localhost:8080/swagger-ui/index.html
# Custom  URL:  http://localhost:8080/swagger.html
springdoc.swagger-ui.path=/swagger.html

# Error Message
server.error.include-message=always
server.error.include-exception=true
```
---
### Project Object Model(pom.xml)
```xml
<dependencies>

	<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.4</version>
	</dependency>

	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
		
<dependencies>
```
---
### Employee Model (Employee.java)
```java
package com.suji.sample.model;

import java.time.LocalDate;

public class Employee {
	
	private int id;
	private String name;
	private String dept;
	private double salary;
	private Gender gender;
	private LocalDate dateOfJoin;
	private boolean active;
    
    // NOTE: Must need Default Constructor for JSON Parsing.
	public Employee() {
	}

	public Employee(int id, String name, String dept, double salary) {
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}

	@Override
	public String toString() {
	    //... Your Code
	}
	
	// Setters and Getters
}
```
---
### Controller (PostDemoController.java)
```java
package com.suji.sample.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.sample.model.Employee;
import com.suji.sample.model.Gender;

@RestController
@RequestMapping("/post")
public class PostDemoController {

	// Return a Employee object.
	@PostMapping("/get_demo_emp")
	public Employee getEmployee() {
	
		Employee emp = new Employee(1, "Sujith", "IT", 50000);
		emp.setDateOfJoin(LocalDate.of(2000, 9, 2));
		emp.setGender(Gender.MALE);
		emp.setActive(true);
	
		return emp;
	}
	
	
	// Reading Data from Request body and return as String.
	@PostMapping("/to_string")
	public String readEmployee(@RequestBody Employee emp) {
		return emp.toString();
	}
	
	
	// Read the EMP from Request body, modify that and return it.
	@PostMapping("/read_emp")
	public Employee getEmployee(@RequestBody Employee emp) {
		
		Employee e = new Employee(emp.getId() * 2, emp.getName(), emp.getDept(), emp.getSalary() * 2);
		e.setActive(true);
		e.setDateOfJoin(LocalDate.of(2014, 3, 3));
		e.setGender(Gender.MALE);
		
		return e;
	}

	// Read Set of Data
	@PostMapping("/all")
	public List<Employee> getAllEmployee() {
		
		Employee emp1 = new Employee(1, "Sujith", "IT", 50000);
		Employee emp2 = new Employee(2, "Pranisha", "CSE", 75000);
		Employee emp3 = new Employee(3, "Srinivas", "MEC", 40000);

		List<Employee> list = List.of(emp1, emp2, emp3);

		return list;
	}
}

```
---
### Employee as  JSON Representation.
```json
{
  "id": 2468,
  "name": "Sujith",
  "dept": "Java SE",
  "salary": 1000690.6906,
  "gender": "MALE",
  "dateOfJoin": "2014-03-03",
  "active": true
}
```

