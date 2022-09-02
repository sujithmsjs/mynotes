# streamstest


### File Structure
```pre
+ streamstest\ 
	+ \src\main\java\com\suji\streamstest
		|---  App.java
		|---  BigDecimalPrac.java
		|---  EpochDayExample.java
		|---  FilterDemo.java
		|---  FlatMaps.java
		|---  IntStreamDemo.java
		|---  MethodReference01.java
		|---  MethodReference02.java
		|---  NCopiesDemo.java
		|---  PassingBehaviours.java
		|---  PassingBehaviours2.java
		|---  PredicateNotDemo.java
		|---  PrintPrimeUsingStreams.java
		|---  RandomGenerators.java
		|---  RandomGenerators2.java
		|---  ReduceDemo.java
		|---  ReduceDemo2.java
		|---  ReduceFunctions.java
		|---  StreamGroupBy.java
		|---  StringUpdate11.java
		|---  ToArrayVerstion11.java
	+ \src\main\java\com\suji\streamstest\model
		|---  Author.java
		|---  Book.java
		|---  BooksProvider.java
		|---  Emp.java
		|---  Employee.java
		|---  EmployeeRepo.java
		|---  Institute.java
		|---  Z.java
	+ \src\main\java\com\suji\streamstest\util
		|---  JwtUtil.java
```
### Index
```pre
1. \model\Author.java
2. \model\Book.java
3. \model\BooksProvider.java
4. \model\Emp.java
5. \model\Employee.java
6. \model\EmployeeRepo.java
7. \model\Institute.java
8. \model\Z.java
9. \src\main\java\com\suji\streamstest\App.java
10. \src\main\java\com\suji\streamstest\BigDecimalPrac.java
11. \src\main\java\com\suji\streamstest\EpochDayExample.java
12. \src\main\java\com\suji\streamstest\FilterDemo.java
13. \src\main\java\com\suji\streamstest\FlatMaps.java
14. \src\main\java\com\suji\streamstest\IntStreamDemo.java
15. \src\main\java\com\suji\streamstest\MethodReference01.java
16. \src\main\java\com\suji\streamstest\MethodReference02.java
17. \src\main\java\com\suji\streamstest\NCopiesDemo.java
18. \src\main\java\com\suji\streamstest\PassingBehaviours.java
19. \src\main\java\com\suji\streamstest\PassingBehaviours2.java
20. \src\main\java\com\suji\streamstest\PredicateNotDemo.java
21. \src\main\java\com\suji\streamstest\PrintPrimeUsingStreams.java
22. \src\main\java\com\suji\streamstest\RandomGenerators.java
23. \src\main\java\com\suji\streamstest\RandomGenerators2.java
24. \src\main\java\com\suji\streamstest\ReduceDemo.java
25. \src\main\java\com\suji\streamstest\ReduceDemo2.java
26. \src\main\java\com\suji\streamstest\ReduceFunctions.java
27. \src\main\java\com\suji\streamstest\StreamGroupBy.java
28. \src\main\java\com\suji\streamstest\StringUpdate11.java
29. \src\main\java\com\suji\streamstest\ToArrayVerstion11.java
30. \src\main\java\com\suji\streamstest\util\JwtUtil.java

```

---

### 1. Author.java

#### \model\Author.java

```java

package com.suji.streamstest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	private String name;
	private int age;
	private int books;
}

```

---

### 2. Book.java

#### \model\Book.java

```java

package com.suji.streamstest.model;

import java.time.LocalDate;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Comparator<Book> {

	private Author author;
	private int pageCount;
	private String title;
	private LocalDate releaseDate;
	
	public Book(String title) {
		super();
		this.title = title;
	}

	@Override
	public int compare(Book o1, Book o2) {
		
		return 0;
	}

	
	
	
	
}

```

---

### 3. BooksProvider.java

#### \model\BooksProvider.java

```java

package com.suji.streamstest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BooksProvider {
	public static List<Book> findAll(){
		
		List<Book> list = new ArrayList<>();
		
		list.add(new Book(new Author("Author a",18,4),30,"Book 1",LocalDate.of(1994, 2, 7)));
		list.add(new Book(new Author("Author a",18,4),30,"Book 2",LocalDate.of(2000, 3, 6)));
		list.add(new Book(new Author("Author b",10,1),30,"Book 3",LocalDate.of(2005, 5, 11)));
		list.add(new Book(new Author("Author c",29,10),30,"Book 4",LocalDate.of(2010, 6, 24)));
		
		return list;
	}
}

```

---

### 4. Emp.java

#### \model\Emp.java

