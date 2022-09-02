# JustWeb


### File Structure
```pre
+ JustWeb\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  edit.html
		|---  hai.html
		|---  hello.html
		|---  show.html
		|---  signup.html
		|---  table.html
		|---  time.html
	+ \src\main\java\com\suji\web
		|---  JustWebApplication.java
	+ \src\test\java\com\suji\web
		|---  JustWebApplicationTests.java
	+ \src\main\java\com\suji\web\ctrl
		|---  HiCtrl.java
	+ \src\main\java\com\suji\web\entity
		|---  Student.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\web\JustWebApplication.java
3. src\main\java\com\suji\web\ctrl\HiCtrl.java
4. src\main\java\com\suji\web\entity\Student.java
5. resources\templates\edit.html
6. resources\templates\hai.html
7. resources\templates\hello.html
8. resources\templates\show.html
9. resources\templates\signup.html
10. resources\templates\table.html
11. resources\templates\time.html
12. src\test\java\com\suji\web\JustWebApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties



```

---

### 2. JustWebApplication.java

#### src\main\java\com\suji\web\JustWebApplication.java

```java

package com.suji.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JustWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustWebApplication.class, args);
		System.out.println("This is just web project");
	}

}

```

---

### 3. HiCtrl.java

#### src\main\java\com\suji\web\ctrl\HiCtrl.java

```java

package com.suji.web.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suji.web.entity.Student;

@Controller
public class HiCtrl {

	// Student(String name, int sno, double cgpa)
	@RequestMapping(value = "/std/{name}/{sno}/{cgpa}", method = RequestMethod.GET)
	public String demo(Student std, Model model) {
		model.addAttribute("abc", std);
		return "show";
	}
	
	@RequestMapping(value = "/std/new", method = RequestMethod.GET)
	public String newStd(Student std, Model model) {
		
		model.addAttribute("std", new Student());
		
		return "signup";
	}
	
	@RequestMapping(value = "/std/save", method = RequestMethod.GET)
	public String saveStd(Student std) {
		System.out.println("Newly cread std: "+std);
		return "redirect:/data";
	}

	//The showRegistration Method

	@GetMapping("/user/registration")
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
	
	
	@RequestMapping("/data")
	public String table(Model model) {
		
		List<Student> stds = new ArrayList<Student>();
		stds.add(new Student("Sujith", 121, 9.2));
		stds.add(new Student("Vish", 300, 6.5));
		stds.add(new Student("Sumanth", 111, 6.2));
		stds.add(new Student("Vineeth", 400, 7.8));
		
		model.addAttribute("stds", stds);
		
		return "table";
	}
	
	@RequestMapping("/std/edit/{id}")
	public String eidt(@PathVariable("id") int id,Student std,Model model) {
		
		std.setSno(id);
		
		model.addAttribute("std", std);
		System.out.println("Trying to edit std: "+std+" & sno: "+id);
		
		return "edit";
	}
	
	@RequestMapping("/std/delete/{id}")
	public String del(@PathVariable("id") int id,Model model) {

		System.out.println("Are you trying to delete "+id+"?");
		
		return "redirect:/data";
	}
	
	

}

```

---

### 4. Student.java

#### src\main\java\com\suji\web\entity\Student.java

```java

package com.suji.web.entity;

public class Student {
	
	private String name;
	private int sno;
	private double cgpa;
	
	public Student(String name, int sno, double cgpa) {
		super();
		this.name = name;
		this.sno = sno;
		this.cgpa = cgpa;
	}
	
	
	
	
	
	public Student() {
		// TODO Auto-generated constructor stub
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public int getSno() {
		return sno;
	}





	public void setSno(int sno) {
		this.sno = sno;
	}





	public double getCgpa() {
		return cgpa;
	}





	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}





	@Override
	public String toString() {
		return "Student [name=" + name + ", sno=" + sno + ", cgpa=" + cgpa + "]";
	}
	
	
	
	
	
}

```

---

### 5. edit.html

#### resources\templates\edit.html

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Page Title</title>
</head>

<body bgcolor="orange">

	<h1>Update your data</h1>
	<form th:object="${std}" th:action="@{/std/save}">
		Sno: <input type="text" th:field="*{sno}" /> <br />
		Name: <input type="text" th:field="*{name}" /> <br />
		CGPA: <input type="text" th:field="*{cgpa}" /> <br />
		<input type="submit" value="Send" />
	</form>

</body>

</html>
```

---

### 6. hai.html

#### resources\templates\hai.html

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">  
<head>
<title>Page Title</title>
</head>
<body>

<h1>Hai, <span th:text="${abc.name}" /></h1>
<h1>Hai, <span th:text="${abc.sno}" /></h1>
<h1>Hai, <span th:text="${abc.cgpa}" /></h1>

</body>
</html>



```

---

### 7. hello.html

#### resources\templates\hello.html

```html

<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
</head>
<body>

<h1>Hello, Sujith</h1>
<p>This is a paragraph.</p>

</body>
</html>



```

---

### 8. show.html

#### resources\templates\show.html

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Page Title</title>
</head>

<body bgcolor="red">

	<form th:action="@{/edit/{sno}(sno=*{sno})}" th:object="${abc}" method="GET"> 
		<input type="text" th:field="*{name}" />
		<input type="text" th:field="*{sno}" />
		<input type="text" th:field="*{cgpa}" />
		<input type="submit" value="Send" />
	</form>
	
</body>

</html>
```

---

### 9. signup.html

#### resources\templates\signup.html

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">  
<head>
<title>Page Title</title>
</head>
<body bgcolor="red">

	<h1>Save your fresh data</h1>
	<form th:object="${std}" th:action="@{/std/save}">
		Sno: <input type="text" th:field="*{sno}" /> <br />
		Name: <input type="text" th:field="*{name}" /> <br />
		CGPA: <input type="text" th:field="*{cgpa}" /> <br />
		<input type="submit" value="Send" />
	</form>

</body>
</html>



```

---

### 10. table.html

#### resources\templates\table.html

```html

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<style>
		table,th,td{
			border: 1px solid black;
			border-collapse: collapse;
			padding: 15px;
		}
	</style>
</head>

<body>

	<a th:href="@{/std/new}">Add New</a>
	<table style="width:100%">
		<tr>
			<th>Sno</th>
			<th>Name</th>
			<th>CGPA</th>
			<th>Actions</th>
		</tr>
		<tr th:each="abc : ${stds}">
			<td th:text="${abc.sno}"></td>
			<td th:text="${abc.name}"></td>
			<td th:text="${abc.cgpa}"></td>
			<td>
				<a th:href="@{/std/edit/{id}(id=${abc.sno})}">Edit</a>
				<a th:href="@{/std/delete/{id}(id=${abc.sno})}">Delete</a>
			</td>
		</tr>
	</table>

</body>

</html>
```

---

### 11. time.html

#### resources\templates\time.html

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">  
<head>
<title>Page Title</title>
</head>
<body>

<h1>Hai, <span th:text="${time}" /></h1>


</body>
</html>



```

---

### 12. JustWebApplicationTests.java

#### src\test\java\com\suji\web\JustWebApplicationTests.java

```java

package com.suji.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JustWebApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

