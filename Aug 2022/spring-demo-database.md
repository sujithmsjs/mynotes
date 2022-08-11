# spring-demo-database


### File Structure
```pre
+ spring-demo-database\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\demo
		|---  SpringDemoDatabaseApplication.java
	+ \src\test\java\com\suji\demo
		|---  SpringDemoDatabaseApplicationTests.java
	+ \src\main\java\com\suji\demo\model
		|---  Student.java
	+ \src\main\java\com\suji\demo\service
		|---  StudentService.java
	+ \src\main\java\com\suji\demo\repository
		|---  AccountRepo.java
		|---  BankRepository.java
		|---  StudentRepository.java
	+ \src\main\java\com\suji\demo\exp
		|---  BankWorkingFailedException.java
	+ \src\main\java\com\suji\demo\pojo
		|---  Account.java
	+ \src\main\java\com\suji\demo\expn\copy
		|---  BankNotWorkingException.java
```
### Index
```pre
1. application.properties
2. model\Student.java
3. service\StudentService.java
4. repository\AccountRepo.java
5. repository\BankRepository.java
6. repository\StudentRepository.java
7. src\main\java\com\suji\demo\SpringDemoDatabaseApplication.java
8. src\main\java\com\suji\demo\exp\BankWorkingFailedException.java
9. src\main\java\com\suji\demo\expn\copy\BankNotWorkingException.java
10. src\main\java\com\suji\demo\pojo\Account.java
11. src\test\java\com\suji\demo\SpringDemoDatabaseApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driverus

logging.level.com.suji.demo = DEBUG
```

---

### 2. Student.java

#### model\Student.java

```java

package com.suji.demo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table( name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(nullable = false)
	
	private String email;
	
	private LocalDate dob;
	
	

	public Student(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public Student(String name, String email, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	public Student() {
		super();
	}

	public Student(int id, String name, String email, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}
	
	
	
	
	
	
}

```

---

### 3. StudentService.java

#### service\StudentService.java

```java

package com.suji.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suji.demo.model.Student;
import com.suji.demo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	// CREATE
	public Student addStudent(Student student) {
		return repository.save(student);
	}
	
	// READ ALL
	public List<Student> allStudents(){
		return repository.findAll();
	}
	
	// READ BY ID
	public Student findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	// UPDATE
	
	
	// DELETE
	public void deleteStudent(Student student) {
		repository.delete(student);
	}
	
	
	
	
	
	
	
	
	
}

```

---

### 4. AccountRepo.java

#### repository\AccountRepo.java

```java

package com.suji.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.demo.pojo.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}

```

---

### 5. BankRepository.java

#### repository\BankRepository.java

```java

package com.suji.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.suji.demo.pojo.Account;

@Repository
public class BankRepository {

	@Autowired
	private AccountRepo repo;
	
	public Account createNewAcc(String name, double amt) {
		return repo.save(new Account(name,amt));
	}
	
	@Transactional
	public void sendMoney(int fromAcc, int toAcc, double amt) {
		Account from = repo.findById(fromAcc).orElse(null);
		Account to = repo.findById(toAcc).orElse(null);
		
		from.setBlnc(from.getBlnc()-amt);
		
		to.setBlnc(to.getBlnc()+amt);
		
		repo.save(from);
		System.out.println("Money debited from acc "+fromAcc+" Amount: "+amt);
		
		repo.save(to);
		System.out.println("Money Credited into acc "+fromAcc+" Amount: "+amt);
		
		//repo.save(new Account("Sandya",12));
		
	}
}

```

---

### 6. StudentRepository.java

#### repository\StudentRepository.java

```java

package com.suji.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.demo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	
}

```

---

### 7. SpringDemoDatabaseApplication.java

#### src\main\java\com\suji\demo\SpringDemoDatabaseApplication.java

```java

package com.suji.demo;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.suji.demo.model.Student;
import com.suji.demo.repository.BankRepository;
import com.suji.demo.service.StudentService;

@SpringBootApplication
public class SpringDemoDatabaseApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoDatabaseApplication.class, args);
	}
	
	@Autowired
	private StudentService service;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		service.addStudent(new Student(1,"Sujith", "sujithmsjs@gmail.com",LocalDate.of(1994, 9, 2)));
		
	}
}

```

---

### 8. BankWorkingFailedException.java

#### src\main\java\com\suji\demo\exp\BankWorkingFailedException.java

```java

package com.suji.demo.exp;

public class BankWorkingFailedException extends Throwable{

	public BankWorkingFailedException() {
		super("Bank not working exception...");
	}
	
	
}

```

---

### 9. BankNotWorkingException.java

#### src\main\java\com\suji\demo\expn\copy\BankNotWorkingException.java

```java

package com.suji.demo.expn.copy;

public class BankNotWorkingException {

}

```

---

### 10. Account.java

#### src\main\java\com\suji\demo\pojo\Account.java

```java

package com.suji.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accno;
	
	
	@Column(unique = true)
	private String name;

	private double blnc;

	public Account(String name, double blnc) {
		super();
		this.name = name;
		this.blnc = blnc;
	}

	public Account() {
		super();
	}
	
	

	
	
}

```

---

### 11. SpringDemoDatabaseApplicationTests.java

#### src\test\java\com\suji\demo\SpringDemoDatabaseApplicationTests.java

```java

package com.suji.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDemoDatabaseApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

