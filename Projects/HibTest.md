# HibTest


### File Structure
```pre
+ HibTest\ 
	|---  pom.xml
	+ \src\main\java\com\suji
		|---  Ans.java
		|---  App.java
		|---  Card.java
		|---  Laptop.java
		|---  Student.java
		|---  User.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\Ans.java
3. src\main\java\com\suji\App.java
4. src\main\java\com\suji\Card.java
5. src\main\java\com\suji\Laptop.java
6. src\main\java\com\suji\Student.java
7. src\main\java\com\suji\User.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.suji</groupId>
	<artifactId>HibTest</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>HibTest</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>

	<dependencies>
	
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.16</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.4.10.Final</version>
		</dependency>

	</dependencies>
</project>

```

---

### 2. Ans.java

#### src\main\java\com\suji\Ans.java

```java

package com.suji;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ans {
	@Id
	private int aid;
	private String answer;
	private String hint;
	
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	@Override
	public String toString() {
		return "Ans [aid=" + aid + ", answer=" + answer + ", hint=" + hint + "]";
	}
	
	
	
}

```

---

### 3. App.java

#### src\main\java\com\suji\App.java

```java

package com.suji;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
	
	public static void main(String[] args) {
		
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		
		Transaction trn = session.getTransaction();
		
		trn.begin();
		
		User user = session.get(User.class, 4);
		System.out.println(user);
		
		trn.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	
	public static void main3(String[] args) {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction trn = session.getTransaction();
		trn.begin();
		
		User user = session.get(User.class, 4);
		System.out.println(user);
		
		trn.commit();
		session.close();
		sessionFactory.close();

	}
	
	public static void main2(String[] args) {
		// Getting Session Object
		Configuration config = new Configuration().configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
		
		User user = new User(2, "Vamshi", 6.2);
		user.setDob(new Date());
		//user.setMarks(78);
		
		
		Transaction trn = session.getTransaction();
		trn.begin();
		
		session.save(user);
		
		
		trn.commit();

		
		session.close();
		sf.close();

	}

	//Retriving Data
	public static void main1(String[] args) {
		Configuration config = new Configuration().configure();

		SessionFactory sf = config.buildSessionFactory();

		Session session = sf.openSession();

		Transaction trn = session.getTransaction();

		trn.begin();

		Student std = session.get(Student.class, 10);
		Laptop lap = std.getLaptop();
		System.out.println(std);
		System.out.println(lap);
		trn.commit();

		session.close();
		sf.close();

	}
}

```

---

### 4. Card.java

#### src\main\java\com\suji\Card.java

```java

package com.suji;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Card {
	@Id
	private int cid;
	private String question;
	
	@OneToMany
	private List<Ans> ans = new ArrayList<Ans>();

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Ans> getAns() {
		return ans;
	}

	public void setAns(List<Ans> ans) {
		this.ans = ans;
	}

	@Override
	public String toString() {
		return "Card [cid=" + cid + ", question=" + question + ", ans=" + ans + "]";
	}
	
	
}

```

---

### 5. Laptop.java

#### src\main\java\com\suji\Laptop.java

```java

package com.suji;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Laptop {
	
	@Id
	private int lapId;
	private	String company;
	private	String processor;
	@OneToOne
	private Student student;
	
	

	public int getLapId() {
		return lapId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setLapId(int lapId) {
		this.lapId = lapId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	@Override
	public String toString() {
		return "Laptop [lapId=" + lapId + ", company=" + company + ", processor=" + processor + "]";
	}
}

```

---

### 6. Student.java

#### src\main\java\com\suji\Student.java

```java

package com.suji;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {
	
	@Id
	private int rollno;
	private String name;
	private String school;
	@OneToOne
	private Laptop laptop;
	
	
	
	public Student() {
		super();
	}

	public Student(int rollno, String name, String school) {
		super();
		this.rollno = rollno;
		this.name = name;
		this.school = school;
	}
	
	public Laptop getLaptop() {
		return laptop;
	}
	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
	public int getRollno() {
		return rollno;
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
		return "Student [rollno=" + rollno + ", name=" + name + ", school=" + school + ", laptop=" + laptop + "]";
	}
	
	
	
	
	
}

```

---

### 7. User.java

#### src\main\java\com\suji\User.java

```java

package com.suji;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int uid;
	private String name;
	private double height;
	private int marks;
	private Date dob;
	

	
	
	public User() {
		super();
	}


	public User(int uid, String name, double height) {
		super();
		this.uid = uid;
		this.name = name;
		this.height = height;
	}
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", height=" + height + ", marks=" + marks + ", dob=" + dob + "]";
	}

}

```

---

