# spring-microservies-1


### File Structure
```pre
+ spring-microservies-1\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\microservies
		|---  SpringMicroservies1Application.java
	+ \src\main\java\com\suji\microservies\limitservice\model
		|---  LimitCongif.java
	+ \src\main\java\com\suji\microservies\limitservice\controller
		|---  Configuration.java
		|---  LimitController.java
```
### Index
```pre
1. resources\application.properties
2. \model\LimitCongif.java
3. \src\main\java\com\suji\microservies\limitservice\controller\Configuration.java
4. \src\main\java\com\suji\microservies\limitservice\controller\LimitController.java
5. \src\main\java\com\suji\microservies\SpringMicroservies1Application.java

```

---

### 1. application.properties

#### resources\application.properties

```properties




limits-service.minimum=99
limits-service.maximum=9999

```

---

### 2. LimitCongif.java

#### \model\LimitCongif.java

```java

package com.suji.microservies.limitservice.model;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class LimitCongif {

	private int maximum;  
	private int minimum;  
	
	
	
	
}

```

---

### 3. Configuration.java

#### \src\main\java\com\suji\microservies\limitservice\controller\Configuration.java

```java

package com.suji.microservies.limitservice.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("limits-service")
public class Configuration {

	private int maximum;
	private int minimum;

}
```

---

### 4. LimitController.java

#### \src\main\java\com\suji\microservies\limitservice\controller\LimitController.java

```java

package com.suji.microservies.limitservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.microservies.limitservice.model.LimitCongif;

@RestController
public class LimitController {

	@GetMapping("/limits")
	public LimitCongif getLimitCongif() {
		return new LimitCongif(100, 1);
	}
	
}

```

---

### 5. SpringMicroservies1Application.java

#### \src\main\java\com\suji\microservies\SpringMicroservies1Application.java

```java

package com.suji.microservies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMicroservies1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservies1Application.class, args);
	}

}

```

---

