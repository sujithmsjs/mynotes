### Application Properties

```properties
# Server Settings
server.port=8081
spring.application.name=Project_Name

# User define values
jwt.secret="sujith@123"

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
```
