# Cram


### File Structure
```pre
+ Cram\ 
	|---  pom.xml
	+ \src\main\resources
		|---  log4j.properties
	+ \src\main\java\com\suji
		|---  App.java
	+ \src\test\java\com\suji
		|---  AppTest.java
	+ \src\main\java\com\suji\model
		|---  Employee.java
		|---  Student.java
		|---  UserData.java
	+ \src\main\java\com\suji\dbutil
		|---  HQLRunner.java
		|---  HibUtil.java
		|---  HibernateUtil.java
```
### Index
```pre
1. log4j.properties
2. pom.xml
3. model\Employee.java
4. model\Student.java
5. model\UserData.java
6. src\main\java\com\suji\App.java
7. src\main\java\com\suji\dbutil\HQLRunner.java
8. src\main\java\com\suji\dbutil\HibUtil.java
9. src\main\java\com\suji\dbutil\HibernateUtil.java
10. src\test\java\com\suji\AppTest.java

```

---

### 1. log4j.properties

#### log4j.properties

```properties

# Root logger details
log4j.rootLogger=INFO,stdout, file
# Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=[%p] %m%n

# Redirect log messages to a log file
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=myapp.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p] %m%n
```

---

### 2. pom.xml

#### pom.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.suji</groupId>
	<artifactId>UserLogin</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>UserLogin</name>
	<url>http://maven.apache.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>


	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.6.4.Final</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.16</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/log4j/log4j -->
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.22</version>
			<scope>provided</scope>
		</dependency>


	</dependencies>
</project>
```

---

### 3. Employee.java

#### model\Employee.java

```java

package com.suji.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;
	
}

```

---

### 4. Student.java

#### model\Student.java

```java

package com.suji.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int marks;
	
	public Student() {
	}
	
	public Student(String name, int marks) {
		super();
		this.name = name;
		this.marks = marks;
	}
}

```

---

### 5. UserData.java

#### model\UserData.java

```java

package com.suji.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.TrueFalseType;

import lombok.Data;

@Data
@Entity
@Table(name = "demo1")
public class UserData {
	@Id
	@Column(name = "user_id", length = 5)
	//@GeneratedValue
	private int uid;
	private String name;
	@Column(name = "username",length = 20,unique = true, nullable = false)
	private String username;
	@Column(nullable = false, length = 10)
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Calendar dob;
	
	@Temporal(TemporalType.TIME)
	@Column(name="birth_time")
	private Date birthtime;

	private double cgpa;
	private float percentage;
	private byte section;
	private int marks;
	
	@Column(name = "has_ncc", nullable = false)
	private boolean hasNCC;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	
	
	public UserData() {
	}
	
	public UserData(int uid ,String name, String username) {
		uid = uid;
		name = name;
		username = username;
	}
	
	public UserData(int uid ,String name, String username, String password) {
		uid = uid;
		name = name;
		username = username;
		password = password;
	}

	public UserData(int uid ,String name, String username, String password, double cgpa, int marks, boolean hasNCC) {
		uid = uid;
		name = name;
		username = username;
		password = password;
		cgpa = cgpa;
		marks = marks;
		hasNCC = hasNCC;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		password = password;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		cgpa = cgpa;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		marks = marks;
	}

	public boolean isHasNCC() {
		return hasNCC;
	}

	public void setHasNCC(boolean hasNCC) {
		hasNCC = hasNCC;
	}



	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		modifyDate = modifyDate;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		dob = dob;
	}

	public Date getBirthtime() {
		return birthtime;
	}

	public void setBirthtime(Date birthtime) {
		birthtime = birthtime;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		percentage = percentage;
	}

	public byte getSection() {
		return section;
	}

	public void setSection(byte section) {
		section = section;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "UserData [uid=" + uid + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}


	
	


	
	
	
}

```

---

### 6. App.java

#### src\main\java\com\suji\App.java

```java

package com.suji;

interface I{
	int m1();
}

class B implements I{

	@Override
	public int m1() {
		System.out.println("B class...");
		return 0;
	}
	
}

class A implements I{

