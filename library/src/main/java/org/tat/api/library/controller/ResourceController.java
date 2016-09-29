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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tat.api.library.model.Owner;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.User;
import org.tat.api.library.service.ResourceService;

@RestController
@RequestMapping("/resources")
public class ResourceController {
	@Autowired
	ResourceService service;

	@ApiOperation(httpMethod="GET",value="Returns list of Resources",responseContainer="List",notes="Returns list of resources")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of resource list"),
			@ApiResponse(code = 400, message = "Bad Request")
	})
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public List<Resource> getResource(
			@RequestParam(value = "offset", defaultValue = "0") @ApiParam(value="Start index for page",defaultValue="0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam(value="No.of items",defaultValue="20") Integer limit,
			@RequestParam(value = "sorts", required = false) @ApiParam(value="List of sort fields") String sorts,
			@RequestParam(value = "fields", required = false) @ApiParam(value="List of fields to be fetched") String fields,
			@RequestParam(value = "all", required = false, defaultValue = "false") @ApiParam(value="Flag to fetch all records",defaultValue="false") boolean all,
			@RequestParam(value = "id", required = false) @ApiParam(value="Id of the resource") String id,
			@RequestParam(value="name",required=false) @ApiParam(value="Name of the resource") String name,
			@RequestParam(value="author", required=false) @ApiParam(value="Author of the resource") String author,
			@RequestParam(value="publication",required=false) @ApiParam(value="Publication") String publication,
			@RequestParam(value="year",required=false) @ApiParam(value="Year of Publication") String year,
			@RequestParam(value="type",required=false) @ApiParam(value="Type of Resource") String type
			) throws Exception {
		//Form the Search request map

		Map<String, String> searchRequest = new HashMap<String, String>();
		if(id != null)
			searchRequest.put("id", id);
		if(name != null)
			searchRequest.put("name", name);
		if(author != null)
			searchRequest.put("author", author);
		if(publication != null)
			searchRequest.put("publication", publication);
		if(year != null)
			searchRequest.put("year", year);
		if(type != null)
			searchRequest.put("type", type);
		List<Resource> resources = service.getResources(offset, limit, sorts, all, searchRequest);
		return resources;
	}

	@ApiOperation(httpMethod="GET",value="Returns resource with the specified Id",notes="Returns resource detail",response=Resource.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of resource details", response = Resource.class),
			@ApiResponse(code = 404, message = "Resource with given id does not exist"),
			@ApiResponse(code = 500, message = "Internal server error")}
			)
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public Resource getResource(@PathVariable long resourceId,
			HttpServletRequest request, HttpServletResponse response) {

		Resource resource = service.getResource(resourceId);
		return resource;
	}

	@ApiOperation(httpMethod="GET",value="Returns user assigned to the resource Id",notes="Returns user detail",response=User.class)
	@RequestMapping(value = "/{resourceId}/owner", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public Owner getResourceOwner(@PathVariable int resourceId,
			HttpServletRequest request, HttpServletResponse response) {

		Owner user = service.getResourceOwner(resourceId);
		return user;
	}

	@ApiOperation(httpMethod="POST",value="Create New Resource",response=Resource.class)
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	public Resource createResource(@RequestBody Resource resource,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		return service.saveResource(resource);
	}

}

