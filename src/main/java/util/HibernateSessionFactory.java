package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tools.VerboseFlags;

/*
 * We are no longer explicitly using JDBC, so we don't need our ConnectionFactory.
 * That said, we will need a way to pass Hibernate sessions around our
 * application. As such, we'll build our SessionFactory, which will return
 * sessions based on the configuration details specified in our
 * hibernate.cfg.xml file, here.
 */
public class HibernateSessionFactory {

	/*
	 * There is only ever going to be a single SessionFactory
	 * in this application. This follows the singleton design
	 * pattern.
	 */
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		if(VerboseFlags.getSession())
			System.out.println("--------------SessionFactory-getSession---------------");
    	Properties props = new Properties();
    	FileReader fr;
		try {
			fr = new FileReader("src/main/resources/connection.properties");
	    	props.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.username", props.getProperty("userName"))
					.setProperty("hibernate.connection.password", props.getProperty("password"))
					.buildSessionFactory();
			
			if(VerboseFlags.getSession()) {
				System.out.println("Session:"+sessionFactory.getCurrentSession());
				System.out.println("-------------/SessionFactory-getSession---------------");
			}
		}
		
		return sessionFactory.getCurrentSession();
	}
}
