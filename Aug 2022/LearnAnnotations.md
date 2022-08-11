# LearnAnnotations


### File Structure
```pre
+ LearnAnnotations\ 
	+ \src\main\java\com\suji\LearnAnnotations
		|---  App.java
	+ \src\test\java\com\suji\LearnAnnotations
		|---  AppTest.java
	+ \src\main\java\com\suji\LearnAnnotations\annos
		|---  King.java
	+ \src\main\java\com\suji\LearnAnnotations\clazz
		|---  A.java
```
### Index
```pre
1. src\main\java\com\suji\LearnAnnotations\App.java
2. src\main\java\com\suji\LearnAnnotations\annos\King.java
3. src\main\java\com\suji\LearnAnnotations\clazz\A.java
4. src\test\java\com\suji\LearnAnnotations\AppTest.java

```

---

### 1. App.java

#### src\main\java\com\suji\LearnAnnotations\App.java

```java

package com.suji.LearnAnnotations;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

```

---

### 2. King.java

#### src\main\java\com\suji\LearnAnnotations\annos\King.java

```java

package com.suji.LearnAnnotations.annos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface King {
	public String key() default "";
}

```

---

### 3. A.java

#### src\main\java\com\suji\LearnAnnotations\clazz\A.java

```java

package com.suji.LearnAnnotations.clazz;

import com.suji.LearnAnnotations.annos.King;

@King(key = "Hellow")
public class A {

}

```

---

### 4. AppTest.java

#### src\test\java\com\suji\LearnAnnotations\AppTest.java

```java

package com.suji.LearnAnnotations;

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

