package repository;

import java.util.List;

import pojo.Student;

public interface IStudentRepository {
	public List<Student> findAll();
	public Student getStudentById(int id);
	public void save(Student student);
	public void delete(int id);
	public void updateStudent(Student student);
}
