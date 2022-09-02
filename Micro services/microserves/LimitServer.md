# LimitServer


### File Structure
```pre
+ LimitServer\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\micro\limitserver
		|---  LimitServerApplication.java
	+ \src\test\java\com\micro\limitserver
		|---  LimitServerApplicationTests.java
	+ \src\main\java\com\micro\limitserver\model
		|---  LimitDetails.java
	+ \src\main\java\com\micro\limitserver\controller
		|---  LimitController.java
	+ \src\main\java\com\micro\limitserver\config
		|---  LimitConfig.java
```
### Index
```pre
1. application.properties
2. model\LimitDetails.java
3. controller\LimitController.java
4. src\main\java\com\micro\limitserver\LimitServerApplication.java
5. src\main\java\com\micro\limitserver\config\LimitConfig.java
6. src\test\java\com\micro\limitserver\LimitServerApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


spring.application.name=limit-service
server.port=8081

# spring.profiles.active=dev

# spring.cloud.config.uri= http://localhost:8888


#	A bootstrap file (properties or yaml) is not needed for the Spring Boot Config Data method of import via spring.config.import.
spring.config.import=optional:configserver:http://localhost:8888
# spring.config.import=configserver:http://localhost:8888

limit-server.max : 100
limit-server.min : 1



#spring.cloud.config.enabled=true

#eureka.client.enabled=false
# eureka.client.eureka-connection-idle-timeout-seconds=30
```

---

### 2. LimitDetails.java

#### model\LimitDetails.java

```java

package com.micro.limitserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LimitDetails {

	private int max;
	private int min;
	
}

```

---

### 3. LimitController.java

#### controller\LimitController.java

```java

package com.micro.limitserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.limitserver.config.LimitConfig;
import com.micro.limitserver.model.LimitDetails;

@RefreshScope
@RestController
@RequestMapping("/limits")
public class LimitController {

	@Autowired
	private LimitConfig config;
	
	@GetMapping("/")
	public LimitDetails getDetails() {
		return new LimitDetails(config.getMax(), config.getMin());
	}
	
	@GetMapping("/double")
	public LimitDetails doubleLimits() {
		return new LimitDetails(config.getMax()*2, config.getMin()*2);
	}
	
	@GetMapping("/triple")
	public LimitDetails getSave() {
		return new LimitDetails(config.getMax()*3, config.getMin()*3);
	}
}

```

---

### 4. LimitServerApplication.java

#### src\main\java\com\micro\limitserver\LimitServerApplication.java

```java

package com.micro.limitserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LimitServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitServerApplication.class, args);
	}

}

```

---

### 5. LimitConfig.java

#### src\main\java\com\micro\limitserver\config\LimitConfig.java

```java

package com.micro.limitserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
@RefreshScope
@ConfigurationProperties(prefix =  "limit-server")
public class LimitConfig {
	
	private int max;
	private int min;
	
}

```

---

### 6. LimitServerApplicationTests.java

#### src\test\java\com\micro\limitserver\LimitServerApplicationTests.java

```java

package com.micro.limitserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LimitServerApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

