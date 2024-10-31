package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pojo.Account;

public class AccountDao {
	private SessionFactory sessionFactory ;
	private Configuration configure ;
	
	public AccountDao(String persistanceName) {
		configure = new Configuration();
		configure = configure.configure(persistanceName);
		sessionFactory = configure.buildSessionFactory();
	}
	
	public Account checkLogin(String username) {
			Session session = sessionFactory.openSession();
			try {
				return (Account) session.get(Account.class,username);
			} catch (Exception e) {
				throw e;
			}
		}
		
	}