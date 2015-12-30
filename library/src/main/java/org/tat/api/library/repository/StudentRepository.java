package org.tat.api.library.repository;

import java.util.List;
import java.util.Set;

import org.tat.api.library.controller.Sort;
import org.tat.api.library.model.Address;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;

public interface StudentRepository {

	Student saveStudent(Student user);

	List<Student> getStudents(int offset, int limit, List<Sort> sortConfig);

	void deleteStudent(int id);

	Student getStudent(int id);
	
	Address getStudentAddress(int id);
	
	Set<Resource> getStudentResources(int id);

	void updateStudent(Student user);
	
	Resource getStudentResource(int studentId,int resourceId);
}