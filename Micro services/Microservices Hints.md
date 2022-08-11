# All file contains
```xml
<properties>
	<java.version>11</java.version>
	<spring-cloud.version>Hoxton.SR8</spring-cloud.version>
</properties>
```
# Eureka clients
```yml
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    hostname: localhost
```
# cloud-config-server
```xml
<artifactId>spring-cloud-config-server</artifactId>
<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
```
```yml
server:
  port: 9296

spring:
  application:
    name: CONFIG-SERVER
  cloud:
    config:
      server:
        git:
          uri: https://github.com/shabbirdwd53/config-server
          clone-on-start: true
```
```java
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class CloudConfigServerApplication {
    // ...
}
```
# cloud-gateway
```xml
<artifactId>spring-boot-starter-actuator</artifactId>
<artifactId>spring-cloud-starter-gateway</artifactId>
<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
<artifactId>spring-cloud-starter-config</artifactId>
<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
```
```java
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class CloudGatewayApplication {
    // ...
}
```
```yml
spring:
  cloud:
    config:
      enabled: true
      uri: http://localhost:9296
```
```yml
server:
  port: 9191

spring:
  application:
    name: API-GATEWAY
  cloud:
    gateway:
      routes:
        - id: USER-SERVICE
          uri: lb://USER-SERVICE
          predicates:
            - Path=/users/**
          filters:
            - name: CircuitBreaker
              args:
                name: USER-SERVICE
                fallbackuri: forward:/userServiceFallBack
        - id: DEPARTMENT-SERVICE
          uri: lb://DEPARTMENT-SERVICE
          predicates:
            - Path=/departments/**
          filters:
            - name: CircuitBreaker
              args:
                name: DEPARTMENT-SERVICE
                fallbackuri: forward:/departmentServiceFallBack


hystrix:
  command:
    fallbackcmd:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 4000


management:
  endpoints:
    web:
      exposure:
        include: hystrix.stream
```


```java

package com.dailycodebuffer.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "User Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod() {
        return "Department Service is taking longer than Expected." +
                " Please try again later";
    }
}
```
# department-service
```xml
<properties>
    <spring-cloud.version>Hoxton.SR8</spring-cloud.version>
</properties>

<artifactId>spring-boot-starter-web</artifactId>
<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
<artifactId>spring-cloud-starter-config</artifactId>
<artifactId>spring-cloud-starter-sleuth</artifactId>
<artifactId>spring-cloud-starter-zipkin</artifactId>
```
```yml
server:
  port: 9001

spring:
  application:
    name: DEPARTMENT-SERVICE
  zipkin:
    base-url: http://127.0.0.1:9411/
```
```yml
spring:
  cloud:
    config:
      enabled: true
      uri: http://localhost:9296
```
```java
@SpringBootApplication
@EnableEurekaClient
public class DepartmentServiceApplication {
    // Code
}
```
# user-service
```
server:
  port: 9002

spring:
  application:
    name: USER-SERVICE
  zipkin:
    base-url: http://127.0.0.1:9411/
```
```yml
spring:
  cloud:
    config:
      enabled: true
      uri: http://localhost:9296
```
```java
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
```
```java
@Autowired
private RestTemplate restTemplate;

Department department = restTemplate.getForObject(
    "http://DEPARTMENT-SERVICE/depts/"+ user.getDeptId(), Department.class);
```
# service-registry
```xml
<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
```
```java
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {
    // ...
}
```
```yml
server:
  port: 8761

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```
# hystrix-dashboard
```xml
<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
<artifactId>spring-cloud-starter-config</artifactId>
```
```java
@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
public class HystrixDashboardApplication {
    // ...
}
```
```yml
server:
  port: 9295

spring:
  application:
    name: HYSTRIX-DASHBOARD

hystrix:
  dashboard:
    proxy-stream-allow-list: "*"
```
