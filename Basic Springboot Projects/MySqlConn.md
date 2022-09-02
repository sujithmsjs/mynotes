# MySqlConn


### File Structure
```pre
+ MySqlConn\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  editStd.html
		|---  home.html
		|---  msg.html
		|---  newStd.html
		|---  test.html
	+ \src\main\resources\static\styles
		|---  basicstyles.css
	+ \src\main\java\com\suji\sqlconn
		|---  MySqlConnApplication.java
	+ \src\test\java\com\suji\sqlconn
		|---  MySqlConnApplicationTests.java
	+ \src\main\java\com\suji\sqlconn\service
		|---  StudentService.java
	+ \src\main\java\com\suji\sqlconn\ctrl
		|---  StudentController.java
		|---  StudentCtrl.java
	+ \src\main\java\com\suji\sqlconn\dao
		|---  StudentDao.java
		|---  TestDemo.java
	+ \src\main\java\com\suji\sqlconn\entity
		|---  Student.java
	+ \src\main\java\com\suji\sqlconn\submain
		|---  TestDemo2.java
	+ \src\main\java\com\suji\sqlconn\service\impl
		|---  StudentServiceImpl.java
```
### Index
```pre
1. application.properties
2. service\StudentService.java
3. src\main\java\com\suji\sqlconn\MySqlConnApplication.java
4. src\main\java\com\suji\sqlconn\ctrl\StudentController.java
5. src\main\java\com\suji\sqlconn\ctrl\StudentCtrl.java
6. src\main\java\com\suji\sqlconn\dao\StudentDao.java
7. src\main\java\com\suji\sqlconn\dao\TestDemo.java
8. src\main\java\com\suji\sqlconn\entity\Student.java
9. src\main\java\com\suji\sqlconn\service\impl\StudentServiceImpl.java
10. src\main\java\com\suji\sqlconn\submain\TestDemo2.java
11. resources\templates\editStd.html
12. resources\templates\home.html
13. resources\templates\msg.html
14. resources\templates\newStd.html
15. resources\templates\test.html
16. static\styles\basicstyles.css
17. src\test\java\com\suji\sqlconn\MySqlConnApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties







spring.datasource.url=jdbc:mysql://localhost:3306/nitro
spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql: true

logging.level.org.springframework.context=DEBUG


```

---

### 2. StudentService.java

#### service\StudentService.java

```java

package com.suji.sqlconn.service;

import java.util.List;

import com.suji.sqlconn.entity.Student;

public interface StudentService {
	public List<Student> getAllStudents();
	public boolean deleteStudentById(int id);
	public Student findStudentById(int id);
	public boolean updateStudent(Student std);
	public boolean saveStudent(Student std);
}

```

---

### 3. MySqlConnApplication.java

#### src\main\java\com\suji\sqlconn\MySqlConnApplication.java

```java

package com.suji.sqlconn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.suji.sqlconn.dao.StudentDao;
import com.suji.sqlconn.dao.TestDemo;
import com.suji.sqlconn.entity.Student;
import com.suji.sqlconn.submain.TestDemo2;

@SpringBootApplication

public class MySqlConnApplication implements ApplicationRunner {
	
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MySqlConnApplication.class, args);
		String[] names = context.getBeanDefinitionNames();
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
		System.out.println("--------------");
		
		 StudentDao dao = context.getBean(StudentDao.class);
		 System.out.println("TestDemo "+dao);	
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	}

}

```

---

### 4. StudentController.java

#### src\main\java\com\suji\sqlconn\ctrl\StudentController.java

```java

package com.suji.sqlconn.ctrl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suji.sqlconn.entity.Student;
import com.suji.sqlconn.service.StudentService;

@Controller
@RequestMapping("std")
public class StudentController {

	private StudentService service;

	public StudentController(StudentService service) {
		super();
		this.service = service;
	}
	
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Student> getAll() {
		List<Student> list = service.getAllStudents();
		return list;
	}
	
	@RequestMapping("/save/{name}/{cgpa}")
	@ResponseBody
	public Student save(@PathVariable("name") String name, @PathVariable("cgpa") double cgpa, Model model) {
		
		System.out.println("Request Received...");
	
		Student std  = new Student(name,cgpa);
		
		service.saveStudent(std);
		return std;
		
	}
	
	@RequestMapping("/create/{sno}/{name}/{cgpa}")
	@ResponseBody
	public String getStd(Student std, Model model) {
		
		System.out.println("Request Received...");
	
		boolean isSaved = service.saveStudent(std);
		
		return "isSaved?"+isSaved+"; "+std;
	}
	
	@RequestMapping("/update/{sno}/{name}/{cgpa}")
	@ResponseBody
	public String update(Student std, Model model) {
		
		boolean isUpdated = service.saveStudent(std);
		
		return "isUpdated?"+isUpdated+"; "+service.findStudentById(std.getSno()).toString();
	}
	
	@RequestMapping("/delete/{sno}")
	@ResponseBody
	public String delete(@PathVariable("sno") int sno, Model model) {
		
		Student std = service.findStudentById(sno);
		boolean isDeleted = service.deleteStudentById(sno);
		Student student = new Student(std.getName(),std.getCgpa());
		
		return "isDeleted?"+isDeleted+"; "+student.toString();
	}
	
	@RequestMapping("/read/{sno}")
	@ResponseBody
	public Student read(@PathVariable("sno") int sno, Model model) {
		
		Student student = service.findStudentById(sno);
		
		return student;
	}

}

```

