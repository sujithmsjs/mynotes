# FirstSpring


### File Structure
```pre
+ FirstSpring\ 
	|---  pom.xml
	+ \src\test\java\com\suji\spring
		|---  AppTest.java
	+ \src\main\java\com\suji\spring\app1
		|---  Bike.java
		|---  Car.java
		|---  Road.java
		|---  Tyre.java
		|---  Vehicle.java
	+ \src\main\java\com\suji\spring\app2
		|---  Controls.java
		|---  CosmicByte.java
		|---  Game.java
		|---  GameRunner.java
		|---  Joystick.java
		|---  Mario.java
		|---  PacMan.java
		|---  Redgear.java
	+ \src\main\java\com\suji\spring\apps
		|---  App.java
		|---  App2.java
	+ \src\main\java\com\suji\spring\config
		|---  App2Config.java
		|---  AppConfig.java
		|---  BeanConfig.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\spring\app1\Bike.java
3. src\main\java\com\suji\spring\app1\Car.java
4. src\main\java\com\suji\spring\app1\Road.java
5. src\main\java\com\suji\spring\app1\Tyre.java
6. src\main\java\com\suji\spring\app1\Vehicle.java
7. src\main\java\com\suji\spring\app2\Controls.java
8. src\main\java\com\suji\spring\app2\CosmicByte.java
9. src\main\java\com\suji\spring\app2\Game.java
10. src\main\java\com\suji\spring\app2\GameRunner.java
11. src\main\java\com\suji\spring\app2\Joystick.java
12. src\main\java\com\suji\spring\app2\Mario.java
13. src\main\java\com\suji\spring\app2\PacMan.java
14. src\main\java\com\suji\spring\app2\Redgear.java
15. src\main\java\com\suji\spring\apps\App.java
16. src\main\java\com\suji\spring\apps\App2.java
17. src\main\java\com\suji\spring\config\App2Config.java
18. src\main\java\com\suji\spring\config\AppConfig.java
19. src\main\java\com\suji\spring\config\BeanConfig.java
20. src\test\java\com\suji\spring\AppTest.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.suji</groupId>
	<artifactId>FirstSpring</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>FirstSpring</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>

	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.3.14</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>5.3.14</version>
		</dependency>

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>

	</dependencies>
</project>

```

---

### 2. Bike.java

#### src\main\java\com\suji\spring\app1\Bike.java

```java

package com.suji.spring.app1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bike")
@Primary
public class Bike implements Vehicle {

	
	public Bike() {
		System.out.println("Bike Constructor");
	}
	
	public void drive() {
		System.out.println(getWheels()+" Wheels Bike Moving");
	}

	public int getWheels() {
		return 2;
	}

}

```

---

### 3. Car.java

#### src\main\java\com\suji\spring\app1\Car.java

```java

package com.suji.spring.app1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {

	private String name;
	
	@Autowired
	private Tyre tyre;
	
	
	public Tyre getTyre() {
		return tyre;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

	public Car(String name) {
		super();
		System.out.println("Car constructor");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car() {
		System.out.println("Car Constructor");
	}
	
	public void drive() {
		System.out.println(getWheels()+" Wheels Car Moving");
		tyre.tyreDesc();
	}

	public int getWheels() {
		return 4;
	}

}

```

---

### 4. Road.java

#### src\main\java\com\suji\spring\app1\Road.java

```java

package com.suji.spring.app1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Road {
	
	@Autowired
	@Qualifier("car")
	private Vehicle vehicle;

	public void showVehicle() {
		System.out.println(vehicle);
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}

```

---

### 5. Tyre.java

#### src\main\java\com\suji\spring\app1\Tyre.java

```java

package com.suji.spring.app1;

import org.springframework.stereotype.Component;

@Component
public class Tyre {
	public void tyreDesc() {
		System.out.println("Tyre is moving...");
	}
}

```

---

### 6. Vehicle.java

#### src\main\java\com\suji\spring\app1\Vehicle.java

```java

package com.suji.spring.app1;

import org.springframework.stereotype.Component;

@Component
public interface Vehicle {
	public void drive();
	public int getWheels();
}

```

---

### 7. Controls.java

#### src\main\java\com\suji\spring\app2\Controls.java

```java

package com.suji.spring.app2;

public interface Controls {
	public void up();
}

```

---

### 8. CosmicByte.java

#### src\main\java\com\suji\spring\app2\CosmicByte.java

```java

package com.suji.spring.app2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CosmicByte implements Joystick {

	public int totalKeys() {
		return 16;
	}

	public String getName() {
		return "Cosmic Byte C3070W Nebula";
	}

	public void play(Game game) {
		System.out.println(game.getName()+" is playing using "+getName());
		game.up();
	}
}

```

