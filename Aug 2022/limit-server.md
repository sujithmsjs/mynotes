# limit-server


### File Structure
```pre
+ limit-server\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\tech\limit
		|---  LimitServerApplication.java
	+ \src\test\java\tech\limit
		|---  LimitServerApplicationTests.java
	+ \src\main\java\tech\limit\model
		|---  Limit.java
	+ \src\main\java\tech\limit\config
		|---  LimitConfig.java
	+ \src\main\java\tech\limit\ctrl
		|---  LimitController.java
```
### Index
```pre
1. application.properties
2. model\Limit.java
3. src\main\java\tech\limit\LimitServerApplication.java
4. src\main\java\tech\limit\config\LimitConfig.java
5. src\main\java\tech\limit\ctrl\LimitController.java
6. src\test\java\tech\limit\LimitServerApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



spring.application.name=limits-service

limits-service.minimum=9
limits-service.maximum=999
```

---

### 2. Limit.java

#### model\Limit.java

```java

package tech.limit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Limit {
	private int min,max;
}

```

---

### 3. LimitServerApplication.java

#### src\main\java\tech\limit\LimitServerApplication.java

```java

package tech.limit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimitServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitServerApplication.class, args);
	}

}


```

---

### 4. LimitConfig.java

#### src\main\java\tech\limit\config\LimitConfig.java

```java

package tech.limit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("limits-service")
public class LimitConfig {
	private int maximum, minimum;
}

```

---

### 5. LimitController.java

#### src\main\java\tech\limit\ctrl\LimitController.java

```java

package tech.limit.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.limit.config.LimitConfig;
import tech.limit.model.Limit;

@RestController
public class LimitController {
	
	@Autowired
	private LimitConfig config;
	
	@GetMapping("/limits")
	public Limit getLimits() {
		return new Limit(config.getMinimum(),config.getMaximum());
	}
	
}

```

---

### 6. LimitServerApplicationTests.java

#### src\test\java\tech\limit\LimitServerApplicationTests.java

```java

package tech.limit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LimitServerApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

