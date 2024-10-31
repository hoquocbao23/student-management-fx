package controller;

import java.util.List;
import java.util.Scanner;

import dao.StudentDao;
import pojo.Student;
import repository.IStudentRepository;
import service.IStudentService;
import service.StudentService;

public class StudentMain {

	public static void main(String[] args) {
		IStudentService studentService = new StudentService();
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Choice:");
			choice = sc.nextInt();
			switch (choice) {
				case 0:
					System.out.println("Quit");
					break;
				case 1:  
					Student saveStudent = studentService.readInformation();
					studentService.save(saveStudent);
					break;
				case 2:
					System.out.println("List Student:");
					List<Student> list = studentService.findAll();
					for (Student student : list) {
						System.out.println(student.toString());
					}
					break;
				case 3:
					System.out.println("StudentID:");
					int id = sc.nextInt();
					Student find = studentService.getStudentById(id);
					if (find != null) {
						System.out.println(find.toString());
					}else {
						System.out.println("Not Found");
					}
					break;
				case 4:
					System.out.println("Student you want delete:");
					int deleteId = sc.nextInt();
					 studentService.delete(deleteId);;
					break;
				
				
				default:
					System.out.println("So khac!");
			}
		}while ( choice != 0);
	}

}
