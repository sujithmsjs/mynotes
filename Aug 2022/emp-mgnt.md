# Employee Student Management


### File Structure
```pre
+ emp-mgnt\ 
	|---  pom.xml
	+ \src\main\resources
		|---  application.properties
		|---  data.sql
	+ \src\main\java\com\suji\empmgnt
		|---  EmpMgntApplication.java
	+ \src\test\java\com\suji\empmgnt
		|---  EmpMgntApplicationTests.java
	+ \src\main\java\com\suji\empmgnt\model
		|---  Address.java
		|---  Employee.java
		|---  Gender.java
		|---  Student.java
	+ \src\main\java\com\suji\empmgnt\controller
		|---  EmployeeController.java
		|---  StudentController.java
	+ \src\main\java\com\suji\empmgnt\service
		|---  EmployeeService.java
		|---  StudentService.java
	+ \src\main\java\com\suji\empmgnt\repository
		|---  EmployeeRepository.java
		|---  StudentRepository.java
	+ \src\main\java\com\suji\empmgnt\service\impl
		|---  EmployeeServiceImpl.java
		|---  StudentServiceImpl.java
```
### Index
```pre
1. application.properties
2. pom.xml
3. model\Address.java
4. model\Employee.java
5. model\Gender.java
6. model\Student.java
7. controller\EmployeeController.java
8. controller\StudentController.java
9. service\EmployeeService.java
10. service\StudentService.java
11. repository\EmployeeRepository.java
12. repository\StudentRepository.java
13. src\main\java\com\suji\empmgnt\EmpMgntApplication.java
14. src\main\java\com\suji\empmgnt\service\impl\EmployeeServiceImpl.java
15. src\main\java\com\suji\empmgnt\service\impl\StudentServiceImpl.java
16. resources\data.sql
17. src\test\java\com\suji\empmgnt\EmpMgntApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


# Server Settings
server.port=8080
spring.application.name=Project_Name

# Open API Available at http://localhost:8080/api-docs/
springdoc.api-docs.path=/api-docs

# Swagger
# Default URL: http://localhost:8080/swagger-ui/index.html
# Custom URL: http://localhost:8080/swagger.html
springdoc.swagger-ui.path=/swagger.html

# DateTimeFormatters
spring.mvc.format.date=yyyy-MM-dd
spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
spring.mvc.format.time=HH:mm:ss

# H2 Data Source
# H2DB URL: http://localhost:8080/h2-console

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sujith
spring.datasource.password=password

# H2 Database Settings
# H2DB URL: http://localhost:8080/h2-console
# Custom URL: 	http://localhost:8080/h2db
# spring.h2.console.path=/h2db

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.h2.console.enabled=true

# JPA Props
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto = create

# Error Message
server.error.include-message=always
server.error.include-exception=true

```

---

### 2. pom.xml

#### pom.xml

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
	<artifactId>emp-mgnt</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>emp-mgnt</name>
	<description>Employee Management</description>
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
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.4</version>
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

### 3. Address.java

#### model\Address.java

```java

package com.suji.empmgnt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Entity
// @Table(name = "addrs")
public class Address {

//	@Id
//	private int id;
	private String pinCode;
	private String street;

}

```

---

### 4. Employee.java

#### model\Employee.java

```java


package com.suji.empmgnt.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String dept;
	private double salary;
	private Gender gender;
	private LocalDate dateOfJoin;
	private boolean active;
	
	//@OneToOne
	//@JoinColumn(name = "addr_id")
	@Embedded
	private Address address;
	
	@CollectionTable(name="emp_langs")
	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> languages;
	

	public Employee(int id, String name, String dept, double salary) {
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}
}

```

---

### 5. Gender.java

#### model\Gender.java

```java

package com.suji.empmgnt.model;

public enum Gender {
	MALE, FEMALE
}

```

---

### 6. Student.java

#### model\Student.java

```java


package com.suji.empmgnt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "students")

public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private String email;
	private int marks;
	private double cgpa;
}

```

---

### 7. EmployeeController.java

#### controller\EmployeeController.java

```java

package com.suji.empmgnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.service.EmployeeService;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return service.getAllEmployee();
	}
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		return service.addEmployee(employee);
	}

}

```

