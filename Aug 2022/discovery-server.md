# discovery-server


### File Structure
```pre
+ discovery-server\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\discovery_server
		|---  DiscoveryServerApplication.java
	+ \src\test\java\com\suji\discovery_server
		|---  DiscoveryServerApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\discovery_server\DiscoveryServerApplication.java
3. src\test\java\com\suji\discovery_server\DiscoveryServerApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


server.port=8761
spring.application.name=service-registry

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

eureka.instance.home-page-url=https://${eureka.hostname}/
eureka.instance.health-check-url=https://${eureka.hostname}/health
eureka.instance.status-page-url=https://${eureka.hostname}/info

eureka.client.healthcheck.enabled=true

```

---

### 2. DiscoveryServerApplication.java

#### src\main\java\com\suji\discovery_server\DiscoveryServerApplication.java

```java

package com.suji.discovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

}

```

---

### 3. DiscoveryServerApplicationTests.java

#### src\test\java\com\suji\discovery_server\DiscoveryServerApplicationTests.java

```java

package com.suji.discovery_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiscoveryServerApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

