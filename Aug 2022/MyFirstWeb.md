# MyFirstWeb


### File Structure
```pre
+ MyFirstWeb\ 
	+ \src\main\resources
		|---  application.properties
	+ \src\main\java\com\suji\classdesc
		|---  ClassUtil.java
	+ \src\main\java\com\suji\first
		|---  MyFirstWebApplication.java
	+ \src\main\java\com\suji\classdesc\ref
		|---  Met.java
```
### Index
```pre
1. application.properties
2. src\main\java\com\suji\classdesc\ClassUtil.java
3. src\main\java\com\suji\classdesc\ref\Met.java
4. src\main\java\com\suji\first\MyFirstWebApplication.java

```

---

### 1. application.properties

#### application.properties

```properties

logging.level.org.springframework.web=DEBUG


```

---

### 2. ClassUtil.java

#### src\main\java\com\suji\classdesc\ClassUtil.java

```java

package com.suji.classdesc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.suji.classdesc.ref.Met;

public class ClassUtil {

	public static String getRootToObjeString(Class clazz) {
		StringBuilder sb = new StringBuilder();
	//	sb.append(ClassUtil.getName(clazz));
		viewRootToObject(clazz, sb);
		return sb.toString();
	}

	private static void viewRootToObject(Class clazz, StringBuilder sb) {
		sb.append(ClassUtil.getName(clazz));
		Class sc = clazz.getSuperclass();
		if (sc != null) {
			sb.append(" >> ");
			viewRootToObject(sc, sb);
		}
	}

	public static TreeSet<Met> getDeclaredMethods(Class clazz) {
		TreeSet<Met> set = new TreeSet<>();

		Method[] methods = clazz.getDeclaredMethods();
		for (Method m : methods) {

			String name = m.getName();
			int mode = m.getModifiers();
			String params = getParameterParenthis(m.getGenericParameterTypes());
			String rt = getTypeName(m.getReturnType());

			Met met = new Met(mode, rt, name, params);
			set.add(met);
		}
		return set;
	}

	public static TreeSet<Met> getAllMethods(Class clazz) {
		TreeSet<Met> set = new TreeSet<>();

		Method[] methods = clazz.getMethods();

		for (Method m : methods) {

			// System.out.println(m);

			String name = m.getName();
			int mode = m.getModifiers();
			String params = getParameterParenthis(m.getGenericParameterTypes());
			String rt = getTypeName(m.getReturnType());

			Met met = new Met(mode, rt, name, params);

			set.add(met);
		}
		return set;
	}

	public static String getName(Class cls) {
		String s = Modifier.toString(cls.getModifiers());
		String name = "";
		if (s.contains("interface")) {
			if (cls.getInterfaces().length > 0) {
				name = cls.getSimpleName() + "(I)";
			} else {
				name = cls.getSimpleName() + "(RI)";
			}

		} else if (s.contains("abstract")) {
			name = cls.getSimpleName() + "(AC)";
		} else if (s.contains("final")) {
			name = cls.getSimpleName() + "(FC)";
		} else {
			name = cls.getSimpleName() + "(C)";
		}
		return name;
	}

	public static Set<String> getDeclaredMethodNames(Class clazz) {
		Set<String> set = new TreeSet<>();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method m : methods) {
			set.add(m.getName());
		}
		return set;
	}

	public static String getTypeName(Type type) {
		String name = type.getTypeName();
		int lastIndex = name.lastIndexOf(".") + 1;
		return lastIndex > -1 ? name.substring(lastIndex) : name;
	}

	public static String getParameterParenthis(Type[] types) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Type t : types) {
			sb.append(ClassUtil.getTypeName(t));
			sb.append(",");
		}
		if (sb.lastIndexOf(",") > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append(")");
		return sb.toString();
	}

	public static String getInterfacesMap(Class clazz) {
		StringBuilder sb = new StringBuilder();
		getInterfaces(clazz, sb);
		return sb.toString();
	}

	public static String getContructorNames(Class clazz) {
		StringBuilder sb = new StringBuilder();
		Constructor[] constructors = clazz.getDeclaredConstructors();

		for (Constructor c : constructors) {
			Type[] types = c.getGenericParameterTypes();

			String string = ClassUtil.getParameterParenthis(types);
			String str = Modifier.toString(c.getModifiers());
			if (str.isBlank()) {
				sb.append(clazz.getSimpleName() + string).append("\n");
			} else {
				sb.append(str + " " + clazz.getSimpleName() + string).append("\n");
			}

		}

		return sb.toString();
	}

	private static void getInterfaces(Class cls, StringBuilder sb) {
		sb.append(ClassUtil.getName(cls));

		Class[] interfaces = cls.getInterfaces();

		if (interfaces.length == 1) {
			sb.append(" ==> ");
			for (Class class1 : interfaces) {
				getInterfaces(class1, sb);

			}
		} else if (interfaces.length > 1) {

			for (Class class1 : interfaces) {
				sb.append("\n");
				sb.append(ClassUtil.getName(cls));
				sb.append(" ==> ");
				getInterfaces(class1, sb);
			}
		}
	}

	public static TreeSet<Met> getSuperclassMethods(Class cls) {
		TreeSet<Met> set = new TreeSet<Met>();

		Class superclass = cls.getSuperclass();
		if (superclass != null) {
			set.addAll(ClassUtil.getAllMethods(superclass));
		}

		return set;
	}

	public static TreeSet<Met> getOwnMethods(Class cls) {

		TreeSet<Met> childMethodSet = ClassUtil.getAllMethods(cls);

		if (cls.isInterface()) {
			childMethodSet.removeAll(getImplInterfaceMethods(cls));
		} else {

			Class superclass = cls.getSuperclass();

			if (superclass != null) {
				childMethodSet.removeAll(ClassUtil.getAllMethods(superclass));
			}

			if (cls.getInterfaces().length > 0) {
				TreeSet<Met> methods = getImplInterfaceMethods(cls);
				childMethodSet.removeAll(methods);
			}
		}

		return childMethodSet;
	}

	public static TreeSet<Met> getImplInterfaceMethods(Class cls) {

		TreeSet<Met> set = new TreeSet<Met>();

		Class[] interfaces = cls.getInterfaces();
		for (Class cl : interfaces) {
			set.addAll(ClassUtil.getAllMethods(cl));
		}

		return set;
	}

	private static void getAll(Class cls, LinkedHashSet<Class> set) {
		// System.out.println(cls);
		set.add(cls);
		if (cls != null) {

			Class[] infs = cls.getInterfaces();

			if (infs.length > 0) {
				for (Class in : infs) {
					getAll(in, set);
				}
			}

			Class superclass = cls.getSuperclass();

			if (superclass != null) {

				getAll(superclass, set);
			}
		}
	}

	public static LinkedHashSet<Class> getAllClasses(Class clazz) {
		LinkedHashSet<Class> set = new LinkedHashSet();
		getAll(clazz, set);
		return set;
	}

	public static void printSet(Class clazz, Set set) {
		for (Object object : set) {
			Met m = (Met) object;

			if (clazz.isInterface()) {

				System.out.println(m.toString().replaceAll("public", "").replace("abstract", "").trim());
			} else {
				System.out.println(m.toString().replaceAll("public", "").trim());
			}

		}
	}

	public static String getIns(Class clazz) {
		StringBuilder sb = new StringBuilder();

		Class[] interfaces = clazz.getInterfaces();
		if (interfaces.length > 0) {

			sb.append(ClassUtil.getName(clazz) + " --> ");
			for (Class inf : interfaces) {
				sb.append(ClassUtil.getName(inf) + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public static void detailedDescription(Class clazz) {
		List<Class> ls = new ArrayList<>(getAllClasses(clazz));
		Collections.reverse(ls);
		detailedDescription(new LinkedHashSet<Class>(ls));
	}

	public static void detailedDescriptionReverse(Class clazz) {
		detailedDescription(getAllClasses(clazz));
	}

	public static void detailedDescriptionReverse(LinkedHashSet<Class> set) {
		List<Class> ls = new ArrayList<>(set);
		Collections.reverse(ls);
		detailedDescription(new LinkedHashSet<Class>(ls));
	}

	public static void detailedDescription(LinkedHashSet<Class> set) {

		// LinkedHashSet<Class> set = ClassUtil.getAllClasses(clazz);

		for (Class cl : set) {
			
			 System.out.println(cl.getCanonicalName());
			if (cl.isInterface()) {
				System.out.println(ClassUtil.getInterfacesMap(cl));
				

				TreeSet<Met> methods = ClassUtil.getOwnMethods(cl);
				if (! methods.isEmpty()) {
					System.out.println("\nMethods:");
					ClassUtil.printSet(cl, methods);
				}

			} else {

				System.out.println(ClassUtil.getRootToObjeString(cl));
				
				if (!getIns(cl).isBlank()) {
					System.out.println(getIns(cl));
				}
				System.out.println();
				
				String names = ClassUtil.getContructorNames(cl);
				if (getIns(cl).isBlank()) {
				//	System.out.println("No Constructors");
				} else {
					System.out.println("Constructors:");
					System.out.println(names);
				}
				System.out.println();
				
				TreeSet<Met> methods = ClassUtil.getOwnMethods(cl);
				if (methods.isEmpty()) {
					
				//	System.out.println("It has no own methods.");
				} else {
					System.out.println("Methods:");
					ClassUtil.printSet(cl, methods);
				}
			}
			System.out.println("\n+---++---++---++---++---++---+\n");
		}

	}

	public static LinkedHashSet<Class> getAllClasses(LinkedHashSet<Class> set) {

		LinkedHashSet<Class> allClasses = new LinkedHashSet<Class>();

		for (Class cl : set) {
			LinkedHashSet<Class> classes = ClassUtil.getAllClasses(cl);
			allClasses.addAll(classes);
		}

		return allClasses;

	}

	public static void getAllClassesDetailedDesc(LinkedHashSet<Class> set) {
		ClassUtil.detailedDescriptionReverse(getAllClasses(set));
	}

}

```