---

### 8. StudentController.java

#### controller\StudentController.java

```java

package com.suji.empmgnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.model.Student;
import com.suji.empmgnt.service.StudentService;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping
	public List<Student> getAllEmployees(){
		return service.getAllStudents();
	}
	
	@PostMapping
	public Student saveEmployee(@RequestBody Student student) {
		return service.saveStudent(student);
	}

}

```

---

### 9. EmployeeService.java

#### service\EmployeeService.java

```java

package com.suji.empmgnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.empmgnt.model.Employee;

@Service
public interface EmployeeService  {
	
	

	public Employee addEmployee(Employee emp);
	public List<Employee> getAllEmployee();
		
}

```

---

### 10. StudentService.java

#### service\StudentService.java

```java

package com.suji.empmgnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.empmgnt.model.Student;

@Service
public interface StudentService  {
	
	public Student saveStudent(Student student);
	public List<Student> getAllStudents();
		
}

```

---

### 11. EmployeeRepository.java

#### repository\EmployeeRepository.java

```java

package com.suji.empmgnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suji.empmgnt.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
}

```

---

### 12. StudentRepository.java

#### repository\StudentRepository.java

```java

package com.suji.empmgnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suji.empmgnt.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}

```

---

### 13. EmpMgntApplication.java

#### src\main\java\com\suji\empmgnt\EmpMgntApplication.java

```java

package com.suji.empmgnt;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.empmgnt.model.Address;
import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.model.Gender;
import com.suji.empmgnt.model.Student;
import com.suji.empmgnt.repository.EmployeeRepository;
import com.suji.empmgnt.repository.StudentRepository;

@SpringBootApplication
public class EmpMgntApplication implements ApplicationRunner {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(EmpMgntApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		Student student = new Student(101,"Sujith", "sujithmsjs@gmail.com", 78, 7.3);
		studentRepository.save(student);
		
		
		Address addr = new Address("507123", "Nehru nagar");
		
		Employee e = new Employee(1, "Sujith","IT", 50_000);
		e.setActive(true);
		e.setDateOfJoin(LocalDate.of(2014, 3, 3));
		e.setGender(Gender.MALE);
		e.setAddress(addr);
		e.setLanguages( List.of("Java", "Python", "C++"));
		
		employeeRepository.save(e);
	}

}

```

---

### 14. EmployeeServiceImpl.java

#### src\main\java\com\suji\empmgnt\service\impl\EmployeeServiceImpl.java

```java

package com.suji.empmgnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.repository.EmployeeRepository;
import com.suji.empmgnt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee addEmployee(Employee emp) {
		return repository.save(emp);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return repository.findAll();
	}

}

```

---

### 15. StudentServiceImpl.java

#### src\main\java\com\suji\empmgnt\service\impl\StudentServiceImpl.java

```java

package com.suji.empmgnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.model.Student;
import com.suji.empmgnt.repository.EmployeeRepository;
import com.suji.empmgnt.repository.StudentRepository;
import com.suji.empmgnt.service.EmployeeService;
import com.suji.empmgnt.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;

	@Override
	public Student saveStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return repository.findAll();
	}

}

```

---

### 16. data.sql

#### resources\data.sql

```sql

INSERT INTO students(id, name, email, marks, cgpa) values(2, 'Pranisha', 'pranisha@gmail.com', 82,7.9);

INSERT INTO students(id, name, email, marks, cgpa) values(3, 'Vineeth', 'vineeth@gmail.com', 61, 6.9);

INSERT INTO students(id, name, email, marks, cgpa) values(4, 'Vamshi', 'vams@gmail.com', 45 ,5.9);

INSERT INTO students(id, name, email, marks, cgpa) values(5, 'Ajay', 'ajaygulla@gmail.com', 35,3.9);

INSERT INTO students(id, name, email, marks, cgpa) values(6, 'Vinika', 'vinika@gmail.com', 92,9.1);
```

---

### 17. EmpMgntApplicationTests.java

#### src\test\java\com\suji\empmgnt\EmpMgntApplicationTests.java

```java

package com.suji.empmgnt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmpMgntApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

