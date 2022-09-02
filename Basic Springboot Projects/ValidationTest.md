# ValidationTest


### File Structure
```pre
+ ValidationTest\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\resources\templates
		|---  blank.html
		|---  home.html
		|---  person-signup.html
	+ \src\main\java\com\suji
		|---  ClassDesc.java
		|---  ValidationTestApplication.java
	+ \src\main\resources\static\styles
		|---  basicstyles.css
	+ \src\test\java\com\suji
		|---  ValidationTestApplicationTests.java
	+ \src\main\java\com\suji\model
		|---  Person.java
		|---  Student.java
	+ \src\main\java\com\suji\service
		|---  StudentService.java
	+ \src\main\java\com\suji\repository
		|---  PersonDao.java
		|---  StudentDao.java
	+ \src\main\java\com\suji\boot
		|---  DataLoader.java
	+ \src\main\java\com\suji\classdesc
		|---  App.java
	+ \src\main\java\com\suji\ctrl
		|---  DemoController.java
		|---  FinalController.java
		|---  MyUniqueHandler.java
		|---  OrgController.java
		|---  PersonController.java
		|---  StudentController.java
		|---  TextCtrl.java
		|---  UserController.java
	+ \src\main\java\com\suji\validator
		|---  UniqueContraint.java
		|---  UniqueValidator.java
```
### Index
```pre
1. application.properties
2. model\Person.java
3. model\Student.java
4. service\StudentService.java
5. repository\PersonDao.java
6. repository\StudentDao.java
7. src\main\java\com\suji\ClassDesc.java
8. src\main\java\com\suji\ValidationTestApplication.java
9. src\main\java\com\suji\boot\DataLoader.java
10. src\main\java\com\suji\classdesc\App.java
11. src\main\java\com\suji\ctrl\DemoController.java
12. src\main\java\com\suji\ctrl\FinalController.java
13. src\main\java\com\suji\ctrl\MyUniqueHandler.java
14. src\main\java\com\suji\ctrl\OrgController.java
15. src\main\java\com\suji\ctrl\PersonController.java
16. src\main\java\com\suji\ctrl\StudentController.java
17. src\main\java\com\suji\ctrl\TextCtrl.java
18. src\main\java\com\suji\ctrl\UserController.java
19. src\main\java\com\suji\validator\UniqueContraint.java
20. src\main\java\com\suji\validator\UniqueValidator.java
21. resources\templates\blank.html
22. resources\templates\home.html
23. resources\templates\person-signup.html
24. static\styles\basicstyles.css
25. src\test\java\com\suji\ValidationTestApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/nitro
spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true

logging.level.org.springframework.context=DEBUG

```

---

### 2. Person.java

#### model\Person.java

```java

package com.suji.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(nullable = false, length = 20)
	@Size(min = 4,max = 20)
	private String name;
	
	@NotNull
	@Pattern(regexp = "[a-z]+")
	@Size(min = 4,max = 12)
	@Column(unique = true, nullable = false,length = 12, name = "user_name")
	private String username;
	
	@NotNull
	@Column(nullable = false, length=25)
	@Size(min = 4,max = 25)
	private String email;
	
	@NotNull
	@Column(nullable = false,length=20)
	@Size(min = 4,max = 20)
	private String password;
}

```

---

### 3. Student.java

#### model\Student.java

```java

package com.suji.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue
	private int sno;
	
	@Pattern(regexp = "[a-z@.]+", message = "Hey, Pattern correct ga entry chey bhe")
	@Column(unique = true)
	private String name;
	
	@Positive
	private double cgpa;
	
	public Student(String name) {
		this.name = name;
	}
}

```

---

### 4. StudentService.java

#### service\StudentService.java

```java

package com.suji.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

	
	
}

```

---

### 5. PersonDao.java

#### repository\PersonDao.java

```java

package com.suji.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.model.Person;

public interface PersonDao extends JpaRepository<Person, Integer>  {

	public boolean existsByName(String name);
	
}

```

---

