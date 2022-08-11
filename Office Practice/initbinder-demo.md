# initbinder-demo


### File Structure
```pre
+ initbinder-demo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\initbinder\demo
		|---  InitbinderDemoApplication.java
	+ \src\test\java\com\suji\initbinder\demo
		|---  InitbinderDemoApplicationTests.java
	+ \src\main\java\com\suji\initbinder\demo\model
		|---  Box.java
```
### Index
```pre
1. application.properties
2. model\Box.java
3. src\main\java\com\suji\initbinder\demo\InitbinderDemoApplication.java
4. src\test\java\com\suji\initbinder\demo\InitbinderDemoApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. Box.java

#### model\Box.java

```java

package com.suji.initbinder.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Box {

    private String text;
    private int value;

}
```

---

### 3. InitbinderDemoApplication.java

#### src\main\java\com\suji\initbinder\demo\InitbinderDemoApplication.java

```java

package com.suji.initbinder.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InitbinderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitbinderDemoApplication.class, args);
	}

}

```

---

### 4. InitbinderDemoApplicationTests.java

#### src\test\java\com\suji\initbinder\demo\InitbinderDemoApplicationTests.java

```java

package com.suji.initbinder.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InitbinderDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

