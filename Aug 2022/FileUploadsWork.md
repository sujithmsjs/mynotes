# FileUploadsWork


### File Structure
```pre
+ FileUploadsWork\ 
	+ Parent Path Not Found
		|---  pom.xml
	+ Parent Path Not Found
		|---  pom.xml
	|---  pom.xml
	+ n\resources
		|---  application.properties
	+ c\main\resources
		|---  application.properties
	+ \src\main\resources
		|---  application.properties
	+ n\resources\templates
		|---  editStd.html
		|---  home.html
		|---  newStd.html
		|---  test.html
	+ c\main\resources\templates
		|---  error-page.html
	+ c\main\resources\templates\person
		|---  signup.html
		|---  welcome.html
	+ c\main\resources\templates\student
		|---  signup.html
		|---  welcome.html
	+ n\resources\static\styles
		|---  basicstyles.css
	+ n\java\com\suji\crud
		|---  CrudOpsApplication.java
	+ c\main\java\com\suji\crudrepo
		|---  CrudRepoDemoApplication.java
		|---  MyRunner.java
	+ \src\main\java\com\suji\fileuploads
		|---  FileUploadsWorkApplication.java
	+ t\java\com\suji\crud
		|---  CrudOpsApplicationTests.java
	+ c\test\java\com\suji\crudrepo
		|---  CrudRepoDemoApplicationTests.java
	+ \src\test\java\com\suji\fileuploads
		|---  FileUploadsWorkApplicationTests.java
	+ c\main\java\com\suji\crudrepo\model
		|---  PersonPojo.java
		|---  StudentPojo.java
	+ \src\main\java\com\suji\fileuploads\model
		|---  FileInfo.java
	+ \src\main\java\com\suji\fileuploads\controller
		|---  FilesController.java
	+ c\main\java\com\suji\crudrepo\service
		|---  EmailService.java
		|---  PersonService.java
		|---  StudentService.java
	+ \src\main\java\com\suji\fileuploads\service
		|---  FilesStorageService.java
	+ c\main\java\com\suji\crudrepo\repository
		|---  PersonRepository.java
		|---  StudentRepository.java
	+ n\java\com\suji\crud\ctrl
		|---  StudentCtrl.java
	+ n\java\com\suji\crud\entity
		|---  Student.java
	+ c\main\java\com\suji\crudrepo\configuration
		|---  DateTimeConfig.java
		|---  WebApplicationConfig.java
	+ c\main\java\com\suji\crudrepo\control
		|---  GlobalDefaultExceptionHandler.java
		|---  PersonController.java
		|---  StudentController.java
	+ c\main\java\com\suji\crudrepo\util
		|---  PrintUtil.java
	+ \src\main\java\com\suji\fileuploads\exception
		|---  FileUploadExceptionAdvice.java
	+ \src\main\java\com\suji\fileuploads\message
		|---  ResponseMessage.java
	+ c\main\java\com\suji\crudrepo\configuration\validation
		|---  ContactNumberConstraint.java
		|---  ContactNumberValidator.java
		|---  FieldsValueMatch.java
		|---  FieldsValueMatchValidator.java
		|---  PersonConstraint.java
		|---  PersonValidator.java
		|---  UniquePersonUsernameContraint.java
		|---  UniquePersonUsernameValidator.java
	+ \src\main\java\com\suji\fileuploads\service\impl
		|---  FilesStorageServiceImpl.java
```
### Index
```pre
1. application.properties
2. application.properties
3. application.properties
4. pom.xml
5. pom.xml
6. pom.xml
7. model\PersonPojo.java
8. model\StudentPojo.java
9. model\FileInfo.java
10. controller\FilesController.java
11. service\EmailService.java
12. service\PersonService.java
13. service\StudentService.java
14. service\FilesStorageService.java
15. repository\PersonRepository.java
16. repository\StudentRepository.java
17. src\main\java\com\suji\crud\CrudOpsApplication.java
18. src\main\java\com\suji\crud\ctrl\StudentCtrl.java
19. src\main\java\com\suji\crud\entity\Student.java
20. src\main\java\com\suji\crudrepo\CrudRepoDemoApplication.java
21. src\main\java\com\suji\crudrepo\MyRunner.java
22. src\main\java\com\suji\crudrepo\configuration\DateTimeConfig.java
23. src\main\java\com\suji\crudrepo\configuration\WebApplicationConfig.java
24. src\main\java\com\suji\crudrepo\configuration\validation\ContactNumberConstraint.java
25. src\main\java\com\suji\crudrepo\configuration\validation\ContactNumberValidator.java
26. src\main\java\com\suji\crudrepo\configuration\validation\FieldsValueMatch.java
27. src\main\java\com\suji\crudrepo\configuration\validation\FieldsValueMatchValidator.java
28. src\main\java\com\suji\crudrepo\configuration\validation\PersonConstraint.java
29. src\main\java\com\suji\crudrepo\configuration\validation\PersonValidator.java
30. src\main\java\com\suji\crudrepo\configuration\validation\UniquePersonUsernameContraint.java
31. src\main\java\com\suji\crudrepo\configuration\validation\UniquePersonUsernameValidator.java
32. src\main\java\com\suji\crudrepo\control\GlobalDefaultExceptionHandler.java
33. src\main\java\com\suji\crudrepo\control\PersonController.java
34. src\main\java\com\suji\crudrepo\control\StudentController.java
35. src\main\java\com\suji\crudrepo\util\PrintUtil.java
36. src\main\java\com\suji\fileuploads\FileUploadsWorkApplication.java
37. src\main\java\com\suji\fileuploads\exception\FileUploadExceptionAdvice.java
38. src\main\java\com\suji\fileuploads\message\ResponseMessage.java
39. src\main\java\com\suji\fileuploads\service\impl\FilesStorageServiceImpl.java
40. resources\templates\editStd.html
41. resources\templates\home.html
42. resources\templates\newStd.html
43. resources\templates\test.html
44. resources\templates\error-page.html
45. resources\templates\person\signup.html
46. resources\templates\person\welcome.html
47. resources\templates\student\signup.html
48. resources\templates\student\welcome.html
49. static\styles\basicstyles.css
50. src\test\java\com\suji\crud\CrudOpsApplicationTests.java
51. src\test\java\com\suji\crudrepo\CrudRepoDemoApplicationTests.java
52. src\test\java\com\suji\fileuploads\FileUploadsWorkApplicationTests.java

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

### 2. application.properties

#### application.properties

```properties


