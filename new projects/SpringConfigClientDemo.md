# SpringConfigClientDemo


### File Structure
```pre
+ SpringConfigClientDemo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\configdemo
		|---  SpringConfigServerDemoApplication.java
	+ \src\main\java\com\suji\configdemo\model
		|---  Limit.java
	+ \src\main\java\com\suji\configdemo\config
		|---  LimitConfig.java
	+ \src\main\java\com\suji\configdemo\controller
		|---  LimitRestController.java
```
### Index
```pre
1. resources\application.properties
2. \model\Limit.java
3. \src\main\java\com\suji\configdemo\config\LimitConfig.java
4. \src\main\java\com\suji\configdemo\controller\LimitRestController.java
5. \src\main\java\com\suji\configdemo\SpringConfigServerDemoApplication.java

```

---

### 1. application.properties

#### resources\application.properties

```properties


#spring.config.import=http://localhost:8888
#spring.config.import=optional
spring.cloud.config.enabled=false
#spring.cloud.config.import-check.enabled=false
spring.application.name=limit-server
server.port=8081

limit-server.minimum=223
limit-server.maximum=343434
```

---

### 2. Limit.java

#### \model\Limit.java

```java

package com.suji.configdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Limit {
	
	private int minimum;
	private int maximum;
}

```

---

### 3. LimitConfig.java

#### \src\main\java\com\suji\configdemo\config\LimitConfig.java

```java

package com.suji.configdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "limit-server")
@Data
public class LimitConfig {
	private int minimum, maximum;
	
}


```

---

### 4. LimitRestController.java

#### \src\main\java\com\suji\configdemo\controller\LimitRestController.java

```java

package com.suji.configdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.configdemo.config.LimitConfig;
import com.suji.configdemo.model.Limit;

import ch.qos.logback.classic.Logger;
import sun.util.logging.resources.logging;

@RestController
public class LimitRestController {

	
	@Autowired
	private LimitConfig config;

	// Get default Limit values
	@GetMapping("/default")
	public Limit getLimits() {
		System.out.println("Client has been called...");
		return new Limit(config.getMinimum(),config.getMaximum());
	}
	
}

```

---

### 5. SpringConfigServerDemoApplication.java

#### \src\main\java\com\suji\configdemo\SpringConfigServerDemoApplication.java

```java

package com.suji.configdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringConfigServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerDemoApplication.class, args);
	}

}

```

---

