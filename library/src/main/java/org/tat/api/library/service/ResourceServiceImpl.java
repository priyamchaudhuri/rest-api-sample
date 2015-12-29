package org.tat.api.library.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.User;
import org.tat.api.library.repository.ResourceRepository;

@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository dao;

	public void saveResource(Resource Resource) {
		dao.saveResource(Resource);
	}

	public List<Resource> getResources() {
		return dao.getResources();
	}

	public void deleteResource(int id) {
		dao.deleteResource(id);
	}

	public Resource getResource(int id) {
		return dao.getResource(id);
	}

	public void updateResource(Resource Resource) {
		dao.updateResource(Resource);
	}
	
	public User getResourceUser(int id) {
		return dao.getResourceUser(id);
	}
}
