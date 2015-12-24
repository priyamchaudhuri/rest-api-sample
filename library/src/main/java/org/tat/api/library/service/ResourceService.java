package org.tat.api.library.service;

import java.util.List;

import org.tat.api.library.model.Resource;

 
public interface ResourceService {
 
	void saveResource(Resource resource);

	List<Resource> getResources();

	void deleteResource(int id);

	Resource getResource(int id);

	void updateResource(Resource Resource);
}