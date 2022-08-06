### Application Properties

```properties
# Server Settings
server.port=8081
spring.application.name=Project_Name

# Open API Available at http://localhost:8080/api-docs/
springdoc.api-docs.path=/api-docs

# Swagger
# Default URL: http://localhost:8080/swagger-ui/index.html
# Custom URL: http://localhost:8080/swagger.html
springdoc.swagger-ui.path=/swagger.html


# DateTimeFormatters
spring.mvc.format.date=yyyy-MM-dd
spring.mvc.format.date-time=yyyy-MM-dd HH:mm:ss
spring.mvc.format.time=HH:mm:ss

# Data Source
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sujith
spring.datasource.password=password

# JPA Props
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto = create

# H2 Database Settings
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.h2.console.enabled=true

# Error Message
server.error.include-message=always
server.error.include-exception=true

```
