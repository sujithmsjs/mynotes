# Test


### File Structure
```pre
+ Test\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\test
		|---  TestApplication.java
	+ \src\test\java\com\suji\test
		|---  TestApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\test\TestApplication.java
3. src\test\java\com\suji\test\TestApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. TestApplication.java

#### src\main\java\com\suji\test\TestApplication.java

```java

package com.suji.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}

```

---

### 3. TestApplicationTests.java

#### src\test\java\com\suji\test\TestApplicationTests.java

```java

package com.suji.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

