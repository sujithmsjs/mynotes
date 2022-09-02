# SpringRunners


### File Structure
```pre
+ SpringRunners\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\runners
		|---  SpringRunnersApplication.java
	+ \src\test\java\com\suji\runners
		|---  SpringRunnersApplicationTests.java
	+ \src\main\java\com\suji\runners\runner
		|---  MyRunner.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\runners\SpringRunnersApplication.java
3. src\main\java\com\suji\runners\runner\MyRunner.java
4. src\test\java\com\suji\runners\SpringRunnersApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

spring.application.name=Hellow

#debug=true

logging.file=/var/tmp/mylog.log	
logging.path=/var/tmp/
#logging.level.root=DEBUG
logging.level.com.suji.runners=TRACE
#logging.level.com.suji.runners=TRACE
```

---

### 2. SpringRunnersApplication.java

#### src\main\java\com\suji\runners\SpringRunnersApplication.java

```java

package com.suji.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRunnersApplication implements ApplicationRunner{
	
	private static Logger logger = LoggerFactory.getLogger(SpringRunnersApplication.class);
	
	@Value("${spring.application.name}")
	private static String name;

	public static void main(String[] args) {
		SpringApplication.run(SpringRunnersApplication.class, args);
		System.out.println("Name: "+name);
		logger.debug("Name: "+name);
		logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("ApplicationRunner Run() method.");
	}

}

```

---

### 3. MyRunner.java

#### src\main\java\com\suji\runners\runner\MyRunner.java

```java

package com.suji.runners.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner, ApplicationRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("This is from CommandLineRunner run method.");
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("This is from ApplicationRunner  run method.");
	}

}

```

---

### 4. SpringRunnersApplicationTests.java

#### src\test\java\com\suji\runners\SpringRunnersApplicationTests.java

```java

package com.suji.runners;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRunnersApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

