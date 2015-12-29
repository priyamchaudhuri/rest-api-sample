package org.tat.api.library.repository;

import java.util.List;

import org.tat.api.library.model.Resource;
import org.tat.api.library.model.User;

public interface ResourceRepository {

	void saveResource(Resource resource);

	List<Resource> getResources();

	void deleteResource(int id);

	Resource getResource(int id);

	void updateResource(Resource resource);
	
	User getResourceUser(int id);
}