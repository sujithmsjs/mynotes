package com.suji;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
	
	public static void main(String[] args) {
		
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		
		
		Transaction trn = session.getTransaction();
		
		trn.begin();
		
		User user = session.get(User.class, 4);
		System.out.println(user);
		
		trn.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	
	public static void main3(String[] args) {
		Configuration config = new Configuration().configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction trn = session.getTransaction();
		trn.begin();
		
		User user = session.get(User.class, 4);
		System.out.println(user);
		
		trn.commit();
		session.close();
		sessionFactory.close();

	}
	
	public static void main2(String[] args) {
		// Getting Session Object
		Configuration config = new Configuration().configure();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
		
		User user = new User(2, "Vamshi", 6.2);
		user.setDob(new Date());
		//user.setMarks(78);
		
		
		Transaction trn = session.getTransaction();
		trn.begin();
		
		session.save(user);
		
		
		trn.commit();

		
		session.close();
		sf.close();

	}

	//Retriving Data
	public static void main1(String[] args) {
		Configuration config = new Configuration().configure();

		SessionFactory sf = config.buildSessionFactory();

		Session session = sf.openSession();

		Transaction trn = session.getTransaction();

		trn.begin();

		Student std = session.get(Student.class, 10);
		Laptop lap = std.getLaptop();
		System.out.println(std);
		System.out.println(lap);
		trn.commit();

		session.close();
		sf.close();

	}
}
