package dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Tickets;
import models.Users;
import tools.VerboseFlags;
import util.HibernateSessionFactory;

public class DAOget {
	public static void none() {
	}

	public static Users getUser(String uName) {
		if(VerboseFlags.getUser()) {
			System.out.println("--------------DAOget-getUser---------------");
			System.out.println("uName:" + uName);
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		Users users = null;

		try {
			tx = session.beginTransaction();
			users = session.get(Users.class, Integer.parseInt(uName)); 

		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		if(VerboseFlags.getUser()) {
			System.out.println("users.toString(): " + users.toString());
			System.out.println("-------------/DAOget-getUser---------------");
		}
		return users;
	}
	public static List<Users> getUsersWhere(String constraint){
		if(VerboseFlags.getUsers()) {
			System.out.println("--------------DAOget-getUsers---------------");
			System.out.println("uconstraint:" + constraint);
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		List<Users> users = null;

		try {
			tx = session.beginTransaction();
			String hql = "FROM Users WHERE " + constraint;
			users = session.createQuery(hql, Users.class).getResultList(); 

		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		if(VerboseFlags.getUsers()) {
			System.out.println("users.toString(): " + users.toString());
			System.out.println("-------------/DAOget-getUsers---------------");
		}
		return users;
	} 

	public static Tickets getTicket(int tNumber) {
		if(VerboseFlags.getTicket())
			System.out.println("--------------getTicket-Method---------------");

		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		Tickets ticket = null;

		try {
			tx = session.beginTransaction();
			ticket = session.get(Tickets.class, tNumber); 
			if(VerboseFlags.getTicket())
				System.out.println(ticket.toString());


		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}

		if(VerboseFlags.getTicket())
			System.out.println("--------------getTicket-Method---------------");
		return ticket;
	}
	public static List<Tickets> getTicketsWhere(String constraint) {
		if(VerboseFlags.getTicketsWhere())
			System.out.println("--------------getTicketsWhere-Method---------------");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		List<Tickets> tickets = null;

		try {
			tx = session.beginTransaction();
			tickets = session.createQuery("FROM Tickets WHERE " + constraint, Tickets.class).getResultList();
			if(VerboseFlags.getTicketsWhere()) {
				for(Iterator<Tickets> iterator = tickets.iterator(); iterator.hasNext();) {
					Tickets ticket = iterator.next();
					System.out.println(ticket.toString());
				}
			}
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		if(VerboseFlags.getTicketsWhere())
			System.out.println("--------------getTicketsWhere-Method---------------");
		return tickets;
	}
	public static List<Tickets> getAllTickets(){
		if(VerboseFlags.getAllTickets())
			System.out.println("--------------getAllTickets-Method---------------");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		List<Tickets> tickets = null;

		try {
			tx = session.beginTransaction();
			tickets = session.createQuery("FROM Tickets", Tickets.class).getResultList();
			if(VerboseFlags.getAllTickets())
				for(Iterator<Tickets> iterator = tickets.iterator(); iterator.hasNext();) {
					Tickets ticket = iterator.next();
					System.out.println(ticket.toString());
				}
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}

		if(VerboseFlags.getAllTickets())
			System.out.println("-------------/getAllTickets-Method---------------");
		return tickets;
	}
	/**
	 * This only works when getting a full row from Tickets
	 * @param mathFunction
	 * @param cName
	 * @return a single Tickets Object
	 */
	public static List<Tickets> getTicketsMathFunction(String mathFunction, String cName, String optionalWhere) {
		String fillerAnd = " AND";
		String fillerWhere = "WHERE";
		if(optionalWhere==null) {
			optionalWhere="";
			fillerAnd = "";
			fillerWhere = "";
		}
		if(VerboseFlags.getTicketsMathFunction())
			System.out.println("--------------getTicketsMathFunction-Method---------------");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		List<Tickets> ticket = null;

		try {
			tx = session.beginTransaction();
			String hql = "FROM Tickets WHERE " + cName + "=(SELECT " + mathFunction + "(" + cName + ") FROM Tickets " + fillerWhere + optionalWhere + ")" + fillerAnd + optionalWhere;
			if(VerboseFlags.getTicketsMathFunction())
				System.out.println("hql: " + hql);
			ticket = session.createQuery(hql, Tickets.class).getResultList();
			if(VerboseFlags.getTicketsMathFunction())
				System.out.println("Ticket.toString():" + ticket.toString());
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}

		if(VerboseFlags.getTicketsMathFunction())
			System.out.println("-------------/getTicketsMathFunction-Method---------------");
		return ticket;
	}

	/**
	 * This only works when getting a full row from Tickets
	 * @param mathFunction
	 * @param cName
	 * @return a single Tickets Object
	 */
	public static List<Tickets> getTicketsMathFunction(String mathFunction, String cName) {
		return getTicketsMathFunction(mathFunction, cName, null);
	}
	public static Double getTicketsSingleColumnMathFunction(String mathFunction, String cName, String optionalWhere) {
		if(optionalWhere==null)
			optionalWhere = "";
		if(VerboseFlags.getTicketsSingleColumnMathFunction())
			System.out.println("--------------DAOget-getTicketsSingleColumnMathFunction---------------");
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		Object dbl = null;

		try {
			String hql = "Select " + mathFunction + "(" + cName + ") FROM Tickets" + optionalWhere;
			tx = session.beginTransaction();
			dbl = session.createQuery(hql, Object.class).uniqueResult();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}

		if(VerboseFlags.getTicketsSingleColumnMathFunction()) {
			System.out.println("dbl.toString():" + dbl.toString());
			System.out.println("-------------/DAOget-getTicketsSingleColumnMathFunction---------------");
		}
		// the database returns a COUNT as a long and not an int. this just makes it so that if that happens, it still shows up as a double
		return Double.parseDouble(dbl.toString());
	}

	public static Double getTicketsSingleColumnMathFunction(String mathFunction, String cName) {
		return getTicketsSingleColumnMathFunction(mathFunction, cName, null);
	}
}
