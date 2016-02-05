package org.tat.api.library.repository;

import java.util.List;
import java.util.Map;

import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;

public interface StudentRepository {

	Student saveStudent(Student user) throws Exception;

	List<Student> getStudents(int offset, int limit, String sorts, String fields, boolean all, Map<String, String> searchRequest) throws Exception;

	void deleteStudent(int id);

	Student getStudent(long id,String fields)throws Exception;

	void updateStudent(Student user);

	Resource getStudentResource(int studentId, int resourceId);
}