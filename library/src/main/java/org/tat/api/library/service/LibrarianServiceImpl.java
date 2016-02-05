package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.Librarian;
import org.tat.api.library.model.Resource;

@Service("LibrarianService")
@Transactional
public class LibrarianServiceImpl implements LibrarianService{

	@Override
	public Librarian saveLibrarian(Librarian librarian) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Librarian> getLibrarians(int offset, int limit, String sorts,
			String fields, boolean all, Map<String, String> searchRequest)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLibrarian(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Librarian getLibrarian(long id, String fields) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource getLibrarianResource(int studentId, int resourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLibrarian(Librarian librarian) {
		// TODO Auto-generated method stub
		
	}

}
