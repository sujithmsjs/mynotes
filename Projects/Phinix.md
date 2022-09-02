# Phinix


### File Structure
```pre
+ Phinix\ 
	|---  pom.xml
	+ \src\main\java\com\suji\cookie
		|---  CookieDemo.java
	+ \src\main\java\com\suji\demo1
		|---  Login.java
		|---  SendRDDemo.java
		|---  Signup.java
		|---  Test.java
	+ \src\main\java\com\suji\util
		|---  Student.java
```
### Index
```pre
1. pom.xml
2. src\main\java\com\suji\cookie\CookieDemo.java
3. src\main\java\com\suji\demo1\Login.java
4. src\main\java\com\suji\demo1\SendRDDemo.java
5. src\main\java\com\suji\demo1\Signup.java
6. src\main\java\com\suji\demo1\Test.java
7. src\main\java\com\suji\util\Student.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.suji</groupId>
	<artifactId>Phinix</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>Phinix Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>

		<finalName>Phinix</finalName>

		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.3.1</version>
			</plugin>
		</plugins>

	</build>


</project>

```

---

### 2. CookieDemo.java

#### src\main\java\com\suji\cookie\CookieDemo.java

```java

package com.suji.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		Cookie ck = new Cookie("username",uname);
		response.addCookie(ck);
		
		response.sendRedirect("welcome.jsp");
	}
}

```

---

### 3. Login.java

#### src\main\java\com\suji\demo1\Login.java

```java

package com.suji.demo1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.util.Student;

public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		PrintWriter out = null;

		try {
			out	= response.getWriter();
			out.append("<h1>Sujith</h1>Served at: ").append(request.getContextPath());

			String uname = request.getParameter("uname");
			String pwd = request.getParameter("pwd");
			String encode = request.getCharacterEncoding();
			
			out.append("<h1> Encode: "+encode+"</h1>");

			boolean isAthenticated =false;
			if (uname.equalsIgnoreCase("admin") && pwd.equalsIgnoreCase("1234")) {
				isAthenticated = true;
				RequestDispatcher rd = request.getRequestDispatcher("dsipatcherTest.jsp");
				
				
				Student s1 = new Student("Jake",123);
				Student s2 = new Student("John",124);
				
				request.setAttribute("s1", s1);
				request.setAttribute("s2", s2);
				
				out.print("<h1>Before Include</h1>");
				rd.forward(request, response);
				out.print("<h1>After Include</h1>");
				
				Student s3 = new Student("Mark",203);
				Student s4 = new Student("Andrew",204);
				
				
				request.setAttribute("s3", s3);
				request.setAttribute("s4", s4);
				

			} else {

				;
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				out.print("<h1>Before Include</h1>");
				rd.include(request, response);
				out.print("<h1>After Include</h1>");
				out.print("<h1>Sorry UserName or Password Error!</h1>");
				
			}
			
			System.out.println("This is the END!!! "+isAthenticated);
		} catch (Exception e) {
			out.print("<h1>"+e+"</h1>");
		}
		
		out.close();
	}

}

```

---

### 4. SendRDDemo.java

#### src\main\java\com\suji\demo1\SendRDDemo.java

```java

package com.suji.demo1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendRDDemo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sec = request.getParameter("word");
		
		response.sendRedirect("https://www.oxfordlearnersdictionaries.com/definition/english/"+sec);
		//request.getRequestDispatcher("dsipatcherTest.jsp").forward(request, response);
		
		
	}
}

```

---

### 5. Signup.java

#### src\main\java\com\suji\demo1\Signup.java

```java

package com.suji.demo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.WriteAbortedException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

	private PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Initializing Instance variable
		out = response.getWriter();

		out.append("<h1>Sujith</h1>Served at: ").append(request.getContextPath());

		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		write("Uname: " + uname);
		write("Password: " + pwd);

		// Servlet Config
		ServletConfig sConfig = getServletConfig();
		String db = sConfig.getInitParameter("db");
		String ip = sConfig.getInitParameter("ip");
		String sname = sConfig.getServletName();
		write(db);
		write(ip);
		write(sname);
		
		// Servlet Context
		ServletContext context1 = request.getServletContext();
		ServletContext context2 = getServletContext();
		ServletContext context3 = sConfig.getServletContext();
		
		if(context1 == context2 && context2 == context3) {
			write("True");
		}
		try {
			//String attname = context1.getAttribute("myname").toString();
			String initname = context1.getInitParameter("myname");
			String initname2 = context1.getInitParameter("hey");
			write("Context1: "+context1);
			
			
		//	write("Attribute name: "+attname);
			write("Init name: "+initname);
			write("Init name2: "+initname2);
			
			System.out.println("This is working without error!");
			
		}catch(Exception e) {
			write(e.toString());
		}
		write("End");
		out.close();
	}

	private void write(String str) {
		out.println("<h1>" + str + "</h1>");
	}

}

```

---

### 6. Test.java

#### src\main\java\com\suji\demo1\Test.java

```java

package com.suji.demo1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suji.util.Student;


public class Test extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			
			RequestDispatcher dispatcher =	request.getRequestDispatcher("dsipatcherTest.jsp");
			
			Student s1 = new Student("Jake",123);
			Student s2 = new Student("John",124);
			
			request.setAttribute("s1", s1);
			request.setAttribute("s2", s2);
			
			dispatcher.forward(request, response);
			
			
			
			
		} catch (Exception e) {
			
		}
	}

}

```

---

### 7. Student.java

#### src\main\java\com\suji\util\Student.java

```java

package com.suji.util;

public class Student {
	
	private String name;
	private int id;
	
	
	
	public Student(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + "]";
	}
	
}

```

---

