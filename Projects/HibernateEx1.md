# HibernateEx1


### File Structure
```pre
+ HibernateEx1\ 
	|---  pom.xml
	+ \src\main\java\com\suji\HibernateEx1\model
		|---  Student.java
		|---  User.java
	+ \src\main\java\com\suji\HibernateEx1\config
		|---  HibernateUtil.java
	+ \src\main\java\com\suji\HibernateEx1\main
		|---  App.java
```
### Index
```pre
1. pom.xml
2. model\Student.java
3. model\User.java
4. src\main\java\com\suji\HibernateEx1\config\HibernateUtil.java
5. src\main\java\com\suji\HibernateEx1\main\App.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.suji</groupId>
	<artifactId>HibernateEx1</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>HibernateEx1</name>
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

### 2. Student.java

#### model\Student.java

```java

package com.suji.HibernateEx1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "stds")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private LocalDate dob;
	private LocalTime examTime;
	
	@CreationTimestamp
	private java.time.LocalDateTime created;
	
	@UpdateTimestamp
	private java.time.LocalDateTime updated;

	
	
	public Student() {
		super();
	}

	public Student(String name, LocalDate dob, LocalTime examTime) {
		super();
		this.name = name;
		this.dob = dob;
		this.examTime = examTime;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalTime getExamTime() {
		return examTime;
	}

	public void setExamTime(LocalTime examTime) {
		this.examTime = examTime;
	}

	public java.time.LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(java.time.LocalDateTime created) {
		this.created = created;
	}

	public java.time.LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(java.time.LocalDateTime updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", dob=" + dob + ", examTime=" + examTime + ", created="
				+ created + ", updated=" + updated + "]";
	}
	
	

}

```

---

### 3. User.java

#### model\User.java

```java

package com.suji.HibernateEx1.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "demo1")
public class User {

	@Id
	@OrderBy
	@Column(name = "user_id", length = 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int uid;
	
	@Column(length = 25)
	private String name;
	
	@Column(name = "username",length = 20,unique = true,updatable = false, nullable = false)
	private String username;
	
	@Column(nullable = false, length = 10)
	private String password;
	
	private Calendar dob;
	
	@Temporal(TemporalType.TIME)
	@Column(name="birth_time")
	private Date birthtime;

	@Column(precision = 2)
	private double cgpa;
	
	@Column(precision = 2)
	private float percentage;
	private byte section;
	private int marks;
	
	@Column(name = "has_ncc", nullable = false)
	private boolean hasNCC;
	
	
	@CreationTimestamp
	@Column(name = "create_date")
	private Calendar createdOn;

	@UpdateTimestamp
	@Column(name = "modify_date")
	private Date modifyDate;
}



```

---

### 4. HibernateUtil.java

#### src\main\java\com\suji\HibernateEx1\config\HibernateUtil.java

```java

package com.suji.HibernateEx1.config;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();

				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();

				sessionFactory = metaData.getSessionFactoryBuilder().build();
			}
			return sessionFactory;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
```

---

### 5. App.java

#### src\main\java\com\suji\HibernateEx1\main\App.java

```java

package com.suji.HibernateEx1.main;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.suji.HibernateEx1.config.HibernateUtil;
import com.suji.HibernateEx1.model.Student;

public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Student s = new Student("Sujith", LocalDate.of(1994, 9, 2), LocalTime.of(21, 30));
        Student s2 = session.get(Student.class, 2);
        
     
        System.out.println(s2);
        
     //   session.saveOrUpdate(s);
     
        
        transaction.commit();
    }
}

```

---

