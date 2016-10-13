package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.tat.api.library.model.Librarian;
import org.tat.api.library.model.Resource;

public interface LibrarianService {
	Librarian saveLibrarian(Librarian librarian)throws Exception;

	List<Librarian> getLibrarians(int offset, int limit, String sorts, String fields, boolean all, Map<String, String> searchRequest) throws Exception;

	void deleteLibrarian(int id);

	Librarian getLibrarian(long id,String fields)throws Exception;

    Resource getLibrarianResource(int studentId, int resourceId);

	void updateLibrarian(Librarian librarian);
}
