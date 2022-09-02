# Config-Server


### File Structure
```pre
+ Config-Server\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\micro\configserver
		|---  ConfigServerApplication.java
	+ \src\test\java\com\micro\configserver
		|---  ConfigServerApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\micro\configserver\ConfigServerApplication.java
3. src\test\java\com\micro\configserver\ConfigServerApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


server.port=8888
spring.application.name=SPRING-CLOULD-CONFIG-SERVER

spring.cloud.config.server.git.uri=file:///C:/Users/sujith.manchala/Desktop/config-server-repo

spring.cloud.config.server.git.clone-on-start=true
management.endpoint.serviceregistry.enabled=false

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone: http://localhost:8761/eureka/
eureka.instance.hostname=localhost
```

---

### 2. ConfigServerApplication.java

#### src\main\java\com\micro\configserver\ConfigServerApplication.java

```java

package com.micro.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}

```

---

### 3. ConfigServerApplicationTests.java

#### src\test\java\com\micro\configserver\ConfigServerApplicationTests.java

```java

package com.micro.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigServerApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

