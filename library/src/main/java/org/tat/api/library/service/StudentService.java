package org.tat.api.library.service;

import java.util.List;

import org.tat.api.library.model.Student;
 
public interface StudentService {
 
	void saveStudent(Student Student);

	List<Student> getStudents();

	void deleteStudent(int id);

	Student getStudent(int id);

	void updateStudent(Student Student);
}