# Datasource

spring.datasource.url=jdbc:mysql://localhost:3306/nitro
spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Settings

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


# Logging Settings
logging.level.root=INFO
logging.level.springframework.context=debug
logging.level.com.suji.crudrepo=DEBUG


# DateTimeFormatters

spring.mvc.format.date=yyyy-MM-dd
spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
spring.mvc.format.time=HH:mm:ss


# Email

spring.main.banner-mode=off

spring.mail.protocol=smtp
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=sujithmsjs@gmail.com
spring.mail.password=jSyr3nf2my#Lf7-6
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true












```

---

### 3. application.properties

#### application.properties

```properties


spring.servlet.multipart.max-file-size=500KB
spring.servlet.multipart.max-request-size=500KB
```

---

### 4. pom.xml

#### pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.9</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.suji</groupId>
	<artifactId>CrudOps</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>CrudOps</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

---

### 5. pom.xml

#### pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.9</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.suji</groupId>
	<artifactId>CrudRepoDemo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>CrudRepoDemo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
		
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```

---

### 6. pom.xml

#### pom.xml

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.suji</groupId>
	<artifactId>FileUploadsWork</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>FileUploadsWork</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

---

### 7. PersonPojo.java

#### model\PersonPojo.java

```java

package com.suji.crudrepo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.suji.crudrepo.configuration.validation.FieldsValueMatch;
import com.suji.crudrepo.configuration.validation.PersonConstraint;
import com.suji.crudrepo.configuration.validation.UniquePersonUsernameContraint;

import lombok.Data;

@Data
@Entity
@Table(name = "persons")
@PersonConstraint
public class PersonPojo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 4, max=16)
	@Column(name = "name", nullable = false)
	private String name;
	
	
	@Column(name = "uname", nullable = false)
	@Size(min = 4, max=10)
	private String username;
	
	@Size(min = 4, max=16)
	@Column(name = "pw", nullable = false)
	private String password;
	
	@Transient
	private String verifyPassword;
	
	@Column(name = "sex")
	private char gender;
	
	@Column(name = "height")
	private double height;
	@Column(name = "ncc")
	private boolean hasNCC;

	@Past
	@Column(name = "date_of_birth")
	private LocalDate dob;
	@Column(name = "registered_on")
	@CreationTimestamp
	private LocalDateTime registered;
	@Column(name = "last_update")
	@LastModifiedDate
	private LocalDateTime lastUpdate;
}


//name,username,password,gender,dob,height,hasNCC,id,registered, lastUpdate
```

