# FirstDemo


### File Structure
```pre
+ FirstDemo\ 
	+ \src\test\java\com\suji\first
		|---  AppTest.java
	+ \src\main\java\com\suji\first\beans
		|---  Car.java
	+ \src\main\java\com\suji\first\config
		|---  MyConfig.java
	+ \src\main\java\com\suji\first\main
		|---  App.java
```
### Index
```pre
1. src\main\java\com\suji\first\beans\Car.java
2. src\main\java\com\suji\first\config\MyConfig.java
3. src\main\java\com\suji\first\main\App.java
4. src\test\java\com\suji\first\AppTest.java

```

---

### 1. Car.java

#### src\main\java\com\suji\first\beans\Car.java

```java

package com.suji.first.beans;

import org.springframework.stereotype.Component;

@Component
public class Car {
	
	private String name;

	public Car(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
	
	
}

```

---

### 2. MyConfig.java

#### src\main\java\com\suji\first\config\MyConfig.java

```java

package com.suji.first.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.suji.first.beans.Car;

@Configuration
@ComponentScan(basePackages = "com.suji.first.beans")
public class MyConfig {

	@Bean("car")
	public Car getCar() {
		return new Car("Tesla");
	}
}

```

---

### 3. App.java

#### src\main\java\com\suji\first\main\App.java

```java

package com.suji.first.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.suji.first.beans.Car;
import com.suji.first.config.MyConfig;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Car car = (Car) context.getBean("car");
        System.out.println(car);
        
    }
}

```

---

### 4. AppTest.java

#### src\test\java\com\suji\first\AppTest.java

```java

package com.suji.first;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}

```

---

