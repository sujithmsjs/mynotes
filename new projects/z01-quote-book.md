# z01-quote-book


### File Structure
```pre
+ z01-quote-book\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\quotebook
		|---  Z01QuoteBookApplication.java
	+ \src\main\java\com\suji\quotebook\model
		|---  Author.java
```
### Index
```pre
1. resources\application.properties
2. \model\Author.java
3. \src\main\java\com\suji\quotebook\Z01QuoteBookApplication.java

```

---

### 1. application.properties

#### resources\application.properties

```properties



```

---

### 2. Author.java

#### \model\Author.java

```java

package com.suji.quotebook.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@GeneratedValue
	private Integer id;

	private String name;

	private LocalDate doj;
}
```

---

### 3. Z01QuoteBookApplication.java

#### \src\main\java\com\suji\quotebook\Z01QuoteBookApplication.java

```java

package com.suji.quotebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Z01QuoteBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(Z01QuoteBookApplication.class, args);
	}

}

```

---