### 6. StudentDao.java

#### repository\StudentDao.java

```java

package com.suji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suji.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer>{
	
	
	public boolean existsByName(String name);
}

```

---

### 7. ClassDesc.java

#### src\main\java\com\suji\ClassDesc.java

```java

package com.suji;

import java.lang.reflect.Modifier;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Stack;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

public class ClassDesc {
	
	public static void main(String[] args) {
		List<Class> cls = new ArrayList<>();
		
		
		cls.add(View.class);
		cls.add(ViewResolver.class);
		cls.add(InternalResourceViewResolver.class);
		cls.add(ObjectError.class);
		cls.add(ConfigurableApplicationContext.class);
		cls.add(SpringApplication.class);
//		cls.add(FieldError.class);
//		cls.add(ObjectError.class);
//		cls.add(FieldError.class);
//		cls.add(ObjectError.class);
//		cls.add(FieldError.class);
//		
		ClassDesc.all(cls);
	}
	
	public static void all(List<Class> cls) {
		for (Class c : cls) {
			new ClassDesc(c).totalDesc();
			System.out.println("___________");
		}
	}

	private Class cl;
	private LinkedHashSet<Class> set = new LinkedHashSet<Class>();

	public ClassDesc(Class cl) {
		super();
		this.cl = cl;
	}
	
	private String subInf(Class cls, StringBuilder sb) {
		
		
		return null;
	}
	
	public void totalDesc() {
		getDesc();
		System.out.println(getRoot(cl));
		for (Class cls: set) {
			
			if(cls.isInterface()) {
				if(! getInterfaces(cls).isBlank()) {
					System.out.println(getInterfaces(cls));
				}
				
			}
			else {
				
				System.out.println(getInterfaces(cls));
			}
			
		}
	
	}

	private String _getDesc(Class cls) {
		set.add(cls);

		if (cls.isInterface()) {
			Class[] in = cls.getInterfaces();

			for (int i = 0; i < in.length; i++) {
				Class inf = in[i];
				_getDesc(inf);  
			}

		} else {
			
			//System.out.println(getRoot(cls));
			//System.out.println(getInterfaces(cls));
			Class[] in = cls.getInterfaces();
			

			for (int i = 0; i < in.length; i++) {
				Class inf = in[i];
				_getDesc(inf);
			}

			Class sc = cls.getSuperclass();
			if (sc != null) {
				
				_getDesc(sc);
			}

		}

		return null;
	}

	private String getDesc() {
		return _getDesc(cl);
	}

	public String getInterfaces(Class cls) {
		StringBuilder sb = new StringBuilder();
		Class[] interfaces = cls.getInterfaces();
		if (interfaces.length > 0) {
			
			
			if(cls.isInterface()) {
				sb.append(getName(cls) + " >> ");
			}else {
				sb.append(getName(cls) + " --> ");
			}
			
			for (int i = 0; i < interfaces.length; i++) {
				Class in = interfaces[i];
				sb.append(getName(in) + ", ");
			}
		}
		return sb.toString();
	}

	private String getInterfaces() {
		StringBuilder sb = new StringBuilder();
		Class[] interfaces = cl.getInterfaces();
		if (interfaces.length > 0) {
			sb.append(getName(cl) + " --> ");
			for (int i = 0; i < interfaces.length; i++) {
				Class in = interfaces[i];
				sb.append(getName(in) + ", ");
			}
		}
		return sb.toString();
	}

	private String getSuper(Class cls, StringBuilder sb) {

		sb.append(getName(cls));
		Class superclass = cls.getSuperclass();
		if (superclass != null) {
			sb.append(" > ");
			getSuper(superclass, sb);
		}

		return sb.toString();
	}

	public String getRoot(Class cls) {
		return getSuper(cls, new StringBuilder());
	}

	public String getName(Class cls) {
		String s = Modifier.toString(cls.getModifiers());
		String name = "";

		if (s.contains("interface")) {
			name = cls.getSimpleName() + "(I)";
		} else if (s.contains("abstract")) {
			name = cls.getSimpleName() + "(AC)";
		} else if (s.contains("final")) {
			name = cls.getSimpleName() + "(FC)";
		} else {
			name = cls.getSimpleName() + "(C)";
		}

		return name;
	}

}

```

