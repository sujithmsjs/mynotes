# MD File Generator
Generate MD Files Automatically.

### Index
```pre
1. resources\application.properties
2. S:\git\mongo-restapi\mongo-rest-api\pom.xml
3. \model\Book.java
4. \model\BookTransaction.java
5. \model\Library.java
6. \model\Student.java
7. \model\StudentsBooks.java
8. \src\main\java\tech\suji\mongo\controller\TestController.java
9. \src\main\java\tech\suji\mongo\MongoRestApiApplication.java
10. \src\main\java\tech\suji\mongo\repository\BookRepository.java
11. \src\main\java\tech\suji\mongo\repository\BookTransactionRepository.java
12. \src\main\java\tech\suji\mongo\repository\LibraryRepository.java
13. \src\main\java\tech\suji\mongo\repository\StudentRepository.java
14. \src\main\java\tech\suji\mongo\repository\StudentsBooksRepository.java

```

---

### 1. application.properties

#### resources\application.properties

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
#server.context-path=/user
```

---

### 2. pom.xml

#### S:\git\mongo-restapi\mongo-rest-api\pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.0-M3</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>tech.suji</groupId>
	<artifactId>mongo-rest-api</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>mongo-rest-api</name>
	<description>MongoDB with Rest API</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>

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
	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>
	<pluginRepositories>
		<pluginRepository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</pluginRepository>
	</pluginRepositories>

</project>

```

---

### 3. Book.java

#### \model\Book.java

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

### 4. BookTransaction.java

#### \model\BookTransaction.java

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

### 5. Library.java

#### \model\Library.java

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

### 6. Student.java

#### \model\Student.java

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

### 7. StudentsBooks.java

#### \model\StudentsBooks.java

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

### 8. TestController.java

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

### 9. MongoRestApiApplication.java

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

### 10. BookRepository.java

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

### 11. BookTransactionRepository.java

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

### 12. LibraryRepository.java

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

### 13. StudentRepository.java

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

### 14. StudentsBooksRepository.java

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

