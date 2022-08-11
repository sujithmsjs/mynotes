# ThymeLeafTest


### File Structure
```pre
+ ThymeLeafTest\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  arithmetics.html
		|---  bgColor.html
		|---  collView.html
		|---  color.html
		|---  home.html
		|---  ifColl.html
		|---  iftest.html
		|---  listView.html
		|---  show.html
	+ \src\main\resources\templates\student
		|---  list.html
	+ \src\main\resources\templates\tron
		|---  home2.html
	+ \src\main\resources\static\styles
		|---  basicstyles.css
	+ \src\main\java\com\suji\thyme
		|---  ThymeLeafTestApplication.java
	+ \src\test\java\com\suji\thyme
		|---  ThymeLeafTestApplicationTests.java
	+ \src\main\java\com\suji\thyme\model
		|---  Book.java
		|---  Cat.java
		|---  Student.java
	+ \src\main\java\com\suji\thyme\controller
		|---  BookController.java
		|---  MyController.java
		|---  StudentController.java
	+ \src\main\java\com\suji\thyme\service
		|---  StudentService.java
	+ \src\main\java\com\suji\thyme\util
		|---  ColorUtil.java
```
### Index
```pre
1. application.properties
2. model\Book.java
3. model\Cat.java
4. model\Student.java
5. controller\BookController.java
6. controller\MyController.java
7. controller\StudentController.java
8. service\StudentService.java
9. src\main\java\com\suji\thyme\ThymeLeafTestApplication.java
10. src\main\java\com\suji\thyme\util\ColorUtil.java
11. resources\templates\arithmetics.html
12. resources\templates\bgColor.html
13. resources\templates\collView.html
14. resources\templates\color.html
15. resources\templates\home.html
16. resources\templates\ifColl.html
17. resources\templates\iftest.html
18. resources\templates\listView.html
19. resources\templates\show.html
20. resources\templates\student\list.html
21. resources\templates\tron\home2.html
22. static\styles\basicstyles.css
23. src\test\java\com\suji\thyme\ThymeLeafTestApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

props.msg=Bienvenido a nuestra tienda de comestibles!

```

---

### 2. Book.java

#### model\Book.java

```java

package com.suji.thyme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private int isbn;
	private String title;
	private String author;
	private String type;
	private int release;
	
	public Book(int isbn, String title, String author, String type) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.type = type;
	}

}

```

---

### 3. Cat.java

#### model\Cat.java

```java

package com.suji.thyme.model;

public class Cat {
	public String getThatBlackCat() {
		return "A pure black cat has been sent";
	}
}

```

---

### 4. Student.java

#### model\Student.java

```java

package com.suji.thyme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	private int rollno;
	private String name;
	private int marks;
	private double cgpa;
	private boolean isPassed;
	private String gender;
	// rollno, name, marks, cgpa, isPassed, gender
	
	
}

```

---

### 5. BookController.java

#### controller\BookController.java

