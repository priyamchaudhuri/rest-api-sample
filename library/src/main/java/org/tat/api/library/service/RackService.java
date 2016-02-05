package org.tat.api.library.service;

import java.util.List;
import java.util.Map;

import org.tat.api.library.model.Rack;

public interface RackService {

	List<Rack> getShelfRacks(int shelfId, Integer offset, Integer limit,
			String sorts, String fields, boolean all,
			Map<String, String> searchRequest);

	Rack getShelfRack(int shelfId, int rackId);

	Rack saveRack(Rack rack, int shelfId);

}
