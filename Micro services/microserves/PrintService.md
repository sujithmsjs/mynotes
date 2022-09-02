# PrintService


### File Structure
```pre
+ PrintService\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\micro\printservice
		|---  PrintServiceApplication.java
	+ \src\test\java\com\micro\printservice
		|---  PrintServiceApplicationTests.java
	+ \src\main\java\com\micro\printservice\model
		|---  LimitDetails.java
	+ \src\main\java\com\micro\printservice\controller
		|---  PrintController.java
	+ \src\main\java\com\micro\printservice\config
		|---  ConfigMessage.java
	+ \src\main\java\com\micro\printservice\feign
		|---  LimitFeignClient.java
```
### Index
```pre
1. application.properties
2. model\LimitDetails.java
3. controller\PrintController.java
4. service\PrintServiceApplication.java
5. service\PrintServiceApplicationTests.java
6. src\main\java\com\micro\printservice\config\ConfigMessage.java
7. src\main\java\com\micro\printservice\feign\LimitFeignClient.java

```

---

### 1. application.properties

#### application.properties

```properties


server.port=8082
spring.application.name=print-service

spring.config.import=configserver:http://localhost:8888

# print-service.msg=Hello, This is demo messag.


```

---

### 2. LimitDetails.java

#### model\LimitDetails.java

```java

package com.micro.printservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LimitDetails {

	private int max;
	private int min;
	
}

```

---

### 3. PrintController.java

#### controller\PrintController.java

```java

package com.micro.printservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.micro.printservice.config.ConfigMessage;
import com.micro.printservice.feign.LimitFeignClient;
import com.micro.printservice.model.LimitDetails;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/print")

public class PrintController {

	@Autowired
	private ConfigMessage config;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LimitFeignClient limitFeignClient;

	
	@GetMapping("/feign")
	public Map<String, Object> printFeign() {
		log.info("Inside /feign method.");
		
		var map = new HashMap<String, Object>();
		LimitDetails details = limitFeignClient.getDetails();
		map.put("MAX", details.getMax());
		map.put("MIN", details.getMin());
		map.put("Msg", config.getMsg());

		return map;
	}
	
	@GetMapping("/double")
	public Map<String, Object> printDouble() {
		
		log.info("Inside /feign method.");
		var map = new HashMap<String, Object>();

		log.info("Inside /feign method.");
		LimitDetails details = limitFeignClient.doubleLimits();
		map.put("MAX", details.getMax());
		map.put("MIN", details.getMin());
		map.put("Msg", config.getMsg());

		return map;
	}
	
	@GetMapping("/triple")
	public Map<String, Object> printTriple() {
		var map = new HashMap<String, Object>();

		log.info("Inside /feign method.");
		LimitDetails details = limitFeignClient.tripleLimits();
		map.put("MAX", details.getMax());
		map.put("MIN", details.getMin());
		map.put("Msg", config.getMsg());

		return map;
	}
	
	@GetMapping("/")
	public Map<String, Object> print() {
		var map = new HashMap<String, Object>();

		LimitDetails details = restTemplate.getForObject("http://LIMIT-SERVICE/limits/", LimitDetails.class);
		map.put("MAX", details.getMax());
		map.put("MIN", details.getMin());
		map.put("Msg", config.getMsg());

		return map;
	}
	
	
}

```

---

### 4. PrintServiceApplication.java

#### service\PrintServiceApplication.java

```java

package com.micro.printservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PrintServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

```

---

### 5. PrintServiceApplicationTests.java

#### service\PrintServiceApplicationTests.java

```java

package com.micro.printservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrintServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

### 6. ConfigMessage.java

#### src\main\java\com\micro\printservice\config\ConfigMessage.java

```java

package com.micro.printservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "print-service")
public class ConfigMessage {
	private String msg;
}

```

---

### 7. LimitFeignClient.java

#### src\main\java\com\micro\printservice\feign\LimitFeignClient.java

```java

package com.micro.printservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.micro.printservice.model.LimitDetails;

// @FeignClient(name = "limits" ,url = "http://localhost:8081/")

@FeignClient(name = "LIMIT-SERVICE", path = "/limits")
public interface LimitFeignClient {

	@GetMapping("/")
	public LimitDetails getDetails();

	@GetMapping("/double")
	public LimitDetails doubleLimits();

	@GetMapping("/triple")
	public LimitDetails tripleLimits();
}

```

---