```java

package com.suji.streamstest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
	
	public static final List<Emp> list = new ArrayList<>();
	
	static {
		list.add(new Emp(1, new BigDecimal("4000")));
		list.add(new Emp(2, new BigDecimal("7000")));
		list.add(new Emp(2, new BigDecimal("5000")));
		list.add(new Emp(1, new BigDecimal("9000")));
	}
	
	private int deptno;
	private BigDecimal salary;
	
	public static List<Emp> findAll(){
		return list;
	}
	 
	
	
}

```

---

### 5. Employee.java

#### \model\Employee.java

```java

package com.suji.streamstest.model;

import java.util.function.Function;

public class Employee
{
    int id;
     
    String name;
     
    int age;
     
    String gender;
     
    String department;
     
    int yearOfJoining;
     
    double salary;
    
    public Employee() {
	}
    
	public Employee(double salary) {
		this.salary = salary;
	}
	
	public <T> T get(Function<Employee, T> function) {
		return function.apply(this);
	}
	 



	public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) 
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }
     
    public int getId() 
    {
        return id;
    }
     
    public String getName() 
    {
        return name;
    }
     
    public int getAge() 
    {
        return age;
    }
     
    public String getGender() 
    {
        return gender;
    }
     
    public String getDepartment() 
    {
        return department;
    }
     
    public int getYearOfJoining() 
    {
        return yearOfJoining;
    }
     
    public double getSalary() 
    {
        return salary;
    }
     
    @Override
    public String toString() 
    {
        return "Id : "+id
                +", Name : "+name
                +", age : "+age
                +", Gender : "+gender
                +", Department : "+department
                +", Year Of Joining : "+yearOfJoining
                +", Salary : "+salary;
    }
}
```

---

### 6. EmployeeRepo.java

#### \model\EmployeeRepo.java

```java

package com.suji.streamstest.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {

	private static List<Employee> employeeList = new ArrayList<Employee>();
	
	static {
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

	}
	
	public static List<Employee>  getEmployees() {
		return employeeList;
	}
	
	
}

```

---

### 7. Institute.java

#### \model\Institute.java

```java

package com.suji.streamstest.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Institute {

	private String name;
	private List<String> locations = new ArrayList<>();

	public static List<Institute> getData() {
		List<Institute> instituteList = new ArrayList<>();
		instituteList.add(new Institute("IIM", Arrays.asList("Bangalore", "Ahmedabad", "Kozhikode", "Lucknow")));
		instituteList.add(new Institute("IIT", Arrays.asList("Delhi", "Mumbai", "Kharagpur")));
		instituteList.add(new Institute("NIFT", Arrays.asList("Hyderabad", "Mumbai", "Patna", "Bang")));
		return instituteList;
	}

	public Institute(String name, List<String> locations) {
		super();
		this.name = name;
		this.locations = locations;
	}

}

```

---

### 8. Z.java

#### \model\Z.java

```java

package com.suji.streamstest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Z {

	private int n;
	
	private String name;

	public Z(String name) {
		super();
		this.name = name;
	}

	public Z instanceMethod(Z z) {
		return new Z(this.getN() + z.getN(), this.getName().concat(z.getName()));
	}
	
	
	
}

```

---

### 9. App.java

#### \src\main\java\com\suji\streamstest\App.java

```java

package com.suji.streamstest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.StringJoiner;

public class App {
	
	public static void main2(String[] args) {
		
		String date = "";
		LocalDate ld = null;// LocalDate.now();
		
		Optional<LocalDate> nullable = Optional.ofNullable(ld);
		if(nullable.isPresent())
			date = ld.toString();
		
		System.out.println("Date: "+date);
		
		//--- Best Alternative
		Optional<String> map2 = Optional.ofNullable(ld).map(LocalDate::toString);
		System.out.println("LocalDate converted into String :"+map2);
		String map = Optional.ofNullable(ld).map(LocalDate::toString).orElse("No date found"); // if Id is null, No data found.
		System.out.println("Date: "+map);
		Optional.ofNullable(ld).map( t -> t.toString() );
		
		String name = new StringJoiner(" ").add("'Sujith").add(null).toString();
		System.out.println("Name: "+name);
		
		
		
		
	}

	public static void main3(String[] args) {
		
		Optional<String> optional = Optional.ofNullable(null);
		Optional<String> filter = optional.filter(s -> s.startsWith("j"));
		
		System.out.println("isPresent : "+filter.isPresent());
		//String string = filter.get(); // By default: NoSuchElementException
		
		Optional<Integer> map = optional.map(s -> s.length());
		map.ifPresent(System.out :: println);
		
		String string = filter.orElseThrow(() -> new NoMoneyException()); // Custom throw: NoSuchElementException

		System.out.println(filter);
		System.out.println(string);
		
		
		
	}
	
}

class NoMoneyException extends RuntimeException{
	
}

```