---

### 8. ValidationTestApplication.java

#### src\main\java\com\suji\ValidationTestApplication.java

```java

package com.suji;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.suji.repository.StudentDao;

@SpringBootApplication
public class ValidationTestApplication {

	public static void main(String[] args) {
		
		
		ConfigurableApplicationContext context = SpringApplication.run(ValidationTestApplication.class, args);
		
		
		System.out.println("ValidationTestApplication Server get Started.");
		StudentDao bean = context.getBean(StudentDao.class);
		System.out.println(bean.existsByName("sujit"));
	}

}

```

---

### 9. DataLoader.java

#### src\main\java\com\suji\boot\DataLoader.java

```java

package com.suji.boot;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.suji.repository.StudentDao;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	private StudentDao dao;
	
	public DataLoader(StudentDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		
	}

}

```

---

### 10. App.java

#### src\main\java\com\suji\classdesc\App.java

```java

package com.suji.classdesc;

import java.lang.String;
import java.util.SortedSet;

/**
 * Hello world!
 *
 */
public class App {

	public static StringBuilder sb = new StringBuilder();
	
	

	public static void main(String[] args) throws ClassNotFoundException {
		// String className = "com.suji.classdesc.C";

		String className = "org.springframework.web.bind.MethodArgumentNotValidException";

		Class cls = Class.forName(className);
		sb.append(cls.getSimpleName());

		classDesc(cls);
		System.out.println(sb.toString());
	}
	
	
	
	private static void subInterfaces(Class cls) {
		StringBuilder s = new StringBuilder();
		
		Class[] interfaces = cls.getInterfaces();
		if (interfaces.length > 0) {
			s.append(cls.getSimpleName()+" >> ");
			for (int i = 0; i < interfaces.length; i++) {
				Class intr = interfaces[i];
				s.append(intr.getSimpleName()).append("\t");
			}
		}
		System.out.println(s);
	}

	private static void intDesc(Class cls) {

		Class[] interfaces = cls.getInterfaces();
		if (interfaces.length > 0) {
			
			subInterfaces(cls);
			
			
			for (int i = 0; i < interfaces.length; i++) {
				Class intr = interfaces[i];
				intDesc(intr);
			}
		}
	}

	private static void classDesc(Class cls) {
		System.out.println();
		if (cls.isInterface()) {

			intDesc(cls);

		} else if (cls.isMemberClass()) {
			System.out.println("It is LocalClass");
		} else {

			Class[] interfaces = cls.getInterfaces();
			
			if (interfaces.length > 0) {
				System.out.print(cls.getSimpleName() + " --> ");

				for (int i = 0; i < interfaces.length; i++) {
					Class intr = interfaces[i];
					System.out.print("[" + intr.getSimpleName() + "]");
				}

				for (int i = 0; i < interfaces.length; i++) {
					Class intr = interfaces[i];
					classDesc(intr);
				}
			}

			if (cls.getSuperclass() != null) {
				Class superclass = cls.getSuperclass();
				sb.append(" > " + superclass.getSimpleName());

				classDesc(superclass);
			}
		}
	}
}

```

---

### 11. DemoController.java

#### src\main\java\com\suji\ctrl\DemoController.java