```java

package com.suji.thyme.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suji.thyme.model.Book;
import com.suji.thyme.model.Cat;
import com.suji.thyme.util.ColorUtil;

@Controller
@RequestMapping("/book")
public class BookController {
	
	
	@GetMapping("/show/{isbn}/{title}/{color}")
	public String show(@PathVariable("isbn") int isbn,@PathVariable("title") String title,@PathVariable("color") String color,Model model) {
		
		System.out.println("Color: "+color);
		
		model.addAttribute("bg", color);
		model.addAttribute("title", title);
		model.addAttribute("isbn",isbn);
		
		return "/show";
	}
	
	@GetMapping("/bg")
	public String bgStyle(Model model) {
		model.addAttribute("bg", ColorUtil.getRandomColorRBG());
		return "/bgColor";
	}
	
	
	
	@GetMapping("/arith")
	public String arith(Model model) {
		
		model.addAttribute("bg", ColorUtil.getRandomColorRBG());
		return "/arithmetics";
	}
	
	
	
	@GetMapping("ifTest")
	public String ifText(Model model) {
		
		model.addAttribute("name","Sujith");
		model.addAttribute("book",new Book(1,"Waterflow","Sujith","War"));	
		model.addAttribute("msg","hey");
		model.addAttribute("num",1);
		model.addAttribute("isGril",true);
		model.addAttribute("obj",null);
		model.addAttribute("arr",new String[] {"A","B","C"});
		
		Collection<String> coll = new ArrayList<>();
		coll.add("Vanitha");
		coll.add("Amani");
		coll.add("Lavana");
		coll.add("Kokila");
		
		model.addAttribute("coll", coll);
		
		
		
		return "iftest";
	}
	
	
	@GetMapping("/coll")
	public String getSet(Model model) {
		printModel(model);
		Random r = new Random();
		
		Collection<Book> coll = new HashSet<>();
		for (int i = 0; i < 20; i++) {
			Book b = new Book(i, "Book "+i, "Auth "+i,"edu",r.nextInt(2));
			coll.add(b);
		}
		model.addAttribute("coll", coll);
		model.addAttribute("bg", "red");
		return "ifColl";
	}
	
	@GetMapping("/getcolor")
	public String getColor(Model model) {
		
		Cat cat = new Cat();
		ColorUtil cu = new ColorUtil();
		
		List<Book> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Book b = new Book(i, "Book "+i, "Author "+i,"edu "+i);
			list.add(b);
		}
		
		model.addAttribute("bookList", list);
		model.addAttribute("cat", cat);
		model.addAttribute("colorUtil", cu);
		return "color";
	}

	@GetMapping("/all")
	public ModelAndView getAll() {
		ModelAndView mav = new ModelAndView();
		List<Book> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Book b = new Book(1, "Atomic Habits", "James clear","edu");
			list.add(b);
		}
		
		Book b = new Book(1, "Atomic Habits", "James clear","edu");
		mav.setViewName("listView");
		mav.addObject("booksList", list);
		mav.addObject("book", b);
		return mav;
	}

	@GetMapping("/{id}")
	public String getOne(@PathVariable int id, Model model) {
		printModel(model);
		Book b = new Book(id, "Atomic Habits", "James clear","edu");
		
		model.addAttribute("book", b);
		model.addAttribute("msg", "This is test message.");
		
		
		return "home";
	}
	
	public void printModel(Model model) {
		Map<String,Object> map = model.asMap();
		Set<Entry<String,Object>> set = map.entrySet();
		for (Entry<String, Object> entry : set) {
			System.out.println("Key: "+entry.getKey()+"; Value: "+entry.getValue());
		}
		
	}

}

```

---

### 6. MyController.java

#### controller\MyController.java

```java

package com.suji.thyme.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suji.thyme.model.Book;

@Controller
public class MyController {

	@RequestMapping("/home")
	public String getHome(Book book) {
		return "tron/home";
	}

	@RequestMapping("/view")
	public String collView(Model model) {
		
		List<Book> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Book b = new Book(i, "Book "+i, "Author "+i,"edu "+i);
			list.add(b);
		}
		
	
		
		model.addAttribute("coll",list);
		
		return "collView";
	}
	
	
}

```

---

### 7. StudentController.java

#### controller\StudentController.java

```java

package com.suji.thyme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suji.thyme.model.Student;
import com.suji.thyme.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Student> allStudents = studentService.getAllStudents();
		System.out.println(allStudents);
		model.addAttribute("students", allStudents);
		
		return "student/list";
	}
}

```

---

### 8. StudentService.java

#### service\StudentService.java

```java

package com.suji.thyme.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.suji.thyme.model.Student;

@Service
public class StudentService {
	public List<Student> getAllStudents(){
		List<Student> list = new ArrayList<>();
		
		//Create dummy students
		Student s1 = new Student(1,"Leya",73,7.6,false,"female");
		Student s2 = new Student(2,"David",83,8.9,true,"male");
		Student s3 = new Student(3,"Mary",99,3.4,true,"female");
		Student s4 = new Student(4,"John",45,4.6,false,"male");
		
		//Adding all studnets to List
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		
		return list;
	}
}

```

---

### 9. ThymeLeafTestApplication.java

#### src\main\java\com\suji\thyme\ThymeLeafTestApplication.java

```java

package com.suji.thyme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeLeafTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeLeafTestApplication.class, args);
		System.out.println("ThymeLeafTestApplication Project");
	}

}

```

---

### 10. ColorUtil.java

#### src\main\java\com\suji\thyme\util\ColorUtil.java

```java

package com.suji.thyme.util;

import java.awt.Color;
import java.util.Random;

public class ColorUtil {
	
	public static Color getRandomColor() {
		Random r = new Random();
		float noOfColors= 50;
		int colorNo = r.nextInt(50);
		int colorCode = Color.HSBtoRGB((1/noOfColors)*colorNo, 1, 1);
		Color color = new Color(colorCode);
		
		return color;
	}
	
	public static String getColorRBG(Color color) {
		String val = "rgb("+color.getRed()+","+color.getGreen()+","+color.getBlue()+")";
		return val;
	}
	
	public static String getRandomColorRBG() {
		return getColorRBG(getRandomColor());
	}
}

```