---

### 10. BigDecimalPrac.java

#### \src\main\java\com\suji\streamstest\BigDecimalPrac.java

```java

package com.suji.streamstest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalPrac {
	public static void main(String[] args) {

		BigDecimal bd = new BigDecimal("1232.31",new MathContext(1, RoundingMode.HALF_UP));
		System.out.println(bd);
		
	}
}

```

---

### 11. EpochDayExample.java

#### \src\main\java\com\suji\streamstest\EpochDayExample.java

```java

package com.suji.streamstest;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import com.suji.streamstest.model.Emp;

public class EpochDayExample {
	public static void main(String[] args) {

		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		System.out.println(randomDate);

	}
}

```

---

### 12. FilterDemo.java

#### \src\main\java\com\suji\streamstest\FilterDemo.java

```java

package com.suji.streamstest;

import java.util.List;
import java.util.stream.Collectors;

import com.suji.streamstest.model.Author;
import com.suji.streamstest.model.Book;
import com.suji.streamstest.model.BooksProvider;

public class FilterDemo 
{
    public static void main2( String[] args )
    {
    	List<Book> list = BooksProvider.findAll();
    	
    	// Extract Authors from the books
    	System.out.println("All:");
    	
    	List<Author> collect = list.stream().map( book -> book.getAuthor()).collect(Collectors.toList());
    	collect.forEach(System.out :: println);
    	
    	// Find Authors who's age is more than 15years
    	System.out.println("Age more than 15:");
    	 	
    	List<Author> list2 = list.stream().map( book -> book.getAuthor() ).filter( auth -> auth.getAge() > 15).collect(Collectors.toList());
    	list2.forEach(System.out :: println);
    	
    	// Get Author names in Uppercase of those who had written more than 2 books.
    	System.out.println("More than one book written Authors:");
    	
    	List<String> list3 = list.stream().map(Book :: getAuthor).filter( auth -> auth.getBooks() > 5).map(Author :: getName).map(String :: toUpperCase).collect(Collectors.toList());
    	list3.forEach(System.out :: println);
    	
    	
    	
    	
    	/*
    	 * Note
    	 * map( book -> book.getAuthor()) is equals to
    	 * map(Book :: getAuthor)
    	 */
    	
    	
        System.out.println( "Hello World!" );
    }
}

```

---

### 13. FlatMaps.java

#### \src\main\java\com\suji\streamstest\FlatMaps.java

```java

package com.suji.streamstest;

import java.util.List;

import com.suji.streamstest.model.Employee;
import com.suji.streamstest.model.EmployeeRepo;
import com.suji.streamstest.model.Institute;

public class FlatMaps {
	public static void main(String[] args) {
		
		List<Institute> list = Institute.getData();
		list.stream().map(Institute::getName).forEach(System.out :: println);
		
		list.stream().map(Institute :: getLocations).forEach(System.out :: println);
		//https://javaconceptoftheday.com/differences-between-java-8-map-and-flatmap/
		System.out.println("\nFlat Maps: \n");
		
		list.stream().flatMap( ins -> ins.getLocations().stream()).distinct().forEach(System.out :: println); 
		
		
		
		
	}
}



```

---

### 14. IntStreamDemo.java

#### \src\main\java\com\suji\streamstest\IntStreamDemo.java

```java

package com.suji.streamstest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IntStreamDemo 
{
    public static void main( String[] args )
    {
    	
    	int[] is = IntStream.of(42,3,21,56,11,12,18).distinct().sorted().toArray();
    	Optional<Integer> secMax = Optional.ofNullable(is[is.length-2]);
    	System.out.println(secMax);
    	
    	
    	int[] in2 = IntStream.of(42,3,3,21,0,56,42,3,3,21,56,11,12,18).toArray();
    	TreeSet<Integer> treeSet = Arrays.stream(in2).boxed().collect(Collectors.toCollection(TreeSet::new));
    	System.out.println(treeSet);
    	
    	
    	
    	
    	
    	
    	
    }
}

```

---

### 15. MethodReference01.java

#### \src\main\java\com\suji\streamstest\MethodReference01.java

