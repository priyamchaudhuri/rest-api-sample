package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Teacher;

@Service("TeacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService{

	@Override
	public Teacher saveTeacher(Teacher teacher) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getTeachers(int offset, int limit, String sorts,
			String fields, boolean all, Map<String, String> searchRequest)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTeacher(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Teacher getTeacher(long id, String fields) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource getTeacherResource(int studentId, int resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}

}
