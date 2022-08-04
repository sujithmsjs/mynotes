# Sample REST Demo application
## Step-04

### Main Method
```java
package com.suji.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleDemoFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleDemoFirstApplication.class, args);
	}
}
```
---
### Simple Interest Controller
```java
package com.suji.sample.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.sample.model.SimpleInterestRequest;
import com.suji.sample.model.SimpleInterestResponse;

@RestController
@RequestMapping("/simple")
public class SimpleInterestController {

	@PostMapping("/interest")
	public SimpleInterestResponse getInterest(@RequestBody SimpleInterestRequest sir) {
	
		int interest = sir.getPrincipal() * sir.getTime() * sir.getRate() /100;
		int total = interest + sir.getPrincipal();
		
		return new SimpleInterestResponse(interest, total);
	}
}
```
---
### SimpleInterestRequest
```java
public class SimpleInterestRequest {
	
	private int principal;
	private int time;
	private int rate;
	
	public SimpleInterestRequest() {
	}

	public SimpleInterestRequest(int principal, int time, int rate) {
		this.principal = principal;
		this.time = time;
		this.rate = rate;
	}
	// Getters and Setter 
}
```
---
### Simple Interest Response
```java
public class SimpleInterestResponse {
	
	private int interest;
	private int total;
	
	public SimpleInterestResponse() {
		super();
	}

	public SimpleInterestResponse(int interest, int total) {
		this.interest = interest;
		this.total = total;
	}
    // Getters and Setter
}
```
---
### application.properties
```properties
# Server Port
server.port=8080

# Open API Available at http://localhost:8080/api-docs/
springdoc.api-docs.path=/api-docs

# Swagger UI Available at http://localhost:8080/swagger.html
springdoc.swagger-ui.path=/swagger.html
```
---
### pom.xml
```xml
<dependencies>

		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.4</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

</dependencies>
```
---
### Input
```json
{
  "principal": 600000,
  "time": 12,
  "rate": 4
}
```
### Output
```json
{
  "interest": 288000,
  "total": 888000
}
```


