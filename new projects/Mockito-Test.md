# Mockito-Test


### File Structure
```pre
+ Mockito-Test\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\mockito
		|---  MockitoTestApplication.java
	+ \src\main\java\com\suji\mockito\model
		|---  User.java
	+ \src\main\java\com\suji\mockito\controller
		|---  UserController.java
	+ \src\main\java\com\suji\mockito\service
		|---  UserService.java
```
### Index
```pre
1. resources\application.properties
2. \model\User.java
3. \src\main\java\com\suji\mockito\controller\UserController.java
4. \src\main\java\com\suji\mockito\MockitoTestApplication.java
5. \src\main\java\com\suji\mockito\service\UserService.java

```

---

### 1. application.properties

#### resources\application.properties

```properties



```

---

### 2. User.java

#### \model\User.java

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

#### \src\main\java\com\suji\mockito\controller\UserController.java

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

### 4. MockitoTestApplication.java

#### \src\main\java\com\suji\mockito\MockitoTestApplication.java

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

### 5. UserService.java

#### \src\main\java\com\suji\mockito\service\UserService.java

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

