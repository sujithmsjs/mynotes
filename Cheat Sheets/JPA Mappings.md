# JPA Table Mappings 
---
# App props

```properties
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
---
### Note: All below Annotions should belongs to Persistence api.

```java
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
```
---
# 1.  One To Many

User can have multiple Todos. However, Todos doesn't have relation ship with User. In this case both classes should be Entities.

```java
class User{
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_todos",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id")
    )
    private Set<Todo> todos;
}
```
```java
class Todo{
}
```
---
# 2. Many To One

A Multiple Set of ToDos can have signle User. However, User has no relationship to ToDos. In this case two tables will be created.
Todos Table will be included *"user_id"* column. In this case both classes should be Entities.

```java
class User{
}
```
```java
class Todo{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
```

---
# 3. One To Many & Many To One

A User can Have multiple ToDo's. A ToDo can have only one User. In this case, two tables depending on each other.
In this case two will be created and both classes should be Entities.

```java
class User{
    @OneToMany(mappedBy = "todoClassUserInstanceVariable")
    private Set<Todo> todos;
}
```
```java
class Todo{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User todoClassUserInstanceVariable;
    
}
```
---
# 4. One To Many & Many To One - without MappedBy

A User can Have multiple ToDos. A ToDo can have only one User. But *mappedBy* is not done by either of these.
So ToDo will be included both *"todo_id"* and *"user_id"* columns.

```java
class User{
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "todo_id")
	private Set<Todo> todos;
}
```
```java
class Todo{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}
```
---
# 5. One To One

A User can have only one ToDo. In this case two tables will be created. User table will be included *"todo_id"* column.

```java
class User{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;
}
```
```java
class Todo{
}
```
---
# 6. One To One At Both Ends

If a User can have only one ToDo, as well as a ToDo can have only one User. In this case two tables will be created.
In User table *"todo_id"* column will be added. In ToDo table *"user_id"* column will be added.

```java
class User{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;
}
```
```java
class ToDo{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
```
---
# 7. Many-to-Many

A User can have Multiple ToDo. A ToDo can be in any Users ToDo list. In this case three tables will be created. Additional table call as *"Join Table"*.
It contains both *"user_id"* and *"todo_id"* columns.

```java
class User{
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
            name = "user_todos",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id")
    )
	private Set<Todo> todos;
}
```
```java
class Todo{
}
```
---
# 8. Many-to-Many - each other

*Note: If any Associations(field) marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn.*
In this case three tables will be created. Additional table call as *"Join Table"*.
It contains both *"user_id"* and *"todo_id"* columns.

```java
class User{
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
            name = "user_todos",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id")
    )
	private Set<Todo> userClassMappedVariable;
}
```
```java
class Todo{
    @ManyToMany(mappedBy = "userClassMappedVariable")
    private Set<User> users;
}
```
---
# 9. OneToOne with @Embeded

Sometimes we don't need to create both tables. Dependable class fields can be included in Primary class table. In this secinario we can we *@Embeded*, it will create only one table with both fields columns.
*Note that Secoundary class must not be Entity*.

```java
@Entity
class User{
    @Embedded
	private Todo todo;
}
```
```java
// Need not to be Entity
class Todo{
}
```
# 10. @Transient Annotation

Transient entity fields are fields that do not participate in persistence or ignore their values are never stored in the database.

@Transient annotation is used to ignore a field to not persist in database in JPA, where as transient key word(In Java) used to ignore a field from serialization.

To ignore fields to not persist in DB, @Transient annotation recommended to use, because it is specific to persistence.

```java
@Entity
class User{
    
    @Transient
    private Address address;

    @Transient
    private LocalDate dob;
}
```
---
# 11. @JsonIgnore

We can also ignore a field directly via the *@JsonIgnore* annotation directly on the field. That field will be not included in JSON Formatting.

```java
public class MyDto {

    @JsonIgnore
    private int intValue;
    
}
```