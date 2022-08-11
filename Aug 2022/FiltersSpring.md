# FiltersSpring


### File Structure
```pre
+ FiltersSpring\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  index.html
	+ \src\main\java\com\suji\filter
		|---  FiltersSpringApplication.java
	+ \src\test\java\com\suji\filter
		|---  FiltersSpringApplicationTests.java
	+ \src\main\java\com\suji\filter\controller
		|---  DemoController.java
	+ \src\main\java\com\suji\filter\filters
		|---  DemoFilter.java
		|---  DemoFilter2.java
		|---  DemoFilter3.java
		|---  DemoFilter4.java
		|---  RequestResponseLoggingFilter.java
	+ \src\main\java\com\suji\filter\interceptor
		|---  DemoInterceptor.java
```
### Index
```pre
1. application.properties
2. controller\DemoController.java
3. src\main\java\com\suji\filter\FiltersSpringApplication.java
4. src\main\java\com\suji\filter\filters\DemoFilter.java
5. src\main\java\com\suji\filter\filters\DemoFilter2.java
6. src\main\java\com\suji\filter\filters\DemoFilter3.java
7. src\main\java\com\suji\filter\filters\DemoFilter4.java
8. src\main\java\com\suji\filter\filters\RequestResponseLoggingFilter.java
9. src\main\java\com\suji\filter\interceptor\DemoInterceptor.java
10. resources\templates\index.html
11. src\test\java\com\suji\filter\FiltersSpringApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

server.port=9090

logging.level.com.suji.filter=TRACE
```

---

### 2. DemoController.java

#### controller\DemoController.java

```java

package com.suji.filter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
	
	@RequestMapping("/home")
	public String showHome() {
		System.out.println("/home requested.");
		return "index";
		
	}
	
}

```

---

### 3. FiltersSpringApplication.java

#### src\main\java\com\suji\filter\FiltersSpringApplication.java

```java

package com.suji.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.suji.filter.filters.RequestResponseLoggingFilter;
import com.suji.filter.interceptor.DemoInterceptor;

@SpringBootApplication
public class FiltersSpringApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(FiltersSpringApplication.class, args);
		System.out.println("FiltersSpringApplication started.");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DemoInterceptor());
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Bean
	public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
		FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new RequestResponseLoggingFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(2);

		return registrationBean;
	}

}

```

---

### 4. DemoFilter.java

#### src\main\java\com\suji\filter\filters\DemoFilter.java

```java

package com.suji.filter.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DemoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Before DoFilter... @Order(1)");

		chain.doFilter(request, response);

		System.out.println("After DoFilter... @Order(1)");
	}
}

```

---

### 5. DemoFilter2.java

#### src\main\java\com\suji\filter\filters\DemoFilter2.java

```java

package com.suji.filter.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class DemoFilter2 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Before DoFilter... @Order(2)");

		chain.doFilter(request, response);

		System.out.println("After DoFilter... @Order(2)");
	}
}

```

---

### 6. DemoFilter3.java

#### src\main\java\com\suji\filter\filters\DemoFilter3.java

```java

package com.suji.filter.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class DemoFilter3 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Before DoFilter... @Order(3)");

		chain.doFilter(request, response);

		System.out.println("After DoFilter... @Order(3)");

	}
}

```

---

### 7. DemoFilter4.java

#### src\main\java\com\suji\filter\filters\DemoFilter4.java

```java

package com.suji.filter.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class DemoFilter4 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Before DoFilter... @Order(4)");

		chain.doFilter(request, response);

		System.out.println("After DoFilter... @Order(4)");

	}
}

```

---

### 8. RequestResponseLoggingFilter.java

#### src\main\java\com\suji\filter\filters\RequestResponseLoggingFilter.java

```java

package com.suji.filter.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

	private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
		chain.doFilter(request, response);
		LOG.info("Logging Response :{}", res.getContentType());
	}

	// other methods
}

```

---

### 9. DemoInterceptor.java

#### src\main\java\com\suji\filter\interceptor\DemoInterceptor.java

```java

package com.suji.filter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DemoInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle "+request);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle "+modelAndView);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}

```

---

### 10. index.html

#### resources\templates\index.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Welcome</h1>


</body>
</html>
```

---

### 11. FiltersSpringApplicationTests.java

#### src\test\java\com\suji\filter\FiltersSpringApplicationTests.java

```java

package com.suji.filter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FiltersSpringApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