```java

package com.suji.ctrl;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suji.model.Student;
import com.suji.repository.StudentDao;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private StudentDao dao;

	@PostMapping(value = "/data")
	@ResponseBody
	public ResponseEntity<Student> postMethodName(@Valid @RequestBody Student student, BindingResult req) {

		Student savedStd = dao.save(student);

		ResponseEntity<Student> entity = new ResponseEntity<Student>(savedStd, HttpStatus.CREATED);

		return entity;
	}

	@PostMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<Student> read(@PathVariable("id") int sno) {

		Student std = dao.getById(sno);

		ResponseEntity<Student> entity = new ResponseEntity<Student>(std, HttpStatus.CREATED);

		return entity;
	}

	@GetMapping("/add")
	public String showAddPersonForm( Student std, Model model) {
		
		
		System.out.println("New std: "+std);
		model.addAttribute("std", std);
		
		return "home";
	}

	@PostMapping("/add")
	public String addPerson(@Valid @RequestBody Student std, BindingResult result, Model model) {
		
		if (result.hasErrors()) {	
			
			System.out.println("Post /add: Has Errors! "+std);
			
			Student save = dao.save(std);
			model.addAttribute("std", save);
			return "home";
		}
		System.out.println("Post /add:  "+std);
		
		return "blank";
	}

	@PostMapping("/addData")
	@ResponseBody
	public ResponseEntity<Student> addPersonData(@Valid @RequestBody Student person, BindingResult result, Model model) {
		@Valid
		Student save = dao.save(person);
		return new ResponseEntity<Student>(save, HttpStatus.CREATED);
	}

}
























```

---

### 12. FinalController.java

#### src\main\java\com\suji\ctrl\FinalController.java

```java

package com.suji.ctrl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.expression.Fields;

import com.suji.model.Student;

@Controller
@RequestMapping("/final")
public class FinalController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		System.out.println();
		registry.addViewController("/results").setViewName("results");
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getHome(Student std, Model model) {
		model.addAttribute("std", std);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHome2(@ModelAttribute("std") Student std) {
		return "home";
	}

	@GetMapping("/showErr")
	public String home(HttpServletResponse res, Model model, Fields fields) {
		res.setStatus(405);
		model.addAttribute("name", "Hellow sujith");
		System.out.println(fields);

		model.addAttribute("std", new Student(1, "Sujith", 5.5));
		return "home";
	}

	@PostMapping("/save")
	public String checkPersonInfo(@Valid Student std, BindingResult bindingResult, Fields fields) {

		if (bindingResult.hasErrors()) {
			System.out.println(fields);
			return "form";
		}

		return "blank";
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public String expn(SQLIntegrityConstraintViolationException ex, BindingResult br) {
		List<FieldError> errors = br.getFieldErrors();
		for (FieldError fieldError : errors) {
			System.out.println("Object name: "+fieldError.getObjectName());
			System.out.println("Field: "+fieldError.getField());
			System.out.println("Default Msg: "+fieldError.getDefaultMessage());
		}
		FieldError fe = new FieldError("String", "name", "User already existed!");
		br.addError(fe);
		return "home";
	}

}

```

---

### 13. MyUniqueHandler.java

#### src\main\java\com\suji\ctrl\MyUniqueHandler.java

```java

package com.suji.ctrl;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyUniqueHandler extends ResponseEntityExceptionHandler {
	
}

```

---

### 14. OrgController.java

#### src\main\java\com\suji\ctrl\OrgController.java

