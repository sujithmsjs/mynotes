
@EnableJpaRepositories:

Spring Data Jpa provides EnableJpaRepositories annotation which is used to scan the packages for configuration and repository class for Spring Data JPA.

### @Transactional:

### @NoRepositoryBean:
Spring doesn't create a repository bean.

### @Param:
```java
@Query("FROM Person p WHERE p.name = :name")
Person findByName(@Param("name") String name);
```

### @Transient:
We can use this annotation to mark a field in a model class as transient. Hence the data store engine won't read or write this field's value.


### Auditing with JPA, Hibernate, and Spring Data JPA

### Internal callback methods
```java
@Entity
public class Bar {
      
    @PrePersist
    public void onPrePersist() { ... }
      
    @PreUpdate
    public void onPreUpdate() { ... }
      
    @PreRemove
    public void onPreRemove() { ... }
      
}
```