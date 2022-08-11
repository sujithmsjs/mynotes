# Mockito-Test


### File Structure
```pre
+ Mockito-Test\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\mockito
		|---  MockitoTestApplication.java
	+ \src\test\java\com\suji\mockito
		|---  MockitoTestApplicationTests.java
	+ \src\main\java\com\suji\mockito\model
		|---  User.java
	+ \src\main\java\com\suji\mockito\controller
		|---  UserController.java
	+ \src\test\java\com\suji\mockito\controller
		|---  UserControllerTest.java
	+ \src\main\java\com\suji\mockito\service
		|---  UserService.java
```
### Index
```pre
1. application.properties
2. model\User.java
3. controller\UserController.java
4. controller\UserControllerTest.java
5. service\UserService.java
6. src\main\java\com\suji\mockito\MockitoTestApplication.java
7. src\test\java\com\suji\mockito\MockitoTestApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. User.java

#### model\User.java

```java

package com.suji.mockito.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int number;
	private String name;
	
}

```

---

### 3. UserController.java

#### controller\UserController.java

```java

package com.suji.mockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suji.mockito.model.User;
import com.suji.mockito.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/users/exists")
	public boolean isExist(@RequestBody User user) {
		return service.isExists(user);
	}
	
	@PostMapping("/users")
	public User getUser(@RequestParam int userId) {
		User u = new User(userId, "Demo name");
		return u;
	}
	
	
	
}

```

---

### 4. UserControllerTest.java

#### controller\UserControllerTest.java

```java

package com.suji.mockito.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suji.mockito.model.User;
import com.suji.mockito.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { UserController.class, RestTemplateAutoConfiguration.class })
@AutoConfigureMockMvc
@ContextConfiguration(classes = UserController.class)
public class UserControllerTest {

	@MockBean
	private UserService service;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonMapper;
	
	@Test
	public void getUsers() throws Exception {
		var userList = new ArrayList<User>();
		User u = new User(1, "Sujith");
		userList.add(u);
		when(service.getAllUsers()).thenReturn(null);
		
		var mockRequest = MockMvcRequestBuilders.get("/api/users");
		var response = mockMvc.perform(mockRequest).andReturn().getResponse();
		int status = response.getStatus();
		
		String content = response.getContentAsString();
		String contentType = response.getContentType();
		System.out.println("Content: "+content);
		System.out.println("contentType: "+contentType);
		System.out.println("JsonMapper: "+jsonMapper.writeValueAsString(userList));
		
		//assertEquals(jsonMapper.writeValueAsString(userList), userList);
		assertEquals(200, status);
	}
	
	@Test
	public void isExistTest() throws Exception {
		
		User u = new User(1, "Sujith");
		String jsonBody = jsonMapper.writeValueAsString(u);

		System.out.println("jsonBody : "+jsonBody);
		
		when(service.isExists(u)).thenReturn(true);
		
		var mockRequest = MockMvcRequestBuilders
				.get("/api/users/exists")
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON);
		
		var response = mockMvc.perform(mockRequest).andReturn().getResponse();
		
		assertEquals(200, response.getStatus());
		assertEquals("true", response.getContentAsString());
	}
	
	
	

}

```

---

### 5. UserService.java

#### service\UserService.java

```java

package com.suji.mockito.service;

import java.util.List;

import com.suji.mockito.model.User;

public interface UserService {

	List<User> getAllUsers();
	boolean isExists(User user);
	User save(User user);
	
}

```

---

### 6. MockitoTestApplication.java

#### src\main\java\com\suji\mockito\MockitoTestApplication.java

```java

package com.suji.mockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockitoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockitoTestApplication.class, args);
	}

}

```

---

### 7. MockitoTestApplicationTests.java

#### src\test\java\com\suji\mockito\MockitoTestApplicationTests.java

```java

package com.suji.mockito;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockitoTestApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

