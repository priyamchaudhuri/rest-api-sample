package org.tat.api.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tat.api.library.model.Rack;
import org.tat.api.library.model.Shelf;
import org.tat.api.library.service.RackService;
import org.tat.api.library.service.ShelfService;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

	@Autowired
	ShelfService service;
	
	@Autowired
	RackService rackService;

	@ApiOperation(httpMethod="GET",value="Returns list of Shelves",responseContainer="List",notes="Returns list of shelves")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of shelf list"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public List<Shelf> getShelves(
			@RequestParam(value = "offset", defaultValue = "0") @ApiParam(required=false,value="Start index for page",defaultValue="0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam(required=false,value="No.of items",defaultValue="20") Integer limit,
			@RequestParam(value = "sorts", required = false) @ApiParam(required=false,value="List of sort fields") String sorts,
			@RequestParam(value = "fields", required = false) @ApiParam(required=false,value="List of fields to be fetched") String fields,
			@RequestParam(value = "all", required = false, defaultValue = "false") @ApiParam(required=false,value="Flag to fetch all records",defaultValue="false") boolean all,
			@RequestParam(value = "id", required = false) @ApiParam(required=false,value="Id of the shelf") String id,
			@RequestParam(value = "location", required = false) @ApiParam(required=false,value="Location of the shelf") String location
			){
		Map<String, String> searchRequest = new HashMap<String, String>();

		if(id != null)
			searchRequest.put("id", id);
		if(location != null)
			searchRequest.put("location", location);
		List<Shelf> shelves = service.getShelves(offset, limit, sorts, fields, all, searchRequest);
		return shelves;

	}

	@ApiOperation(httpMethod="GET",value="Returns shelf with the specified Id",notes="Returns shelf detail",response=Shelf.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of shelf details", response = Shelf.class),
			@ApiResponse(code = 404, message = "Shelf with given id does not exist"),
			@ApiResponse(code = 500, message = "Internal server error")}
			)
	@RequestMapping(value = "/{shelfId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public Shelf getShelf(@PathVariable int shelfId,@ApiParam(required=false,value="List of fields to be fetched") @RequestParam(value = "fields", required = false) String fields,
			HttpServletRequest request, HttpServletResponse response) {

		Shelf shelf = service.getShelf(shelfId,fields);
		return shelf;
	}
	
	@ApiOperation(httpMethod="GET",value="Returns list of racks",notes="Returns racks assigned to the shelf with the specified Id",responseContainer="List")
	@RequestMapping(value = "/{shelfId}/racks", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public List<Rack> getShelfRacks(
			@RequestParam(value = "offset", defaultValue = "0") @ApiParam(required=false,value="Start index for page",defaultValue="0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam(required=false,value="No.of items",defaultValue="20") Integer limit,
			@RequestParam(value = "sorts", required = false) @ApiParam(required=false,value="List of sort fields") String sorts,
			@RequestParam(value = "fields", required = false) @ApiParam(required=false,value="List of fields to be fetched") String fields,
			@RequestParam(value = "all", required = false, defaultValue = "false") @ApiParam(required=false,value="Flag to fetch all records",defaultValue="false") boolean all,
			@RequestParam(value = "id", required = false) @ApiParam(required=false,value="Id of the rack") String id,
			@RequestParam(value = "rackNumber", required = false) @ApiParam(required=false,value="Rack Number") String rackNumber,
			@PathVariable int shelfId,
			HttpServletRequest request, HttpServletResponse response) {

	Map<String, String> searchRequest = new HashMap<String, String>();
		
		if(id != null)
			searchRequest.put("id", id);
		if(rackNumber != null)
			searchRequest.put("fname", rackNumber);
		List<Rack> racks = rackService.getShelfRacks(shelfId,offset, limit, sorts, fields, all, searchRequest);
		return racks;
	}

	@ApiOperation(httpMethod="GET",value="Returns rack detail",notes="Returns rack with the specified Id",response=Rack.class)
	@RequestMapping(value = "/{shelfId}/racks/{rackId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public Rack getRack(
			@PathVariable int shelfId,@PathVariable int rackId,
			HttpServletRequest request, HttpServletResponse response) {
		
		Rack rack = rackService.getShelfRack(shelfId,rackId);
		return rack;
	}
	
	@ApiOperation(httpMethod="POST",value="Create New Shelf",response=Shelf.class)
	@Transactional
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public Shelf createShelf(@Valid @RequestBody Shelf shelf,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return service.saveShelf(shelf);
	}
	
	@ApiOperation(httpMethod="POST",value="Create New Rack",response=Rack.class)
	@Transactional
	@RequestMapping(value="/{shelfId}/racks",method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public Rack createRack(@Valid @RequestBody Rack rack,@PathVariable int shelfId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return rackService.saveRack(rack,shelfId);
	}
}