---

### 8. StudentPojo.java

#### model\StudentPojo.java

```java

package com.suji.crudrepo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class StudentPojo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@NotNull(message = "LastName can not be null!!")
	@NotEmpty(message = "LastName can not be empty!!")
	private String name;
	
	@NotNull(message = "Choose the subject count you are going to study!")
	@Min(value = 4, message = "Student should enroll to minimum 4 subjects!!")
	@Max(value = 8, message = "Student can enroll to maximum 8 subjects!!")
	private int subjectCount;
	
	@NotNull
	@Min(1)
	@Max(12)
	private int grade;
	
	@NotNull
	@Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
	@Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
	private String mobileNo;
	
	@NotNull(message = "Please enter birth date")
	@Past(message = "Birth date should be less than current date!!")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate birthDate;
}





```

---

### 9. FileInfo.java

#### model\FileInfo.java

```java

package com.suji.fileuploads.model;

public class FileInfo {
	
	private String name;
	private String url;

	public FileInfo(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
```

---

### 10. FilesController.java

#### controller\FilesController.java

```java

package com.suji.fileuploads.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.suji.fileuploads.message.ResponseMessage;
import com.suji.fileuploads.model.FileInfo;
import com.suji.fileuploads.service.FilesStorageService;

@Controller
//@CrossOrigin("http://localhost:8081")
public class FilesController {
	@Autowired
	FilesStorageService storageService;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.save(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
			return new FileInfo(filename, url);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
```

---

### 11. EmailService.java

#### service\EmailService.java

```java

package com.suji.crudrepo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String toEmail, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        //Caused by: org.springframework.mail.MailAuthenticationException: Authentication failed;
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("sujthmsjs@gmail.com");

        javaMailSender.send(mailMessage);
    }
}
```

---

### 12. PersonService.java

#### service\PersonService.java

```java

package com.suji.crudrepo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suji.crudrepo.model.PersonPojo;
import com.suji.crudrepo.repository.PersonRepository;

@Service
public class PersonService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonService.class);

	private PersonRepository repository;

	public PersonService(PersonRepository repository) {
		LOG.info("PersonRepository: "+repository);
		this.repository = repository;
	}
	
	public PersonPojo savePerson(PersonPojo student) {
		PersonPojo save = repository.save(student);
		if(save != null)
			LOG.debug("Persaon Datasaved: "+student);
		else
			LOG.error("Person Data not saved: "+student);
		
		return save;
	}
	
	
	
}

```

---

### 13. StudentService.java

#### service\StudentService.java

```java

package com.suji.crudrepo.service;

import org.springframework.stereotype.Service;
import com.suji.crudrepo.model.StudentPojo;
import com.suji.crudrepo.repository.StudentRepository;




@Service
public class StudentService {

	private StudentRepository repository;

	public StudentService(StudentRepository repository) {
		System.out.println("StudentRepository Autowiring...");
		this.repository = repository;
	}
	
	public StudentPojo saveStudent(StudentPojo student) {
		return repository.save(student);
	}
}

```

---

### 14. FilesStorageService.java

#### service\FilesStorageService.java

```java

package com.suji.fileuploads.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	public void init();

	public void save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();
}

```

---

### 15. PersonRepository.java

#### repository\PersonRepository.java

```java

package com.suji.crudrepo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.crudrepo.model.PersonPojo;

public interface PersonRepository extends JpaRepository<PersonPojo, Integer> {

	boolean existsByUsername(String value);

}

```

---

### 16. StudentRepository.java

#### repository\StudentRepository.java

```java

package com.suji.crudrepo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suji.crudrepo.model.StudentPojo;

public interface StudentRepository extends JpaRepository<StudentPojo, Integer> {

}

```

---

### 17. CrudOpsApplication.java

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

