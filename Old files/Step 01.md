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
