# spring-entity-mapping


### File Structure
```pre
+ spring-entity-mapping\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\mapping
		|---  SpringEntityMappingApplication.java
	+ \src\test\java\com\suji\mapping
		|---  SpringEntityMappingApplicationTests.java
	+ \src\main\java\com\suji\mapping\model
		|---  Address.java
		|---  Author.java
		|---  Book.java
		|---  Chapter.java
	+ \src\main\java\com\suji\mapping\controller
		|---  BaseController.java
	+ \src\main\java\com\suji\mapping\advice
		|---  CustomExceptionDetails.java
		|---  GlobleException.java
	+ \src\main\java\com\suji\mapping\repo
		|---  AddressRepo.java
		|---  AuthorRepo.java
		|---  BookRepo.java
		|---  ChapterRepo.java
```
### Index
```pre
1. application.properties
2. model\Address.java
3. model\Author.java
4. model\Book.java
5. model\Chapter.java
6. controller\BaseController.java
7. src\main\java\com\suji\mapping\SpringEntityMappingApplication.java
8. src\main\java\com\suji\mapping\advice\CustomExceptionDetails.java
9. src\main\java\com\suji\mapping\advice\GlobleException.java
10. src\main\java\com\suji\mapping\repo\AddressRepo.java
11. src\main\java\com\suji\mapping\repo\AuthorRepo.java
12. src\main\java\com\suji\mapping\repo\BookRepo.java
13. src\main\java\com\suji\mapping\repo\ChapterRepo.java
14. src\test\java\com\suji\mapping\SpringEntityMappingApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties





# User define values
jwt.secret ="sujith@123"


# Data Source
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# JPA Props
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto = create
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.defer-datasource-initialization=true


spring.h2.console.enabled=true




```

---

### 2. Address.java

#### model\Address.java

```java

package com.suji.mapping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addrs")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String village;
	private String state;
	private String country;
	private String pin;
	
	public Address(String village) {
		this.village = village;
	}
	
	
}

```

---

### 3. Author.java

#### model\Author.java

```java

package com.suji.mapping.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "addr", nullable = false)
	@JoinColumn(name = "addr")
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author") // NOTE mappedBy values should be EQUAL to Mapped class Varable name.
	Set<Book> books; // NOTE: Cascade Delete one author will reflect all the books.

	public Author(String name, Set<Book> books) {
		this.name = name;
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
	}
	
	
}

```

---

### 4. Book.java

#### model\Book.java

```java

package com.suji.mapping.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author sujith.manchala
 *
 */
@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@ManyToOne(cascade = CascadeType.ALL,  fetch = FetchType.LAZY )
	// @JoinColumn(name = "aid", nullable = false)
	@JoinColumn(name = "aid")
	private Author author;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	private List<Chapter> chapters;

	public Book(String title, Author author, List<Chapter> chapters) {
		this.title = title;
		this.author = author;
		this.chapters = chapters;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}

	public Book(String title) {
		this.title = title;
	}
}

```

---

### 5. Chapter.java

#### model\Chapter.java

```java

package com.suji.mapping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chapters")
public class Chapter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int no;
	
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "bid", nullable = false)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "aid")
	private Author author;

	public Chapter(int no, Book book) {
		this.no = no;
		this.book = book;
	}

}

```

---

### 6. BaseController.java

#### controller\BaseController.java

```java

package com.suji.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.mapping.model.Author;
import com.suji.mapping.repo.AuthorRepo;

@RestController
@RequestMapping("/base")
public class BaseController {

	@Autowired
	private AuthorRepo repo;
	
	// TEST Connection
	@GetMapping(path = {"/","","/check"})
	public String test() {
		return "SUCCESS! Server is ready!";
	}
	
	@GetMapping("/authors")
	public List<Author> getAllAuthors(){
		return repo.findAll(); 
	}
	
}

```

---

### 7. SpringEntityMappingApplication.java

#### src\main\java\com\suji\mapping\SpringEntityMappingApplication.java

