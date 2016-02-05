package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.Rack;

@Service("RackService")
@Transactional
public class RackServiceImpl implements RackService{

	@Override
	public List<Rack> getShelfRacks(int shelfId, Integer offset,
			Integer limit, String sorts, String fields, boolean all,
			Map<String, String> searchRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rack getShelfRack(int shelfId, int rackId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rack saveRack(Rack rack, int shelfId) {
		// TODO Auto-generated method stub
		return null;
	}

}
