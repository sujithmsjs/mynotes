# spring-jwt-security


### File Structure
```pre
+ spring-jwt-security\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\tech\suji
		|---  SpringJwtSecurityApplication.java
	+ \src\test\java\tech\suji
		|---  SpringJwtSecurityApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\tech\suji\SpringJwtSecurityApplication.java
3. src\test\java\tech\suji\SpringJwtSecurityApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. SpringJwtSecurityApplication.java

#### src\main\java\tech\suji\SpringJwtSecurityApplication.java

```java

package tech.suji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJwtSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtSecurityApplication.class, args);
	}

}

```

---

### 3. SpringJwtSecurityApplicationTests.java

#### src\test\java\tech\suji\SpringJwtSecurityApplicationTests.java

```java

package tech.suji;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJwtSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

