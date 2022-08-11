# spring-basics-test


### File Structure
```pre
+ spring-basics-test\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\basics
		|---  SpringBasicsTestApplication.java
	+ \src\test\java\com\suji\sheershika
		|---  SpringBasicsTestApplicationTests.java
	+ \src\main\java\com\suji\basics\model
		|---  Student.java
	+ \src\main\java\com\suji\basics\aspect
		|---  DemoAspect.java
	+ \src\main\java\com\suji\basics\dao
		|---  StudentDao.java
```
### Index
```pre
1. application.properties
2. model\Student.java
3. src\main\java\com\suji\basics\SpringBasicsTestApplication.java
4. src\main\java\com\suji\basics\aspect\DemoAspect.java
5. src\main\java\com\suji\basics\dao\StudentDao.java
6. src\test\java\com\suji\sheershika\SpringBasicsTestApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. Student.java

#### model\Student.java

```java

package com.suji.basics.model;



import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class Student {
	
	private int rollno;
	private String name;
	private String school;
	
	public Student() {
		super();
		System.out.println("Default Constructor of Student classs");
	}
	
	public Student(int rollno, String name, String school) {
		super();
		System.out.println("Parameterized Constructor of Student classs");
		this.rollno = rollno;
		this.name = name;
		this.school = school;
	}
	
	public int getRollno() {
		return rollno;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Init method of Student classs");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Destroy method of Student classs");
	}
	
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", school=" + school + "]";
	}
}

```

---

### 3. SpringBasicsTestApplication.java

#### src\main\java\com\suji\basics\SpringBasicsTestApplication.java

```java

package com.suji.basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.suji.basics.dao.StudentDao;
import com.suji.basics.model.Student;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class SpringBasicsTestApplication implements ApplicationRunner {
	
	@Autowired
	private StudentDao studentDao;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBasicsTestApplication.class, args);
		/*
		String[] names = context.getBeanDefinitionNames();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}*/
		
		Student student = context.getBean("student", Student.class);
		System.out.println("Student :"+student);
		
		
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(studentDao.addStudent(new Student(101, "Sujith", "SCHS")));
	}

}

```

---

### 4. DemoAspect.java

#### src\main\java\com\suji\basics\aspect\DemoAspect.java

```java

package com.suji.basics.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.suji.basics.model.Student;

@Aspect
@Component
public class DemoAspect {

	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* add*(..))")
	private void addStudent() {}
	
	@Before("addStudent()  and args(student)")
    public void before(Student student){
      //  logger.info("This content from aspect");
		System.out.println(" --> This Context is from aspect! "+student);
    }	

	@AfterReturning("addStudent()")
	public void after(){
		System.out.println(" <-- This Context is from aspect! ");
	}	
	
	
	
}

```

---

### 5. StudentDao.java

#### src\main\java\com\suji\basics\dao\StudentDao.java

```java

package com.suji.basics.dao;

import org.springframework.stereotype.Component;

import com.suji.basics.model.Student;

@Component
public class StudentDao {
	public boolean addStudent(Student student) {
		System.out.println("Student Added");
		return true;
	}
}

```

---

### 6. SpringBasicsTestApplicationTests.java

#### src\test\java\com\suji\sheershika\SpringBasicsTestApplicationTests.java

```java

package com.suji.sheershika;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBasicsTestApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

