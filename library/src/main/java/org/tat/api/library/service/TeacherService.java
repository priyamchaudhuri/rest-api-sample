package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Teacher;


public interface TeacherService {

	Teacher saveTeacher(Teacher teacher)throws Exception;

	List<Teacher> getTeachers(int offset, int limit, String sorts, String fields, boolean all, Map<String, String> searchRequest) throws Exception;

	void deleteTeacher(int id);

	Teacher getTeacher(long id,String fields)throws Exception;

    Resource getTeacherResource(int studentId, int resourceId);

	void updateTeacher(Teacher teacher);
}