### 18. StudentCtrl.java

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

### 19. Student.java

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

### 20. CrudRepoDemoApplication.java

#### src\main\java\com\suji\crudrepo\CrudRepoDemoApplication.java

```java

package com.suji.crudrepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudRepoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRepoDemoApplication.class, args);
	}

}

```

---

### 21. MyRunner.java

#### src\main\java\com\suji\crudrepo\MyRunner.java

```java

package com.suji.crudrepo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.suji.crudrepo.service.EmailService;

@Component
public class MyRunner  implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

	@Override
	public void run(String... args) throws Exception {
	
		logger.debug("Email Sent Successfully.");
		logger.info("This is just some info");
		logger.debug("This is just some info");
	}
}

```

---

### 22. DateTimeConfig.java

#### src\main\java\com\suji\crudrepo\configuration\DateTimeConfig.java

```java

package com.suji.crudrepo.configuration;

import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class DateTimeConfig extends WebMvcConfigurationSupport {

	@Override
	public FormattingConversionService mvcConversionService() {
		
		return super.mvcConversionService();
	}
}

```

---

### 23. WebApplicationConfig.java

#### src\main\java\com\suji\crudrepo\configuration\WebApplicationConfig.java

```java

package com.suji.crudrepo.configuration;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/notFound").setViewName("forward:/error-page.html");
	}

	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
		
		return container -> {
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notFound"));
		};
	}

}
```

---

### 24. ContactNumberConstraint.java

#### src\main\java\com\suji\crudrepo\configuration\validation\ContactNumberConstraint.java

```java

package com.suji.crudrepo.configuration.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ContactNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactNumberConstraint {

	String message() default "Invalid phone number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}


```

---

### 25. ContactNumberValidator.java

#### src\main\java\com\suji\crudrepo\configuration\validation\ContactNumberValidator.java

```java

package com.suji.crudrepo.configuration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

	@Override
	public void initialize(ContactNumberConstraint contactNumber) {
	}

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
		
		System.out.println("Out for validation: " + contactField);
		return (contactField != null) && contactField.matches("[0-9]+") && (contactField.length() == 10);
	}

}
```

---

### 26. FieldsValueMatch.java

#### src\main\java\com\suji\crudrepo\configuration\validation\FieldsValueMatch.java

```java

package com.suji.crudrepo.configuration.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
```

---

### 27. FieldsValueMatchValidator.java

#### src\main\java\com\suji\crudrepo\configuration\validation\FieldsValueMatchValidator.java

```java

package com.suji.crudrepo.configuration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

	private String field;
	private String fieldMatch;

	public void initialize(FieldsValueMatch constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

		System.out.println(field+":"+fieldValue+"; "+fieldMatch+":"+fieldMatchValue);
		
		if (fieldValue != null) {
			System.out.println("Password doesn't match.");
			return fieldValue.equals(fieldMatchValue);
		} else {
			System.out.println("Password matched.");
			return fieldMatchValue == null;
		}
	}
}
```

---

### 28. PersonConstraint.java

#### src\main\java\com\suji\crudrepo\configuration\validation\PersonConstraint.java

```java

package com.suji.crudrepo.configuration.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonValidator.class)
public @interface PersonConstraint {
	
	String message() default "Username already Exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

```

---

### 29. PersonValidator.java

#### src\main\java\com\suji\crudrepo\configuration\validation\PersonValidator.java

```java

package com.suji.crudrepo.configuration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suji.crudrepo.MyRunner;
import com.suji.crudrepo.model.PersonPojo;
import com.suji.crudrepo.repository.PersonRepository;

public class PersonValidator implements ConstraintValidator<PersonConstraint, PersonPojo> {

	private static final Logger LOG = LoggerFactory.getLogger(MyRunner.class);
	
	@Autowired
	private PersonRepository service;
	
	@Override
	public boolean isValid(PersonPojo person, ConstraintValidatorContext context) {
		
		String username = person.getUsername();

		boolean isExists = service.existsByUsername(username);
		LOG.info("Username: "+username+"; isExists? "+isExists);
		
		boolean isPasswordMatched = person.getPassword().equals(person.getVerifyPassword());
		LOG.info("Is Password Matched? "+isPasswordMatched);
		
		boolean isValid = (!isExists) && isPasswordMatched;
		if(isValid)
			LOG.debug("Username and passwords are valid.");
		else
			LOG.error("Username and passwords aren't valid.");
		return (!isExists) && isPasswordMatched;
	}

}

```

