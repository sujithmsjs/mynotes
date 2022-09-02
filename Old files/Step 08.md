# Demo Application Name
Use this to get to know about Demo Application Name

## Index
```pre
1. \src\main\resources\application.properties
2. \src\main\java\tech\suji\mongo\MongoRestApiApplication.java
3. \src\main\java\tech\suji\mongo\controller\TestController.java
4. \src\main\java\tech\suji\mongo\model\Book.java
5. \src\main\java\tech\suji\mongo\model\BookTransaction.java
6. \src\main\java\tech\suji\mongo\model\Library.java
7. \src\main\java\tech\suji\mongo\model\Student.java
8. \src\main\java\tech\suji\mongo\model\StudentsBooks.java
9. \src\main\java\tech\suji\mongo\repository\BookRepository.java
10. \src\main\java\tech\suji\mongo\repository\BookTransactionRepository.java
11. \src\main\java\tech\suji\mongo\repository\LibraryRepository.java
12. \src\main\java\tech\suji\mongo\repository\StudentRepository.java
13. \src\main\java\tech\suji\mongo\repository\StudentsBooksRepository.java
14. \src\test\java\tech\suji\mongo\MongoRestApiApplicationTests.java
```
---
### 1. application.properties

#### \src\main\resources\application.properties


```properties
springdoc.swagger-ui.url=/swagger-ui.html
# springdoc.swagger-ui.operationsSorter=method


#Local MongoDB config
#spring.data.mongodb.authentication-database=admin
#spring.data.mongodb.username=root
#spring.data.mongodb.password=root
spring.data.mongodb.database=library_db
#spring.data.mongodb.port=27017
#spring.data.mongodb.host=localhost

# App config
server.port=8080
#spring.application.name=BootMongo
#server.context-path=/user```

---

### 2. MongoRestApiApplication.java

#### \src\main\java\tech\suji\mongo\MongoRestApiApplication.java


```java
package tech.suji.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MongoRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoRestApiApplication.class, args);
	}
	
}
```

---

### 3. TestController.java

#### \src\main\java\tech\suji\mongo\controller\TestController.java


```java
package tech.suji.mongo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@GetMapping("/test")
	public String test() {
		return "Success";
	}
}
```

---

### 4. Book.java

#### \src\main\java\tech\suji\mongo\model\Book.java


```java
package tech.suji.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Book {

	@Id
	private int id;
	private String title;
	
}
```

---

### 5. BookTransaction.java

#### \src\main\java\tech\suji\mongo\model\BookTransaction.java


```java
package tech.suji.mongo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class BookTransaction {

	@Id
	private int id;
	
	@DBRef
	private Book book;
	
	@DBRef
	private Student student;
	
	private LocalDate date;
	
}
```

---

### 6. Library.java

#### \src\main\java\tech\suji\mongo\model\Library.java


```java
package tech.suji.mongo.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Library {
	
	@Id
	private int id;
	private Set<Book> books = new LinkedHashSet<>();
	
	public Library(Set<Book> books) {
		this.books = books;
	}

	
	
}
```

---

### 7. Student.java

#### \src\main\java\tech\suji\mongo\model\Student.java


```java
package tech.suji.mongo.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Student {
	
	@Id
	private int id;
	private String name;
	
	public Student(String name) {
		this.name = name;
	}
}
```

---

### 8. StudentsBooks.java

#### \src\main\java\tech\suji\mongo\model\StudentsBooks.java


```java
package tech.suji.mongo.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class StudentsBooks {

	@Id
	private int id;
	
	@DBRef
	private StudentsBooks student;
	
	@DBRef
	private Set<Book> books = new LinkedHashSet<>();

	public StudentsBooks(StudentsBooks student, Set<Book> books) {
		this.student = student;
		this.books = books;
	}
	
	
}
```

---

### 9. BookRepository.java

#### \src\main\java\tech\suji\mongo\repository\BookRepository.java


```java
package tech.suji.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tech.suji.mongo.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

	
}
```

---

### 10. BookTransactionRepository.java

#### \src\main\java\tech\suji\mongo\repository\BookTransactionRepository.java


```java
package tech.suji.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tech.suji.mongo.model.BookTransaction;

@Repository
public interface BookTransactionRepository extends MongoRepository<BookTransaction, Integer> {

	
}
```

---

### 11. LibraryRepository.java

#### \src\main\java\tech\suji\mongo\repository\LibraryRepository.java


```java
package tech.suji.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tech.suji.mongo.model.Library;

@Repository
public interface LibraryRepository extends MongoRepository<Library, Integer> {

	
}
```

---

### 12. StudentRepository.java

#### \src\main\java\tech\suji\mongo\repository\StudentRepository.java


```java
package tech.suji.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tech.suji.mongo.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {

	
}
```

---

### 13. StudentsBooksRepository.java

#### \src\main\java\tech\suji\mongo\repository\StudentsBooksRepository.java


```java
package tech.suji.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tech.suji.mongo.model.StudentsBooks;

@Repository
public interface StudentsBooksRepository extends MongoRepository<StudentsBooks, Integer> {
	
	
}
```

---

### 14. MongoRestApiApplicationTests.java

#### \src\test\java\tech\suji\mongo\MongoRestApiApplicationTests.java


```java
package tech.suji.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MongoRestApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

---