```java

package com.suji.ctrl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.suji.model.Student;
import com.suji.repository.StudentDao;

@Controller
@RequestMapping("org")
public class OrgController {

	private final StudentDao dao;

	public OrgController(StudentDao dao) {
		this.dao = dao;
	}
	

	@GetMapping("/add")
	public String showAddPersonForm( Student std, Model model) {
		
		
		System.out.println("New std: "+std);
		model.addAttribute("std", std);
		
		return "home";
	}

	@PostMapping("/add")
	public String addPerson(@Valid @RequestBody Student std, BindingResult result, Model model) {

		if (result.hasErrors()) {

			System.out.println("Post /add: Has Errors! " + std);

			Student save = dao.save(std);
			model.addAttribute("std", save);
			return "home";
		}
		System.out.println("Post /add:  " + std);

		return "blank";
	}

	@PostMapping("/addData")
	@ResponseBody
	public ResponseEntity<Student> addPersonData(@Valid @RequestBody Student person, BindingResult result,
			Model model) {
		@Valid
		Student save = dao.save(person);
		return new ResponseEntity<Student>(save, HttpStatus.CREATED);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Student> addUser(@RequestBody @Valid Student std) {
		Student save = dao.save(std);
		System.out.println(save);
		// ResponseEntity.ok("User data is valid");
		return new ResponseEntity<Student>(std, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	@Valid
	@ResponseBody
	public Optional<Student> getUser(@PathVariable @Positive Integer id) {
		System.out.println("{id} Id: " + id);
		return dao.findById(id);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, String> get(BindException ex) {

		Map<String, String> errors = new HashMap<>();

		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			String key = fieldError.getField();
			String value = fieldError.getDefaultMessage();
			errors.put(key, value);
		}

		return errors;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();

		Set<ConstraintViolation<?>> set = ex.getConstraintViolations();

		ex.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});

		return errors;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(SQLIntegrityConstraintViolationException ex) {

		Map<String, String> errors = new HashMap<>();

		errors.put("name", ex.getMessage());
		errors.put("ErrorCode", String.valueOf(ex.getErrorCode()));

		System.out.println("Atleast, it's working here.");
		return errors;
	}

}

```

---

### 15. PersonController.java

#### src\main\java\com\suji\ctrl\PersonController.java

```java

package com.suji.ctrl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.expression.Fields;

import com.suji.model.Person;
import com.suji.repository.PersonDao;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	
	@Autowired
	private PersonDao dao;

	@RequestMapping("/signup")
	public String signup(Person person, Fields f) {
		
		
		return "person-signup";
	}
	
	@PostMapping("/register")
	public String saveStd(@Valid Person person, BindingResult br, Model model) {

		

		if (dao.existsByName(person.getUsername())) {
			
			FieldError fe = new FieldError("person", "username", person.getUsername(), true, null, null,
					"Username \"" + person.getUsername()+ "\" already existed.");
			br.addError(fe);

		}

		if (br.hasFieldErrors()) {
			return "person-signup";
		}

		Person savedPerson = dao.save(person);
		
		if (savedPerson != null) {
			model.addAttribute("isSaved", true);
			model.addAttribute("savedPerson", savedPerson);
			model.addAttribute("student", new Person());
		}

		return "person-signup";
	}

}

```

---

### 16. StudentController.java

#### src\main\java\com\suji\ctrl\StudentController.java

