package org.tat.api.library.repository;

import java.util.List;
import java.util.Set;

import org.tat.api.library.model.Address;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;

public interface StudentRepository {

	void saveStudent(Student user);

	List<Student> getStudents();

	void deleteStudent(int id);

	Student getStudent(int id);
	
	Address getStudentAddress(int id);
	
	Set<Resource> getStudentResources(int id);

	void updateStudent(Student user);
}