```java

package com.suji.mapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.mapping.model.Address;
import com.suji.mapping.model.Author;
import com.suji.mapping.model.Book;
import com.suji.mapping.model.Chapter;
import com.suji.mapping.repo.AuthorRepo;
import com.suji.mapping.repo.BookRepo;

@SpringBootApplication
public class SpringEntityMappingApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringEntityMappingApplication.class, args);
	}

	@Autowired
	private AuthorRepo repo;

	@Autowired
	private BookRepo bookRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Author a = new Author();
		
		Book book = new Book("Atomic Habits");
		book.setAuthor(a);
		a.setBooks(Set.of(book));

		
		repo.save(a);
		
		// List<Chapter> list = Stream.iterate(new Chapter(1, book), ch -> new
		// Chapter(ch.getNo()+1, book)).limit(10).collect(Collectors.toList());

		// book.setChapters(list);

		// Address adrr = new Address("Yellandu");

		// Set<Book> books = Stream.iterate(new Book("Book",a, list), b -> new
		// Book("Book",a, list)).limit(5).collect(Collectors.toSet());

		// a.setAddress(adrr);
		// a.setBooks(Set.of(book));

//		System.out.println("Author: "+a);
//		books.forEach( b -> {
//			bookRepo.save(b);
//			System.out.println("Book "+b);
//			b.getChapters().forEach( ch -> System.out.println("Ch : "+ch));;
//		});

		// Author save = repo.save(a);

		// Optional.of(save);

	}

}

```

---

### 8. CustomExceptionDetails.java

#### src\main\java\com\suji\mapping\advice\CustomExceptionDetails.java

```java

package com.suji.mapping.advice;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionDetails {

	private int status;
	private String message;
	private Class exceptionClass;
	private LocalDateTime timestamp;
	
}

```

---

### 9. GlobleException.java

#### src\main\java\com\suji\mapping\advice\GlobleException.java

```java

package com.suji.mapping.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobleException {

	// GENERIC EXCEPTION HANDLER
		@ResponseBody
		@ExceptionHandler(Exception.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public ResponseEntity<CustomExceptionDetails> badRequestHandler(Exception ex) {

			// Adding details about exception
			CustomExceptionDetails customEx = new CustomExceptionDetails();
			customEx.setStatus(HttpStatus.BAD_REQUEST.value());
			customEx.setMessage(ex.getMessage());
			customEx.setExceptionClass(ex.getClass());
			customEx.setTimestamp(LocalDateTime.now());

			// Returning ResponseEntity with Exception details.
			return new ResponseEntity<CustomExceptionDetails>(customEx, HttpStatus.BAD_REQUEST);
		}

	
}

```

---

### 10. AddressRepo.java

#### src\main\java\com\suji\mapping\repo\AddressRepo.java

```java

package com.suji.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.suji.mapping.model.Address;
import com.suji.mapping.model.Chapter;

@RepositoryRestResource(path = "chapters")
public interface AddressRepo extends JpaRepository<Address, Integer> {
	
}

```

---

### 11. AuthorRepo.java

#### src\main\java\com\suji\mapping\repo\AuthorRepo.java

```java

package com.suji.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.suji.mapping.model.Author;

@RepositoryRestResource(path = "author")
public interface AuthorRepo extends JpaRepository<Author, Integer> {
	
}

```

---

### 12. BookRepo.java

#### src\main\java\com\suji\mapping\repo\BookRepo.java

```java

package com.suji.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.suji.mapping.model.Book;

@RepositoryRestResource(path = "books")
public interface BookRepo extends JpaRepository<Book, Integer> {
	
}

```

---

### 13. ChapterRepo.java

#### src\main\java\com\suji\mapping\repo\ChapterRepo.java

```java

package com.suji.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.suji.mapping.model.Chapter;

@RepositoryRestResource(path = "chapters")
public interface ChapterRepo extends JpaRepository<Chapter, Integer> {
	
}

```

---

### 14. SpringEntityMappingApplicationTests.java

#### src\test\java\com\suji\mapping\SpringEntityMappingApplicationTests.java

```java

package com.suji.mapping;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringEntityMappingApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

