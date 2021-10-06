package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import models.Tickets;

public class DAOupdate {

	public static void saveOrUpdateTicket(Tickets ticket) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = util.HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(ticket);
			tx.commit();
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