---

### 30. UniquePersonUsernameContraint.java

#### src\main\java\com\suji\crudrepo\configuration\validation\UniquePersonUsernameContraint.java

```java

package com.suji.crudrepo.configuration.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniquePersonUsernameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePersonUsernameContraint {

	String message() default "Username already Exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}


```

---

### 31. UniquePersonUsernameValidator.java

#### src\main\java\com\suji\crudrepo\configuration\validation\UniquePersonUsernameValidator.java

```java

package com.suji.crudrepo.configuration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suji.crudrepo.MyRunner;
import com.suji.crudrepo.repository.PersonRepository;
import com.suji.crudrepo.service.PersonService;

public class UniquePersonUsernameValidator implements ConstraintValidator<UniquePersonUsernameContraint, String> {

	private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);
	
	@Autowired
	private PersonRepository service;
	
	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
	
		logger.info(contactField);
		boolean isExists = service.existsByUsername(contactField);
		
		System.out.println("Username at validation: "+contactField+"; isExists: "+isExists);
	 	return isExists;
	}

}
```

---

### 32. GlobalDefaultExceptionHandler.java

#### src\main\java\com\suji\crudrepo\control\GlobalDefaultExceptionHandler.java

```java

package com.suji.crudrepo.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.suji.crudrepo.model.PersonPojo;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("personPojo", new PersonPojo());
		mav.setViewName("/person/signup");
		return mav;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public ModelAndView handleConflict() {
		System.out.println("404: Error!");
		ModelAndView mav = new ModelAndView();
		mav.addObject("personPojo", new PersonPojo());
		mav.setViewName("");
		return mav;
	}

}

```

---

### 33. PersonController.java

#### src\main\java\com\suji\crudrepo\control\PersonController.java

```java

package com.suji.crudrepo.control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Binding;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suji.crudrepo.MyRunner;
import com.suji.crudrepo.model.PersonPojo;
import com.suji.crudrepo.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

	private static final Logger LOG = LoggerFactory.getLogger(MyRunner.class);

	@Autowired
	private PersonService personService;

	@RequestMapping("/signup")
	public String getSignupForm(PersonPojo person) {
		System.out.println("Loading Form: " + person);
		return "person/signup";
	}

	@PostMapping("/save-person")
	public String saveFormData(@Valid PersonPojo person, BindingResult results) {
		LOG.info("Inside /save-person, errors has been thrown.");

		if (results.hasErrors()) {
			generateFieldErrors(results);
			LOG.error("Form validation failed...");
			return "person/signup";
		}
		
		PersonPojo savedPerson = personService.savePerson(person);
		LOG.debug("Person data saved: " + savedPerson);
		return "/person/welcome";
	}

	private void generateFieldErrors(BindingResult result) {

		List<ObjectError> globalError = result.getGlobalErrors();
		

		for (ObjectError objectError : globalError) {

			LOG.error("Global Errors: " + objectError.getCode());
	

			if (objectError.getCode().equalsIgnoreCase("PersonConstraint")) {
				FieldError fc = new FieldError(objectError.getObjectName(), "verifyPassword", objectError.getDefaultMessage());
				FieldError fc2 = new FieldError(objectError.getObjectName(), "password",
						objectError.getDefaultMessage());

				System.out.println(fc);
				result.addError(fc);
				result.addError(fc2);
				Object[] arguments = objectError.getArguments();
				for (Object obj : arguments) {
					System.out.println("Arg: " + obj);
				}
			}
		}
	}

	private Map<String, String> generateObjectErrors(BindingResult result) {
		Map<String, String> fieldErrorMap = new HashMap<>();
		result.getGlobalErrors().forEach(objectError -> {
			if (objectError.getCode().contains("ConfirmPassword")) {
				fieldErrorMap.put("confirmPassword", objectError.getDefaultMessage());
			}
		});
		return fieldErrorMap;
	}

}

```

---

### 34. StudentController.java

#### src\main\java\com\suji\crudrepo\control\StudentController.java