	@Override
	public int m1() {
		System.out.println("A class...");
		return 0;
	}
	
}

public class App {	

	public static void main(String[] args) {
		int[] n = new int[10];

		I i = new A();
		I i2 = new B();
		System.out.println(i2.getClass());
	
	}
	
}

```

---

### 7. HQLRunner.java

#### src\main\java\com\suji\dbutil\HQLRunner.java

```java

package com.suji.dbutil;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;


public class HQLRunner extends JFrame implements ActionListener, ListSelectionListener{
	
	private SessionFactory sessionFactory = null;
	
	// all containers 
	private JPanel pnlTop = new JPanel(new BorderLayout());
	private JPanel pnlBottom = new JPanel(new BorderLayout());	
	private JSplitPane pnlSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlTop, pnlBottom);

	// all components
	private JTextArea txtHQL = new JTextArea(6,100);
	
	private JComboBox cmbLimit = new JComboBox(new String[]{"All", "100", "1000", "10000"});
	private JButton btnRunHQL = new JButton("Run HQL Query");
	private JButton btnRunSQL = new JButton("Run SQL Query");   
	
	private JTable tblResult = new JTable();
	private JTextArea txtGeneratedSQL = new JTextArea();
	private JList lstHistory = new JList();	

	// model holds all the previously executed queries...
	private List<String> lstQueryHistory = new ArrayList<String>();
	
	private HQLRunner(SessionFactory sessFact){
		
		sessionFactory = sessFact;
		
		setMinimumSize(new Dimension(800,600));
		setTitle("HQL Runner");
		
		pnlTop.setBorder(new javax.swing.border.TitledBorder("Enter your HQL below and hit 'Run'"));
		pnlTop.add(txtHQL, BorderLayout.CENTER);
		cmbLimit.setSelectedIndex(1); // limit 100 is selected by default...
		btnRunHQL.addActionListener(this);
		btnRunSQL.addActionListener(this);
		
		pnlTop.add( new JPanel() {{ 
							add(new JLabel(" Limit"));
							add(cmbLimit);
							add(new JLabel("Rows "));
							add(btnRunHQL);
							add(btnRunSQL);			
						}}
						, BorderLayout.SOUTH);
		
		pnlBottom.add( new JTabbedPane() {{
								addTab("Result", new JScrollPane(tblResult));
								addTab("Generated SQL", new JScrollPane(txtGeneratedSQL));
								addTab("History", lstHistory);
							}}, BorderLayout.CENTER);
		
		lstHistory.addListSelectionListener(this);
		lstHistory.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		pnlSplit.setDividerLocation(0.4d);
		
		getContentPane().add(pnlSplit);
		
		setVisible(true);
	}

	public void runHQL(String strHQL){
		addToHistory(strHQL);
		txtGeneratedSQL.setText(toSql(strHQL));
		
		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();
		
		Query q = session.createQuery(strHQL);
		
		if (!"All".equals(cmbLimit.getSelectedItem())) {
			q.setMaxResults( Integer.parseInt((String)cmbLimit.getSelectedItem()) );
		}
		
		List lst = q.list();
		setResult(lst);
	}
	
	public void runSQL(String strHQL){
		addToHistory(strHQL);
		
		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();
		trans.begin();
		 NativeQuery query = session.createSQLQuery(strHQL);
		
		
		if (!"All".equals(cmbLimit.getSelectedItem())) {
			query.setMaxResults( Integer.parseInt((String)cmbLimit.getSelectedItem()) );
		}
		List lst = query.list();
		trans.commit();
		setResult(lst);
	}	
	
	private void addToHistory(String strHQL){
		
		lstQueryHistory.add(strHQL);
		lstHistory.setModel(new ListModel() {
			@Override public void removeListDataListener(ListDataListener arg0) {}
			@Override public int getSize() { return lstQueryHistory.size();}
			@Override public Object getElementAt(int index) { return lstQueryHistory.get(index);}
			@Override public void addListDataListener(ListDataListener arg0) {}
		});		
	}
	
	private void setResult(final List lst){
		
		tblResult.setModel(new TableModel() {
			
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
			
			@Override
			public void removeTableModelListener(TableModelListener l) {}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {   
				
				Object row = lst.get(rowIndex);
				if (row instanceof Object[]) {
					Object[] cols = (Object[])row;
					return cols[columnIndex];
				}else {
					try{
						BeanInfo testBeanInfo = Introspector.getBeanInfo(row.getClass());
						return testBeanInfo.getPropertyDescriptors()[columnIndex].getReadMethod().invoke(row);
					}catch(Exception ex){
						ex.printStackTrace();
						return row;
					}
				}
			}
			
			@Override
			public int getRowCount() { return lst.size();}
			
			@Override
			public String getColumnName(int columnIndex) {
				if (lst.size()>0){
					Object row = lst.get(0);
					if (row instanceof Object[]) {
						return ((Object[])row)[columnIndex].getClass().getName();
					}else {
						try {
							BeanInfo testBeanInfo = Introspector.getBeanInfo(row.getClass());
							return testBeanInfo.getPropertyDescriptors()[columnIndex].getName();							
						} catch (IntrospectionException e) {
							e.printStackTrace();
							return "????";
						}
					}
				}					
				return "";
			}
			
			@Override
			public int getColumnCount() {
				if (lst.size()>0){
					Object row = lst.get(0);
					if (row instanceof Object[]) {
						return ((Object[])row).length;
					}else {
						try {
							BeanInfo testBeanInfo = Introspector.getBeanInfo(row.getClass());
							return testBeanInfo.getPropertyDescriptors().length;
						} catch (IntrospectionException e) {
							e.printStackTrace();
							return 0;
						}
					}
				}
				return 0;
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (lst.size()>0){
					Object row = lst.get(0);
					if (row instanceof Object[]) {
						return ((Object[])row)[columnIndex].getClass();
					}else return Object.class;
				}				
				return null;
			}
			
			@Override
			public void addTableModelListener(TableModelListener l) {
				
			}
		});		
	}

	// ref: http://narcanti.keyboardsamurais.de/hibernate-hql-to-sql-translation.html
	public String toSql(String hqlQueryText){

	    if (hqlQueryText!=null && hqlQueryText.trim().length()>0){
	      final QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
	      final SessionFactoryImplementor factory = (SessionFactoryImplementor) sessionFactory;
	      final QueryTranslator translator = translatorFactory.createQueryTranslator(hqlQueryText, hqlQueryText, java.util.Collections.EMPTY_MAP , factory, null);
	      translator.compile(java.util.Collections.EMPTY_MAP, false);
	      return translator.getSQLString();
	    }
	    return null;
	  }
	

	/****  Event-Handlers  ****/ 
	public void actionPerformed(ActionEvent e) {
		
		
		setResult(new ArrayList());
		
		
		
		txtGeneratedSQL.setText("");		
		if (e.getSource() == btnRunHQL) runHQL(txtHQL.getText());
		else if (e.getSource() == btnRunSQL) runSQL(txtHQL.getText());
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		txtHQL.setText(lstQueryHistory.get(lstHistory.getSelectedIndex()));
	}

	
	/****  Main Method  ****/
	public static void main(String[] args) {
		HQLRunner runner = new HQLRunner(HibernateUtil.getSessionFactory());
		runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

```

---

### 8. HibUtil.java

#### src\main\java\com\suji\dbutil\HibUtil.java

```java

package com.suji.dbutil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibUtil {
	private SessionFactory sessionFactory;

	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings																					// hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}

```

---

### 9. HibernateUtil.java

#### src\main\java\com\suji\dbutil\HibernateUtil.java

```java

package com.suji.dbutil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			if(sessionFactory == null) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				
				
				
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();
				
			}
			return sessionFactory;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Session openSession() {
		if(sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;	
	}
	
	public static void shutdown() {
		sessionFactory.close();
	}
}

```

---

### 10. AppTest.java

#### src\test\java\com\suji\AppTest.java

```java

package com.suji;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}

```

---

