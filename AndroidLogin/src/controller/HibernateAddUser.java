package controller;

import com.mysql.jdbc.Connection;
import java.util.Date;
import model.Users;
import model.Login;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateAddUser {
	
	private static SessionFactory factory;
    private static Transaction tx;
    private static Session session;
    
    @SuppressWarnings("deprecation")
	public HibernateAddUser () {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
      }
    }
    
    public static void closeConnection() {
        factory.close();
    }
    
    public void addUser(String firstName, String lastName, String email, int age, Date createDate) {
        session = factory.openSession();
        try {
            tx = session.beginTransaction();
            Users user = new Users(firstName, lastName, email, age, createDate);
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setAge(age);
            user.setCreateDate(createDate);   
            session.save(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.flush();
            session.close();
        }
    }
    
    public void addLogin(String userName, String password, Date createDate) {
    		session = factory.openSession();
    		try {
    			tx = session.beginTransaction();
    			Login login = new Login(userName, password, createDate);
    			login.setUsername(userName);
    			login.setPassword(password);
    			login.setCreateDate(createDate);
    			session.save(login);
    			tx.commit();
    		} catch (HibernateException e) {
    			if (tx!=null) tx.rollback();
    			e.printStackTrace();
    		} finally {
    			session.flush();
    			session.close();
    		}
    	
    }

}
