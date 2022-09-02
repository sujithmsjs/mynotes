# rest-resources


### File Structure
```pre
+ rest-resources\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\restresources
		|---  RestResourcesApplication.java
	+ \src\test\java\com\suji\restresources
		|---  RestResourcesApplicationTests.java
	+ \src\main\java\com\suji\restresources\model
		|---  Student.java
	+ \src\main\java\com\suji\restresources\repository
		|---  StudentRepository.java
```
### Index
```pre
1. application.properties
2. model\Student.java
3. repository\StudentRepository.java
4. src\main\java\com\suji\restresources\RestResourcesApplication.java
5. src\test\java\com\suji\restresources\RestResourcesApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

### Data Source ###
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

### JPA Props ###
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto = create

### H2 Database Settings ###
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.h2.console.enabled=true

```

---

### 2. Student.java

#### model\Student.java

```java

package com.suji.restresources.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int marks;
	private LocalDate dob;
	
	public Student(String name, int marks, LocalDate dob) {
		this.name = name;
		this.marks = marks;
		this.dob = dob;
	}
	
	
}

```

---

### 3. StudentRepository.java

#### repository\StudentRepository.java

```java

package com.suji.restresources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.suji.restresources.model.Student;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Integer> {

}

```

---

### 4. RestResourcesApplication.java

#### src\main\java\com\suji\restresources\RestResourcesApplication.java

```java

package com.suji.restresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestResourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestResourcesApplication.class, args);
	}

}

```

---

### 5. RestResourcesApplicationTests.java

#### src\test\java\com\suji\restresources\RestResourcesApplicationTests.java

```java

package com.suji.restresources;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestResourcesApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

