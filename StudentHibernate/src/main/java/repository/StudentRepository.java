package repository;

import java.util.List;

import dao.HStudentDao;
import dao.StudentDao;
import pojo.Student;

public class StudentRepository implements IStudentRepository {
	private HStudentDao studentDao = null;
	
	public StudentRepository(String filename) {
		studentDao = new HStudentDao(filename);
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentDao.getStudents();
	}

	@Override
	public void save(Student student) {
		studentDao.save(student);
		
	}

	@Override
	public void delete(int id) {
		studentDao.delete(id);
		
	}

	@Override
	public void updateStudent(Student student) {
		studentDao.update(student);
	}

	@Override
	public Student getStudentById(int id) {
		
		return studentDao.findById(id);
	}


}
