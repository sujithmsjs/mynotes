# h2-database


### File Structure
```pre
+ h2-database\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\h2
		|---  H2DatabaseApplication.java
		|---  JwtUser.java
	+ \src\test\java\com\suji\h2
		|---  H2DatabaseApplicationTests.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\h2\H2DatabaseApplication.java
3. src\main\java\com\suji\h2\JwtUser.java
4. src\test\java\com\suji\h2\H2DatabaseApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. H2DatabaseApplication.java

#### src\main\java\com\suji\h2\H2DatabaseApplication.java

```java

package com.suji.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class H2DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2DatabaseApplication.class, args);
	}

}

```

---

### 3. JwtUser.java

#### src\main\java\com\suji\h2\JwtUser.java

```java

package com.suji.h2;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JwtUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String name;
	private String username;
	private String password;
	

//	@JoinColumn(name = "role")
	@Embedded
	private Set<String> roles;
	private boolean enabled;

	public void addRoles(String role) {
		roles.add(role);
	}
	
	
}

```

---

### 4. H2DatabaseApplicationTests.java

#### src\test\java\com\suji\h2\H2DatabaseApplicationTests.java

```java

package com.suji.h2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class H2DatabaseApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

