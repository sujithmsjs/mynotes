# EmployeeManagement


### File Structure
```pre
+ EmployeeManagement\ 
	+ \src\main\resources
		|---  application.properties
		|---  data.sql
	+ \src\main\java\com\suji\empmgnt
		|---  EmployeeManagementApplication.java
	+ \src\test\java\com\suji\empmgnt
		|---  EmployeeManagementApplicationTests.java
	+ \src\main\java\com\suji\empmgnt\model
		|---  Department.java
		|---  Employee.java
		|---  SalGrade.java
	+ \src\main\java\com\suji\empmgnt\controller
		|---  EmployeeController.java
	+ \src\main\java\com\suji\empmgnt\service
		|---  EmployeeService.java
		|---  EmployeeServiceMongoImpl.java
		|---  EmployeeServiceMysqlImpl.java
	+ \src\main\java\com\suji\empmgnt\repository
		|---  EmployeeRepository.java
```
### Index
```pre
1. application.properties
2. model\Department.java
3. model\Employee.java
4. model\SalGrade.java
5. controller\EmployeeController.java
6. service\EmployeeService.java
7. service\EmployeeServiceMongoImpl.java
8. service\EmployeeServiceMysqlImpl.java
9. repository\EmployeeRepository.java
10. src\main\java\com\suji\empmgnt\EmployeeManagementApplication.java
11. resources\data.sql
12. src\test\java\com\suji\empmgnt\EmployeeManagementApplicationTests.java

```

---

### 1. application.properties

#### application.properties

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

#### model\Department.java

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

#### model\Employee.java

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

#### model\SalGrade.java

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

#### controller\EmployeeController.java

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

### 6. EmployeeService.java

#### service\EmployeeService.java

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

### 7. EmployeeServiceMongoImpl.java

#### service\EmployeeServiceMongoImpl.java

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

### 8. EmployeeServiceMysqlImpl.java

#### service\EmployeeServiceMysqlImpl.java

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

### 9. EmployeeRepository.java

#### repository\EmployeeRepository.java

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

### 10. EmployeeManagementApplication.java

#### src\main\java\com\suji\empmgnt\EmployeeManagementApplication.java

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

### 11. data.sql

#### resources\data.sql

```sql


INSERT INTO dept(deptno,dname,loc) VALUES(10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO dept(deptno,dname,loc) VALUES(20, 'RESEARCH', 'DALLAS');
INSERT INTO dept(deptno,dname,loc) VALUES(30, 'SALES', 'CHICAGO');
INSERT INTO dept(deptno,dname,loc) VALUES(40, 'OPERATIONS', 'BOSTON');



INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7839, 'KING', 'PRESIDENT', null,
PARSEDATETIME('17-11-1981','d-M-yyyy'),
 5000, null, 10 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7698, 'BLAKE', 'MANAGER', 7839,
PARSEDATETIME('1-5-1981','d-M-yyyy'),
 2850, null, 30);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7782, 'CLARK', 'MANAGER', 7839,
PARSEDATETIME('9-6-1981','d-M-yyyy'),
 2450, null, 10);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7566, 'JONES', 'MANAGER', 7839,
PARSEDATETIME('2-4-1981','d-M-yyyy'),
 2975, null, 20);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7788, 'SCOTT', 'ANALYST', 7566,
PARSEDATETIME('19-4-1987','d-M-yyyy'),
 3000, null, 20);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7902, 'FORD', 'ANALYST', 7566,
PARSEDATETIME('3-12-1981','d-M-yyyy'),
 3000, null, 20 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7369, 'SMITH', 'CLERK', 7902,
PARSEDATETIME('17-12-1980','d-M-yyyy'),
 800, null, 20 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7499, 'ALLEN', 'SALESMAN', 7698,
PARSEDATETIME('20-2-1981','d-M-yyyy'),
 1600, 300, 30);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7521, 'WARD', 'SALESMAN', 7698,
PARSEDATETIME('22-2-1981','d-M-yyyy'),
 1250, 500, 30 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7654, 'MARTIN', 'SALESMAN', 7698,
PARSEDATETIME('28-9-1981','d-M-yyyy'),
 1250, 1400, 30 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7844, 'TURNER', 'SALESMAN', 7698,
PARSEDATETIME('8-9-1981','d-M-yyyy'),
 1500, 0, 30);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7876, 'ADAMS', 'CLERK', 7788,
PARSEDATETIME('23-5-1987', 'd-M-yyyy'),
 1100, null, 20 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7900, 'JAMES', 'CLERK', 7698,
PARSEDATETIME('3-12-1981','d-M-yyyy'),
 950, null, 30 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7934, 'MILLER', 'CLERK', 7782,
PARSEDATETIME('23-1-1982','d-M-yyyy'),
 1300, null, 10 );

INSERT INTO salgrade(grade,losal,hisal) VALUES (1, 700, 1200);
INSERT INTO salgrade(grade,losal,hisal) VALUES (2, 1201, 1400);
INSERT INTO salgrade(grade,losal,hisal) VALUES (3, 1401, 2000);
INSERT INTO salgrade(grade,losal,hisal) VALUES (4, 2001, 3000);
INSERT INTO salgrade(grade,losal,hisal) VALUES (5, 3001, 9999);


```

---

### 12. EmployeeManagementApplicationTests.java

#### src\test\java\com\suji\empmgnt\EmployeeManagementApplicationTests.java

```java

package com.suji.empmgnt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