---

### 11. arithmetics.html

#### resources\templates\arithmetics.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>
	<h1 th:text=" ' Answer is: ' + (5+6) + 'Only'" ></h1>
	<h1 th:text="12341+22234" ></h1>
	<h1 th:text="5*6" ></h1>
	<h1 th:text="1/50" ></h1>
	<h1 th:text="10%6" ></h1>
	<h1 th:text="10%2" ></h1>
	<h1 th:text=" '(10%2==2-2) '+(10%2==2-2)" ></h1>
	<h1 th:text="10%6==8-4" ></h1>
	<h1 th:text="10%2" ></h1>
	<h1 th:text="10%3" ></h1>
	<h1 th:text="10/2" ></h1>
	<h1 th:text="2%10" ></h1>
	<h1 th:text="5%10" ></h1>
	
	<h1 th:text="10/5" ></h1>
	<h1 th:text="100%5==0" ></h1>
	<h1 th:text="'Hey therer'+'How are you Man!'" ></h1>
</body>

</html>
```

---

### 12. bgColor.html

#### resources\templates\bgColor.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body th:styleappend=" 'background-color:' + ${bg} ">




</body>

</html>
```

---

### 13. collView.html

#### resources\templates\collView.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>

	<h1 th:text=" 'coll.toString ' + ${coll.toString}"></h1>
	<h1 th:text="'coll: ' + ${coll}"></h1>

	<div th:styleappend="'background-color:' + ColorUtil.randomColorRBG" th:each="book : ${coll}">
		<h1 th:text="${book.title}"></h1>
		<h3 th:text="${book.isbn}"></h3>
		<h3 th:text="${book.author}"></h3>
		<h3 th:text="${book.type}"></h3>
	</div>
	 


</body>

</html>
```

---

### 14. color.html

#### resources\templates\color.html

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
		<h1 th:text=" ${cat.getThatBlackCat}==null"></h1>
		<h1 th:text=" ${cat.thatBlackCat}==null"></h1>
		<h1 th:text=" 'That black cat: ' + ${cat.thatBlackCat}==null"></h1>
		<h1
			th:text=" ${cat.getThatBlackCat}!=null?'Hey cat found':'Sorry, Cat not found.'"></h1>
		<h1 th:text="  ${cat.getThatBlackCat()} "></h1>

		<h1 th:text="${colorUtil} "></h1>
		<h1 th:text="${colorUtil.getRandomColorRBG}"></h1>
	</div>

	<div th:each="book : ${bookList}"
		th:with="color=${colorUtil.getRandomColorRBG}"
		th:styleappend="'background-color:'+ ${color}"
		th:unless="${book.isbn}%2==0">

		<h1 th:text="${book.title}"></h1>
		<h3 th:text="${colorUtil}"></h3>
		
		<a th:href="@{   '/book/show/' + ${book.isbn} + '/' + ${book.title} +'/'+ ${color}  }">Goto 1</a>
		
		
		<a th:href="@{/book/show/{isbn}/{title}/(a=${book.isbn},b=${book.title},c=${color}}">Goto 2</a>
		
		
		<a th:href="@{/book/show/{a}/{b}/{c}(a=${book.isbn},b=${book.title},c=${color}}">Goto 3</a>


		<a th:href="@{/book/show/{a}(a=${book.isbn}/{b}(b=${book.title})/{c}(c=${color})}">Goto 4</a>

		<a th:href="@{/show/{a}/{b}/{c}(a='th:text=${book.isbn}',b='${book.title}',c='Sky')}">Goto 5</a>

		<a th:href="@{show/{a}/{b}/{c}(a='water',b='Fire',c='Sky')}">Goto 6</a>

		<a th:href="@{/std/edit/{sno}(sno=${book.isbn})}">Goto 7</a>
		<a th:href="@{/std/delete/{sno}(sno=${book.title})}">Goto 8</a>
	

	</div>


</body>

</html>
```

---

