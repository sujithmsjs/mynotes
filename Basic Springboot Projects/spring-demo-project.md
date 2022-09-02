# spring-demo-project


### File Structure
```pre
+ spring-demo-project\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\demo
		|---  SpringDemoProjectApplication.java
	+ \src\test\java\com\suji\demo
		|---  SpringDemoProjectApplicationTests.java
	+ \src\main\java\com\suji\demo\pojo
		|---  Bumper.java
		|---  Car.java
		|---  Engine.java
		|---  InlineEngine.java
		|---  StrightEngine.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\demo\SpringDemoProjectApplication.java
3. src\main\java\com\suji\demo\pojo\Bumper.java
4. src\main\java\com\suji\demo\pojo\Car.java
5. src\main\java\com\suji\demo\pojo\Engine.java
6. src\main\java\com\suji\demo\pojo\InlineEngine.java
7. src\main\java\com\suji\demo\pojo\StrightEngine.java
8. src\test\java\com\suji\demo\SpringDemoProjectApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. SpringDemoProjectApplication.java

#### src\main\java\com\suji\demo\SpringDemoProjectApplication.java

```java

package com.suji.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.suji.demo.pojo.Car;

@SpringBootApplication
public class SpringDemoProjectApplication {
	
	

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringDemoProjectApplication.class, args);
		
		
		for(String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
		System.out.println("--------------USER DEFINE BEANS---------------------");
		
		Car car = context.getBean(Car.class);
		System.out.println(car);
		
		
	}

}

```

---

### 3. Bumper.java

#### src\main\java\com\suji\demo\pojo\Bumper.java

```java

package com.suji.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class Bumper {

}

```

---

### 4. Car.java

#### src\main\java\com\suji\demo\pojo\Car.java

```java

package com.suji.demo.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {
	
	@Autowired
	@Qualifier(value = "strightEngine")
	private Engine engine;
	@Autowired
	private Bumper bumpers;
	

	public Engine getEngine() {
		return engine;
	}

	
	public void setEngine( Engine engine) {
		this.engine = engine;
	}

	@Override
	public String toString() {
		return "Car [engine=" + engine + ", bumper=" + bumpers + "]";
	}
}

```

---

### 5. Engine.java

#### src\main\java\com\suji\demo\pojo\Engine.java

```java

package com.suji.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public interface Engine {
	public void start();
}

```

---

### 6. InlineEngine.java

#### src\main\java\com\suji\demo\pojo\InlineEngine.java

```java

package com.suji.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class InlineEngine implements Engine {

	@Override
	public void start() {
		System.out.println("Inline Engine has been started.");

	}

}

```

---

### 7. StrightEngine.java

#### src\main\java\com\suji\demo\pojo\StrightEngine.java

```java

package com.suji.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class StrightEngine implements Engine {

	@Override
	public void start() {
		System.out.println("Stright Engine has been started.");

	}

}

```

---

### 8. SpringDemoProjectApplicationTests.java

#### src\test\java\com\suji\demo\SpringDemoProjectApplicationTests.java

```java

package com.suji.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDemoProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

