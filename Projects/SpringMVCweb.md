# SpringMVCweb


### File Structure
```pre
+ SpringMVCweb\ 
	|---  pom.xml
	+ \src\main\java\com\suji\ctrl
		|---  AddCtrl.java
		|---  AddServlet.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\ctrl\AddCtrl.java
3. src\main\java\com\suji\ctrl\AddServlet.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.suji</groupId>
	<artifactId>SpringMVCweb</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>SpringMVCweb Maven Webapp</name>
	<url>http://maven.apache.org</url>


	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>


	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.3.14</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>5.3.14</version>
		</dependency>

	


	</dependencies>


	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.3.1</version>
			</plugin>
		</plugins>

		<finalName>SpringMVCweb</finalName>
	</build>
</project>
```

---

### 2. AddCtrl.java

#### src\main\java\com\suji\ctrl\AddCtrl.java

```java

package com.suji.ctrl;

import org.springframework.stereotype.Controller;

@Controller
public class AddCtrl {
	public void add() {
		System.out.println("it's adding...");
		
	}
}

```

---

### 3. AddServlet.java

#### src\main\java\com\suji\ctrl\AddServlet.java

```java

package com.suji.ctrl;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

```

---

