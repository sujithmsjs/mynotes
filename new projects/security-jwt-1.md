# security-jwt-1


### File Structure
```pre
+ security-jwt-1\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\tech\suji\jwt
		|---  SpringSecurityDemo2Application.java
	+ \src\main\java\tech\suji\jwt\model
		|---  Address.java
		|---  Student.java
		|---  Technology.java
	+ \src\main\java\tech\suji\jwt\config
		|---  AppConfig.java
	+ \src\main\java\tech\suji\jwt\repository
		|---  StudentRepository.java
	+ \src\main\java\tech\suji\jwt\util
		|---  TokenGenerator.java
```
### Index
```pre
1. resources\application.properties
2. \model\Address.java
3. \model\Student.java
4. \model\Technology.java
5. \src\main\java\tech\suji\jwt\config\AppConfig.java
6. \src\main\java\tech\suji\jwt\repository\StudentRepository.java
7. \src\main\java\tech\suji\jwt\SpringSecurityDemo2Application.java
8. \src\main\java\tech\suji\jwt\util\TokenGenerator.java

```

---

### 1. application.properties

#### resources\application.properties

```properties

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

#### \model\Address.java

```java

package tech.suji.jwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String city;
	private String pincode;
	
	public Address(String city, String pincode) {
		super();
		this.city = city;
		this.pincode = pincode;
	}


}

```

---

### 3. Student.java

#### \model\Student.java

```java

package tech.suji.jwt.model;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name ="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private LocalDate dob;
	@OneToOne
	private Address address;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Technology> technologies;
	
	public Student(String username, String password, LocalDate dob) {
		super();
		this.username = username;
		this.password = password;
		this.dob = dob;
	}
	
	
	
}

```

---

### 4. Technology.java

#### \model\Technology.java

```java

package tech.suji.jwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Technology {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	public Technology(String name) {
		super();
		this.name = name;
	}
	
	
	
	
}

```

---

### 5. AppConfig.java

#### \src\main\java\tech\suji\jwt\config\AppConfig.java

```java

package tech.suji.jwt.config;

public class AppConfig {

}

```

---

### 6. StudentRepository.java

#### \src\main\java\tech\suji\jwt\repository\StudentRepository.java

```java

package tech.suji.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tech.suji.jwt.model.Student;

@RepositoryRestResource(collectionResourceRel = "coll", itemResourceRel = "item",path = "stds")
public interface StudentRepository extends JpaRepository<Student, Integer>  {

}

```

---

### 7. SpringSecurityDemo2Application.java

#### \src\main\java\tech\suji\jwt\SpringSecurityDemo2Application.java

```java

package tech.suji.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemo2Application.class, args);
	}

}

```

---

### 8. TokenGenerator.java

#### \src\main\java\tech\suji\jwt\util\TokenGenerator.java

```java

package tech.suji.jwt.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerator {

	private final String SECRET_KEY;
	private static final long EXP_TIME_IN_MINS = 2;

	public TokenGenerator(String secret) {
		SECRET_KEY = secret;
	}

	// Generating Token
	public String generateToken(String username) {

		// Issure Date: NOW
		LocalDateTime now = LocalDateTime.now();

		// Expire Date:
		LocalDateTime withMinute = LocalDateTime.now().plusMinutes(EXP_TIME_IN_MINS);

		JwtBuilder builder = Jwts.builder()
				// .setId("101")
				.setSubject(username)
				// .setIssuer("Sujith")
				.setIssuedAt(toDate(now)).setExpiration(toDate(withMinute))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(SECRET_KEY.getBytes()));

		return builder.compact();
	}

	public long getCountDownSecounds(String token) {
		Claims claims = getClaims(token);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expireAt = LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());

		Duration between = Duration.between(now, expireAt);

		return between.toSeconds();
	}

	// Get Claims
	public Claims getClaims(String token) {
		byte[] encode = Base64.getEncoder().encode(SECRET_KEY.getBytes());

		return Jwts.parser().setSigningKey(encode).parseClaimsJws(token).getBody();
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	// Print Claims
	public void printClaims(String token) {
		Claims claims = getClaims(token);

		System.out.println("Reading Claims for token: " + token);
		System.out.println(claims.getId());
		System.out.println(claims.getSubject());
		System.out.println(claims.getIssuer());
		System.out.println(claims.getIssuedAt());
		System.out.println(claims.getExpiration());

	}

	public boolean isTimeExpired(String token) {
		return getCountDownSecounds(token) < 0;
	}

	public boolean validateToken(String username, String token) {
		return getClaims(token).getSubject().equals(username) && !isTimeExpired(token);
	}

	////////////////////////////////////////////////////////////

	private Date toDate(LocalDateTime dateTime) {
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}

```

---

