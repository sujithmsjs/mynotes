# Feign Sample-01

### Print Server
```xml
<artifactId>spring-boot-starter-web</artifactId>
<artifactId>spring-cloud-starter-openfeign</artifactId>
<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
<artifactId>spring-cloud-starter-config</artifactId>
<artifactId>lombok</artifactId>
```
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
```java
package com.micro.printservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.micro.printservice.model.LimitDetails;

// @FeignClient(name = "limits" ,url = "http://localhost:8081/")
/*
Name implies Loadbalancer name, it can be EUREKA-SERVER's service name.
Path implies as the base url of all the urls.
*/
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
```java
@RestController
@RequestMapping("/print")
public class PrintController {

	@Autowired
	private ConfigMessage config;
	
	@Autowired
	private LimitFeignClient limitFeignClient;
	
	@GetMapping("/feign")
	public Map<String, Object> printFeign() {
	
		var map = new HashMap<String, Object>();
		LimitDetails details = limitFeignClient.getDetails();
		map.put("MAX", details.getMax());
		map.put("MIN", details.getMin());
		map.put("Msg", config.getMsg());

		return map;
	}
```
### Limit Server
```java
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
