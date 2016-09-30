package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.Owner;
import org.tat.api.library.model.Resource;
import org.tat.api.library.repository.ResourceRepository;

@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository dao;

	public Resource saveResource(Resource Resource) {
		return dao.saveResource(Resource);
		
	}

	public void deleteResource(int id) {
		dao.deleteResource(id);
	}

	public Resource getResource(long id) {
		return dao.getResource(id);
	}

	public void updateResource(Resource Resource) {
		dao.updateResource(Resource);
	}
	
	public Owner getResourceOwner(int id) {
		return dao.getResourceOwner(id);
	}
	
	public List<Resource> getStudentResources(long id,int offset, int limit, String sorts, boolean all, Map<String, String> searchRequest) throws Exception {
		return dao.getStudentResources(id,offset, limit, sorts, all, searchRequest);
	}
	
	public Resource getStudentResource(long studentId, long resourceId) {

		return dao.getStudentResource(studentId, resourceId);
	}
	
	public List<Resource> getTeacherResources(long id,int offset, int limit, String sorts,boolean all, Map<String, String> searchRequest) {
		//return dao.getStudentResources(id,offset, limit, sorts, fields, all, searchRequest);
		return null;
	}
	
	public Resource getTeacherResource(long teacherId, long resourceId) {

		//return dao.getStudentResource(studentId, resourceId);
		return null;
	}

	@Override
	public Resource getLibrarianResource(long librarianId, long resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getLibrarianResources(long librarianId,
			Integer offset, Integer limit, String sorts, 
			boolean all, Map<String, String> searchRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getResources(Integer offset, Integer limit,
			String sorts, boolean all,
			Map<String, String> searchRequest) throws Exception {
		return dao.getResources(offset, limit, sorts, all, searchRequest);
	}
}
