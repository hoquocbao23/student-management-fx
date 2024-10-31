package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pojo.Student;

public class HStudentDao {
	private SessionFactory sessionFactory ;
	private Configuration configuration ;
	
		public HStudentDao(String persistanceName) {
			configuration = new Configuration();
			configuration = configuration.configure(persistanceName);
			sessionFactory = configuration.buildSessionFactory();
		}
		
		public void save(Student student) {
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(student);
				t.commit();
				System.out.println("Successfully saved");
			} catch (Exception e) {
				// TODO: handle exception
				t.rollback();
				System.out.println("Error" + e.getMessage());
			} finally {
				//sessionFactory.close();
				//session.close();
			}
		}
		public List<Student> getStudents() {
			List<Student> students = null;
			Session session = sessionFactory.openSession();
			try {
				students = session.createQuery("from Student", Student.class).list();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error" + e.getMessage());
			} 
			return students;
		}
		public void delete(int studentId) {
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			try {
				
				Student student = (Student) session.get(Student.class,studentId);
				session.delete(student);
				t.commit();
				System.out.println("Delete saved");
			} catch (Exception e) {
				// TODO: handle exception
				t.rollback();
				throw e;
			} 
		}
		
		public Student findById(int studentId) {
			Session session = sessionFactory.openSession();
			try {
				return (Student) session.get(Student.class,studentId);
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
		}
		
		public void update(Student student) {
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(student);
				t.commit();
				System.out.println("Update successfully");
			} catch (Exception e) {
				t.rollback();
				System.out.println("Error "+e.getMessage());
			} finally {
				//sessionFactory.close();
				//session.close();
			}
		}
	}

	
	


