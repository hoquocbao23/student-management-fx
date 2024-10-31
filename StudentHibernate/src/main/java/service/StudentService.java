package service;

import java.util.List;
import java.util.Scanner;

import pojo.Student;
import repository.IStudentRepository;
import repository.StudentRepository;

public class StudentService implements IStudentService {
	String filename = "hibernate.cfg.xml";
	private IStudentRepository studentRepository = null;
	
	

	public StudentService() {
		studentRepository = new StudentRepository(filename);
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return studentRepository.getStudentById(id);
	}

	@Override
	public void save(Student student) {
		if (student.getMark() < 0 || student.getMark() > 10  ) {
			throw new IllegalAccessError("Mark is invalid");
		}
		studentRepository.save(student);
		
	}

	@Override
	public void delete(int id) {
		studentRepository.delete(id);
		
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.updateStudent(student);
	}

	@Override
	public Student readInformation() {
		Student newStudent = new Student();
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap first name");
		String firstname = sc.nextLine();
		System.out.println("Nhap last name");
		String lastname = sc.nextLine();
		System.out.println("Nhap diem");
		int mark = sc.nextInt();
		
		newStudent.setFirstname(firstname);
		newStudent.setLastname(lastname);
		newStudent.setMark(mark);
		return newStudent ;
	}

}