```java

package com.suji.crudrepo.control;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suji.crudrepo.model.StudentPojo;
import com.suji.crudrepo.service.StudentService;
import com.suji.crudrepo.util.PrintUtil;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@RequestMapping("/signup")
	public String getSignupForm(StudentPojo student, Model model) {
		return "student/signup";
	}
	
	@RequestMapping("/save-student")
	public String saveStudentForm(@Valid StudentPojo student, BindingResult results, Model model) {
		
		if(results.hasErrors()) {
			PrintUtil.showBindingResult(results);
			return "student/signup";
		}
		
		StudentPojo savedStudent = service.saveStudent(student);
		model.addAttribute("message", savedStudent.getName()+" You're id id: "+savedStudent.getId());
		return "student/welcome"; 
	}
}

```

---

### 35. PrintUtil.java

#### src\main\java\com\suji\crudrepo\util\PrintUtil.java

```java

package com.suji.crudrepo.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class PrintUtil {

	public static void showBindingResult(BindingResult results) {
		List<FieldError> error = results.getFieldErrors();
		for (FieldError fieldError : error) {
			System.out.println(
					"Field Name: " + fieldError.getField() + "; Error Message : " + fieldError.getDefaultMessage());
		}
	}

}

```

---

### 36. FileUploadsWorkApplication.java

#### src\main\java\com\suji\fileuploads\FileUploadsWorkApplication.java

```java

package com.suji.fileuploads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suji.fileuploads.service.FilesStorageService;

@SpringBootApplication
public class FileUploadsWorkApplication implements CommandLineRunner {

	@Autowired
	private FilesStorageService filesStorageService;
	
	public static void main(String[] args) {
		SpringApplication.run(FileUploadsWorkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		filesStorageService.deleteAll();
		filesStorageService.init();
	}
	

}

```

---

### 37. FileUploadExceptionAdvice.java

#### src\main\java\com\suji\fileuploads\exception\FileUploadExceptionAdvice.java

```java

package com.suji.fileuploads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.suji.fileuploads.message.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
	
	}
}

```

---

### 38. ResponseMessage.java

#### src\main\java\com\suji\fileuploads\message\ResponseMessage.java

```java

package com.suji.fileuploads.message;

public class ResponseMessage {
	private String message;

	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
```

---

### 39. FilesStorageServiceImpl.java

#### src\main\java\com\suji\fileuploads\service\impl\FilesStorageServiceImpl.java

```java

package com.suji.fileuploads.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.suji.fileuploads.service.FilesStorageService;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	
	private final Path root = Paths.get("uploads");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}
}
```

---

### 40. editStd.html

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

### 41. home.html

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

### 42. newStd.html

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

### 43. test.html

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

### 44. error-page.html

#### resources\templates\error-page.html

```html

<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Registration page</title>
	<style>
		h1{
			text-align: center;
			color:red;
		}
	</style>
</head>

<body>
	<h2>Error Page</h2>
</body>

</html>
```

---

### 45. signup.html

#### resources\templates\person\signup.html

```html

<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Registration page</title>
	<style>
		p{
			display:inline;
			color:red;
		}
	</style>
</head>

<body>
	<h2>Person Signup form</h2>

	<form th:action="@{/person/save-person}" th:object="${personPojo}" method="POST">
		
		<label for="fname">Name:</label><br />
		<input type="text" th:field="*{name}" name="name">
		<p th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></p>
		
		<br />

		<label for="uname">Username:</label><br>
		<input type="text" th:field="*{username}" name="username">
		<p th:if="${#fields.hasErrors('username')}" th:errors="*{username}"></p>
		
		<br />

		<label for="lname">Password:</label><br />
		<input type="password" th:field="*{password}" name="password">
		<p th:if="${#fields.hasErrors('password')}" th:errors="*{password}"></p>
		
		<br />

		<label for="lname">Password Again:</label><br />
		<input type="password" th:field="*{verifyPassword}" name="verifyPassword">
		<p th:if="${#fields.hasErrors('verifyPassword')}" th:errors="*{verifyPassword}"></p>
		
		<br />

		<label for="lname">Date of Birth:</label><br />
		<input type="date" th:field="*{dob}" name="dob">
		<p th:if="${#fields.hasErrors('dob')}" th:errors="*{dob}"></p>
		
		<br />
		
		<label for="lname">Height:</label><br />
		<input type="number" th:field="*{height}" name="height"><br />
		<p th:if="${#fields.hasErrors('dob')}" th:errors="*{dob}"></p>
		
		<br />
		
		<input type="checkbox" th:field="*{hasNCC}" name="hasNCC" value="hasNCC">
		<label for="vehicle1">Have NCC</label><br />
		
		<br />

		<input type="radio" th:field="*{gender}" name="gender" value="M" checked> Male
		<input type="radio" th:field="*{gender}" name="gender" value="F"> Female
		<p th:if="${#fields.hasErrors('gender')}" th:errors="*{gender}"> </p>

		<br />
		<input type="submit" value="Register" />
		<input type="reset" />
	</form>

</body>

</html>
```

