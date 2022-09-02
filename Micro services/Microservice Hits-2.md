# Service Registry
```java
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}
}
```
```
spring-cloud-starter-netflix-eureka-server
```
```properties
server.port=8761
spring.application.name=service-registry

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```
---
# Config Server
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
```
```
spring-cloud-config-server
spring-cloud-starter-netflix-eureka-client
```
```properties
server.port=8888
spring.application.name=SPRING-CLOULD-CONFIG-SERVER

spring.cloud.config.server.git.uri=file:///C:/Users/sujith.manchala/Desktop/config-server-repo

spring.cloud.config.server.git.clone-on-start=true
management.endpoint.serviceregistry.enabled=false

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone: http://localhost:8761/eureka/
eureka.instance.hostname=localhost
```
# Limit Service
```application
server.port=8081
spring.application.name=limit-service
spring.profiles.active=dev

# spring.cloud.config.uri= http://localhost:8888
#	A bootstrap file (properties or yaml) is not needed for the Spring Boot Config Data method of import via spring.config.import.

spring.config.import=optional:configserver:http://localhost:8888

# spring.config.import=configserver:http://localhost:8888

#spring.cloud.config.enabled=true
```
```
spring-cloud-starter-netflix-eureka-client
spring-cloud-starter-config
```
```java
@SpringBootApplication
@EnableEurekaClient
public class LimitServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(LimitServerApplication.class, args);
	}
}
```
```properties
limit-server.max : 999
limit-server.min : 111

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone: http://localhost:8761/eureka/
eureka.instance.hostname=localhost
```
---
# Print Service
```properties
server.port=8082
spring.application.name=print-service

spring.config.import=configserver:http://localhost:8888
```
```properties
print-service.msg=This message is from REpo Edit 1

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone: http://localhost:8761/eureka/
eureka.instance.hostname=localhost
```
```
spring-cloud-starter-netflix-eureka-client
spring-cloud-starter-config
```
```java
@SpringBootApplication
@EnableEurekaClient
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
@RestController
@RequestMapping("/print")

public class PrintController {

	@Autowired
	private ConfigMessage config;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/")
	public Map<String, Object> print(){
		var map = new HashMap<String, Object>(); 
		
		LimitDetails details = restTemplate.getForObject("http://LIMIT-SERVICE/limit/", LimitDetails.class);
		map.put("MAX", details.getMax());
		map.put("MIN", details.getMin());
		map.put("Msg", config.getMsg());
		
		return map;
	}
}
```
---
# API Gateway
```
spring-cloud-starter-config
spring-cloud-starter-gateway
spring-cloud-starter-netflix-eureka-client
spring-boot-starter-actuator
spring-cloud-starter-netflix-hystrix
```
```java
@SpringBootApplication
@EnableEurekaClient
public class CouldGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouldGatewayApplication.class, args);
	}
}
```
```yml
server:
  port: 9191

spring:
  application:
    name: api-gateway

  cloud:
    gateway:
      routes:
      
        - id: LIMIT-SERVICE
          uri: lb://LIMIT-SERVICE
          predicates:
          - Path=/limit/**
          
        - id: PRINT-SERVICE
          uri: lb://PRINT-SERVICE
          predicates:
          - Path=/print/**

  config:
    import: configserver:http://localhost:8888
```
```
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone: http://localhost:8761/eureka/
eureka.instance.hostname=localhost
```


