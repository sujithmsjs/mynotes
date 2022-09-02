# my-app


### File Structure
```pre
+ my-app\ 
	+ \src\main\resources
		|---  messages.properties
	+ \src\main\resources\templates
		|---  error.html
		|---  layout.html
	+ \src\main\resources\templates\home
		|---  index.html
	+ \src\main\resources\static\css
		|---  app.css
	+ \src\main\java\io\bootify\my_app
		|---  HomeController.java
		|---  MyAppApplication.java
	+ \src\main\java\io\bootify\my_app\config
		|---  ControllerConfig.java
		|---  DomainConfig.java
		|---  LocalDevConfig.java
	+ \src\main\java\io\bootify\my_app\domain
		|---  Employee.java
	+ \src\main\java\io\bootify\my_app\repos
		|---  EmployeeRepository.java
	+ \src\main\java\io\bootify\my_app\util
		|---  WebUtils.java
```
### Index
```pre
1. messages.properties
2. src\main\java\io\bootify\my_app\HomeController.java
3. src\main\java\io\bootify\my_app\MyAppApplication.java
4. src\main\java\io\bootify\my_app\config\ControllerConfig.java
5. src\main\java\io\bootify\my_app\config\DomainConfig.java
6. src\main\java\io\bootify\my_app\config\LocalDevConfig.java
7. src\main\java\io\bootify\my_app\domain\Employee.java
8. src\main\java\io\bootify\my_app\repos\EmployeeRepository.java
9. src\main\java\io\bootify\my_app\util\WebUtils.java
10. resources\templates\error.html
11. resources\templates\home\index.html
12. resources\templates\layout.html
13. static\css\app.css

```

---

### 1. messages.properties

#### messages.properties

```properties

app.title=MyApp
navigation.toggle=Toggle navigation
navigation.home=Home
home.index.headline=Welcome to your new app\!
home.index.text=Your app was created with <a href\="https\://bootify.io" target\="_blank">Bootify.io</a> and can now be customized. If you are satisfied with the result, <strong>please recommend us to other developers</strong> - so Bootify.io can grow further.
home.index.swagger=Swagger is <a href\="/swagger-ui.html" target\="_blank">available here</a>.
error.message=The page you are looking for is not available right now.

```

---

### 2. HomeController.java

#### src\main\java\io\bootify\my_app\HomeController.java

```java

package io.bootify.my_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

}

```

---

### 3. MyAppApplication.java

#### src\main\java\io\bootify\my_app\MyAppApplication.java

```java

package io.bootify.my_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MyAppApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }

}

```

---

### 4. ControllerConfig.java

#### src\main\java\io\bootify\my_app\config\ControllerConfig.java

```java

package io.bootify.my_app.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


@ControllerAdvice
public class ControllerConfig {

    @InitBinder
    void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}

```

---

### 5. DomainConfig.java

#### src\main\java\io\bootify\my_app\config\DomainConfig.java

```java

package io.bootify.my_app.config;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.my_app.domain")
@EnableJpaRepositories("io.bootify.my_app.repos")
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class DomainConfig {

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

}

```

---

### 6. LocalDevConfig.java

#### src\main\java\io\bootify\my_app\config\LocalDevConfig.java

```java

package io.bootify.my_app.config;

import java.io.File;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;


@Configuration
@Profile("local")
public class LocalDevConfig {

    public LocalDevConfig(final TemplateEngine templateEngine) throws IOException {
        File sourceRoot = new ClassPathResource("application.yml").getFile().getParentFile();
        while (sourceRoot.listFiles((dir, name) -> name.equals("mvnw")).length != 1) {
            sourceRoot = sourceRoot.getParentFile();
        }
        final FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();
        fileTemplateResolver.setPrefix(sourceRoot.getPath() + "/src/main/resources/templates/");
        fileTemplateResolver.setSuffix(".html");
        fileTemplateResolver.setCacheable(false);
        fileTemplateResolver.setCharacterEncoding("UTF-8");
        fileTemplateResolver.setCheckExistence(true);

        templateEngine.setTemplateResolver(fileTemplateResolver);
    }

}

```

