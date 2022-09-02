# spring-security-demo-1


### File Structure
```pre
+ spring-security-demo-1\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\tech\suji\jwt
		|---  SpringSecurityDemo1Application.java
	+ \src\test\java\tech\suji\jwt
		|---  SpringSecurityDemo1ApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\tech\suji\jwt\SpringSecurityDemo1Application.java
3. src\test\java\tech\suji\jwt\SpringSecurityDemo1ApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. SpringSecurityDemo1Application.java

#### src\main\java\tech\suji\jwt\SpringSecurityDemo1Application.java

```java

package tech.suji.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemo1Application.class, args);
	}

}

```

---

### 3. SpringSecurityDemo1ApplicationTests.java

#### src\test\java\tech\suji\jwt\SpringSecurityDemo1ApplicationTests.java

```java

package tech.suji.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityDemo1ApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

