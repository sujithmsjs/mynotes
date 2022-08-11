# spring-jwt-2


### File Structure
```pre
+ spring-jwt-2\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\jwt
		|---  SpringJwt2Application.java
	+ \src\test\java\com\suji\jwt
		|---  SpringJwt2ApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\jwt\SpringJwt2Application.java
3. src\test\java\com\suji\jwt\SpringJwt2ApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. SpringJwt2Application.java

#### src\main\java\com\suji\jwt\SpringJwt2Application.java

```java

package com.suji.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJwt2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwt2Application.class, args);
	}

}

```

---

### 3. SpringJwt2ApplicationTests.java

#### src\test\java\com\suji\jwt\SpringJwt2ApplicationTests.java

```java

package com.suji.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJwt2ApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