---

### 7. Employee.java

#### src\main\java\io\bootify\my_app\domain\Employee.java

```java

package io.bootify.my_app.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(precision = 10, scale = 2)
    private BigDecimal salary;

    @Column
    private LocalDate joinDate;

    @Column
    private Boolean enabled;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(final BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(final LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}

```

---

### 8. EmployeeRepository.java

#### src\main\java\io\bootify\my_app\repos\EmployeeRepository.java

```java

package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

```

---

### 9. WebUtils.java

#### src\main\java\io\bootify\my_app\util\WebUtils.java

```java

package io.bootify.my_app.util;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;


@Component
public class WebUtils {

    public static final String MSG_SUCCESS = "MSG_SUCCESS";
    public static final String MSG_INFO = "MSG_INFO";
    public static final String MSG_ERROR = "MSG_ERROR";
    private static MessageSource messageSource;
    private static LocaleResolver localeResolver;

    public WebUtils(final MessageSource messageSource, final LocaleResolver localeResolver) {
        WebUtils.messageSource = messageSource;
        WebUtils.localeResolver = localeResolver;
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getMessage(final String code, final Object... args) {
        return messageSource.getMessage(code, args, code, localeResolver.resolveLocale(getRequest()));
    }

    public static boolean isRequiredField(final Object dto, final String fieldName) throws
            NoSuchFieldException {
        return dto.getClass().getDeclaredField(fieldName).getAnnotation(NotNull.class) != null;
    }

}

```

---

### 10. error.html

#### resources\templates\error.html

```html

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
        layout:decorate="~{layout}">
    <head>
        <title th:text="${status} + ' - ' + ${error}" />
    </head>
    <body>
        <div layout:fragment="content">
            <h1 th:text="${status} + ' - ' + ${error}" class="mb-4" />
            <p th:utext="#{error.message}" />
        </div>
    </body>
</html>

```

---

### 11. index.html

#### resources\templates\home\index.html

```html

<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
        layout:decorate="~{layout}">
    <head>
        <title th:text="#{home.index.headline}" />
    </head>
    <body>
        <div layout:fragment="content">
            <h1 th:text="#{home.index.headline}" class="mb-4" />
            <p th:utext="#{home.index.text}" />
            <p th:utext="#{home.index.swagger}" class="mb-5" />
        </div>
    </body>
</html>

```

---

### 12. layout.html

#### resources\templates\layout.html

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    <head>
        <title th:text="#{app.title}" layout:title-pattern="$CONTENT_TITLE | $LAYOUT_TITLE" />
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous" />
        <link th:href="@{/css/app.css}" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous" defer></script>
        <script th:src="@{/js/app.js}" defer></script>
    </head>
    <body>
        <header class="bg-light">
            <div class="container">
                <nav class="navbar navbar-light navbar-expand-md">
                    <a th:href="@{/}" class="navbar-brand">
                        <img th:src="@{/images/logo.png}" th:alt="#{app.title}" width="30" height="30" class="d-inline-block align-top">
                        <span th:text="#{app.title}" class="ps-1" />
                    </a>
                    <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarToggle"
                            th:aria-label="#{navigation.toggle}" aria-controls="navbarToggle" aria-expanded="false">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarToggle">
                        <ul class="navbar-nav ms-auto">
                            <li class="navbar-item">
                                <a th:href="@{/}" th:text="#{navigation.home}" class="nav-link" />
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
        <main class="my-5">
            <div class="container">
                <p th:if="${MSG_SUCCESS}" th:text="${MSG_SUCCESS}" class="alert alert-success mb-4" role="alert" />
                <p th:if="${MSG_INFO}" th:text="${MSG_INFO}" class="alert alert-info mb-4" role="alert" />
                <p th:if="${MSG_ERROR}" th:text="${MSG_ERROR}" class="alert alert-danger mb-4" role="alert" />
                <div layout:fragment="content" />
            </div>
        </main>
    </body>
</html>

```

---

### 13. app.css

#### static\css\app.css

```css


```

---