```java

package com.suji.ctrl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.spring5.expression.Fields;

import com.suji.model.Student;
import com.suji.repository.StudentDao;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentDao dao;

	@GetMapping("/signup")
	public String singup(Student std) {
		return "home";
	}

	@PostMapping("/saveStd")
	public String saveStd(@Valid Student std, BindingResult br, Model model) {

		boolean isThere = dao.existsByName(std.getName());
		if (isThere) {
			FieldError fe = new FieldError("student", "name", std.getName(), true, null, null, "Username "+std.getName()+" already existed.");
			br.addError(fe);
			
		
		}

		System.out.println("Model data of /saveStd");
		showModelData(model);
		System.out.println("Field Errors data of /saveStd");
		showFieldErrors(br);

		if (br.hasFieldErrors()) {
			return "home";
		}
		
		Student save = dao.save(std);
		if(save!=null) {
			model.addAttribute("isSaved",true);
			model.addAttribute("std", save);
			model.addAttribute("student", new Student());
		}else {
			model.addAttribute("isSaved",false);
		}
		
		
		return "home";
	}

	@GetMapping("/home")
	public String home(Student std, Model model) {

		System.out.println("Model data of /home");
		showModelData(model);

		//model.addAttribute("student", new Student(142, "Fantastic Club", 7.8));

		return "home";
	}

	@PostMapping("/save")
	public String save(@Valid Student student, BindingResult br, Model model) {

		if (br.hasErrors()) {
			System.out.println("/save:  hasErrors() ");
		}
		if (br.hasFieldErrors()) {
			System.out.println("/save:  hasFieldErrors() ");
			List<FieldError> fieldErrors = br.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println(fieldError.getField());
				System.out.println(fieldError.getDefaultMessage());
			}
		}
		if (br.hasFieldErrors("name")) {
			System.out.println("/save: hasFieldErrors(\"name\") ");

		}

		System.out.println("/save: " + student + "; ");

		model.addAttribute(br);
		model.addAttribute("student", student);

		System.out.println("Model data of /save");
		showModelData(model);

		student = dao.save(student);
		boolean exists = dao.existsById(student.getSno());

		br.addError(new FieldError("String", "name", "Name should be unique!"));
		showFieldErrors(br);

		System.out.println("After DB Error!");
		if (br.hasErrors()) {
			return "home";
		}

		return "blank";
	}

	private void showFieldErrors(BindingResult br) {
		List<FieldError> errors = br.getFieldErrors();
		for (FieldError fieldError : errors) {
			System.out.println("objectName : " + fieldError.getObjectName());
			System.out.println("field : " + fieldError.getField());
			System.out.println("rejectedValue : " + fieldError.getRejectedValue());
			System.out.println("bindingFailure : " + fieldError.isBindingFailure());
			System.out.println("codes : " + Arrays.toString(fieldError.getCodes()));
			System.out.println("arguments : " +Arrays.toString( fieldError.getArguments()));
			System.out.println("defaultMessage: " + fieldError.getDefaultMessage());
		}
	}

	private void showModelData(Model model) {
		System.out.println("--Model Data--");
		Map<String, Object> map = model.asMap();
		Set<Entry<String, Object>> set = map.entrySet();
		System.out.println("total objects: " + set.size());
		System.out.println(map.keySet());
		for (Entry<String, Object> entry : set) {
			System.out.println("Key : " + entry.getKey() + "\nValue :" + entry.getValue());
			System.out.println();
		}
		System.out.println("----");

	}
}

```

---

### 17. TextCtrl.java

#### src\main\java\com\suji\ctrl\TextCtrl.java

```java

package com.suji.ctrl;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suji.model.Student;

@Controller
@RequestMapping("/test")
public class TextCtrl {

	@RequestMapping("/hi")
	@ResponseBody
	public String hi() {
		return "Hellow";
	}

	@RequestMapping("/hai")
	public String hai(Model model) {
		model.addAttribute("msg", "Hello Sujith!");
		model.addAttribute("std", new Student("Sujith"));
		return "home";
	}

	@RequestMapping("/check")
	public String check(Model model) {
		model.addAttribute("msg", "Hello Sujith!");
		model.addAttribute("std", new Student("Sujith"));
		return "home";
	}
	
	@RequestMapping("/read")
	@ResponseBody
	public String read(@Valid Student student, Errors errors, Model model) {
		if (null != errors && errors.getErrorCount() > 0) {
			return errors.toString();
		} else {
			model.addAttribute("msg", "Details saved successfully!!");
			return student.toString();
		}
	}
	
	@RequestMapping("/signup")
	public String submitStudentDetails(@Valid Student student, Errors errors, Model model) {
		if (null != errors && errors.getErrorCount() > 0) {
			return "home";
		} else {
			model.addAttribute("msg", "Details saved successfully!!");
			return "blank";
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	public Student save(Student student) {
		return student;
	}

	@RequestMapping(value="/saveValid", method = RequestMethod.POST)
	public String saveValid(@Valid Student std,BindingResult results, Model model) {
		System.out.println("Student object : "+std);
		if (results.hasErrors()) {
			model.addAttribute("std", std);
			return "home";
		} else {
			model.addAttribute("msg", "Details saved successfully!!");
			return "blank";
		}
	}

}

```

---

### 18. UserController.java

#### src\main\java\com\suji\ctrl\UserController.java