```java

package com.suji.streamstest;

interface A{
	public void print();
}


public class MethodReference01 
{
    public static void main( String[] args )
    {
    	A a = new A() {
    		public void print() {
    			System.out.println("Hello");
    		}
    	};
    	a.print();
    	
    	//The target type of this expression must be a functional interface
    	A a2 = () -> {System.out.println("Hello Lamda");};
    	a2.print();
    	/*
    	A a3 = App::printStatic;
    	a3.print();
    	
    	A a4 = new App()::printInstance;
    	a4.print();
    	
    	A a5 = new App()::printAndReturn;
    	a5.print();
    	
    	A a6 = new App()::printAndReturnString;
    	a6.print();
    	
    	A a7 = new App()::printAndReturnString;
    	a7.print();
    	
    	A a8 = new String()::isBlank;
    	a8.print();
    	*/
    	//A a5 = App::printInstance; Cannot make a static reference to the non-static method printInstance() from the type App
    }
    
    public static void printStatic() {
    	System.out.println("Hello Static Method reference.");
    }
    
    public void printInstance() {
    	System.out.println("Hello Instance Method reference.");
    }
    
    public int printAndReturn() {
    	System.out.println("printAndReturn");
    	return 1;
    }
    
    public String printAndReturnString() {
    	System.out.println("printAndReturnString");
    	return "Demo text";
    }
    
    public String printAndReturnString(String str) {
    	System.out.println("printAndReturnString "+str);
    	return "Demo text : "+str;
    }
    
    
    
}

```

---

### 16. MethodReference02.java

#### \src\main\java\com\suji\streamstest\MethodReference02.java

```java

package com.suji.streamstest;

import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReference02 
{
    public static void main( String[] args )
    {
    	/*
    	 public interface Predicate<T> {
    	 	 boolean test(T t);
    	 }
    	 */
    	
    	Predicate<String> p = str -> str.isBlank();
    
    	Predicate<String> p2 = String::isBlank;
    	//Cannot make a static reference to the non-static method equalsIgnoreCase(String) from the type String
    	// Predicate<String> p3 = String::equalsIgnoreCase;
    	//Predicate<String> p4 = String::charAt; The type String does not define charAt(String) that is applicable here
    	
    	Predicate<String> p5 = String::isBlank;
    	
    	String str = "Demotest";
    	Function<String,Integer> s = str::compareTo; // str -> str.compareTo(T t)
    	//Function<String, Integer> s = String::compareTo; s->s.compareTo(?)
    	
    	
    	Function<String,Boolean> s2 = str::startsWith;
    	
    	System.out.println(s.apply("Abc"));
    	
    }
}

```

---

### 17. NCopiesDemo.java

#### \src\main\java\com\suji\streamstest\NCopiesDemo.java

```java

package com.suji.streamstest;

import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import com.suji.streamstest.model.Z;

import lombok.Data;

@Data
public class NCopiesDemo {
	public static void main(String[] args) {
		
		
		List<Z> copies = Collections.nCopies(10, new Z());
		List<String> list = List.of("a", "b", "b","d");
		
		
		
		System.out.println(list);
		Stream<Z> map = list.stream().map(Z::new);
		
		map.forEach(System.out :: println);
		
		UnaryOperator<Z> zUOp = z -> z.instanceMethod(copies.get(1));
		System.out.println(zUOp.apply(copies.get(2)));
		
		
			
		
	}
}

```

---

### 18. PassingBehaviours.java

#### \src\main\java\com\suji\streamstest\PassingBehaviours.java

```java

package com.suji.streamstest;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassingBehaviours 
{
    public static void main( String[] args )
    {
    	List<String> list = Stream.iterate("String", s -> s.concat("s")).limit(10).collect(Collectors.toList());
    	print(list, s -> s.length() > 8);
    	print(list, s -> s.length() < 8);
    	
    }
    
    public static <T> void  print(List<T> list, Predicate<T> predicate) {
    	list.stream().filter(predicate).forEach(System.out :: println);
    }
    
}

```

---

### 19. PassingBehaviours2.java

#### \src\main\java\com\suji\streamstest\PassingBehaviours2.java

```java

package com.suji.streamstest;

import com.suji.streamstest.model.Employee;

public class PassingBehaviours2 
{
    public static void main( String[] args )
    {
    	Employee e = new Employee(1, "Tony", 57, "Male", "Sales", 2021, 10_000);
    	
    	String name = e.get(Employee::getName);
    	Integer age = e.get(Employee::getAge);
    	Integer age2 = e.get(emp -> emp.getAge());
    	
    	
    	System.out.println(name);
    	System.out.println(age);
    	System.out.println(age2);
    }
}

```

---

### 20. PredicateNotDemo.java

#### \src\main\java\com\suji\streamstest\PredicateNotDemo.java

