# OneToMany


### File Structure
```pre
+ OneToMany\ 
	|---  pom.xml
	+ \src\main\java\com\suji\acc
		|---  Acc.java
		|---  AccApp.java
		|---  User.java
	+ \src\main\java\com\suji\conn
		|---  HibernateUtil.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\acc\Acc.java
3. src\main\java\com\suji\acc\AccApp.java
4. src\main\java\com\suji\acc\User.java
5. src\main\java\com\suji\conn\HibernateUtil.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.suji</groupId>
	<artifactId>Heb1</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>Heb1</name>
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

### 2. Acc.java

#### src\main\java\com\suji\acc\Acc.java

```java

package com.suji.acc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Acc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@ManyToOne
	private User user;
	
	
	//Constructors.
	public Acc() {}
	public Acc(String name) {
		this.name = name;
	}
	public Acc(String name,User user) {
		this.name = name;
		this.user = user;
	}
	
	//Setters and Getters
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
	public User getUser() {
		return user;
	}
	public void setUser(User users) {
		this.user = users;
	}
	
	@Override
	public String toString() {
		return "Acc [id=" + id + ", name=" + name + "]";
	}
}

```

---

### 3. AccApp.java

#### src\main\java\com\suji\acc\AccApp.java

```java

package com.suji.acc;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.suji.conn.HibernateUtil;

public class AccApp {
	
	public static void main(String[] args) {

		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();
		
		User u = new User("Sujith");
		Acc a1 = new Acc("HDFC", u);
		Acc a2 = new Acc("CITY", u);
		Acc a3 = new Acc("DBS");
		
		
		List<Acc> accs = new ArrayList<Acc>();
		accs.add(a1);
		accs.add(a2);
		accs.add(a3);
		
		u.setAcc(accs);
		System.out.println("===============================BEFORE SAVE==============================");
		session.save(u);
		System.out.println("=============================== AFTER SAVE ==============================");
		trx.commit();

		System.out.println("End of Code");
	}
}

```

---

### 4. User.java

#### src\main\java\com\suji\acc\User.java

```java

package com.suji.acc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	// @GeneratedValue(strategy = GenerationType.AUTO)

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER,
			mappedBy = "user",
			orphanRemoval = true)
	private List<Acc> acc = new ArrayList<Acc>();


	public User() {
	}

	public User(String name, List<Acc> acc) {
		this.name = name;
		this.acc = acc;
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public List<Acc> getAcc() {
		return acc;
	}
	public void setAcc(List<Acc> acc) {
		this.acc = acc;
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
		return "User [id=" + id + ", name=" + name + ", acc=" + acc + "]";
	}
}

```

---

### 5. HibernateUtil.java

#### src\main\java\com\suji\conn\HibernateUtil.java

```java

package com.suji.conn;

import org.hibernate.Session;
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
	
	public static Session openSession() {
		return sessionFactory.openSession();
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
```

---

