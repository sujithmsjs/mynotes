# Simple Rest API


### File Structure
```pre
+ sample-demo-first\ 
	|---  pom.xml
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\sample
		|---  SampleDemoFirstApplication.java
	+ \src\main\java\com\suji\sample\model
		|---  Employee.java
	+ \src\main\java\com\suji\sample\controller
		|---  EmployeeController.java
		|---  TestControllerEasy.java
		|---  TestControllerMedium.java
	+ \src\main\java\com\suji\sample\service
		|---  EmployeeService.java
	+ \src\main\java\com\suji\sample\serviceImpl
		|---  EmployeeServiceImpl.java
```
### Index
```pre
1. resources\application.properties
2. \model\Employee.java
3. \src\main\java\com\suji\sample\controller\EmployeeController.java
4. \src\main\java\com\suji\sample\controller\TestControllerEasy.java
5. \src\main\java\com\suji\sample\controller\TestControllerMedium.java
6. \src\main\java\com\suji\sample\SampleDemoFirstApplication.java
7. \src\main\java\com\suji\sample\service\EmployeeService.java
8. \src\main\java\com\suji\sample\serviceImpl\EmployeeServiceImpl.java
9. E:\SpringBoot STS\Workspace\sample-demo-first\pom.xml

```

---

### 1. application.properties

#### resources\application.properties

```properties



# Server Port
server.port=8080

# Open API Available at http://localhost:8080/api-docs/
springdoc.api-docs.path=/api-docs

# Swagger UI Available at http://localhost:8080/swagger.html
springdoc.swagger-ui.path=/swagger.html


```

---

### 2. Employee.java

#### \model\Employee.java

```java

package com.suji.sample.model;

public class Employee {

	private int id;
	private String name;
	private String dept;
	private int salary;
	
	public Employee() {
	}

	public Employee(int id, String name, String dept, int salary) {
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
	
	
}

```

---

### 3. EmployeeController.java

#### \src\main\java\com\suji\sample\controller\EmployeeController.java

```java

package com.suji.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.sample.model.Employee;
import com.suji.sample.service.EmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	//Dependencies
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(path = "/test")
	public String test() {
		return "Hello World";
	}
	
	
	// http://localhost:8080/employees/all
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/get/{empId}")
	public Employee getEmployeeById(@PathVariable(value = "empId") Integer id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping("/save")
	public String saveEmployee(@RequestBody(required = true) Employee employee) {
		return employeeService.saveEmployee(employee);
	}
}

```

---

### 4. TestControllerEasy.java

#### \src\main\java\com\suji\sample\controller\TestControllerEasy.java

```java

package com.suji.sample.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.sample.model.Employee;

@RestController
@RequestMapping("/easy")
public class TestControllerEasy {
	
	// NOTE: No two APIs must not have same METHOD and SAME Mapping.

	// ________GET MAPPING__________//
	// GET Method is used for READ/GET data

	// Say Hello
	@GetMapping("/hi")
	public String sayHi() {
		return "Hello world";
	}

	// Add two numbers
	// "Path variable name" and "Method parameter variable" name must be same.

	@GetMapping("/add/{num1}/{num2}")
	public String add(@PathVariable int num1, @PathVariable int num2) {

		return "Sum is: " + (num1 + num2);
	}

	// Reverse String
	// "Path variable name" and "Method parameter variable" name must be same.

	@GetMapping("/reverse/{inputStr}")
	public String add(@PathVariable String inputStr) {
		return "Reverse String is: " + new StringBuilder(inputStr).reverse();
	}

	// Get Fake employee
	// "Path variable name" and "Method parameter variable" name must be same.

	@GetMapping("/fake/{empId}")
	public Employee getFake(@PathVariable int empId) {

		return new Employee(empId, "FAke name", "Fake Dept", 10000);
	}

	// ________POST MAPPING__________//
	// POST Method is used for CREATE/SAVE data

	// Double Employee salary and id
	// "Request Body" must be provide as JSON Object

	@PostMapping("/double")
	public Employee doubleIt(@RequestBody Employee e) {
		return new Employee(e.getId() * 2, e.getName(), e.getDept(), e.getSalary() * 2);
	}

	// Employee Details in String
	// "Request Body" must be provide as JSON Object

	@PostMapping("/instr")
	public String details(@RequestBody Employee e) {
		return e.toString();
	}

	// ________PUT MAPPING__________//
	// PUT Method is used for UPDATE data

	// Update Employee
	// "Path variable name" and "Method parameter variable" name must be same.
	// "Request Body" must be provide as JSON Object

	@PutMapping("/update/{empId}")
	public Employee updateEmployee(@PathVariable int empId, @RequestBody Employee emp) {
		Employee newEmp = new Employee(empId, "Updated Name", "Updated Dept", 10000);
		return newEmp;
	}

	// ________DELETE MAPPING__________//
	// DELETE Method is used for DELETE data

	// Update Employee
	// "Path variable name" and "Method parameter variable" name must be same.

	@DeleteMapping("/delete/{empId}")
	public String deleteEmployeeById(@PathVariable int empId) {
		return "Employee with id '" + empId + "' has been deleted";
	}

}

```

