### Application Properties
```java
#server.port=8081
#sayHello.jsp
#/WEB-INF/jsp/sayHello.jsp

# /WEB-INF/jsp/login.jsp => View Resolver
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
logging.level.org.springframework=info
logging.level.com.in28minutes.springboot.myfirstwebapp=info

spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.defer-datasource-initialization=true

logging.level.org.springframework = info

# Actuator Endpoints
management.endpoints.web.exposure.include=*

spring.mvc.format.date=yyyy-MM-dd
logging.level.org.springframework=debug

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

```
### Acuator:
Monitor and Check the health of the Server

http://localhost:8080/actuator
http://localhost:8080/actuator/metrics/http.server.requests

### Hal-Explorer

http://localhost:8080