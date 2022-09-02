# spring-data-jpg-prac


### File Structure
```pre
+ spring-data-jpg-prac\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\tech\suji
		|---  SpringDataJpgPracApplication.java
	+ \src\test\java\tech\suji
		|---  SpringDataJpgPracApplicationTests.java
	+ \src\main\java\tech\suji\model
		|---  Answer.java
		|---  Employee.java
		|---  Gender.java
		|---  Question.java
		|---  Student.java
	+ \src\main\java\tech\suji\repository
		|---  EmployeeRepository.java
		|---  QuestionRepository.java
		|---  StudentRepository.java
		|---  StudentRepositoryDepricated.java
	+ \src\main\java\tech\suji\util
		|---  StudentUtil.java
```
### Index
```pre
1. application.properties
2. model\Answer.java
3. model\Employee.java
4. model\Gender.java
5. model\Question.java
6. model\Student.java
7. repository\EmployeeRepository.java
8. repository\QuestionRepository.java
9. repository\StudentRepository.java
10. repository\StudentRepositoryDepricated.java
11. src\main\java\tech\suji\SpringDataJpgPracApplication.java
12. src\main\java\tech\suji\util\StudentUtil.java
13. src\test\java\tech\suji\SpringDataJpgPracApplicationTests.java

```

---

### 1. application.properties

#### application.properties

```properties


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.username=root
spring.datasource.password=apple
spring.datasource.url=jdbc:mysql://localhost:3306/bigdata
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect


#driverClassName: com.mysql.cj.jdbc.Driver
#dialect: org.hibernate.dialect.MySQL8Dialect

```

---

### 2. Answer.java

#### model\Answer.java

```java

package tech.suji.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "answers")
public class Answer {

	public Answer(String ansString) {
		this.answername = ansString;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ans_id;
    
    private String answername;
}



```

---

### 3. Employee.java

#### model\Employee.java

```java

package tech.suji.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="e")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empno;
	
	@Column(length = 15)
	private String ename;
	
	@Column(length = 15)
	private String job;
	
	@Column(length = 15)
	private String mgr;
	
	
	private LocalDate hiredate;
	
	@Column(length = 4)
	private BigDecimal sal; //decimal(19,2)
	private BigDecimal comm; //decimal(19,2) 
	
	private int deptno;

}

```

---

### 4. Gender.java

#### model\Gender.java

```java

package tech.suji.model;

public enum Gender {
	Male,Female;
}

```

---

### 5. Question.java

#### model\Question.java

```java

package tech.suji.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;

@Data
@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int que_id;


	private String qname;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "qid")
	@OrderColumn(name = "type")
	private List<Answer> answers = new ArrayList<Answer>();

	

	public Question(String qname, List<Answer> answers) {
		super();
		this.qname = qname;
		this.answers = answers;
	}



	public Question() {
		super();
	}

	
	
}



```

---

### 6. Student.java

#### model\Student.java

```java

package tech.suji.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;




@Data
@Entity
@Table(name ="students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 15, unique = true)
	private String name;
	
	@Column(length  = 15, nullable = false)
	private String password;
	
	private boolean active;
	
	private Gender gender;
	
	private LocalDate dob;
	
	private Year joinYear;
	
	private int math;
	private int science;
	private int english;

	@Column(length = 4)
	private BigDecimal height; //decimal(19,2) 
	
	@CreatedDate
	private LocalDateTime created;
	
	@LastModifiedDate
	private LocalDateTime lastUpdated;
	
	
	public Student() {
		super();
	}


	public Student(String name, String password, boolean active, Gender gender, LocalDate dob, Year joinYear, int math,
			int science, int english, BigDecimal height) {
		super();
		this.name = name;
		this.password = password;
		this.active = active;
		this.gender = gender;
		this.dob = dob;
		this.joinYear = joinYear;
		this.math = math;
		this.science = science;
		this.english = english;
		this.height = height;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", active=" + active + ", gender=" + gender + ", dob=" + dob
				+ "]";
	}


	


	
	
	
	
	
}


```

---

### 7. EmployeeRepository.java

#### repository\EmployeeRepository.java