### 15. home.html

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


	<h1 th:text="${book.title}"></h1>
	<h1 th:text=" (true and false) ? 'Hehe' : 'Huhu' "></h1>
	<h1 th:text="${msg}"></h1>
	<h1 th:text=" 'Hey there, this is general text' "></h1>
	<h1 th:if=!false th:text="'Hello bangaram'">if false</h1>
	<h1 th:if=true>if true</h1>
	<h1 th:unless=false>unless false</h1>
	<h1 th:unless=true>unless true</h1>


	<div th:switch="${book.type}">
		<p th:case="'edu'">This is Education book</p>
		<p th:case="'help'">This is selfhelp book</p>
		<p th:case="'comic'">This is Comit</p>
	</div>

	<div th:object="${book}">
		<p th:text="*{isbn}"></p>
		<p th:text="*{title}"></p>
		<p th:text="*{author}"></p>
		<p th:text="*{type}"></p>
	</div>

	<div th:object="${book}">
		<p th:field="*{isbn}"></p>
		<p th:field="*{title}"></p>
		<p th:field="*{author}"></p>
		<p th:field="*{type}"></p>
	</div>
	
	
	<form th:object="${book}">
		<input type="text" th:field="*{isbn}" />
		<input type="text" th:field="*{title}" />
		<input type="text" th:field="*{author}" />
		<input type="text" th:field="*{type}" />
	</form>


	<!--
<h1 th:if="${false}?" >view</h1>
<h1 th:text="#{props.msg}"></h1>
	<form th:action="@{/student/saveStd}" th:object="${student}" method="POST">
		
		<input type="text" th:field="*{sno}"  placeholder="Sno" />
		<p th:if="${#fields.hasErrors('sno')}" th:errors="*{sno}">Incorrect date</p>
		<br />

		<input type="text" th:field="*{name}" placeholder="Full Name" />
		<p th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Incorrect date</p>
		<br />
		<input type="text" th:field="*{cgpa}" placeholder="CGPA" />
		<p th:if="${#fields.hasErrors('cgpa')}" th:errors="*{cgpa}">Incorrect date</p>
		<br />
		<input type="submit" />
	</form>


	<div th:if="${isSaved}" th:object="${std}">
		<h1>Data Saved.</h1>
		<p>Sno: <span th:text="*{sno}"></span>.</p>
		<p>Name: <span th:text="*{name}"></span>.</p>
		<p>CGPA: <span th:text="*{cgpa}"></span>.</p>
	</div>

-->

</body>

</html>
```

---

### 16. ifColl.html

#### resources\templates\ifColl.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />

<style>
	.edu{
		background-color: green;
	}
	.help{
		background-color: red;
	}
</style>

</head>

<body>
	
		<div th:each="book : ${coll}" th:classappend="${book.release}==1? 'edu':'help'">
		
			<h1 th:text="${book.title}"></h1>
			<h2 th:text="${book.isbn}"></h2>
			<h2 th:text="${book.author}"></h2>
			<h2 th:text="${book.type}"></h2>
			
			<h2 th:if="${book.release}==0" th:text="'Book type: '+${book.release}" ></h2>
			
			<h2  class="th:text=${book.release}==1?edu:help"></h2>
			
			<h2 class="" th:attrappend="class=${book.title}" ></h2>
			
			<h2 class="row" th:classappend="${book.release}==1? 'edu':'help'"></h2>
			
			<h2 th:classappend="${book.release}==1? 'edu':'help'"></h2>
			<h2 th:styleappend="${book.release}==1? 'edu':'help'"></h2>
			
		</div>
	


</body>

</html>
```

---

### 17. iftest.html

#### resources\templates\iftest.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>
	<h1>Hello, <span th:text="${name}"></span></h1>
	
	<!-- Object Print isbn, title, author, type, release -->
	<h1><span th:text="${book}"></span></h1>
	<h1><span th:text="${book.isbn}"></span></h1>
	<h1><span th:text="${book.title}"></span></h1>
	<h1><span th:text="${book.author}"></span></h1>
	<h1><span th:text="${book.type}"></span></h1>
	
	<!-- Collection Print -->
	<div th:each=" text : ${coll} ">
		<h3 th:text="${text}"></h3>
	</div>
	
	<!-- String Array Print -->
	<div th:each=" text : ${arr} ">
		<h3 th:text="${text}"></h3>
	</div>
	
	
	
	
	<h1 th:if="${msg}=='hey'">Hellow world</h1>
	<h1 th:if="${msg}=='hellow'">Hellow Galexy</h1>
	<h1 th:text="${msg}=='hey'">Hellow world</h1>
	<h1 th:text="${msg}=='hey123'">Hellow world</h1>
	
	<h1 th:if="${isGirl}">It is girl</h1>
	<h1 th:if="${num}==2">Num = 1</h1>
	<h1 th:if="${num}==1">Num = 2</h1>
	
	<h1 th:if="${obj}==null">Object is null</h1>
	
	
	<h1 th:if=true th:text="'if ture, Some text'"></h1>
	<h1 th:if=false th:text="'if false, Some text'"></h1>
	<h1 th:if=true >Hellow</h1>
	<h1 th:if=false >Hey hey</h1>
	
