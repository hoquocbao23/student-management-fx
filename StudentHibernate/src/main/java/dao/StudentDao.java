package dao;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Session;

import pojo.Student;
public class StudentDao {
	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	public StudentDao(String persistanceName) {
		emf = Persistence.createEntityManagerFactory(persistanceName);
	}
	
	public void saveStudent(Student student) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(student);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Error" + e.getMessage());
		}finally {
			//em.close();
		}
		
		
		
	}
	public List<Student> getAllStudent(){
		List<Student> list = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			list = em.createQuery("from Student").getResultList();
			
		} catch (Exception e) {
			
			System.out.println("Error" + e.getMessage());
		}
		return list;
	}
	
	public void updateStudent(Student student) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Student findStudent = em.find(Student.class, student.getId());
			if (findStudent != null) {
				if (!student.getFirstname().equals("")) {
					findStudent.setFirstname(student.getFirstname());
				}
				if (!student.getLastname().equals("")) {
					findStudent.setLastname(student.getLastname());
				}
				
					findStudent.setMark(student.getMark());
				
				
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
	}
	
	public Student getStudentById(int studentID) {
		Student student = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			student = em.find(Student.class, studentID);
		}catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}
		return student;
	}
	
	public void deleteStudent(int studentId) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Student temp = em.find(Student.class, studentId);
			em.remove(temp);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}
		
	}
	

}
