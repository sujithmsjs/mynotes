# Crud Operation with Thymeleaf


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
		|---  TestController.java
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
4. \src\main\java\com\suji\sample\controller\TestController.java
5. \src\main\java\com\suji\sample\SampleDemoFirstApplication.java
6. \src\main\java\com\suji\sample\service\EmployeeService.java
7. \src\main\java\com\suji\sample\serviceImpl\EmployeeServiceImpl.java
8. E:\SpringBoot STS\Workspace\sample-demo-first\pom.xml

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

import javax.websocket.server.PathParam;

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
	
	@GetMapping(path = "")
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

### 4. TestController.java

#### \src\main\java\com\suji\sample\controller\TestController.java

```java

package com.suji.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.sample.model.Employee;

@RestController
@RequestMapping("/test")
public class TestController {
	
	//________GET MAPPING__________//
	
	// Say Hi
	@GetMapping("/hi")
	public String sayHi() {
		return "Hello world";
	}

	// Add two numbers
	@GetMapping("/add/{num1}/{num2}")
	public String add(@PathVariable(required = true, name = "num1") int a, @PathVariable(required = true, name = "num1") int b) {
		return "Sum is: "+(a+b);
	}
	
	// Reverse String
	@GetMapping("/reverse/{inputStr}")
	public String add(@PathVariable(required = true, name = "inputStr") String str) {
		return "Reverse String is: "+ new StringBuilder(str).reverse();
	}
	
	// Get Fake employee
	@GetMapping("/fake/{empId}")
	public Employee getFake(@PathVariable(required = true, name = "empId") int id) {
		return new Employee(id, "FAke name", "Fake Dept", 10000);
	}
	
	//________POST MAPPING__________//

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
}

```

---

### 5. SampleDemoFirstApplication.java

#### \src\main\java\com\suji\sample\SampleDemoFirstApplication.java

```java

package com.suji.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.sample.model.Employee;
import com.suji.sample.service.EmployeeService;

@SpringBootApplication
public class SampleDemoFirstApplication implements ApplicationRunner {

	
	@Autowired
	private EmployeeService employeeService;
	
	public static void main(String[] args) {
		SpringApplication.run(SampleDemoFirstApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		 employeeService.saveEmployee(new Employee(1, "Pranisha", "IT", 50000));
		 employeeService.saveEmployee(new Employee(1, "Sujith", "OP", 35000));
		 employeeService.saveEmployee(new Employee(1, "Sravaki", "Medical", 20000));
	}

}

```

---

### 6. EmployeeService.java

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

### 7. EmployeeServiceImpl.java

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

### 8. pom.xml

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

