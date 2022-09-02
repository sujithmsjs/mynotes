# Native Queries JPA Cheat Sheet
---
## Model class
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="emp")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private LocalDate hiredate;
	private BigDecimal sal;
	
	@Column(nullable = true)
	private BigDecimal comm;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "dept")
	private Department dept;

}
```

## Repository
```java
package com.suji.empmgnt.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suji.empmgnt.model.Employee;

@Repository
public interface EmployeeRepositoryNativeQuries extends JpaRepository<Employee, Integer> {
    /////////////////// /////////
	///// RETRIEVE QUERIES //////
	/////////////////// /////////
	
	// Fine Employees whose salary is 800
	@Query(value = "select * FROM EMP e where e.sal = 800", nativeQuery = true)
	public List<Employee> findWhosSalaryIs800();

	// Show the Max Salary
	@Query(value = "SELECT max(sal) FROM EMP", nativeQuery = true)
	public BigDecimal findMaxSalary();

	// Find Max Salaried Employee
	@Query(value ="SELECT * FROM emp WHERE sal IN(SELECT MAX(sal) FROM emp)", nativeQuery = true)
	public List<Employee> findMaxSalEmployee();
	
	// Select Employees matching with pattern
	@Query(value = "SELECT * FROM EMP WHERE ENAME LIKE %:pattern%", nativeQuery = true)
	public List<Employee> searchBy(String pattern);
	
	//////////////////////////////////////////////////////////////////////////////
	// NOTE: @Modifying queries can only use void or int/Integer as return type!
	// NOTE: Whenever you use this @Modiying methods, that method should be annotated with @Transactional
	// NOTE: Child table(EMP table) should not have "CascadeType.ALL" or "CascadeType.ALL"
	//////////////////////////////////////////////////////////////////////////////
	
	/////////////////// ////////
	///// DELETE QUERIES ///////
	/////////////////// ////////
	
	// Delete Employees whose name is like
	@Modifying
	@Query(value = "DELETE FROM EMP WHERE ENAME LIKE :name", nativeQuery = true)
	public long deleteByName(String name);
	
	// Delete Employees whose salary is below the given number
	@Modifying
	@Query(value = "DELETE FROM EMP WHERE SAL <= :salary", nativeQuery = true)
	public int deleteBelowSalaryEmployees(double salary);
	
	// Delete All Employees
	@Modifying
	@Query(value = "DELETE FROM EMP", nativeQuery = true)
	public long deleteAllEmployees();
	
	// Delete Employees whose name starts with the given pattern
	@Modifying
	@Query(value = "DELETE FROM EMP WHERE ENAME LIKE %:pattern", nativeQuery = true)
	public int deleteEmploeesStartsWith(String pattern);
	
	////////////////////////////
	///// UPDATE QUERIES //////
	///////////////////////////
	
	// Set :newSalary to those, whose salary is below :belowSalary.
	@Modifying
	@Query(value = "UPDATE EMP SET SAL=:newSalary WHERE SAL<=:belowSalary", nativeQuery = true)
	public int updateEmpSalary(double newSalary, double belowSalary);
	

	// Salary increment by Id
	@Modifying 
	@Query(value = "UPDATE EMP SET SAL = SAL + :increment WHERE EMPNO=:id", nativeQuery = true)
	public int updateSalaryById(int id, double increment);

	// Salary increment by Id
	@Modifying
	@Query(value = "UPDATE EMP SET SAL = SAL + :increment WHERE EMPNO=:id", nativeQuery = true)
	public List<Employee> updateSalaryById2(int id, double increment);

	
}

```