# CouldGateway


### File Structure
```pre
+ CouldGateway\ 
	+ \src\main\java\com\micro\gateway
		|---  CouldGatewayApplication.java
	+ \src\test\java\com\micro\gateway
		|---  CouldGatewayApplicationTests.java
```
### Index
```pre
1. src\main\java\com\micro\gateway\CouldGatewayApplication.java
2. src\test\java\com\micro\gateway\CouldGatewayApplicationTests.java

```

---

### 1. CouldGatewayApplication.java

#### src\main\java\com\micro\gateway\CouldGatewayApplication.java

```java

package com.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CouldGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouldGatewayApplication.class, args);
	}

}

```

---

### 2. CouldGatewayApplicationTests.java

#### src\test\java\com\micro\gateway\CouldGatewayApplicationTests.java

```java

package com.micro.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouldGatewayApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

