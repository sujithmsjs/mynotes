# Blanks


### File Structure
```pre
+ Blanks\ 
	|---  pom.xml
	+ \src\main\java\com\suji\blanks
		|---  App.java
	+ \src\test\java\com\suji\blanks
		|---  AppTest.java
	+ \src\main\java\com\suji\blanks\model
		|---  Answer.java
		|---  Question.java
```
### Index
```pre
1. pom.xml
2. model\Answer.java
3. model\Question.java
4. src\main\java\com\suji\blanks\App.java
5. src\test\java\com\suji\blanks\AppTest.java

```

---

### 1. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.suji</groupId>
  <artifactId>Blanks</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>Blanks</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    
    <dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.22</version>
			<scope>provided</scope>
		</dependency>
    
  </dependencies>
</project>

```

---

### 2. Answer.java

#### model\Answer.java

```java

package com.suji.blanks.model;

import lombok.Data;

@Data
public class Answer {
	
	private int id;
	private String answer;
	private String hint;
	
	
	
	public Answer(String answer, String hint) {
		super();
		this.answer = answer;
		this.hint = hint;
	}



	public Answer() {
		super();
	}
	
	
}

```

---

### 3. Question.java

#### model\Question.java

```java

package com.suji.blanks.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Question {
	
	private int id;
	private String question;
	private List<Answer> answers = new ArrayList<>();
	
}

```

---

### 4. App.java

#### src\main\java\com\suji\blanks\App.java

```java

package com.suji.blanks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.function.Consumer;

import javax.swing.plaf.nimbus.State;

import com.suji.blanks.model.Answer;
import com.suji.blanks.model.Question;

public class App {

	public static void main(String[] args) {

		Question question = getQuestion("<This:?> is <mangostarts: with m>, it the most <dilicious: tasty> fruit");
		System.out.println(question);
	
	}

	public static Question getQuestion(String str) {
		
		Question question = new Question();
		List<Answer> answers = question.getAnswers();
		
		boolean hasError = false;
		
		Stack<Character> stack = new Stack<>();
		stack.add('#');

		
		StringBuilder sb = new StringBuilder();
		StringBuilder queStr = new StringBuilder();
		Answer ans = new Answer();
		
		for (int i = 0; i < str.length(); i++) {

			switch (str.charAt(i)) {
			case '<':
				if (stack.peek() == '#') {
					stack.push(str.charAt(i));
				} else {
					hasError = true;
					break;
				}
				break;
			case ':':
				if (stack.peek() == '<') {
					ans.setAnswer(sb.toString());
					//System.out.println("Answer: " + ans);
					sb = new StringBuilder();
				} else {
					hasError = true;
					break;
				}
				stack.push(str.charAt(i));

				break;
			case '>':
				if (stack.peek() == '<') {
					ans.setAnswer(sb.toString());
					
					Answer a = new Answer();
					a.setAnswer(ans.getAnswer());
					a.setHint(ans.getHint());
					answers.add(a);
					//System.out.println("Answer: " + ans);
					
					sb = new StringBuilder();
				} else if (stack.peek() == ':') {
					ans.setHint(sb.toString());
					
					Answer a = new Answer();
					a.setAnswer(ans.getAnswer());
					a.setHint(ans.getHint());
					answers.add(a);
					//System.out.println("Answer: " + ans);
					sb = new StringBuilder();
				} else {
					hasError = true;
					break;
				}
				stack.clear();
				stack.push('#');
				break;
			default:
				if (stack.peek() == '<') {
					sb.append(str.charAt(i));
					queStr.append('_');
				}else if(stack.peek() == ':') {
					sb.append(str.charAt(i));
				}else {
					queStr.append(str.charAt(i));
				}
				break;
			}
		}
		
		question.setQuestion(queStr.toString());
		
		if(hasError) {
			return null;
		}else {
			return  question;
		}
	}

}

```

---

### 5. AppTest.java

#### src\test\java\com\suji\blanks\AppTest.java

```java

package com.suji.blanks;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}

```

---

