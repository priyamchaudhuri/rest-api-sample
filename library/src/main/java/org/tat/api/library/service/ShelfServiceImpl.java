package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.Shelf;

@Service("ShelfService")
@Transactional
public class ShelfServiceImpl implements ShelfService{

	@Override
	public List<Shelf> getShelves(Integer offset, Integer limit, String sorts,
			String fields, boolean all, Map<String, String> searchRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shelf getShelf(int shelfId, String fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shelf saveShelf(Shelf shelf) {
		// TODO Auto-generated method stub
		return null;
	}

}
