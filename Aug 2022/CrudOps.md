# CrudOps


### File Structure
```pre
+ CrudOps\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  editStd.html
		|---  home.html
		|---  newStd.html
		|---  test.html
	+ \src\main\resources\static\styles
		|---  basicstyles.css
	+ \src\main\java\com\suji\crud
		|---  CrudOpsApplication.java
	+ \src\test\java\com\suji\crud
		|---  CrudOpsApplicationTests.java
	+ \src\main\java\com\suji\crud\ctrl
		|---  StudentCtrl.java
	+ \src\main\java\com\suji\crud\entity
		|---  Student.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\crud\CrudOpsApplication.java
3. src\main\java\com\suji\crud\ctrl\StudentCtrl.java
4. src\main\java\com\suji\crud\entity\Student.java
5. resources\templates\editStd.html
6. resources\templates\home.html
7. resources\templates\newStd.html
8. resources\templates\test.html
9. static\styles\basicstyles.css
10. src\test\java\com\suji\crud\CrudOpsApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.url=jdbc:mysql://localhost:3306/nitro
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

logging.level.org.springframework.context.config=DEBUG

```

---

### 2. CrudOpsApplication.java

#### src\main\java\com\suji\crud\CrudOpsApplication.java

```java

package com.suji.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.crud.entity.Student;

@SpringBootApplication
public class CrudOpsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOpsApplication.class, args);
		System.out.println("CurdObjs Project...");
		
		
	}

}

```

---

### 3. StudentCtrl.java

#### src\main\java\com\suji\crud\ctrl\StudentCtrl.java

```java

package com.suji.crud.ctrl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suji.crud.entity.Student;

@Controller
public class StudentCtrl {

	@RequestMapping("/home")
	public String home(Model model) {
		
		List<Student> stds = new ArrayList<>();
		stds.add(new Student(109, "Sujith", 9.4));
		stds.add(new Student(343, "Sumanth", 8.4));
		stds.add(new Student(234, "Anitha", 7.4));
		stds.add(new Student(267, "Shamala", 4.4));
		
		model.addAttribute("stds", stds);
		
		return "home";
	}
	
	@RequestMapping("/std/edit/{sno}")
	public String editStd(@PathVariable("sno") int sno, Model model) {
		
		//Retrive data from database
		Student std = new Student(943,"Johncena", 87.54);
		std.setSno(sno);
		
		//Adding new object to model
		model.addAttribute("std", std);
		
		return "editStd";
	}
	
	@RequestMapping("/atest")
	@ResponseBody
	public String checkTest(Model model,Student tudent) {
		Map<String, Object> map = model.asMap();
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			System.out.println("Key:"+string+" ; Value: "+model.getAttribute(string));
			
		}
		
		return "Check console";
		
	}
	
	
	@RequestMapping("/std/new")
	public String newStd(Model model) {
		
		//New Student object created.
		Student std = new Student();
		
		//Adding new object to model
		model.addAttribute("std", std);
		
		System.out.println("New empty student object has been created.");
		
	return "newStd";
	}
	
	@RequestMapping("/test")
	public String test() {
		System.out.println("In Test Controller...");
		return "test";
	}
	
	
	
	
	@RequestMapping("/std/delete/{sno}")
	public String delStd(@PathVariable("sno") int sno) {
		
		System.out.println("Student with sno:"+sno+" wil be deleted. Redirecting to home.");
		
		return "redirect:/home";
	}
	
	@RequestMapping("/std/save")
	public String saveStd(Student std) {
		
		System.out.println("Saving... std:"+std+" Redirecting to home.");
		
		return "redirect:/home";
	}
	
	
	
	
	
}

```

---

### 4. Student.java

#### src\main\java\com\suji\crud\entity\Student.java

```java

package com.suji.crud.entity;

public class Student {
	
	private int sno;
	private String name;
	private double cgpa;
	
	public Student() {
		
	}
	
	public Student(int sno, String name, double cgpa) {
		super();
		this.sno = sno;
		this.name = name;
		this.cgpa = cgpa;
	}
	
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String toString() {
		return "Student [sno=" + sno + ", name=" + name + ", cgpa=" + cgpa + "]";
	}
}

```

---

### 5. editStd.html

#### resources\templates\editStd.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Update Student</title>
	<meta charset="UTF-8">
	<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body bgcolor="orange">
	<div>
		<h1>Update new student</h1>
		
		<form th:object="${std}" th:action="@{/std/save}">
			<label for="fname">Sno:</label><br />
			<input type="text" th:field="*{sno}"><br />

			<label for="lname">Name:</label><br />
			<input type="text" th:field="*{name}"><br />

			<label for="lname">CGPA:</label><br />
			<input type="text" th:field="*{cgpa}"><br />
			<input type="submit" value="Signup">
		</form>

	</div>
</body>

</html>
```

---

### 6. home.html

#### resources\templates\home.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>
	<div>

		<a th:href="@{/std/new}">Create New Student</a>

		<table>
			<tr>
				<th>Sno</th>
				<th>Name</th>
				<th>CGPA</th>
				<th>Actions</th>
			</tr>

			<tr th:each="std : ${stds}">
				<td th:text="${std.sno}"></td>
				<td th:text="${std.name}"></td>
				<td th:text="${std.cgpa}"></td>

				<td><a th:href="@{/std/edit/{sno}(sno=${std.sno})}">Edit</a> <a
					th:href="@{/std/delete/{sno}(sno=${std.sno})}">Delete</a></td>

			</tr>
		</table>
	</div>
</body>

</html>
```

---

### 7. newStd.html

#### resources\templates\newStd.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Create New</title>
	<meta charset="UTF-8">
	<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body bgcolor="cyan">
	<div>
		<h1>Create new student</h1>
		<form th:object="${std}" th:action="@{/std/save}">

			<label for="fname">Sno:</label><br />
			<input type="text" th:field="*{sno}"><br />

			<label for="lname">Name:</label><br />
			<input type="text" th:field="*{name}"><br />

			<label for="lname">CGPA:</label><br />
			<input type="text" th:field="*{cgpa}"><br />

			<input type="submit" value="Signup">
		</form>

	</div>
</body>

</html>
```

---

### 8. test.html

#### resources\templates\test.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Test Page</title>
	<meta charset="UTF-8">
	<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>
	<h1>This is just testing page.</h1>
</body>
</html>
```

---

### 9. basicstyles.css

#### static\styles\basicstyles.css

```css

div, form, table {
	margin: auto;
	width: 60%;
	/*  border: 3px solid rgb(0, 255, 64);*/
	padding: 10px;
}

form {
	text-align: center;
}

a, h1, h2, p {
	text-align: center;
	display: block;
}

table, th, td {
	margin: 10px;
	padding: 10px;
	border: 2px solid black;
	border-collapse: collapse;
	margin-left: auto;
	margin-right: auto;
}
```

---

### 10. CrudOpsApplicationTests.java

#### src\test\java\com\suji\crud\CrudOpsApplicationTests.java

```java

package com.suji.crud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudOpsApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

