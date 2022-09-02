# ServiceRegistry


### File Structure
```pre
+ ServiceRegistry\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\micro\servicereg
		|---  ServiceRegistryApplication.java
	+ \src\test\java\com\micro\servicereg
		|---  ServiceRegistryApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\micro\servicereg\ServiceRegistryApplication.java
3. src\test\java\com\micro\servicereg\ServiceRegistryApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



server.port=8761
spring.application.name=service-registry

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false



```

---

### 2. ServiceRegistryApplication.java

#### src\main\java\com\micro\servicereg\ServiceRegistryApplication.java

```java

package com.micro.servicereg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}
}

```

---

### 3. ServiceRegistryApplicationTests.java

#### src\test\java\com\micro\servicereg\ServiceRegistryApplicationTests.java

```java

package com.micro.servicereg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceRegistryApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