---

### 46. welcome.html

#### resources\templates\person\welcome.html

```html

<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Registration page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body>
	<div align="center" th:object="${personPojo}" class="col-md-12">
	
		<h2>id: <span th:text="*{id}"></span></h2>
		<h2>name: <span th:text="*{name}"></span></h2>
		<h2>username: <span th:text="*{username}"></span></h2>
		<h2>password: <span th:text="*{password}"></span></h2>
		<h2>gender: <span th:text="*{gender}"></span></h2>
		<h2>dob: <span th:text="*{dob}"></span></h2>
		<h2>height: <span th:text="*{height}"></span></h2>
		<h2>hasNCC: <span th:text="*{hasNCC}"></span></h2>
		<h2>registered: <span th:text="*{registered}"></span></h2>
		<h2>lastUpdate: <span th:text="*{lastUpdate}"></span></h2>
		
	</div>
</body>

</html>
```

---

### 47. signup.html

#### resources\templates\student\signup.html

```html

<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Registration page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body>
	<div align="center" class="col-md-12">
		<h2>Welcome to Student registration page</h2>
		<div class="col-md-6">
			<form action="#" th:action="@{/student/save-student}" th:object="${studentPojo}" method="post">
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" class="form-control" id="name" th:field="*{name}">
					<p class="alert alert-danger" th:if="${#fields.hasErrors('name')}" th:errors="*{name}">
				</div>
				<div class="form-group">
					<label for="subjectCount">Subjects Count:</label>
					<input type="number" class="form-control" id="subjectCount" th:field="*{subjectCount}">
					<p class="alert alert-danger" th:if="${#fields.hasErrors('subjectCount')}"
						th:errors="*{subjectCount}" />
				</div>
				<div class="form-group">
					<label for="grade">Grade:</label>
					<input type="number" class="form-control" id="grade" th:field="*{grade}" />
					<p class="alert alert-danger" th:if="${#fields.hasErrors('grade')}" th:errors="*{grade}" />
				</div>
				<div class="form-group">
					<label for="mobile">Mobile Number:</label>
					<input type="number" class="form-control" id="mobile" th:field="*{mobileNo}" />
					<p class="alert alert-danger" th:if="${#fields.hasErrors('mobileNo')}" th:errors="*{mobileNo}" />
				</div>
				<div class="form-group">
					<label for="bd">Birth Date:</label>
					<input type="date" class="form-control" id="bd" th:field="*{birthDate}" />
					<p class="alert alert-danger" th:if="${#fields.hasErrors('birthDate')}" th:errors="*{birthDate}" />
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
	</div>
</body>

</html>
```

---

### 48. welcome.html

#### resources\templates\student\welcome.html

```html

<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Registration page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>

<body>
	<div align="center" class="col-md-12">
		<h2>Welcome to Student registration page</h2>
		<div class="col-md-6">
			<h1>Welcome, <span th:text="${message}"></span></h1>
		</div>
		<a th:href="@{student/signup}">Back to Signup</a>
	</div>
</body>

</html>
```

---

### 49. basicstyles.css

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

### 50. CrudOpsApplicationTests.java

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

### 51. CrudRepoDemoApplicationTests.java

#### src\test\java\com\suji\crudrepo\CrudRepoDemoApplicationTests.java

```java

package com.suji.crudrepo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudRepoDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

### 52. FileUploadsWorkApplicationTests.java

#### src\test\java\com\suji\fileuploads\FileUploadsWorkApplicationTests.java

```java

package com.suji.fileuploads;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileUploadsWorkApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