```java

package com.suji.streamstest;

import java.util.function.Predicate;

public class PredicateNotDemo 
{
    public static void main( String[] args )
    {
    	String str = "demo";
    	Predicate<String> predicate = Predicate.not(String::isBlank); // Takes T and returns boolean
    	Predicate<String> predicate2 = Predicate.not(s -> s.isBlank()); // Takes T and returns boolean
    	// String::isBlank = s -> s.isBlank(); String instance with the reference method.
    	boolean test = predicate.test(str);
    	System.out.println(test);
    	
    }
}

```

---

### 21. PrintPrimeUsingStreams.java

#### \src\main\java\com\suji\streamstest\PrintPrimeUsingStreams.java

```java

package com.suji.streamstest;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class PrintPrimeUsingStreams {
	public static void main(String[] args) {
		
		/*
		Function<Integer, Boolean> isPrime = i -> {
			
			boolean match = IntStream.iterate(2, v -> v < i,  v -> v+1 ).allMatch( n -> i%n !=0);
			boolean noneMatch = IntStream.iterate(2, v -> v < i,  v -> v+1 ).noneMatch(n -> i%n !=0);
			
			
			return noneMatch;
		};
		*/
		
		IntPredicate isPrime = i -> IntStream.iterate(2, n -> n < i, n -> n+1).allMatch( n -> i%n != 0);
			
		System.out.println(isPrime.test(18));
		
		IntStream.iterate(1 , i -> i < 100, i ->  i+1).filter(isPrime).forEach(System.out :: println);;
		
		
	}
}

```

---

### 22. RandomGenerators.java

#### \src\main\java\com\suji\streamstest\RandomGenerators.java

```java

package com.suji.streamstest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomGenerators {
	public static void main(String[] args) {

		String name = getRandomName();
		String number = getRandomNumber();
		System.out.println(name);
		System.out.println(number);

	}
	
	public static BigDecimal getRandomSalary() {
		
		return null;
	}

	public static LocalDate getRandomDate() {
		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		return randomDate;
	}

	public static String getRandomNumber() {
		return getRandomText('0', '9' + 1, 30); // 0:48,9:56; Can prints 0 and 9 also
	}

	public static String getRandomName() {
		return getRandomText('A', 'Z' + 1, 30); // A:65, Z:90 Can prints 65 and 90 also
	}

	public static String getRandomText(int origin, int bound, int size) {
		IntStream ints = new Random().ints(origin, bound).limit(size);
		StringBuilder builder = ints.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append);
		// String substring =
		// builder.substring(0,1).concat(builder.substring(1).toLowerCase());

		return builder.toString();
	}
}

```

---

### 23. RandomGenerators2.java

#### \src\main\java\com\suji\streamstest\RandomGenerators2.java

```java

package com.suji.streamstest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

enum Course{
	JAVA, C, PHYTHON, RUBY, DOT_NET;
}


public class RandomGenerators2 {
	public static void main(String[] args) {
		Random r = new Random();
		
		boolean anyMatch = r.ints(10, 0, 10).anyMatch(i -> i == 9);
		System.out.println(anyMatch);
		
		LocalDate date = LocalDate.now();
		LocalDate days = date.minusYears(18).minusDays(r.nextInt( 365 * 12));
		System.out.println(days);
		
		StringBuilder collect = r.ints(10, 'A', 'Z'+1).collect(StringBuilder::new,StringBuilder::appendCodePoint, StringBuilder::append);
		System.out.println(collect);
		
		
		
		
		IntFunction<String> course = i -> List.of("Java","C","Oracle","Python","Ruby").get(i);
		 
		
		System.out.println(Math.pow(2, 4)-1);
		
		
		
		
		String randomInt = Integer.toBinaryString(r.nextInt(15)+1);
		
		//Stream<String> stream = IntStream.range(0, list.size()).mapToObj( i -> (randomInt.charAt(i) == '1')? list.get(i) : null);
		//stream.forEach(System.out :: println);
		
		
		
		System.out.println("Random cources: ");
		
		List<String> list = List.of("Java","C","Oracle","Python","Ruby");
		
		IntStream.range(0, list.size()).mapToObj( i -> r.nextBoolean() ? list.get(i) : null).filter(s -> s!=null)
		.forEach(System.out :: println);
		
		

		
		
		
		
	}
}

```

---

### 24. ReduceDemo.java

#### \src\main\java\com\suji\streamstest\ReduceDemo.java

