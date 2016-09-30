package org.tat.api.library.repository;

import java.util.List;
import java.util.Map;

import org.tat.api.library.model.Owner;
import org.tat.api.library.model.Resource;

public interface ResourceRepository {

	Resource saveResource(Resource resource);

	List<Resource> getResources(Integer offset, Integer limit, String sorts, boolean all, Map<String, String> searchRequest) throws Exception;

	void deleteResource(int id);

	Resource getResource(long id);

	void updateResource(Resource resource);
	
	List<Resource> getStudentResources(long id,int offset, int limit, String sorts, boolean all, Map<String, String> searchRequest) throws Exception;
	
	Resource getStudentResource(long studentId, long resourceId);

	Owner getResourceOwner(int id);
}