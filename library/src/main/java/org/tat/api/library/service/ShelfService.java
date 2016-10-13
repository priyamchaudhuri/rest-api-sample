package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.tat.api.library.model.Shelf;

public interface ShelfService {

	List<Shelf> getShelves(Integer offset, Integer limit, String sorts, String fields, boolean all, Map<String, String> searchRequest);

	Shelf getShelf(int shelfId, String fields);

	Shelf saveShelf(Shelf shelf);

}
