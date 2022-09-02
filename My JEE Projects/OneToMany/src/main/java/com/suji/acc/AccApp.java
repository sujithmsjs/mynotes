package com.suji.acc;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.suji.conn.HibernateUtil;

public class AccApp {
	
	public static void main(String[] args) {

		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();
		
		User u = new User("Sujith");
		Acc a1 = new Acc("HDFC", u);
		Acc a2 = new Acc("CITY", u);
		Acc a3 = new Acc("DBS");
		
		
		List<Acc> accs = new ArrayList<Acc>();
		accs.add(a1);
		accs.add(a2);
		accs.add(a3);
		
		u.setAcc(accs);
		System.out.println("===============================BEFORE SAVE==============================");
		session.save(u);
		System.out.println("=============================== AFTER SAVE ==============================");
		trx.commit();

		System.out.println("End of Code");
	}
}
