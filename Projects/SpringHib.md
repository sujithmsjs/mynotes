# SpringHib


### File Structure
```pre
+ SpringHib\ 
	|---  pom.xml
	+ \src\main\resources
		|---  messages.properties
	+ \src\main\java\com\suji\SpringHib\model
		|---  AbstractGame.java
		|---  AbstractJoystick.java
		|---  Animal.java
		|---  Bird.java
		|---  Book.java
		|---  Box.java
		|---  Game.java
		|---  GameRunner.java
		|---  Joystick.java
		|---  Mario.java
		|---  Parrot.java
		|---  RedGear.java
		|---  Student.java
	+ \src\main\java\com\suji\SpringHib\app
		|---  App.java
	+ \src\main\java\com\suji\SpringHib\config
		|---  HibernateConfig.java
		|---  MyConfig.java
	+ \src\main\java\com\suji\SpringHib\mod
		|---  Mobile.java
		|---  Processor.java
		|---  Screen.java
```
### Index
```pre
1. messages.properties
2. pom.xml
3. model\AbstractGame.java
4. model\AbstractJoystick.java
5. model\Animal.java
6. model\Bird.java
7. model\Book.java
8. model\Box.java
9. model\Game.java
10. model\GameRunner.java
11. model\Joystick.java
12. model\Mario.java
13. model\Parrot.java
14. model\RedGear.java
15. model\Student.java
16. src\main\java\com\suji\SpringHib\app\App.java
17. src\main\java\com\suji\SpringHib\config\HibernateConfig.java
18. src\main\java\com\suji\SpringHib\config\MyConfig.java
19. src\main\java\com\suji\SpringHib\mod\Mobile.java
20. src\main\java\com\suji\SpringHib\mod\Processor.java
21. src\main\java\com\suji\SpringHib\mod\Screen.java

```

---

### 1. messages.properties

#### messages.properties

```properties

name=Sujith

```

---

### 2. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.suji</groupId>
	<artifactId>SpringHib</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>SpringHib</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	</properties>

	<dependencies>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.3.14</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.16</version>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.22</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.6.4.Final</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>5.1.6.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.apache.tomcat</groupId>
			<artifactId>tomcat-dbcp</artifactId>
			<version>9.0.1</version>
		</dependency>

		<dependency>
			<groupId>org.hibernate.validator</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>6.0.13.Final</version>
		</dependency>





	</dependencies>
</project>

```

---

### 3. AbstractGame.java

#### model\AbstractGame.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public abstract class AbstractGame implements Game {
	@Id
	private int id;
	private String name;
	private double duration;
}

```

---

### 4. AbstractJoystick.java

#### model\AbstractJoystick.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public abstract class AbstractJoystick implements Joystick {

	@Id
	private  int id;
	private String name;
}

```

---

### 5. Animal.java

#### model\Animal.java

```java

package com.suji.SpringHib.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="Animal")  
public class Animal {
	@Id
	private int id;
	private int legs;
	private String name;
	private double speed;
}

```

---

### 6. Bird.java

#### model\Bird.java

```java

package com.suji.SpringHib.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@DiscriminatorValue(value="Bird")  
public class Bird extends Animal {
	
	private int wings;
}

```

---

### 7. Book.java

#### model\Book.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String title;
	
	@ManyToOne()
	private Student student;
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + "]";
	}
}

```

---

### 8. Box.java

#### model\Box.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Box {
	@Id
	private int id;
	@NotNull
	@Size(min = 4)
	private String name;
}

```

---

### 9. Game.java

#### model\Game.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

public interface Game {
	
	String getName();
	double getDuration();
}

```

---

### 10. GameRunner.java

#### model\GameRunner.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component

@Entity
public class GameRunner {
	
	@Id
	private int id;
	
	private String name;
	
	@Autowired
	@OneToOne
	private AbstractGame game;
	
	@Autowired
	@OneToOne
	private AbstractJoystick stick;
	
	public void play() {
		stick.playGame(game);
	}
	
}

```

---

### 11. Joystick.java

#### model\Joystick.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Entity;

public interface Joystick {
	
	public String getName();
	public void playGame(Game game);
}

```

---

### 12. Mario.java

#### model\Mario.java

