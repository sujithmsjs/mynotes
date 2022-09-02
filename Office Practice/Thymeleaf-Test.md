# Thymeleaf-Test


### File Structure
```pre
+ Thymeleaf-Test\ 
	+ \src\main\resources\templates
		|---  test.html
	+ \src\main\java\com\suji\thyme
		|---  ThymeleafTestApplication.java
	+ \src\test\java\com\suji\thyme
		|---  ThymeleafTestApplicationTests.java
	+ \src\main\java\com\suji\thyme\controller
		|---  DemoController.java
```
### Index
```pre
1. controller\DemoController.java
2. src\main\java\com\suji\thyme\ThymeleafTestApplication.java
3. resources\templates\test.html
4. src\test\java\com\suji\thyme\ThymeleafTestApplicationTests.java

```

---

### 1. DemoController.java

#### controller\DemoController.java

```java

package com.suji.thyme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {

	@GetMapping("/v2/test")
	@ResponseBody
	public String demoTestV2() {
		
		return "Hello...";
	}
	
	@GetMapping("/test")
	public ModelAndView demoTest() {
		ModelAndView mnv = new ModelAndView("test");
		mnv.addObject("name", "Sujith");
		System.out.println("In controller...");
		return mnv;
	}
	
}

```

---

### 2. ThymeleafTestApplication.java

#### src\main\java\com\suji\thyme\ThymeleafTestApplication.java

```java

package com.suji.thyme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.context.WebContext;

@SpringBootApplication
public class ThymeleafTestApplication {
	
	@Autowired
	private WebContext webContext;

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafTestApplication.class, args);
	}

}

```

---

### 3. test.html

#### resources\templates\test.html

```html

<html>
	<body>
		<p th:text=" 'Hellow ' + ${name}">This is Paragraph</p>
		<p th:text="#{msgs.headers.mytext}" ></p>
	</body>
</html>
```

---

### 4. ThymeleafTestApplicationTests.java

#### src\test\java\com\suji\thyme\ThymeleafTestApplicationTests.java

```java

package com.suji.thyme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThymeleafTestApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

