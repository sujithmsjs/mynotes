### Classes Required
```
Project
  |
  |-- controller
  |     |--- EmployeeController.java
  |-- exception
  |     |--- EmployeeNotFoundException.java
  |-- dto
  |     |-- ExceptionResponse.java
```
### ExceptionResponse.java
```java
package com.example.demo.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	
	private HttpStatus status;
	private String message;
	
}
```
### ExceptionResponse.java
```java
package com.example.demo.excep;

public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException(long id) {
		super("Employee Id: "+id+" not found");
	}
}
```
### ExceptionResponse.java
```java
package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.excep.EmployeeNotFoundException;
import com.example.demo.excep.ExceptionResponse;

@RestController
@RequestMapping("/demo")

public class EmployeeController {

	@ResponseStatus(HttpStatus.FOUND)
	@GetMapping("/get/{id}")
	public String getEmployee(@PathVariable int id) {
		if (id > 10) {
			throw new EmployeeNotFoundException(id);
		}
		return "id:" + id + " is Found";
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ExceptionResponse handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		log.info("Insdie handleEmployeeNotFoundException of DemoController " + ex.getMessage());

		ExceptionResponse exRes = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());

		log.info("Insdie handleEmployeeNotFoundException: Returned " + exRes);
		return exRes;
	}
}

```