```java

package tech.suji.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.suji.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


	List<Employee> findAllByOrderByEname();
	List<Employee> findAllByOrderByEnameDesc();
	List<Employee> findAllByOrderBySalDesc();
	List<Employee> findAllByOrderBySalAsc();
	List<Employee> findAllByOrderBySal();
	
	List<Employee> findByEname(String name);
	List<Employee> findByEnameIs(String name);
	List<Employee> findByEnameEquals(String name);
	List<Employee> findByEnameIsNot(String name);
	
	List<Employee> findByCommIsNull();
	List<Employee> findByCommIsNotNull();
	
	List<Employee> findByEnameStartingWith(String prefix);
	List<Employee> findByEnameStartingWithAndEnameEndingWith(String prefix,String suffix);
	List<Employee> findByEnameContaining(String infix);
	List<Employee> findByEnameEndingWith(String suffix);

	List<Employee> findByEnameLike(String likePattern);
	List<Employee> findByEnameNotLike(String likePattern);
	
	
	List<Employee> findBySalLessThan(BigDecimal sal);
	List<Employee> findBySalLessThanEqual(BigDecimal sal);
	List<Employee> findBySalGreaterThanEqual(BigDecimal sal);
	List<Employee> findBySalGreaterThan(BigDecimal bigDecimal);
	List<Employee> findBySalLessThanOrderBySalAsc(BigDecimal bigDecimal);
	List<Employee> findBySalLessThanAndCommNotNull(BigDecimal bigDecimal);
	List<Employee> findBySalLessThanAndCommNotNullOrderByComm(BigDecimal bigDecimal);
	
	List<Employee> findByDeptno(int i);
	List<Employee> findBySalBetween(BigDecimal bigDecimal, BigDecimal bigDecimal2);
	List<Employee> findByDeptnoIn(List<Integer> collect);
	
	Optional<Employee> getByEname(String name);
	
	boolean existsByEname(String value);
	
	
	Employee getByUsername(String name);
	
	List<User> findByActiveTrue();
	List<User> findByActiveFalse();
	
	List<User> findByBirthDateAfter(ZonedDateTime birthDate);
	List<User> findByBirthDateBefore(ZonedDateTime birthDate);
	
	List<User> findByNameOrBirthDate(String name, ZonedDateTime birthDate);
	List<User> findByNameOrBirthDateAndActive(String name, ZonedDateTime birthDate, Boolean active);
	
}

```

---

### 8. QuestionRepository.java

#### repository\QuestionRepository.java

```java

package tech.suji.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.suji.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}

```

---

### 9. StudentRepository.java

#### repository\StudentRepository.java

```java

package tech.suji.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tech.suji.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query(value = "SELECT u FROM Student u WHERE u.math >=35 AND u.science >= 35 AND u.english >= 35")
	Page<Student> findStudentsWhoPassed(Pageable pageable);

	@Query(value = "SELECT u FROM Student u WHERE u.math < 35 OR u.science < 35 OR u.english < 35")
	Page<Student> findStudentsWhoFailed(Pageable pageable);

	// List<Student> findStudentsWhoPassed();
	// Page<T> findAll(Pageable pageable)

}

```

---

### 10. StudentRepositoryDepricated.java

#### repository\StudentRepositoryDepricated.java

```java

package tech.suji.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import tech.suji.model.Gender;
import tech.suji.model.Student;

@Deprecated
public interface StudentRepositoryDepricated{

	Student findByName(String string);

	List<Student> findByDobAfter(LocalDate of);

	List<Student> findByDobBefore(LocalDate of);

	List<Student> findByActiveTrue();

	List<Student> findByActiveFalse();

	List<Student> findByOrderByName();

	List<Student> findByOrderByCgpaDesc();

	List<Student> findByOrderByDob();

	Student findFirstByOrderByName();

	Optional<Student> findFirstByOrderByCgpa();

	Optional<Student> findTopByOrderByCgpaDesc();

	List<Student> findTop3ByOrderByCgpaDesc();

	Optional<Student> findFirstByName(String string);

	Optional<Student> findFirstByGender(Gender gender);

	Optional<Student> findFirstByGenderOrderByCgpaDesc(Gender gender);

	Optional<Student> findFirstByGenderOrderByCgpa(Gender gender);

	List<Student> findFirst3ByOrderByCgpaDesc();

	List<Student> findAll(Sort sort);

	// Page<Product> allProducts =
	// productRepository.findAll(firstPageWithTwoElements);

	// List<Product> allTenDollarProducts = productRepository.findAllByPrice(10,
	// secondPageWithFiveElements);

	// Pageable sortedByName = PageRequest.of(0, 3, Sort.by("name"));

	// Pageable sortedByPriceDesc = PageRequest.of(0, 3, Sort.by("price").descending());

	// Pageable sortedByPriceDescNameAsc = PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));

}

```

