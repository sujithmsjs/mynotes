# streams-java


### File Structure
```pre
+ streams-java\ 
	+ \src\main\java\tech\suji\streams_java
		|---  App.java
		|---  Student.java
```
### Index
```pre
1. \src\main\java\tech\suji\streams_java\App.java
2. \src\main\java\tech\suji\streams_java\Student.java

```

---

### 1. App.java

#### \src\main\java\tech\suji\streams_java\App.java

```java

package tech.suji.streams_java;

import java.util.Random;
import java.util.stream.Stream;

import lombok.Data;

public class App {

	public static void main(String[] args) {
		
		Random r = new Random();

		
	}
}


```

---

### 2. Student.java

#### \src\main\java\tech\suji\streams_java\Student.java

```java

package tech.suji.streams_java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private int id;
	private String name;
}

```

---

