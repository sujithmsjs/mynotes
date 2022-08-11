# RestfulDemo


### File Structure
```pre
+ RestfulDemo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\restful
		|---  RestfulDemoApplication.java
	+ \src\test\java\com\suji\restful
		|---  RestfulDemoApplicationTests.java
	+ \src\main\java\com\suji\restful\controller
		|---  CatController.java
	+ \src\main\java\com\suji\restful\entity
		|---  Cat.java
	+ \src\main\java\com\suji\restful\exception
		|---  ExceptionAdvicer.java
```
### Index
```pre
1. application.properties
2. controller\CatController.java
3. src\main\java\com\suji\restful\RestfulDemoApplication.java
4. src\main\java\com\suji\restful\entity\Cat.java
5. src\main\java\com\suji\restful\exception\ExceptionAdvicer.java
6. src\test\java\com\suji\restful\RestfulDemoApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

#LOGGING

logging.level.com.suji.restful=TRACE


spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.url=jdbc:mysql://localhost:3306/rest

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

logging.level.org.springframework.context.config=DEBUG



```

---

### 2. CatController.java

#### controller\CatController.java

```java

package com.suji.restful.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.suji.restful.entity.Cat;

@RestController
@RequestMapping("/cat")
public class CatController {

	@GetMapping(value = "/str")
	public String getString() { // Content-Type: text/plain;charset=UTF-8
		return "This is String";
	}

	@GetMapping(value = "/getOne")
	public Cat getOne() {
		return new Cat(1, "Kitty", "Black"); // Content-Type: application/json
	}

	@GetMapping(value = "/getArray")
	public List<Cat> getArray() {
		List<Cat> list = Arrays.asList(new Cat(1, "Kitty", "Black"), new Cat(1, "Seth", "Brown"),
				new Cat(1, "Nitro", "White"));

		return list;
	}

	@GetMapping("/get")
	public ResponseEntity<Cat> getSomething() {
		return new ResponseEntity<Cat>(new Cat(1, "Kitty", "Black"), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getMap")
	public ResponseEntity<HashMap> getMap() {
		HashMap<String, String> hash = new HashMap<>();

		hash.put("Black cat1", "Kitty1");
		hash.put("Black cat2", "Kitty2");
		hash.put("Black cat3", "Kitty3");
		hash.put("Black cat4", "Kitty4");

		return new ResponseEntity<HashMap>(hash, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getInt")
	public int getEx() {
		return 10 / 0;
	}
	
	@Autowired
	RestTemplate restTemplate;


	@RequestMapping(value = "/rest")
	public String getProductList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("http://localhost:8080/cat/getArray", HttpMethod.GET, entity, String.class).getBody();
	}

}

```

---

### 3. RestfulDemoApplication.java

#### src\main\java\com\suji\restful\RestfulDemoApplication.java

```java

package com.suji.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulDemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(RestfulDemoApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("Prompt us to add interceptor");

		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}

```

---

### 4. Cat.java

#### src\main\java\com\suji\restful\entity\Cat.java

```java

package com.suji.restful.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String color;
	
	
}

```

---

### 5. ExceptionAdvicer.java

#### src\main\java\com\suji\restful\exception\ExceptionAdvicer.java

```java

package com.suji.restful.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvicer {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap> getError(Exception ex){
		
		HashMap<String,String> map = new HashMap<>();
		map.put("Handler", "Exception");
		map.put("Error ", ex.getMessage());
		map.put("Cause", ex.getCause()+"");
		map.put("CanonicalName", ex.getClass().getCanonicalName());
		
		return new ResponseEntity<HashMap>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<HashMap> getError(ArithmeticException ex){
		
		HashMap<String,String> map = new HashMap<>();
		map.put("Handler", "Arithmetic Exception");
		map.put("Message", ex.getMessage());
		map.put("CanonicalName", ex.getClass().getCanonicalName());
		
		return new ResponseEntity<HashMap>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> getError(Exception e){
//
//		return new ResponseEntity<String>("Error! "+e.getClass().getCanonicalName(),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
}

```

---

### 6. RestfulDemoApplicationTests.java

#### src\test\java\com\suji\restful\RestfulDemoApplicationTests.java

```java

package com.suji.restful;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestfulDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

