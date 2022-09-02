ApplicationContext:
It uses dependency injection to achieve inversion of control.

The interfaces BeanFactory and ApplicationContext represent the Spring IoC container. 
BeanFactory(I)
ApplicationContext(I)


ClassPathXmlApplicationContext(C)


A BEAN is an object that the Spring container instantiates, assembles, and manages.


4. Configuring Beans in the Container
4.1. Java-Based Configuration
4.2. Annotation-Based Configuration
4.3. XML-Based Configuration


4.1. Java-Based Configuration
-> Java configuration typically uses @Bean-annotated methods within a @Configuration class.
-> A class annotated with @Configuration indicates that it contains Spring bean configurations.

@Configuration
@ComponentScan(basePackages = "com.suji.TestSpring.beans")
public class MyConfig {

	@Bean(name = "car")
	public Car getCar() {
		return new Car("Tesla", 56, getEngine());
	}
	
	@Bean(name = "engine")
	public Engine getEngine() {
		Engine e = new Engine();
		e.setPower(30);
		return e;
	}
}


4.2. Annotation-Based Configuration
ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
Some examples of these annotations are @Component, @Controller, @Service, @Repository, @Autowired, and @Qualifier.


@Configuration
@ComponentScan(basePackages = "com.suji.TestSpring.beans")
public class MyConfig {

	@Bean(name = "car")
	public Car getCar() {
		return new Car("Tesla", 56);
	}
	
	@Bean(name = "e1")
	public Engine getEngine() {
		Engine e = new Engine(30);
		return e;
	}
	
	@Bean(name = "e2")
	public Engine getEngine2() {
		Engine e = new Engine(100);
		return e;
	}
}

public class Car {
	
	private String name;
	private double milage;
	
	@Autowired
	//@Qualifier("e1")
	private Engine engine;

}
OUTPUT:
NoUniqueBeanDefinitionException: No qualifying bean of type 'com.suji.TestSpring.beans.Engine' available: expected single matching bean but found 2: e1,e2


5. Types of ApplicationContext
https://www.baeldung.com/spring-application-context
5.1. AnnotationConfigApplicationContext
5.2. AnnotationConfigWebApplicationContext
5.3. XmlWebApplicationContext
5.4. FileSystemXMLApplicationContext
5.5. ClassPathXmlApplicationContext


5.1. AnnotationConfigApplicationContext

BeanFactory <- ListableBeanFactory <- ApplicationContext

It can take classes annotated with @Configuration, @Component.

ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
Car car = (Car) context.getBean("car");
System.out.println(car);


5.2. AnnotationConfigWebApplicationContext
5.3. XmlWebApplicationContext
5.4. FileSystemXMLApplicationContext
5.5. ClassPathXmlApplicationContext




NoSuchBeanDefinitionException: No qualifying bean of type available: expected at least 1 bean which qualifies as autowire candidate.


Dependency Injection:
Dependency injection is a pattern we can use to implement IoC, where the control being inverted is setting an object's dependencies.

IoC Container:
An IoC container is a common characteristic of frameworks that implement IoC.
In the Spring framework, the interface ApplicationContext represents the IoC container. The Spring container is responsible for instantiating, configuring and assembling objects known as beans, as well as managing their life cycles.


1. Constructor-Based DI
2. Setter-Based DI
3. Field-Based





1. Constructor-Based DI
-----------------------
@Configuration
public class AppConfig {

    @Bean
    public Item item1() {
        return new ItemImpl1();
    }

    @Bean
    public Store store() {
        return new Store(item1());
    }
}

2. Setter-Based DI
------------------
@Bean
public Store store() {
    Store store = new Store();
    store.setItem(item1());
    return store;
}

3. Field-Based
--------------
public class Store {
    @Autowired
    private Item item; 
}


Autowiring Dependencies
-----------------------
Wiring allows the Spring container to automatically resolve dependencies between collaborating beans by inspecting the beans that have been defined.

There are four modes of autowiring a bean using an XML configuration:

no: the default value – this means no autowiring is used for the bean and we have to explicitly name the dependencies.
byName: autowiring is done based on the name of the property, therefore Spring will look for a bean with the same name as the property that needs to be set.
byType: similar to the byName autowiring, only based on the type of the property. This means Spring will look for a bean with the same type of the property to set. If there's more than one bean of that type, the framework throws an exception.
constructor: autowiring is done based on constructor arguments, meaning Spring will look for beans with the same type as the constructor arguments.