```java

package com.suji.ctrl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.suji.model.Student;
import com.suji.repository.StudentDao;

@RestController
@RequestMapping("users")
@Validated
public class UserController {

	private final StudentDao dao;

	public UserController(StudentDao dao) {
		this.dao = dao;
	}

	@PostMapping
	public ResponseEntity<Student> addUser(@RequestBody @Valid Student std) {
		Student save = dao.save(std);
		System.out.println(save);
		// ResponseEntity.ok("User data is valid");
		return new ResponseEntity<Student>(std, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	@Valid
	public Optional<Student> getUser(@PathVariable @Positive Integer id) {
		System.out.println("{id} Id: " + id);
		return dao.findById(id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}

	
	
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, String> get(BindException ex) {

		Map<String, String> errors = new HashMap<>();

		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			String key = fieldError.getField();
			String value = fieldError.getDefaultMessage();
			errors.put(key, value);
		}

		return errors;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();

		Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
		
		ex.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});

		return errors;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(SQLIntegrityConstraintViolationException ex) {
		
		Map<String, String> errors = new HashMap<>();

		errors.put("name", ex.getMessage());
		errors.put("ErrorCode", String.valueOf(ex.getErrorCode()) );
		 
		System.out.println("Atleast, it's working here.");
		return errors;
	}

}

```

---

### 19. UniqueContraint.java

#### src\main\java\com\suji\validator\UniqueContraint.java

```java

package com.suji.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueContraint {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

```

---

### 20. UniqueValidator.java

#### src\main\java\com\suji\validator\UniqueValidator.java

```java

package com.suji.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements 
ConstraintValidator<UniqueContraint, String> {

  @Override
  public void initialize(UniqueContraint unique) {
	  
	  
  }

  @Override
  public boolean isValid(String uniqueField, ConstraintValidatorContext cxt) {
	  
	
	  
	  
	  return false;
  }



}
```

---

### 21. blank.html

#### resources\templates\blank.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Home Page</title>
	<meta charset="UTF-8">
	<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>
	 <div th:object="${student}">
    <p>Sno: <span th:text="*{sno}"></span>.</p>
    <p>Name: <span th:text="*{name}"></span>.</p>
    <p>CGPA: <span th:text="*{cgpa}"></span>.</p>
  </div>
</body>

</html>
```

---

### 22. home.html

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

	<form th:action="@{/student/saveStd}" th:object="${student}" method="POST">
		<!--
		<input type="text" th:field="*{sno}"  placeholder="Sno" />
		<p th:if="${#fields.hasErrors('sno')}" th:errors="*{sno}">Incorrect date</p>
		<br />
-->
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



</body>

</html>
```

---

### 23. person-signup.html

#### resources\templates\person-signup.html

```html

<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Home Page</title>
	<meta charset="UTF-8">
	<link th:href="@{/styles/basicstyles.css}" rel="stylesheet" />
</head>

<body>

	<form th:action="@{/person/register}" th:object="${person}" method="POST">


		<input type="text" th:field="*{name}" placeholder="Full Name" />
		<p th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></p>
		<br />

		<input type="text" th:field="*{username}" placeholder="User Name" />
		<p th:if="${#fields.hasErrors('username')}" th:errors="*{username}"></p>
		<br />

		<input type="email" th:field="*{email}" placeholder="Email" />
		<p th:if="${#fields.hasErrors('email')}" th:errors="*{email}"></p>
		<br />

		<input type="password" th:field="*{password}" placeholder="Pass Word" />
		<p th:if="${#fields.hasErrors('password')}" th:errors="*{password}"></p>
		<br />
		<input type="reset" />
		<input type="submit" />
	</form>

	<div th:if="${isSaved}" th:object="${savedPerson}">
		<h1>Data Saved.</h1>
		<p>Id : <span th:text="*{id}"></span>.</p>
		<p>Name : <span th:text="*{name}"></span>.</p>
		<p>Username : <span th:text="*{username}"></span>.</p>
		<p>Emain : <span th:text="*{email}"></span>.</p>
		<p>Password : <span th:text="*{password}"></span>.</p>
	</div>



</body>

</html>
```

---

### 24. basicstyles.css

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

### 25. ValidationTestApplicationTests.java

#### src\test\java\com\suji\ValidationTestApplicationTests.java

```java

package com.suji;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidationTestApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

