# streams-java


### File Structure
```pre
+ streams-java\ 
	+ \src\main\java\tech\suji\streams_java
		|---  App.java
		|---  Student.java
	+ \src\test\java\tech\suji\streams_java
		|---  AppTest.java
```
### Index
```pre
1. src\main\java\tech\suji\streams_java\App.java
2. src\main\java\tech\suji\streams_java\Student.java
3. src\test\java\tech\suji\streams_java\AppTest.java

```

---

### 1. App.java

#### src\main\java\tech\suji\streams_java\App.java

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

#### src\main\java\tech\suji\streams_java\Student.java

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

### 3. AppTest.java

#### src\test\java\tech\suji\streams_java\AppTest.java

```java

package tech.suji.streams_java;

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

