package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.tat.api.library.model.Owner;
import org.tat.api.library.model.Resource;

 
public interface ResourceService {
 
	Resource saveResource(Resource resource);

	List<Resource> getResources(Integer offset, Integer limit, String sorts, String fields, boolean all, Map<String, String> searchRequest) throws Exception;

	void deleteResource(int id);

	Resource getResource(long id);

	void updateResource(Resource Resource);
	
    List<Resource> getStudentResources(long id,int offset, int limit, String sorts, String fields, boolean all, Map<String, String> searchRequest) throws Exception;
    
    Resource getStudentResource(long studentId, long resourceId);
    
    List<Resource> getTeacherResources(long id,int offset, int limit, String sorts, String fields, boolean all, Map<String, String> searchRequest);
    
    Resource getTeacherResource(long teacherId, long resourceId);

	Resource getLibrarianResource(long librarianId, long resourceId);

	List<Resource> getLibrarianResources(long librarianId, Integer offset,
			Integer limit, String sorts, String fields, boolean all,
			Map<String, String> searchRequest);

	Owner getResourceOwner(int resourceId);
}