
### Query Methods

https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.predicate

### Query method declaration in UserRepository

```java
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByLastname(String lastname);

  User findByEmailAddress(String emailAddress);
  
}
```

### Query method subject keywords

1. find...By
2. exists…By
3. count…By
4. delete…By