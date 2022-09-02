package com.suji.main;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.suji.dbutil.HibernateUtil;
import com.suji.model.Question;
import com.suji.service.QuestionGenerator;

public class App {
	public static void main(String[] args) {
		Session session = HibernateUtil.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		
		Question question = QuestionGenerator.getQuestion("Very Small?<Tiny: Tin>");
		
		System.out.println(question);
		int n = (int) session.save(question);
		System.out.println("Saved id :"+n);
		
		transaction.commit();
	}
}
