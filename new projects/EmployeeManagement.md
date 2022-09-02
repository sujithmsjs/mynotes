# EmployeeManagement


### File Structure
```pre
+ EmployeeManagement\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\empmgnt
		|---  EmployeeManagementApplication.java
	+ \src\main\java\com\suji\empmgnt\model
		|---  Department.java
		|---  Employee.java
		|---  SalGrade.java
	+ \src\main\java\com\suji\empmgnt\controller
		|---  EmployeeController.java
	+ \src\main\java\com\suji\empmgnt\repository
		|---  EmployeeRepository.java
	+ \src\main\java\com\suji\empmgnt\service
		|---  EmployeeService.java
		|---  EmployeeServiceMongoImpl.java
		|---  EmployeeServiceMysqlImpl.java
```
### Index
```pre
1. resources\application.properties
2. \model\Department.java
3. \model\Employee.java
4. \model\SalGrade.java
5. \src\main\java\com\suji\empmgnt\controller\EmployeeController.java
6. \src\main\java\com\suji\empmgnt\EmployeeManagementApplication.java
7. \src\main\java\com\suji\empmgnt\repository\EmployeeRepository.java
8. \src\main\java\com\suji\empmgnt\service\EmployeeService.java
9. \src\main\java\com\suji\empmgnt\service\EmployeeServiceMongoImpl.java
10. \src\main\java\com\suji\empmgnt\service\EmployeeServiceMysqlImpl.java

```

---

### 1. application.properties

#### resources\application.properties

```properties


# Zuul Client Details
spring.application.name=employees
server.port=8090

# User define values
jwt.secret ="sujith@123"


# Data Source
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sujith
spring.datasource.password=suji@1234

# JPA Props
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto = create
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.defer-datasource-initialization=true

spring.h2.console.enabled=true



```

---

### 2. Department.java

#### \model\Department.java

```java

package com.suji.empmgnt.model;

import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dept")
public class Department {
	@Id
	private int deptno;
	private String dname;
	private String loc;

}

```

---

### 3. Employee.java

#### \model\Employee.java

```java

package com.suji.empmgnt.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="emp")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private LocalDate hiredate;
	private BigDecimal sal;
	@Column(nullable = true)
	private BigDecimal comm;
	@OneToOne
	@JoinColumn(name = "dept")
	private Department dept;
	
	// SELECT e.job, count(job) from Employee GROUP BY a.job
	// SELECT e.job, max(e.sal) from Employee e GROUP BY e.job
}

```

---

### 4. SalGrade.java

#### \model\SalGrade.java

```java

package com.suji.empmgnt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salgrade")
public class SalGrade {
	@Id
	private int	grade;
	private int  losal;
	private int hisal;
}

```

---

### 5. EmployeeController.java

#### \src\main\java\com\suji\empmgnt\controller\EmployeeController.java

```java

package com.suji.empmgnt.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.service.EmployeeService;
import com.suji.empmgnt.service.EmployeeServiceMongoImpl;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	@Qualifier("employeeServiceMongoImpl")
	EmployeeService empService;
	
	
	// GET:

	@GetMapping(path = { "", "/" })
	public List<Employee> findAll() {
		Scanner s;
		return empService.getAllEmployees();
	}

	@GetMapping(path = { "/{id}" })
	public Employee getById(@PathVariable Integer id) {

		return empService.getEmployeeById(id).orElseThrow();

	}

}

```

---

### 6. EmployeeManagementApplication.java

#### \src\main\java\com\suji\empmgnt\EmployeeManagementApplication.java

```java

package com.suji.empmgnt;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.repository.EmployeeRepository;
import com.suji.empmgnt.service.EmployeeService;

@SpringBootApplication
public class EmployeeManagementApplication implements ApplicationRunner {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	@Qualifier("employeeServiceMongoImpl")
	EmployeeService empService;

	@Autowired
	EmployeeRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> createQuery = builder.createQuery(Employee.class);
		Root<Employee> root = createQuery.from(Employee.class);
	}

}

```

---

### 7. EmployeeRepository.java

#### \src\main\java\com\suji\empmgnt\repository\EmployeeRepository.java

```java

package com.suji.empmgnt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suji.empmgnt.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	@Query("SELECT e.ename, d.dname FROM Employee e JOIN e.dept d")
	List<?> findEmployeesWithDept();

	@Query("SELECT e.ename FROM Employee e where e.id = :id")
	Optional<String> findEnameById(int id);

	@Query("SELECT e.ename FROM Employee e where e.mgr = :mgr")
	List<String> findFirstByMgr(int mgr);
	
	@Query("SELECT e.job, max(sal) from Employee e GROUP BY e.job")
	List<?> groupByJobMax();
	
	@Query("SELECT e.job, min(sal) from Employee e GROUP BY e.job")
	List<?> groupByJobMin();

	@Query("SELECT e.job, count(sal) from Employee e GROUP BY e.job")
	List<?> groupByJobCount();

	@Query("SELECT e.job, avg(sal) from Employee e GROUP BY e.job")
	List<?> groupByJobAvg();
	
	@Query("SELECT e.job, sum(sal) from Employee e GROUP BY e.job")
	List<?> groupByJobSum();
	
}



```

---

### 8. EmployeeService.java

#### \src\main\java\com\suji\empmgnt\service\EmployeeService.java

```java

package com.suji.empmgnt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.suji.empmgnt.model.Employee;

@Service
public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Optional<Employee> getEmployeeById(Integer id);
	
	public Employee saveEmployee(Employee employee);

}

```

---

### 9. EmployeeServiceMongoImpl.java

#### \src\main\java\com\suji\empmgnt\service\EmployeeServiceMongoImpl.java

```java

package com.suji.empmgnt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.repository.EmployeeRepository;

@Service

public class EmployeeServiceMongoImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	
	
	
}

```

---

### 10. EmployeeServiceMysqlImpl.java

#### \src\main\java\com\suji\empmgnt\service\EmployeeServiceMysqlImpl.java

```java

package com.suji.empmgnt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.suji.empmgnt.model.Employee;
import com.suji.empmgnt.repository.EmployeeRepository;

@Service
@Primary
public class EmployeeServiceMysqlImpl implements EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	
	
	
}

```

---

