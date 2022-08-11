# weather-server


### File Structure
```pre
+ weather-server\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\weather
		|---  WeatherServerApplication.java
	+ \src\test\java\com\suji\weather
		|---  WeatherServerApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\weather\WeatherServerApplication.java
3. src\test\java\com\suji\weather\WeatherServerApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. WeatherServerApplication.java

#### src\main\java\com\suji\weather\WeatherServerApplication.java

```java

package com.suji.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServerApplication.class, args);
	}

}

```

---

### 3. WeatherServerApplicationTests.java

#### src\test\java\com\suji\weather\WeatherServerApplicationTests.java

```java

package com.suji.weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherServerApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

