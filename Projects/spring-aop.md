# spring-aop


### File Structure
```pre
+ spring-aop\ 
	|---  pom.xml
	+ \src\main\java\com\suji\springaop
		|---  App.java
	+ \src\test\java\com\suji\springaop
		|---  AppTest.java
	+ \src\main\java\com\suji\springaop\model
		|---  Account.java
	+ \src\main\java\com\suji\springaop\aspect
		|---  DemoAspect.java
		|---  MyLoggingAspect.java
		|---  PointCutACombineAspect.java
		|---  PointCutAspect.java
		|---  PointcutExpressions.java
	+ \src\main\java\com\suji\springaop\config
		|---  MyConfig.java
	+ \src\main\java\com\suji\springaop\dao
		|---  AccountDAO.java
		|---  MemberDAO.java
		|---  StudentDAO.java
```
### Index
```pre
1. pom.xml
2. model\Account.java
3. src\main\java\com\suji\springaop\App.java
4. src\main\java\com\suji\springaop\aspect\DemoAspect.java
5. src\main\java\com\suji\springaop\aspect\MyLoggingAspect.java
6. src\main\java\com\suji\springaop\aspect\PointCutACombineAspect.java
7. src\main\java\com\suji\springaop\aspect\PointCutAspect.java
8. src\main\java\com\suji\springaop\aspect\PointcutExpressions.java
9. src\main\java\com\suji\springaop\config\MyConfig.java
10. src\main\java\com\suji\springaop\dao\AccountDAO.java
11. src\main\java\com\suji\springaop\dao\MemberDAO.java
12. src\main\java\com\suji\springaop\dao\StudentDAO.java
13. src\test\java\com\suji\springaop\AppTest.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.suji</groupId>
	<artifactId>spring-aop</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>spring-aop</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>

	<dependencies>

		<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.9.7</version>
			<scope>compile</scope>
		</dependency>


		<!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>5.3.16</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.3.16</version>
		</dependency>


		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
</project>

```

---

### 2. Account.java

#### model\Account.java

```java

package com.suji.springaop.model;

public class Account {
	private int id;
	private String name;
	
	public Account(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + "]";
	}
	
	
}

```

---

### 3. App.java

#### src\main\java\com\suji\springaop\App.java

```java

package com.suji.springaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.suji.springaop.config.MyConfig;
import com.suji.springaop.dao.AccountDAO;
import com.suji.springaop.dao.MemberDAO;
import com.suji.springaop.dao.StudentDAO;
import com.suji.springaop.model.Account;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(MyConfig.class);
        AccountDAO acc = acac.getBean(AccountDAO.class);
        MemberDAO mem = acac.getBean(MemberDAO.class);
        StudentDAO std = acac.getBean(StudentDAO.class);
        
        acc.addAccount(new Account(101,"Sujith"), false);
        acc.doWork();
        acc.getSomething();
        acc.setSomoething(0);
        mem.addAccount();
        
        std.addAccount();
        
        acac.close();
    }
}


```

---

### 4. DemoAspect.java

#### src\main\java\com\suji\springaop\aspect\DemoAspect.java

```java

package com.suji.springaop.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAspect {

	@Pointcut("execution(* add*(..))")
	public void add() {}
	
	@Before("add()")
	public void beforeAdd(Joinpoint jp) {
		System.out.println("--> Aspect Before return ");
	}
	
	@AfterReturning("add()")
	public void demo(Joinpoint jp) {
		System.out.println("<-- Aspect After return");
	} 
}

```

---

### 5. MyLoggingAspect.java

#### src\main\java\com\suji\springaop\aspect\MyLoggingAspect.java

```java

package com.suji.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect @Component
public class MyLoggingAspect {

	//@Before("execution(* addAccount())")
	//@Before("execution(* add*(*))")
	//@Before("execution(* add*(com.suji.springaop.model.Account,*))")
	//@Before("execution(* add*(com.suji.springaop.model.Account,..))")
	//@Before("execution(* add*(..))")
	@Before("execution(* com.suji.springaop.dao.*.*(..))")
	public void before() {
		System.out.println("This is before Before Aspect");
	}
	
}

```

---

