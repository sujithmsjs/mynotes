spring:
  main:
    log-startup-info: false

  jackson:
    property-naming-strategy: UPPER_CAMEL_CASE
    serialization:
      indent-output: true

---
## Spring Security Hacks
```java
package com.in28minutes.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	//LDAP or Database
	//In Memory 
	
	//InMemoryUserDetailsManager
	//InMemoryUserDetailsManager(UserDetails... users)
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		
		UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
		UserDetails userDetails2 = createNewUser("ranga", "dummydummy");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);

		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER","ADMIN")
									.build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//All URLs are protected
	//A login form is shown for unauthorized requests
	//CSRF disable
	//Frames
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		return http.build();
	}	
}
```
## Application.properties
```properties
#server.port=8081
#sayHello.jsp
#/WEB-INF/jsp/sayHello.jsp

# /WEB-INF/jsp/login.jsp => View Resolver
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
logging.level.org.springframework=info
logging.level.com.in28minutes.springboot.myfirstwebapp=info

spring.mvc.format.date=yyyy-MM-dd

#spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.defer-datasource-initialization=true

spring.datasource.url=jdbc:mysql://localhost:3306/todos
spring.datasource.username=todos-user
spring.datasource.password=dummytodos
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.jpa.hibernate.ddl-auto=update

#/connect todos-user@localhost:3306
#docker run --detach 
#--env MYSQL_ROOT_PASSWORD=dummypassword 
#--env MYSQL_USER=todos-user 
#--env MYSQL_PASSWORD=dummytodos 
#--env MYSQL_DATABASE=todos 
#--name mysql 
#--publish 3306:3306 
#mysql:8-oracle
```
## Date Picker
```html
<div class="container">
	
	<h1>Enter Todo Details</h1>
	
	<form:form method="post" modelAttribute="todo">

		<fieldset class="mb-3">				
			<form:label path="description">Description</form:label>
			<form:input type="text" path="description" required="required"/>
			<form:errors path="description" cssClass="text-warning"/>
		</fieldset>

		<fieldset class="mb-3">				
			<form:label path="targetDate">Target Date</form:label>
			<form:input type="text" path="targetDate" required="required"/>
			<form:errors path="targetDate" cssClass="text-warning"/>
		</fieldset>

		
		<form:input type="hidden" path="id"/>

		<form:input type="hidden" path="done"/>

		<input type="submit" class="btn btn-success"/>
	
	</form:form>
	
</div>

<%@ include file="common/footer.jspf" %>

<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'yyyy-mm-dd'
	});
</script>
```
spring.mvc.format.date=yyyy-MM-dd
spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
spring.mvc.format.time=HH:mm:ss
spring.mvc.format.date-time=iso

# ISO-8601 formatting
spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
```
@PostMapping("/local-date-time")
    public void dateTime(@RequestParam("localDateTime") 
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        // ...
    }
```
## Receiving Data from Server side
```java
@PostMapping("/date")
public void date(@RequestParam("date") 
  @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
    // ...
}
```


```properties
spring.jackson.date-format= # For instance, `yyyy-MM-dd HH:mm:ss`.
spring.jackson.default-property-inclusion= # including properties during serialization. 
spring.jackson.deserialization.*= # Jackson on/off features for deserialization.
spring.jackson.generator.*= # Jackson on/off features for generators.
spring.jackson.joda-date-time-format= # Joda date time format string.
spring.jackson.locale= # Locale used for formatting.
spring.jackson.mapper.*= # Jackson general purpose on/off features.
spring.jackson.parser.*= # Jackson on/off features for parsers.
spring.jackson.property-naming-strategy= # PropertyNamingStrategy.
spring.jackson.serialization.*= # Jackson on/off features for serialization.
spring.jackson.time-zone= #  Time zone
spring.jackson.visibility.*= # To limit which methods (and fields) are auto-detected.
```