```java

package com.suji.streamstest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import com.suji.streamstest.model.Emp;

public class ReduceDemo 
{
    public static void main( String[] args )
    {
    	List<Emp> list = Emp.findAll();
    	
    	BinaryOperator<Emp> bo = (e1, e2) -> new Emp(0, e1.getSalary().add(e2.getSalary() )	);
    	
    	Optional<Emp> reduce = list.stream().reduce(bo);
    	Optional<Emp> collect = list.stream().collect(Collectors.reducing(bo));
    	
    	System.out.println(reduce.get());
    	System.out.println(collect.get().getSalary().divide(new BigDecimal(list.size())));
    	
    	
    	
    	
    }
    
    
    class Averager extends Emp{
    	int count;
    	int salary;
    }
}

```

---

### 25. ReduceDemo2.java

#### \src\main\java\com\suji\streamstest\ReduceDemo2.java

```java

package com.suji.streamstest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import com.suji.streamstest.model.Emp;

public class ReduceDemo2
{
    public static void main( String[] args )
    {
    	List<Emp> list = Emp.findAll();
    	
    	Emp identity = new Emp(0,new BigDecimal("1000"));
    	
    	BinaryOperator<Emp> accumulator = (e1, e2) ->
    	{
    		System.out.println("Accumulator: "+e1+": "+e2);
    		return new Emp(0, e1.getSalary().add(e2.getSalary()));
    	};
    	
    	BiFunction<Emp,BigDecimal,Emp> biAccumulator = (e1, sal) ->{
    		System.out.println("BiAccumulator: "+e1+": "+sal);
    		return new Emp(0, e1.getSalary().add(sal));
    	};
    	
    	BinaryOperator<Emp> combiner = (e1, e2) ->{
    		System.out.println("Combiner: "+e1+": "+e2);
    		return new Emp(0, e1.getSalary().add(e2.getSalary()));
    	};
    	
    	System.out.println(list.get(1));
    	System.out.println(biAccumulator.apply(list.get(1), new BigDecimal("1000")));
    	
    	Emp emp = list.stream().reduce(identity, accumulator, combiner);
    	System.out.println(emp.getSalary());
    	
    	
    	/*
    	
    	// Accumulator
    	Optional<Emp> reduce = list.stream().reduce(accumulator);
    	
    	System.out.println(reduce.get().getSalary());
    	
    	// Identity, Accumulator
    	Emp emp = list.stream().reduce(identity ,accumulator);
    	System.out.println(emp.getSalary());
    	
    	// Identity, Accumulator, Combiner
    	//Emp emp2 = list.stream().peek(System.out :: println).reduce(identity, biAccumulator, combiner);
    	
    	//System.out.println(biAccumulator.apply(list.get(1), list.get(2)));
    	
    	// <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner);
    	*/
    }
    
    class Averager{
    	int count;
    	int salary;
    }
}

```

---

### 26. ReduceFunctions.java

#### \src\main\java\com\suji\streamstest\ReduceFunctions.java

```java

package com.suji.streamstest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import com.suji.streamstest.model.Emp;

public class ReduceFunctions 
{
    public static void main( String[] args )
    {
    	List<Emp> list = Emp.findAll();
    	
    	Emp identity = new Emp(0,new BigDecimal("1000"));
    	
    	BinaryOperator<Emp> accumulator = (e1, e2) -> new Emp(0, e1.getSalary().add(e2.getSalary()));
    	
    	BiFunction<Emp,BigDecimal,Emp> biAccumulator = (e1, sal) -> new Emp(0, e1.getSalary().add(sal));
    	
    	BinaryOperator<Emp> combiner = (e1, e2) -> new Emp(0, e1.getSalary().add(e2.getSalary()));
    	
    	// Accumulator
    	Optional<Emp> reduce = list.stream().reduce(accumulator);
    	
    	System.out.println(reduce.get().getSalary());
    	
    	// Identity, Accumulator
    	Emp emp = list.stream().reduce(identity ,accumulator);
    	System.out.println(emp.getSalary());
    	
    	// Identity, Accumulator, Combiner
    	Emp emp2 = list.stream().peek(System.out :: println).reduce(identity, accumulator, combiner);
    	System.out.println(emp2.getSalary());
    	
    	
    	System.out.println(list.get(1));
    	System.out.println(biAccumulator.apply(list.get(1), new BigDecimal("1000")));
    	//System.out.println(biAccumulator.apply(list.get(1), list.get(2)));
    	
    	// <U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner);
    	
    }
}

```

---

### 27. StreamGroupBy.java

#### \src\main\java\com\suji\streamstest\StreamGroupBy.java

