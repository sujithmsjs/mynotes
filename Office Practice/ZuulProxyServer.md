# ZuulProxyServer


### File Structure
```pre
+ ZuulProxyServer\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\zuul
		|---  ZuulProxyServerApplication.java
	+ \src\test\java\com\suji\zuul
		|---  ZuulProxyServerApplicationTests.java
	+ \src\main\java\com\suji\zuul\filters
		|---  ErrorFilter.java
		|---  PostFilter.java
		|---  PreFilter.java
		|---  RouteFilter.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\zuul\ZuulProxyServerApplication.java
3. src\main\java\com\suji\zuul\filters\ErrorFilter.java
4. src\main\java\com\suji\zuul\filters\PostFilter.java
5. src\main\java\com\suji\zuul\filters\PreFilter.java
6. src\main\java\com\suji\zuul\filters\RouteFilter.java
7. src\test\java\com\suji\zuul\ZuulProxyServerApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



#Zuul routes. Here for /student path, we are routing to localhost:8090 with extra path after that.
zuul.routes.employees.url=http://localhost:8090
 
#Ribbon is auto integrated with Zuul and for this exercise we are not using that.
ribbon.eureka.enabled=false
 
#Will start the gateway server @8080
server.port=8765
```

---

### 2. ZuulProxyServerApplication.java

#### src\main\java\com\suji\zuul\ZuulProxyServerApplication.java

```java

package com.suji.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.suji.zuul.filters.ErrorFilter;
import com.suji.zuul.filters.PostFilter;
import com.suji.zuul.filters.PreFilter;
import com.suji.zuul.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulProxyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyServerApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}

```

---

### 3. ErrorFilter.java

#### src\main\java\com\suji\zuul\filters\ErrorFilter.java

```java

package com.suji.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class ErrorFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		return null;
	}

	@Override
	public String filterType() {
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}

```

---

### 4. PostFilter.java

#### src\main\java\com\suji\zuul\filters\PostFilter.java

```java

package com.suji.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		return null;
	}

	@Override
	public String filterType() {
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}

```

---

### 5. PreFilter.java

#### src\main\java\com\suji\zuul\filters\PreFilter.java

```java

package com.suji.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		return null;
	}

	@Override
	public String filterType() {
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}

```

---

### 6. RouteFilter.java

#### src\main\java\com\suji\zuul\filters\RouteFilter.java

```java

package com.suji.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class RouteFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		return null;
	}

	@Override
	public String filterType() {
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}

```

---

### 7. ZuulProxyServerApplicationTests.java

#### src\test\java\com\suji\zuul\ZuulProxyServerApplicationTests.java

```java

package com.suji.zuul;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZuulProxyServerApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

