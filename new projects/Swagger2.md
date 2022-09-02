# Swagger2


### File Structure
```pre
+ Swagger2\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\swagger
		|---  Swagger2Application.java
	+ \src\main\java\com\suji\swagger\controller
		|---  HomeController.java
```
### Index
```pre
1. resources\application.properties
2. \src\main\java\com\suji\swagger\controller\HomeController.java
3. \src\main\java\com\suji\swagger\Swagger2Application.java

```

---

### 1. application.properties

#### resources\application.properties

```properties



```

---

### 2. HomeController.java

#### \src\main\java\com\suji\swagger\controller\HomeController.java

```java

package com.suji.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

	
	@GetMapping("/test")
	public String test() {
		return "Hellow world";
	}
	
}

```

---

### 3. Swagger2Application.java

#### \src\main\java\com\suji\swagger\Swagger2Application.java

```java

package com.suji.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Swagger2Application {

	public static void main(String[] args) {
		SpringApplication.run(Swagger2Application.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.suji.swagger")).build();
	}

}

```

---