```java

package com.suji.streamstest;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.suji.streamstest.model.Employee;
import com.suji.streamstest.model.EmployeeRepo;

public class StreamGroupBy {

	public static void main2(String[] args) {
		
		List<Employee> employees = EmployeeRepo.getEmployees();
		
		// Count Males and Females
		Map<String, Long> map = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		map.forEach((k, v) -> System.out.println(k + ":" + v));

		// Show all departments
		List<String> list = employees.stream().map(Employee::getDepartment).distinct()
				.collect(Collectors.toList());
		list.forEach(System.out::println);

		// Avg age of Male and Female Employees
		Map<String, Double> map2 = employees.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(e -> e.getAge())));
		map2.forEach((k, v) -> System.out.println(k + ":" + v));

		// Maximun salary in both genders
		Map<String, Optional<Employee>> map3 = employees.stream().collect(Collectors.groupingBy(
				Employee::getGender, Collectors.maxBy((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))));
		map3.forEach((k, v) -> System.out.println(k + ":" + v));

		Optional<Employee> optional = employees.stream()
				.max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()));
		System.out.println("Highest pain employee in Org: " + optional.get());

		// Average salary of the Organization
		Double avgSal = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println("Average salary of the Organization : " + avgSal);

		// Total cost of organization
		DoubleSummaryStatistics collect = employees.stream()
				
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("Total cost of organization : " + collect.getSum());
		System.out.println("Total cost of organization : " + collect.getAverage());

		double salary = employees.stream()
				.collect(Collectors.reducing(new Employee(), (a, b) -> new Employee(a.getSalary() + b.getSalary())))
				.getSalary();

		System.out.println("Total salary : " + salary);

		// Highest paid Salary in the organization
		Map<String, Double> map4 = employees.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		map4.forEach((k, v) -> System.out.println(k + " : " + v));

		// Average Salary in each organization
		Map<String, DoubleSummaryStatistics> map5 = employees.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.summarizingDouble(Employee::getSalary)));
		
		map5.forEach((k, v) -> System.out.println(k + " : Count-" + v.getCount()+" Salary-"+v.getSum()));
		
		// Find highest paid employee using maxBy method
		Optional<Employee> collect2 = employees.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		System.out.println("Max paid employee: "+collect2.get().getName());
		
		// Get the names of all employees who have joined after 2015.
		employees.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).forEach(System.out :: println);
		
		// Count the number of employees in each department.
		Map<String, Long> map6 = employees.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.counting()));
		System.out.println("---------Number of employees in each department----------");
		map6.forEach((k,v) -> System.out.println(k+" : "+v));
		
		// What is the average salary of each department?
		Map<String, Double> map7 = employees.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("----------Average salary of each department----------");
		map7.forEach((k,v) -> System.out.println(k+" : "+v));
		
		// Get the details of youngest male employee in the product development department?
		//Optional<Employee> min = employees.stream().filter(e -> e.getDepartment().equals("Product Development")).filter(e -> e.getGender().equals("Male")).min((e1,e2) -> e1.getAge() - e2.getAge());
		Optional<Employee> min = employees.stream().filter(e -> e.getDepartment().equals("Product Development") && e.getGender().equals("Male")).min(Comparator.comparingInt(Employee::getAge));
		System.out.println("----------youngest male employee----------");
		System.out.println(min.get());
		
		// Who has the most working experience in the organization?
		Optional<Employee> max = employees.stream().max( (e1, e2) -> e2.getYearOfJoining() - e1.getYearOfJoining());
		Optional<Employee> max2 = employees.stream().min(Comparator.comparingInt(Employee:: getYearOfJoining));
		 Optional<Employee> findFirst = employees.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
		 
		
		System.out.println("\nMost working experience in the organization----------");
		System.out.println(max2.get());
		System.out.println(findFirst.get());
		
		
		// How many male and female employees are there in the sales and marketing team?
		Map<String, Long> map8 = employees.stream().filter(e -> e.getDepartment().equals("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println("\n\tMale and female employees in the sales and marketing:");
		map8.forEach((k,v) -> System.out.println(k+" : "+v));
		
		//  What is the average salary of male and female employees?
		Map<String, Double> map9 = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
		System.out.println("\n\t Average salary of male and female employees:");
		map9.forEach((k,v) -> System.out.println(k+" : "+v));
		
		// List down the names of all employees in each department?
		Map<String, List<Employee>> map10 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toList()));
		System.out.println("\n\t Names of all employees in each department:");
		//Set<Entry<String,List<Employee>>> entrySet = map10.entrySet();
		
		map10.forEach( (k,v) -> {
			System.out.println(">>>"+k+" :");
			v.forEach(val -> System.out.println(val.getName()));
		});
		
		// What is the average salary and total salary of the whole organization?
		Double totalOrgSal = employees.stream().collect(Collectors.summingDouble(Employee :: getSalary));
		Double totalOrgAvgSal = employees.stream().collect(Collectors.averagingDouble(Employee :: getSalary));
		System.out.println("\n totalOrgSal :"+totalOrgSal);
		System.out.println("\n totalOrgAvgSal :"+totalOrgAvgSal);
		
		// Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
		Map<String, List<Employee>> map11 = employees.stream().collect(Collectors.groupingBy( e-> e.getAge()>=25? "Above 25":"Below 25" ));
		map11.forEach( (k,v) -> {
			System.out.println(">>>"+k+" :");
			v.forEach(val -> System.out.println(val.getName()));
		});
		
		// Using partitionBy
		Map<Boolean, List<Employee>> map12 = employees.stream().collect(Collectors.partitioningBy( e -> e.getAge() >=25));
		System.out.println("Younger and Older employees: ");
		map12.forEach( (k,v) -> {
			System.out.println(">>>"+k+" :");
			v.forEach(val -> System.out.println(val.getName()));
		});
		System.out.println("Employees whos age is above 25: ");
		map12.get(true).forEach(val -> System.out.println(val.getName()));
		
		// Who is the oldest employee in the organization? What is his age and which department he belongs to?
		Optional<Employee> max3 = employees.stream().max(Comparator.comparingInt(Employee :: getAge));
		System.out.println("Oldest in the Org: "+max3.get().getName());
		
	}

}

```