```java

package com.suji.SpringHib.model;

import org.springframework.stereotype.Component;

@Component
public class Mario implements Game {

	public String getName() {
		return "Mario";
	}

	public double getDuration() {
		return 4.5;
	}

}

```

---

### 13. Parrot.java

#### model\Parrot.java

```java

package com.suji.SpringHib.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;


@Data
@DiscriminatorValue(value="Parrot")  
public class Parrot extends Bird {
	@Column(name = "beak_color")
	private String beakColor;

}

```

---

### 14. RedGear.java

#### model\RedGear.java

```java

package com.suji.SpringHib.model;

import org.springframework.stereotype.Component;

@Component
public class RedGear implements Joystick {

	public void playGame(Game game) {
		System.out.println(game.getName()+" is playing now in "+getName()+". will be stopped after "+game.getDuration());
	}

	public String getName() {
		return "RedGear";
	}

}

```

---

### 15. Student.java

#### model\Student.java

```java

package com.suji.SpringHib.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "student")
	private List<Book> books = new ArrayList<>();
}





```

---

### 16. App.java

#### src\main\java\com\suji\SpringHib\app\App.java

```java

package com.suji.SpringHib.app;

import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.suji.SpringHib.config.HibernateConfig;
import com.suji.SpringHib.config.MyConfig;
import com.suji.SpringHib.mod.Mobile;
import com.suji.SpringHib.model.Bird;
import com.suji.SpringHib.model.Book;
import com.suji.SpringHib.model.Box;
import com.suji.SpringHib.model.Student;


public class App 
{

	
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
    
    	String[] definitionNames = context.getBeanDefinitionNames();
    	System.out.println("Bean Defination Names:");
    	for (String string : definitionNames) {
			System.out.println(string);
		}
    	System.out.println("======================");

    	Mobile bean = context.getBean(Mobile.class);
    	bean.run();
    	
    	
    	
		
 
    }
}

```

---

### 17. HibernateConfig.java

#### src\main\java\com\suji\SpringHib\config\HibernateConfig.java

```java

package com.suji.SpringHib.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.suji.SpringHib.model" );
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/nitro");
		dataSource.setUsername("root");
		dataSource.setPassword("apple");
	
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		
		return hibernateProperties;
	}
	

//	@Bean()
//	public HibernateTemplate hibernateTemplate(){
//		SessionFactory factory = sessionFactory().getObject();
//		
//		HibernateTemplate hibernateTemplate = new HibernateTemplate();
//		hibernateTemplate.setSessionFactory(factory);
//		return hibernateTemplate;
//	}
	
}


```

---

### 18. MyConfig.java

#### src\main\java\com\suji\SpringHib\config\MyConfig.java

```java

package com.suji.SpringHib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.suji.SpringHib.mod.Processor;
import com.suji.SpringHib.mod.Screen;

@Configuration
@ComponentScan(basePackages = "com.suji.SpringHib.mod")
public class MyConfig {
	
	@Bean(name = {"scr1","scr2","scr3"})
	public Screen screen() {
		Screen screen = new Screen() {
			@Override
			public String getName() {
				return "Samsung Screen";
			}
		};
		return screen;
	}
	
	@Bean(name = "pro")
	public Processor processor() {
		Processor pro = new Processor() {
			@Override
			public void compile(Screen mobile) {
				System.out.println(mobile.getName()+" will be turned on soon.");
			}
		};
		return pro;
	}
	
	
	
	
	
	
}

```

---

### 19. Mobile.java

#### src\main\java\com\suji\SpringHib\mod\Mobile.java

```java

package com.suji.SpringHib.mod;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mobile {
	@Autowired
	Processor pro;
	@Autowired
	Screen screen;
	

	public void run() {
		pro.compile(screen);
	}
	
	
}

```

---

### 20. Processor.java

#### src\main\java\com\suji\SpringHib\mod\Processor.java

```java

package com.suji.SpringHib.mod;

public interface Processor {
	public void compile(Screen mobile);
}

```

---

### 21. Screen.java

#### src\main\java\com\suji\SpringHib\mod\Screen.java

```java

package com.suji.SpringHib.mod;

public interface Screen {
	public String getName();
}

```

---