---

### 11. SpringDataJpgPracApplication.java

#### src\main\java\tech\suji\SpringDataJpgPracApplication.java

```java

package tech.suji;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tech.suji.model.Answer;
import tech.suji.model.Employee;
import tech.suji.model.Question;
import tech.suji.repository.EmployeeRepository;
import tech.suji.repository.QuestionRepository;

@SpringBootApplication
public class SpringDataJpgPracApplication implements ApplicationRunner {

	@Autowired
	private EmployeeRepository repo;
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringDataJpgPracApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("___________________________________________");

		repo.findAll().forEach(System.out :: println);
		
		Optional<Employee> ename = repo.getByEname("sujith");
		System.out.println(ename.isPresent()+": "+ename.isEmpty());
		
		System.out.println("___________________________________________");

	}
}
```

---

### 12. StudentUtil.java

#### src\main\java\tech\suji\util\StudentUtil.java

```java

package tech.suji.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import tech.suji.model.Gender;
import tech.suji.model.Student;

public class StudentUtil {
	public static Student generateStudent() {

		String[] letters = { "ka", "zu", "mi", "te", "tu", "lu", "ji", "ri", "ki", "zu", "me", "ta", "rin", "to", "mo",
				"no", "ke", "shi", "ari", "chi", "do", "ru", "mei", "na", "fu", "z" };

		String[] male = { "k", "th", "m", "n", "m", "z", "q", "h", "sh" };
		String[] female = { "a", "i" };
		Gender gender;

		Random r = new Random();

		List<String> list = r.ints((int) (Math.random() * 2 + 1), 1, letters.length).boxed().map(n -> letters[n])
				.collect(Collectors.toList());

		if (r.nextBoolean()) {
			list.add(male[r.nextInt(male.length)]);
			gender = Gender.Male;
		} else {
			list.add(male[r.nextInt(male.length)]);
			list.add(female[r.nextInt(female.length)]);
			gender = Gender.Female;
		}
		StringBuilder name = new StringBuilder();
		list.forEach(s -> name.append(s));

		boolean isActive = r.nextBoolean();
		int science = r.nextInt(101);
		int math = r.nextInt(101);
		int english = r.nextInt(101);

		Supplier<LocalDate> dobSupplier = () -> {
			LocalDate date = null;
			while(date == null) {
				try {
					date = LocalDate.of(r.nextInt(12) + 1990, r.nextInt(12) + 1, r.nextInt(31) + 1);
				} catch (Exception e) {	
					System.out.println(e.getMessage());
				}
			}
			return date;
		};

		Supplier<Boolean> activeSupplier = () -> r.nextInt(3)!=0; //0,1,2 
		Supplier<Year> yearSupplier = () -> Year.of(r.nextInt(12) + 2010);
		Supplier<BigDecimal> heightSupplier = () -> new BigDecimal((Math.random() * 2.0)+4.2);
		
		return new Student(name.toString(), name.toString(), activeSupplier.get(), gender, dobSupplier.get(), yearSupplier.get(), math, science, english, heightSupplier.get());
	}

}

```

---

### 13. SpringDataJpgPracApplicationTests.java

#### src\test\java\tech\suji\SpringDataJpgPracApplicationTests.java

```java

package tech.suji;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJpgPracApplicationTests {

	@Test
	void contextLoads() {
	}

}

```

---