---

### 5. StudentCtrl.java

#### src\main\java\com\suji\sqlconn\ctrl\StudentCtrl.java

```java

package com.suji.sqlconn.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suji.sqlconn.entity.Student;
import com.suji.sqlconn.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentCtrl {

	private StudentService service;

	public StudentCtrl(StudentService service) {
		this.service = service;
	}

	@RequestMapping("/home")
	public String home(Model model) {

		List<Student> stds = service.getAllStudents();

		model.addAttribute("stds", stds);

		return "home";
	}

	@RequestMapping("/edit/{sno}")
	public String editStd(@PathVariable("sno") int sno, Model model) {

		// Retrive data from database
		Student std = service.findStudentById(sno);
		
		// Adding new object to model
		model.addAttribute("std", std);

		return "editStd";
	}

	@RequestMapping("/new")
	public String newStd(Model model) {

		// New Student object created.
		Student std = new Student();

		// Adding new object to model
		model.addAttribute("std", std);

		System.out.println("New empty student object has been created.");

		return "newStd";
	}



	@RequestMapping("/delete/{sno}")
	public String delStd(@PathVariable("sno") int sno) {

		service.deleteStudentById(sno);

		return "redirect:/student/home";
	}

	@RequestMapping("/save")
	public String saveStd(Student std) {

		service.saveStudent(std);

		return "redirect:/student/home";
	}

}

```

---

### 6. StudentDao.java

#### src\main\java\com\suji\sqlconn\dao\StudentDao.java

```java

package com.suji.sqlconn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suji.sqlconn.entity.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer>{

}

```

---

### 7. TestDemo.java

#### src\main\java\com\suji\sqlconn\dao\TestDemo.java

```java

package com.suji.sqlconn.dao;

import org.springframework.stereotype.Component;

@Component
public class TestDemo {
	public void show() {
		System.out.println("In TestDemo class...");
	}
}

```

---

### 8. Student.java

#### src\main\java\com\suji\sqlconn\entity\Student.java

```java

package com.suji.sqlconn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sno;
	@Column(unique = true)
	private String name;
	private double cgpa;
	
	public Student() {
		super();
	}
	
	public Student(String name, double cgpa) {
		super();
		this.name = name;
		this.cgpa = cgpa;
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
	@Override
	public String toString() {
		return "Student [sno=" + sno + ", name=" + name + ", cgpa=" + cgpa + "]";
	}
	
	
	
	
}

```

---

### 9. StudentServiceImpl.java

#### src\main\java\com\suji\sqlconn\service\impl\StudentServiceImpl.java

```java

package com.suji.sqlconn.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.sqlconn.dao.StudentDao;
import com.suji.sqlconn.entity.Student;
import com.suji.sqlconn.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	
	private StudentDao dao;

	public StudentServiceImpl(StudentDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<Student> getAllStudents() {
		
		return dao.findAll();
	}

	@Override
	public boolean deleteStudentById(int id) {
		boolean isSaved = false;
		try {
			dao.deleteById(id);
			isSaved= true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return isSaved;
	}

	@Override
	public Student findStudentById(int id) {
		return dao.findById(id).orElse(new Student());
	}

	@Override
	public boolean updateStudent(Student std) {
		 dao.save(std);
		 return true;
	}

	@Override
	public boolean saveStudent(Student std) {
			boolean isSaved = true;
			try {
				dao.save(std);
			} catch (Exception e) {
				isSaved = false;
				System.err.println(e.getMessage());
			}
		
		
		return isSaved;
	}

}

```

---

### 10. TestDemo2.java

#### src\main\java\com\suji\sqlconn\submain\TestDemo2.java

```java

package com.suji.sqlconn.submain;

import org.springframework.stereotype.Component;

@Component
public class TestDemo2 {
	public void show() {
		System.out.println("In TestDemo2....");
	}
}

```

---

### 11. editStd.html

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
		<form th:object="${std}" th:action="@{/student/save}">

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

### 12. home.html

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

		<a th:href="@{/student/new}">Create New Student</a>

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

				<td>
					<a th:href="@{/student/edit/{sno}(sno=${std.sno})}">Edit</a>
					<a th:href="@{/student/delete/{sno}(sno=${std.sno})}">Delete</a>
				</td>

			</tr>
		</table>
	</div>
</body>

</html>
```

---

### 13. msg.html

#### resources\templates\msg.html

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
		<h2 th:value="${msg}"></h2>
	</div>
</body>

</html>
```

---

### 14. newStd.html

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
		<form th:object="${std}" th:action="@{/student/save}">

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

### 15. test.html

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

### 16. basicstyles.css

#### static\styles\basicstyles.css

```css

div,form,table {
  margin: auto;
  width: 60%;
/*  border: 3px solid rgb(0, 255, 64);*/
  padding: 10px;
  
}

form{
	text-align: center;
}


a,h1,h2,p{
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

### 17. MySqlConnApplicationTests.java

#### src\test\java\com\suji\sqlconn\MySqlConnApplicationTests.java

```java

package com.suji.sqlconn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySqlConnApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