---

### 3. Met.java

#### src\main\java\com\suji\classdesc\ref\Met.java

```java

package com.suji.classdesc.ref;

import java.lang.reflect.Modifier;
import java.util.Objects;

public class Met implements Comparable<Met>{
	
	private String name;
	private	int mode;
	private	String params ;
	private String rt;
	
	public Met(int mode, String rt, String name, String param) {
		
		this.name = name;
		this.mode = mode;
		this.params = param;
		this.rt = rt;
	}

	@Override
	public int compareTo(Met m) {
		return name.concat(params).compareTo(m.name.concat(m.params));
	}

	@Override
	public boolean equals(Object obj) {
		Met m = (Met) obj;
		return name.concat(params).equals(m.name.concat(m.params));
	}

	@Override
	public String toString() {
		//return rt+" "+name+params;
		return Modifier.toString(mode)+"\t"+rt+"\t"+name+params;
	}
	
	
	
	
	

	

}

```

---

### 4. MyFirstWebApplication.java

#### src\main\java\com\suji\first\MyFirstWebApplication.java

```java

package com.suji.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.TreeSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.suji.classdesc.ClassUtil;

@SpringBootApplication
public class MyFirstWebApplication {

	public static void main(String[] args) {

		LinkedHashSet<Class> set = new LinkedHashSet<Class>();
		set.add(ConfigurableApplicationContext.class);
		set.add(SpringApplication.class);
		set.add(AnnotationConfigApplicationContext.class);
		set.add(WebApplicationContext.class);
		set.add(InternalResourceViewResolver.class);
		set.add(DispatcherServlet.class);
		set.add(ModelAndView.class);
		set.add(Model.class);
		set.add(AbstractAnnotationConfigDispatcherServletInitializer.class);
		set.add(BindingResult.class);
		set.add(FieldError.class);
		set.add(HttpStatus.class);
		set.add(HttpEntity.class);
		set.add(ResponseEntity.class);
		
		ClassUtil.getAllClassesDetailedDesc(set);

		// ConfigurableApplicationContext context =
		// SpringApplication.run(MyFirstWebApplication.class, args);
		// String[] names = context.getBeanDefinitionNames();
//		for (int i = 0; i < names.length; i++) {
//			System.out.println(names[i]);
//		}

	}

}

```

---