### 6. PointCutACombineAspect.java

#### src\main\java\com\suji\springaop\aspect\PointCutACombineAspect.java

```java

package com.suji.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class PointCutACombineAspect {

	//Pointcut declarations 
	@Pointcut("execution(* com.suji.springaop.dao.*.*(..))")
	public void pointCutDemo() {};
	
	@Pointcut("execution(* com.suji.springaop.dao.*.get*(..))")
	public void getters() {};
	
	@Pointcut("execution(* com.suji.springaop.dao.*.set*(..))")
	public void setters() {};
	
	@Pointcut("pointCutDemo() && !(getters() || setters())")
	public void pointCutDemo2() {};
	
	@Before("getters()")
	public void before() {
		System.out.println("---- This is before Before Aspect");
	}
	/*
	@Before("pointCutDemo2()")
	public void before2() {
		System.out.println(">> This is before Before Aspect");
	}*/
	
}

```

---

### 7. PointCutAspect.java

#### src\main\java\com\suji\springaop\aspect\PointCutAspect.java

```java

package com.suji.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class PointCutAspect {

	//Pointcut declarations
	@Pointcut("execution(* com.suji.springaop.dao.*.*(..))")
	public void pointCutDemo() {};
	
	
	@Before("pointCutDemo()")
	public void before() {
		System.out.println("---- This is before Before Aspect");
	}
	
	@Before("pointCutDemo()")
	public void before2() {
		System.out.println(">> This is before Before Aspect");
	}
	
}

```

---

### 8. PointcutExpressions.java

#### src\main\java\com\suji\springaop\aspect\PointcutExpressions.java

```java

package com.suji.springaop.aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class PointcutExpressions {
	//Pointcut declarations 
	@Pointcut("execution(* com.suji.springaop.dao.*.*(..))")
	public void pointCutDemo() {};
	
	@Pointcut("execution(* com.suji.springaop.dao.*.get*(..))")
	public void getters() {};
	
	@Pointcut("execution(* com.suji.springaop.dao.*.set*(..))")
	public void setters() {};
	
	@Pointcut("pointCutDemo() && !(getters() || setters())")
	public void pointCutDemo2() {};
	
	@Before("getters()")
	public void before() {
		System.out.println("---- This is before Before Aspect");
	}
}

```

---

### 9. MyConfig.java

#### src\main\java\com\suji\springaop\config\MyConfig.java

```java

package com.suji.springaop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.suji.springaop")
public class MyConfig {

}

```

---

### 10. AccountDAO.java

#### src\main\java\com\suji\springaop\dao\AccountDAO.java

```java

package com.suji.springaop.dao;

import org.springframework.stereotype.Component;

import com.suji.springaop.model.Account;

@Component
public class AccountDAO {
	public void addAccount(Account account, boolean vipFlag) {
		System.out.println(getClass()+" DOING MY JOB, ADDING ACCOUNT.");
	}
	
	public void doWork() {
		System.out.println(getClass()+" DOING TIME WASTE.");
	}
	
	public int getSomething() {
		System.out.println(getClass()+" GETTER METHOD");
		return 0;
	}
	
	public void setSomoething(int value) {
		System.out.println(getClass()+" SETTER METHOD");
	}
}

```

---

### 11. MemberDAO.java

#### src\main\java\com\suji\springaop\dao\MemberDAO.java

```java

package com.suji.springaop.dao;

import org.springframework.stereotype.Component;

@Component
public class MemberDAO {
	public void addAccount() {
		System.out.println(getClass()+" DOING MY JOB, ADDING ACCOUNT.");
	}

	private void doSomething() {
	System.out.println(getClass()+"Doing someting");
	}
}


```

---

### 12. StudentDAO.java

#### src\main\java\com\suji\springaop\dao\StudentDAO.java

```java

package com.suji.springaop.dao;

import org.springframework.stereotype.Component;

@Component
public class StudentDAO {
	public void addAccount() {
		System.out.println(getClass()+" DOING MY JOB, ADDING ACCOUNT.");
	}
}

```

---

### 13. AppTest.java

#### src\test\java\com\suji\springaop\AppTest.java

```java

package com.suji.springaop;

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

