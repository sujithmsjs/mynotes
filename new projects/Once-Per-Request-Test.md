# Once-Per-Request-Test


### File Structure
```pre
+ Once-Per-Request-Test\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\filtertest
		|---  OncePerRequestTestApplication.java
	+ \src\main\java\com\suji\filtertest\model
		|---  CustomExceptionDetails.java
	+ \src\main\java\com\suji\filtertest\advice
		|---  GlobalExceptionHandler.java
	+ \src\main\java\com\suji\filtertest\controller
		|---  DemoController.java
	+ \src\main\java\com\suji\filtertest\filter
		|---  DemoFilter.java
```
### Index
```pre
1. resources\application.properties
2. \model\CustomExceptionDetails.java
3. \src\main\java\com\suji\filtertest\advice\GlobalExceptionHandler.java
4. \src\main\java\com\suji\filtertest\controller\DemoController.java
5. \src\main\java\com\suji\filtertest\filter\DemoFilter.java
6. \src\main\java\com\suji\filtertest\OncePerRequestTestApplication.java

```

---

### 1. application.properties

#### resources\application.properties

```properties



```

---

### 2. CustomExceptionDetails.java

#### \model\CustomExceptionDetails.java

```java

package com.suji.filtertest.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;



public class CustomExceptionDetails {

	private int status;
	private String customMessage;
	private String message;
	private Class exceptionClass;
	private LocalDateTime timestamp;

	public CustomExceptionDetails(Exception ex, HttpStatus status, String customMessage) {
		this.status = status.value();
		this.message = ex.getMessage();
		this.customMessage = customMessage;
		this.exceptionClass = ex.getClass();
		this.timestamp = LocalDateTime.now();
		
		
	}

	public CustomExceptionDetails() {
		super();
	}

	public CustomExceptionDetails(int status, String customMessage, String message, Class exceptionClass,
			LocalDateTime timestamp) {
		super();
		this.status = status;
		this.customMessage = customMessage;
		this.message = message;
		this.exceptionClass = exceptionClass;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Class getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(Class exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	

}

```

---

### 3. GlobalExceptionHandler.java

#### \src\main\java\com\suji\filtertest\advice\GlobalExceptionHandler.java

```java

package com.suji.filtertest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex){
		return new ResponseEntity<String>("Hey check you entered bad request: "+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}

```

---

### 4. DemoController.java

#### \src\main\java\com\suji\filtertest\controller\DemoController.java

```java

package com.suji.filtertest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/divide/{divident}/{divisor}")
	public String getValue(@PathVariable int divident,@PathVariable int divisor) {
		return "Value is "+(divident/divisor);
	}
	
	//@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex){
		return new ResponseEntity<String>("DemoController: "+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}

```

---

### 5. DemoFilter.java

#### \src\main\java\com\suji\filtertest\filter\DemoFilter.java

```java

package com.suji.filtertest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suji.filtertest.model.CustomExceptionDetails;

@Component
@RestController
public class DemoFilter extends OncePerRequestFilter {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("Before exception...");

		try {
			System.out.println(1 / 0);
			
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			handleInvalidCorrelationId(response, e);
			System.out.println("Exception caught: " + e.getMessage());

		//	response.getWriter().write(e.getMessage());
			// response.sendError(HttpStatus.ACCEPTED.value());
		}

		System.out.println("After exception...");

		System.out.println("Before filter chain...");

		System.out.println("After filter chain...");
	}
	
	/*
	 * Note:
	 * the Servlet Filter executes before Spring's own DispatchServlet
	 * 
	 */

	private void handleInvalidCorrelationId(HttpServletResponse response, Exception ex) throws IOException {
		CustomExceptionDetails ced = new CustomExceptionDetails(ex, HttpStatus.BAD_REQUEST, "Check /Zero exception.");
	//	response.sendError(HttpStatus.BAD_REQUEST.value());
		response.setContentType("application/json");
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.getWriter().write(objectMapper.writeValueAsString(ced));
		
		
	}

}

```

---

### 6. OncePerRequestTestApplication.java

#### \src\main\java\com\suji\filtertest\OncePerRequestTestApplication.java

```java

package com.suji.filtertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OncePerRequestTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OncePerRequestTestApplication.class, args);
	}

}

```

---

