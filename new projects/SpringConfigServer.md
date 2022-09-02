# SpringConfigServer


### File Structure
```pre
+ SpringConfigServer\ 
	+ \src\main\resources
		|---  application.properties
		|---  limit-server.properties
	+ \src\main\java\com\suji\configdemo
		|---  SpringConfigServerApplication.java
```
### Index
```pre
1. resources\application.properties
2. resources\limit-server.properties
3. \src\main\java\com\suji\configdemo\SpringConfigServerApplication.java

```

---

### 1. application.properties

#### resources\application.properties

```properties


spring.application.name=spring-could-config-server
server.port=8888

#spring.cloud.config.server.git.uri=ssh://localhost/config-repo
#spring.cloud.config.server.git.clone-on-start=true
#spring.security.user.name=root
#spring.security.user.password=password
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=/SpringConfigServer/src/main/resources/limit-server.properties
```

---

### 2. limit-server.properties

#### resources\limit-server.properties

```properties


limit-server.minimum=223
limit-server.maximum=343434
```

---

### 3. SpringConfigServerApplication.java

#### \src\main\java\com\suji\configdemo\SpringConfigServerApplication.java

```java

package com.suji.configdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigServer
@RestController
public class SpringConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerApplication.class, args);
	}
	
	@GetMapping(path = {"/",""})
	public String check() {
		return "Server is running...";
	}

}

```

---

