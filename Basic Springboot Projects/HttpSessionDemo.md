# HttpSessionDemo


### File Structure
```pre
+ HttpSessionDemo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  home.html
		|---  profile.html
	+ \src\main\java\com\suji\session
		|---  HttpSessionDemoApplication.java
	+ \src\test\java\com\suji\session
		|---  HttpSessionDemoApplicationTests.java
	+ \src\main\java\com\suji\session\controller
		|---  DemoController.java
	+ \src\main\java\com\suji\session\filter
		|---  DemoFilter.java
```
### Index
```pre
1. application.properties
2. controller\DemoController.java
3. src\main\java\com\suji\session\HttpSessionDemoApplication.java
4. src\main\java\com\suji\session\filter\DemoFilter.java
5. resources\templates\home.html
6. resources\templates\profile.html
7. src\test\java\com\suji\session\HttpSessionDemoApplicationTests.java

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

package com.suji.session.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/login")
	public String login(HttpSession session,String name, Model model) {
		
		if(session.getAttribute("name") != null) {
			model.addAttribute("sessionid",session.getId());
			model.addAttribute("message","You are already logged in!");
		}else if(name != null && !name.isBlank()){
			model.addAttribute("sessionid",session.getId());
			model.addAttribute("message","You logged in successfully!!");
			session.setAttribute("name", name);
		}else {
			model.addAttribute("message","Please provide username!");
		}
		
		return "home";
	}
	
	@RequestMapping("/name")
	public String getName(HttpSession session,Model model) {
		
		System.out.println("Session name: "+session.getAttribute("name"));
		
		if(session.getAttribute("name") != null) {
			model.addAttribute("sessionid",session.getId());
		}else {
			model.addAttribute("sessionid","You must login first");
		}
		
	
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session,Model model) {
		String attribute = (String) session.getAttribute("name");
		
		if(attribute != null) {
			session.invalidate();
			model.addAttribute("sessionid","Session Invalidated.");
		}else {
			model.addAttribute("sessionid","Session Attribute null");
		}
		
	
		return "home";
	}
	
	
}

```

---

### 3. HttpSessionDemoApplication.java

#### src\main\java\com\suji\session\HttpSessionDemoApplication.java

```java

package com.suji.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpSessionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpSessionDemoApplication.class, args);
	}

}

```

---

### 4. DemoFilter.java

#### src\main\java\com\suji\session\filter\DemoFilter.java

```java

package com.suji.session.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class DemoFilter implements Filter {

	private static int reqCount = 0;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		reqCount++;
		System.out.println("Request count: "+reqCount);
		chain.doFilter(request, response);
		
		
	}

}

```

---

### 5. home.html

#### resources\templates\home.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>

<h1 th:text="${sessionid}"></h1>
<h1 th:text="${session.name}"></h1>
<h1 th:text="${message}"></h1>

</body>

</html>
```

---

### 6. profile.html

#### resources\templates\profile.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Profile File</title>
</head>
<body>
	<h1>Profile</h1>
	<h1 th:text="${sessionid}"></h1>
	<h1 th:text="${session.name}"></h1>
	<h1 th:text="${message}"></h1>
</body>
</html>
```

---

### 7. HttpSessionDemoApplicationTests.java

#### src\test\java\com\suji\session\HttpSessionDemoApplicationTests.java

```java

package com.suji.session;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HttpSessionDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

