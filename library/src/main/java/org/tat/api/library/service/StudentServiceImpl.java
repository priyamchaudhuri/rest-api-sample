package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;
import org.tat.api.library.repository.StudentRepository;

@Service("StudentService")
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository dao;

	public Student saveStudent(Student Student) throws Exception{
		return dao.saveStudent(Student);
	}

	public List<Student> getStudents(int offset, int limit, String sorts, boolean all, Map<String, String> searchRequest) throws Exception {
		return dao.getStudents(offset, limit, sorts, all, searchRequest);
	}

	public void deleteStudent(int id) {
		dao.deleteStudent(id);
	}

	public Student getStudent(long id) throws Exception {
		return dao.getStudent(id);
	}



	public void updateStudent(Student Student) {
		dao.updateStudent(Student);
	}

	public Resource getStudentResource(int studentId, int resourceId) {

		return dao.getStudentResource(studentId, resourceId);
	}

}