---

### 9. Game.java

#### src\main\java\com\suji\spring\app2\Game.java

```java

package com.suji.spring.app2;


public interface Game extends Controls {
	public String getName();
}

```

---

### 10. GameRunner.java

#### src\main\java\com\suji\spring\app2\GameRunner.java

```java

package com.suji.spring.app2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
	@Autowired
	@Qualifier(value = "pacMan")
	private Game game;
	@Autowired
	private Joystick joystick;
	
	public void play() {
		joystick.play(game);
	}
	
}

```

---

### 11. Joystick.java

#### src\main\java\com\suji\spring\app2\Joystick.java

```java

package com.suji.spring.app2;

import org.springframework.stereotype.Component;

public interface Joystick {
	public int totalKeys();
	public String getName();
	public void play(Game game);
}

```

---

### 12. Mario.java

#### src\main\java\com\suji\spring\app2\Mario.java

```java

package com.suji.spring.app2;

import org.springframework.stereotype.Component;

@Component
public class Mario implements Game {

	public String getName() {
		return "Super Mario";
	}

	public void up() {
		System.out.println("Jumped");
	}



}

```

---

### 13. PacMan.java

#### src\main\java\com\suji\spring\app2\PacMan.java

```java

package com.suji.spring.app2;

import org.springframework.stereotype.Component;

@Component
public class PacMan implements Game {

	public void up() {
		System.out.println("Turned upwards...");
	}

	public String getName() {
		return "Pac-Man";
	}



}

```

---

### 14. Redgear.java

#### src\main\java\com\suji\spring\app2\Redgear.java

```java

package com.suji.spring.app2;

import org.springframework.stereotype.Component;

@Component
public class Redgear implements Joystick {

	public int totalKeys() {
		return 10;
	}

	public String getName() {
		return "Redgear Pro Wireless Gamepad";
	}

	public void play(Game game) {
		System.out.println(game.getName()+" is playing using "+getName()+"; ");
		game.up();
	}
}

```

---

### 15. App.java

#### src\main\java\com\suji\spring\apps\App.java

```java

package com.suji.spring.apps;

import java.util.Arrays;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suji.spring.app1.Bike;
import com.suji.spring.app1.Car;
import com.suji.spring.app1.Road;
import com.suji.spring.app1.Vehicle;
import com.suji.spring.config.AppConfig;
import com.suji.spring.config.BeanConfig;

public class App {
	public static void main1(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("spring.xml");
		// ApplicationContext context = new
		// AnnotationConfigApplicationContext(BeanConfig.class);
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
		System.out.println("==================");
	 	Road road =  (Road) context.getBean("road");
		Vehicle vehicle = road.getVehicle();
		vehicle.drive();
		Car car = (Car) context.getBean("car");
		System.out.println(vehicle.getClass());
		System.out.println(vehicle==car);
	}
}

```

---

### 16. App2.java

#### src\main\java\com\suji\spring\apps\App2.java

```java

package com.suji.spring.apps;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.suji.spring.app2.GameRunner;
import com.suji.spring.config.App2Config;

public class App2 {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(App2Config.class);
		int count = context.getBeanDefinitionCount();
		String[] names = context.getBeanDefinitionNames();
		System.out.println(Arrays.asList(names));
		System.out.println("Count: "+count);
		
		System.out.println("===============");
		
		GameRunner gameRunner = (GameRunner) context.getBean("gameRunner");
		gameRunner.play();
	}
}

```

---

### 17. App2Config.java

#### src\main\java\com\suji\spring\config\App2Config.java

```java

package com.suji.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.suji.spring.app2")
public class App2Config {

}

```

---

### 18. AppConfig.java

#### src\main\java\com\suji\spring\config\AppConfig.java

```java

package com.suji.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.suji.spring.model")
public class AppConfig {

}

```

---

### 19. BeanConfig.java

#### src\main\java\com\suji\spring\config\BeanConfig.java

```java

package com.suji.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.suji.spring.app1.Bike;
import com.suji.spring.app1.Car;
import com.suji.spring.app1.Tyre;
import com.suji.spring.app1.Vehicle;

@Configuration
public class BeanConfig {
	
	public Vehicle getVehicle() {
		return getCar();
	}

	@Bean
	public Car getCar() {
		return new Car("Tesla");
	}
	
	@Bean
	public Tyre getTyre() {
		return new Tyre();
	}
	
	@Bean
	public Bike getBike() {
		return new Bike();
	}
}

```

---

### 20. AppTest.java

#### src\test\java\com\suji\spring\AppTest.java

```java

package com.suji.spring;

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