---

### 5. TestControllerMedium.java

#### \src\main\java\com\suji\sample\controller\TestControllerMedium.java

```java

package com.suji.sample.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.sample.model.Employee;

@RestController
@RequestMapping("/medium")
public class TestControllerMedium {

	// ________GET MAPPING__________//
	// GET Method is used for READ/GET data

	// Say Hi
	@GetMapping("/hi")
	public String sayHi() {
		return "Hello world";
	}

	// Add two numbers
	@GetMapping("/add/{num1}/{num2}")
	public String add(@PathVariable(required = true, name = "num1") int a,
			@PathVariable(required = true, name = "num2") int b) {
		
		return "Sum is: " + (a + b);
	}

	// Reverse String
	@GetMapping("/reverse/{inputStr}")
	public String add(@PathVariable(required = true, name = "inputStr") String str) {
		
		return "Reverse String is: " + new StringBuilder(str).reverse();
	}

	// Get Fake employee
	@GetMapping("/fake/{empId}")
	public Employee getFake(@PathVariable(required = true, name = "empId") int id) {
		
		return new Employee(id, "FAke name", "Fake Dept", 10000);
	}

	// ________POST MAPPING__________//
	// POST Method is used for CREATE/SAVE data

	// Double Employee salary and id
	@PostMapping("/double")
	public Employee doubleIt(@RequestBody(required = true) Employee e) {
		return new Employee(e.getId() * 2, e.getName(), e.getDept(), e.getSalary() * 2);
	}

	// Employee Details in String
	@PostMapping("/instr")
	public String details(@RequestBody(required = true) Employee e) {
		return e.toString();
	}

	// ________PUT MAPPING__________//
	// PUT Method is used for UPDATE data

	@PutMapping("/update/{empId}")
	public Employee updateEmployee(@PathVariable(required = true, name = "empId") int id,
			@RequestBody(required = true) Employee e) {
		Employee newEmp = new Employee(id, "Updated Name", "Updated Dept", 10000);
		return newEmp;
	}

	// ________DELETE MAPPING__________//
	// DELETE Method is used for DELETE data

	@DeleteMapping("/delete/{empId}")
	public String deleteEmployeeById(@PathVariable(required = true, name = "empId") int id) {
		return "Employee with id '" + id + "' has been deleted";
	}

}

```

---

### 6. SampleDemoFirstApplication.java

#### \src\main\java\com\suji\sample\SampleDemoFirstApplication.java

```java

package com.suji.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleDemoFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleDemoFirstApplication.class, args);
	}
}

```

---

### 7. EmployeeService.java

#### \src\main\java\com\suji\sample\service\EmployeeService.java

```java

package com.suji.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.sample.model.Employee;

@Service
public interface EmployeeService {

	List<Employee> getAllEmployees();
	Employee getEmployeeById(int id);
	String saveEmployee(Employee employee);
	
}

```

---

### 8. EmployeeServiceImpl.java

#### \src\main\java\com\suji\sample\serviceImpl\EmployeeServiceImpl.java

```java

package com.suji.sample.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.sample.model.Employee;
import com.suji.sample.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private List<Employee> employeeList = new ArrayList<>();
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = null;
		for(Employee e : employeeList) {
			if(e.getId() == id) {
				employee = e;
			}
		}	
		return employee;
	}

	@Override
	public String saveEmployee(Employee employee) {
		
		if(employeeList.add(employee)) {
			return "Employee with id '"+employee.getId()+"' created successfully. ";
		}else {
			return "Sorry failed to create Employee with id '"+employee.getId()+"'";
		}
	}

}

```

---

### 9. pom.xml

#### E:\SpringBoot STS\Workspace\sample-demo-first\pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.2</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.suji</groupId>
	<artifactId>sample-demo-first</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>sample-demo-first</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>11</java.version>
	</properties>
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
			</plugin>
		</plugins>
	</build>

</project>

```

---