</body>

</html>
```

---

### 18. listView.html

#### resources\templates\listView.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>

	<div th:object="${book}">
		<h1 th:text="*{title}"></h1>
		<h3 th:text="*{isbn}"></h3>
		<h3 th:text="*{author}"></h3>
		<h3 th:text="*{type}"></h3>
	</div>

	<h1 th:text="${booksList.toString}"></h1>


	<div th:each="book : ${booksList}">
		<h1 th:text="${book.title}"></h1>
		<h3 th:text="${book.isbn}"></h3>
		<h3 th:text="${book.author}"></h3>
		<h3 th:text="${book.type}"></h3>
	</div>
	


</body>

</html>
```

---

### 19. show.html

#### resources\templates\show.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body th:styleappend=" 'background-color:' + ${bg} ">
	<h1 th:text="${title}"></h1>
	<h1 th:text="${isbn}"></h1>
	<a href="/book/getcolor">Back</a>
</body>

</html>
```

---

### 20. list.html

#### resources\templates\student\list.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>

<h1>Student/list working...</h1>


	<!-- Print Studnets with rollno, name, marks, cgpa, isPassed, gender -->
	<div  th:each="studnet : ${students}" th:if="${studnet.gender} == 'female'" th:object="${studnet}" >
	<!-- 
		<h1>Rollno:<span th:text="        	${studnet.rollno} +100	"></span></h1>
		<h1>Name: <span th:text="	${studnet.name}	"></span></h1>
		<h1>Marks: <span th:text="${studnet.marks}"></span></h1>
	 -->
	 	<h1>Rollno:<span th:text=" *{rollno} + 100	"></span></h1>
		<h1>Name: <span th:text=" *{name}	"></span></h1>
		<h1>Marks: <span th:text=" *{marks}"></span></h1>
	
		<h1>CGPA: <span th:text="${studnet.cgpa}"></span></h1>
		<h1>IsPassed?: <span th:text="${studnet.isPassed}"></span></h1>
		<h1>Gender: <span th:text="${studnet.gender}"></span></h1>
		
		<a th:href="@{/student/update/{rollno}( rollno = ${studnet.rollno} )}">Edit</a>
		<a th:href="@{student/delete/{rollno}( rollno = ${studnet.rollno} )}">Delete</a>
		<a th:href="@{'/student/delete/' + 'sexy'}">Sexy</a>
		<a th:href="@{ '/student/delete/' + ${studnet.name}  }">Do something</a>
	</div>




<!--  
	<div th:object="${book}">
		<h1 th:text="*{title}"></h1>
		<h3 th:text="*{isbn}"></h3>
		<h3 th:text="*{author}"></h3>
		<h3 th:text="*{type}"></h3>
	</div>

	<h1 th:text="${booksList.toString}"></h1>


	<div th:each="book : ${booksList}">
		<h1 th:text="${book.title}"></h1>
		<h3 th:text="${book.isbn}"></h3>
		<h3 th:text="${book.author}"></h3>
		<h3 th:text="${book.type}"></h3>
	</div>
	
-->

</body>

</html>
```

---

### 21. home2.html

#### resources\templates\tron\home2.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
<title>Home Page</title>
<meta charset="UTF-8">
<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body bgcolor=red>

	<h1 th:text="${book}"></h1>
	<a th:href="@{/show/{a}/{b}/{c}(a=${book.isbn})(b=${book.title})(c=${book.type})}">Goto</a>
	<a th:href="@{/show/{id}(id=${book.isbn})/{id}(id=${book.isbn})/{id}(id=${book.isbn})}">Goto</a>
	<a th:href="@{   '/show/' + ${book.isbn} + '/' + ${book.title} +'/'+ ${book.type}  }">Goto</a>
</body>

</html>
```

---

### 22. basicstyles.css

#### static\styles\basicstyles.css

```css

div {
  border: 1px outset black;
  background-color: #33FF73;    
  text-align: center;
  margin:10px;
}
```

---

### 23. ThymeLeafTestApplicationTests.java

#### src\test\java\com\suji\thyme\ThymeLeafTestApplicationTests.java

```java

package com.suji.thyme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThymeLeafTestApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

