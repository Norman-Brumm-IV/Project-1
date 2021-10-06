package core;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Tickets;
import util.HibernateSessionFactory;

public class TestingThings {
	public static void main(String[] args) {
		TestingThings ME = new TestingThings();
		System.out.println(Double.parseDouble(null));
		
//		  ME.listEmployees();
	}
	
	private void listEmployees() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
//			List tickets = session.createQuery("FROM Tickets").list();
			List<Tickets> tickets = session.createQuery("FROM Tickets", Tickets.class).getResultList(); // this and the line above end up doing the same thing
			for(Iterator<Tickets> iterator = tickets.iterator(); iterator.hasNext();) {
				Tickets ticket = (Tickets) iterator.next();
				System.out.println(ticket.toString());
			}
		} catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	
	

}
