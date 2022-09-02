# initbinder-demo


### File Structure
```pre
+ initbinder-demo\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\initbinder\demo
		|---  InitbinderDemoApplication.java
	+ \src\main\java\com\suji\initbinder\demo\model
		|---  Box.java
```
### Index
```pre
1. resources\application.properties
2. \model\Box.java
3. \src\main\java\com\suji\initbinder\demo\InitbinderDemoApplication.java

```

---

### 1. application.properties

#### resources\application.properties

```properties



```

---

### 2. Box.java

#### \model\Box.java

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

#### \src\main\java\com\suji\initbinder\demo\InitbinderDemoApplication.java

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

