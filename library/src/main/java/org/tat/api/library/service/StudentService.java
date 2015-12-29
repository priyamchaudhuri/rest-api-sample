package org.tat.api.library.service;

import java.util.List;
import java.util.Set;

import org.tat.api.library.controller.Sort;
import org.tat.api.library.model.Address;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;
 
public interface StudentService {
 
	void saveStudent(Student Student);

	List<Student> getStudents(int offset, int limit, List<Sort> sortConfigs);

	void deleteStudent(int id);

	Student getStudent(int id);
	
	Address getStudentAddress(int id);
	
	Set<Resource> getStudentResources(int id);
	
	Resource getStudentResource(int studentId,int resourceId);

	void updateStudent(Student Student);
}