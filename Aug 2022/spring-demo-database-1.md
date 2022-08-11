# spring-demo-database-1


### File Structure
```pre
+ spring-demo-database-1\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\firstapp
		|---  SpringDemoDatabase1Application.java
	+ \src\test\java\com\suji\firstapp
		|---  SpringDemoDatabase1ApplicationTests.java
	+ \src\main\java\com\suji\firstapp\controller
		|---  DemoController.java
```
### Index
```pre
1. application.properties
2. controller\DemoController.java
3. src\main\java\com\suji\firstapp\SpringDemoDatabase1Application.java
4. src\test\java\com\suji\firstapp\SpringDemoDatabase1ApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. DemoController.java

#### controller\DemoController.java

```java

package com.suji.firstapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

	@RequestMapping("/login")
	@ResponseBody
	public String loginController() {
		return "Hello World";
	}
}

```

---

### 3. SpringDemoDatabase1Application.java

#### src\main\java\com\suji\firstapp\SpringDemoDatabase1Application.java

```java

package com.suji.firstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoDatabase1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoDatabase1Application.class, args);
	}

}

```

---

### 4. SpringDemoDatabase1ApplicationTests.java

#### src\test\java\com\suji\firstapp\SpringDemoDatabase1ApplicationTests.java

```java

package com.suji.firstapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDemoDatabase1ApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

