# AbstractWebApp


### File Structure
```pre
+ AbstractWebApp\ 
	|---  pom.xml
	+ \src\main\java\com\suji\service
		|---  AddService.java
	+ \src\main\java\com\suji\config
		|---  MyWebInitializer.java
		|---  WebConfig.java
	+ \src\main\java\com\suji\ctrl
		|---  AddCtrl.java
```
### Index
```pre
1. pom.xml
2. service\AddService.java
3. src\main\java\com\suji\config\MyWebInitializer.java
4. src\main\java\com\suji\config\WebConfig.java
5. src\main\java\com\suji\ctrl\AddCtrl.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.suji</groupId>
  <artifactId>WebApp</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>WebApp Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
  
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    
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
		<finalName>WebApp</finalName>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.3.1</version>
			</plugin>
		</plugins>
	</build>
	
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>
</project>

```

---

### 2. AddService.java

#### service\AddService.java

```java

package com.suji.service;

public class AddService {
	public static int add(int n, int m) {
		return n+m;
	}
}

```

---

### 3. MyWebInitializer.java

#### src\main\java\com\suji\config\MyWebInitializer.java

```java

package com.suji.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("getRootConfigClasses");
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("getServletConfigClasses");
		return new Class[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("getServletMappings");
		return new String[] {"/"};
	}

}

```

---

### 4. WebConfig.java

#### src\main\java\com\suji\config\WebConfig.java

```java

package com.suji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan({ "com.suji.ctrl" })
public class WebConfig {
	
		public WebConfig() {
			System.out.println("WebConfig");
		}
	
	  @Bean 
	  public InternalResourceViewResolver viewResolver() {
	  InternalResourceViewResolver vr = new InternalResourceViewResolver();
	  // vr.setPrefix("./webapp/");
	  vr.setSuffix(".jsp");
	  System.out.println("viewResolver");
	  return vr;
	  }
}

```

---

### 5. AddCtrl.java

#### src\main\java\com\suji\ctrl\AddCtrl.java

```java

package com.suji.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suji.service.AddService;

@Controller
public class AddCtrl {
	
	@RequestMapping("/hello")
	public ModelAndView add(@RequestParam("num1")int n1,@RequestParam("num2") int n2,HttpServletRequest req, HttpServletResponse res) {

		
		int ans = AddService.add(n1, n2);
		System.out.println("Ans is "+ans);
		//ModelAndView mv = new ModelAndView("display.jsp");
		ModelAndView mv = new ModelAndView("display");
		mv.addObject("ans",ans);
		System.out.println(mv);
		return mv;
		
	}
	
	@RequestMapping("/hello3")
	public ModelAndView add3(HttpServletRequest req, HttpServletResponse res) {

		int n1 =Integer.parseInt(req.getParameter("num1"));
		int n2 =Integer.parseInt(req.getParameter("num2"));
		
		int ans = AddService.add(n1, n2);
		System.out.println("Ans is "+ans);
		//ModelAndView mv = new ModelAndView("display.jsp");
		ModelAndView mv = new ModelAndView("display");
		mv.addObject("ans",ans);
		System.out.println(mv);
		return mv;
		
	}
	
	
	@RequestMapping("/hello2")
	public String add2() {

		return "display.jsp";
	}
}

```

---