---

### 28. StringUpdate11.java

#### \src\main\java\com\suji\streamstest\StringUpdate11.java

```java

package com.suji.streamstest;

import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class StringUpdate11 
{
    public static void main( String[] args )
    {
    	String str = "\n\n\tDemo";
    	System.out.println(str.isBlank()); // Java v11
    	System.out.println(str.isEmpty()); // Java v1.6
    	IntStream stream = str.chars(); // Java v1.9
    	stream.forEach(System.out :: println);
    	str.lines(); 
    	boolean b = str.isBlank(); //Adjusts the indentation of each line of this string
    	
    	System.out.println(b);
    	
    	String s = "Demo";
    	String repeat = s.repeat(10);
    	System.out.println(repeat);
    	
    }
    
}

```

---

### 29. ToArrayVerstion11.java

#### \src\main\java\com\suji\streamstest\ToArrayVerstion11.java

```java

package com.suji.streamstest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;

public class ToArrayVerstion11 {
	public static void main(String[] args) {

		Collection<String> immutableCollections = List.of("java", "Python", "Grove");

		Collection<String> list = new ArrayList<>(immutableCollections);
		
		// String[] array = (String[]) list.toArray(); ClassCastException

		String[] array2 = list.toArray(new String[0]); 

		IntFunction<String[]> intF = i -> new String[i]; //String[]::new; 

		/*
		 NOTE:
		 T[] toArray(IntFunction<T[]> generator)
		 
		 Ex:
		 Below mentioned two statements are equal.
		 
		 toArray( i -> new String[i]);
		 toArray(String[]::new);
		 
		 */
		
		String[] strings = intF.apply(7);

		list.toArray(intF); // Java v11
		
		list.removeIf( str -> str.charAt(0) == 'j'); // Java v1.8

		System.out.println(list);
	}
}

```

---

### 30. JwtUtil.java

#### \src\main\java\com\suji\streamstest\util\JwtUtil.java

```java

package com.suji.streamstest.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	// Generate Token
	public static String generateToken(String id, String subject, String key) {

		byte[] secretKey = Base64.getEncoder().encode(key.getBytes());

		JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuer("Sujith")
				.setIssuedAt(new Date(System.nanoTime()))
				.setExpiration(new Date(System.nanoTime() + TimeUnit.MINUTES.toMillis(10)))
				.signWith(SignatureAlgorithm.HS256, secretKey);

		return builder.compact();
	}

	// Get Claims
	public static Claims getClaims(String secretKey, String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

		return claims;
	}

	public static void printClaims(Claims claims) {

		System.out.println(claims.getId());
		System.out.println(claims.getSubject());
		System.out.println(claims.getIssuer());
		System.out.println(claims.getIssuedAt());
		System.out.println(claims.getExpiration());
	}

	public static String getSubject(String secretKey, String token) {
		return getClaims(secretKey, token).getSubject();
	}

	public static boolean isValidToken(String secretKey, String token) {
		return getClaims(secretKey, token).getExpiration().after(new Date(System.currentTimeMillis()));
	}
}
```

---

