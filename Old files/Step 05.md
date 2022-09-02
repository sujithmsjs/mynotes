# Demo Application Name
Use this to get to know about Demo Application Name
- \src\main\resources\application.properties
- \src\main\java\tech\suji\mongo\MongoRestApiApplication.java
- \src\main\java\tech\suji\mongo\controller\TestController.java
- \src\main\java\tech\suji\mongo\model\Book.java
- \src\main\java\tech\suji\mongo\model\BookTransaction.java
- \src\main\java\tech\suji\mongo\model\Library.java
- \src\main\java\tech\suji\mongo\model\Student.java
- \src\main\java\tech\suji\mongo\model\StudentsBooks.java
- \src\main\java\tech\suji\mongo\repository\BookRepository.java
- \src\main\java\tech\suji\mongo\repository\BookTransactionRepository.java
- \src\main\java\tech\suji\mongo\repository\LibraryRepository.java
- \src\main\java\tech\suji\mongo\repository\StudentRepository.java
- \src\main\java\tech\suji\mongo\repository\StudentsBooksRepository.java
- \src\test\java\tech\suji\mongo\MongoRestApiApplicationTests.java
### application.properties

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

### MongoRestApiApplication.java

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

### TestController.java

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

### Book.java

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

### BookTransaction.java

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

### Library.java

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

### Student.java

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

### StudentsBooks.java

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

### BookRepository.java

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

### BookTransactionRepository.java

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

### LibraryRepository.java

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

### StudentRepository.java

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

### StudentsBooksRepository.java

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

### MongoRestApiApplicationTests.java

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

