package org.tat.api.library.controller;

import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RestController;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Teacher;
import org.tat.api.library.service.ResourceService;
import org.tat.api.library.service.TeacherService;


@RestController
@RequestMapping("/teachers")
@Api(value="Teachers API")
public class TeacherController {

	@Autowired
	TeacherService service;
	
	@Autowired 
	ResourceService resourceService;
	
	@ApiOperation(httpMethod="GET",value="Returns list of Teachers",responseContainer="List",notes="Returns list of teachers")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful retrieval of teachers list", response = Teacher.class),
		    @ApiResponse(code = 400, message = "Bad Request")
	})
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public List<Teacher> getTeachers(
			@RequestParam(value = "offset", defaultValue = "0") @ApiParam(required=false,value="Start index for page",defaultValue="0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam(required=false,value="No.of items",defaultValue="20") Integer limit,
			@RequestParam(value = "sorts", required = false) @ApiParam(required=false,value="List of sort fields") String sorts,
			@RequestParam(value = "fields", required = false) @ApiParam(required=false,value="List of fields to be fetched") String fields,
			@RequestParam(value = "all", required = false, defaultValue = "false") @ApiParam(required=false,value="Flag to fetch all records",defaultValue="false") boolean all,
			@RequestParam(value = "id", required = false) @ApiParam(required=false,value="Id of the teacher") String id,
			@RequestParam(value = "fname", required = false) @ApiParam(required=false,value="First Name of the teacher") String fname,
			@RequestParam(value = "lname", required = false) @ApiParam(required=false,value="Last Name of the teacher") String lname,
			@RequestParam(value = "joiningDate", required = false)@ApiParam(required=false,value="Joining Date of the teacher")  String joiningDate,
			@RequestParam(value = "addressLine1", required = false)@ApiParam(required=false,value="Address Line 1 of the teacher")  String addressLine1,
			@RequestParam(value = "addressLine2", required = false)@ApiParam(required=false,value="Address Line 2 of the teacher")  String addressLine2,
			@RequestParam(value = "addressLine3", required = false)@ApiParam(required=false,value="Address Line 3 of the teacher")  String addressLine3,
			@RequestParam(value = "city", required = false)@ApiParam(required=false,value="City of the teacher")  String city,
			@RequestParam(value = "state", required = false)@ApiParam(required=false,value="State of the teacher")  String state,
			@RequestParam(value = "country", required = false)@ApiParam(required=false,value="Country of the teacher")  String country,
			@RequestParam(value = "empCode", required = false)@ApiParam(required=false,value="Country of the teacher")  String empCode,
			@RequestParam(value = "department", required = false)@ApiParam(required=false,value="Department of the teacher")  String department
			) throws Exception{

		//Form the Search request map
		
		Map<String, String> searchRequest = new HashMap<String, String>();
		
		if(id != null)
			searchRequest.put("id", id);
		if(fname != null)
			searchRequest.put("fname", fname);
		if(lname != null)
			searchRequest.put("lname", lname);
		if(empCode != null)
			searchRequest.put("empCode", empCode);
		if(joiningDate != null)
			searchRequest.put("joiningDate", joiningDate);
		if(city != null)
			searchRequest.put("city", city);
		if(state != null)
			searchRequest.put("state", state);
		if(country != null)
			searchRequest.put("country", country);
		if(department != null)
			searchRequest.put("department", department);
		if(addressLine1 != null)
			searchRequest.put("addressLine1", addressLine1);
		if(addressLine2 != null)
			searchRequest.put("addressLine2", addressLine2);
		if(addressLine3 != null)
			searchRequest.put("addressLine3", addressLine3);
		List<Teacher> teachers = service
				.getTeachers(offset, limit, sorts, fields, all, searchRequest);
		return teachers;
	}

	@ApiOperation(httpMethod="GET",value="Returns teacher with the specified Id",notes="Returns teacher detail",response=Teacher.class)
	@RequestMapping(value = "/{teacherId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successful retrieval of teacher details", response = Teacher.class),
		    @ApiResponse(code = 404, message = "Teacher with given id does not exist"),
		    @ApiResponse(code = 400, message = "Bad Request"),
		    @ApiResponse(code = 500, message = "Internal server error")}
		)
	public Teacher getTeacher(@PathVariable("teacherId") long teacherId,
			@ApiParam(required=false,value="List of fields to be fetched") @RequestParam(value = "fields", required = false) String fields,HttpServletRequest request, HttpServletResponse response) throws Exception {

		Teacher teacher = service.getTeacher(teacherId,fields);
		return teacher;
	}

	@ApiOperation(httpMethod="GET",value="Returns list of resources",notes="Returns resources assigned to the teacher with the specified Id",responseContainer="List")
	@RequestMapping(value = "/{teacherId}/resources", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public List<Resource> getTeacherResources(
			@RequestParam(value = "offset", defaultValue = "0") @ApiParam(required=false,value="Start index for page",defaultValue="0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam(required=false,value="No.of items",defaultValue="20") Integer limit,
			@RequestParam(value = "sorts", required = false) @ApiParam(required=false,value="List of sort fields") String sorts,
			@RequestParam(value = "fields", required = false) @ApiParam(required=false,value="List of fields to be fetched") String fields,
			@RequestParam(value = "all", required = false, defaultValue = "false") @ApiParam(required=false,value="Flag to fetch all records",defaultValue="false") boolean all,
			@RequestParam(value = "id", required = false) @ApiParam(required=false,value="Id of the resource") String id,
			@RequestParam(value = "name", required = false) @ApiParam(required=false,value="Name of the resource") String name,
			@RequestParam(value = "author", required = false) @ApiParam(required=false,value="Author of the resource") String author,
			@RequestParam(value = "publication", required = false) @ApiParam(required=false,value="Publication of the resource") String publication,
			@RequestParam(value = "year", required = false) @ApiParam(required=false,value="Year of resource publication") String year,
			@RequestParam(value = "type", required = false)  @ApiParam(required=false,value="Type of resource")String type,
			@PathVariable long teacherId,
			HttpServletRequest request, HttpServletResponse response) {

	Map<String, String> searchRequest = new HashMap<String, String>();
		
		if(id != null)
			searchRequest.put("id", id);
		if(name != null)
			searchRequest.put("fname", name);
		if(author != null)
			searchRequest.put("lname", author);
		if(publication != null)
			searchRequest.put("publication", publication);
		if(year != null)
			searchRequest.put("year", year);
		if(type != null)
			searchRequest.put("type", type);
		List<Resource> resources = resourceService.getTeacherResources(teacherId,offset, limit, sorts, all, searchRequest);
		return resources;
	}

	@ApiOperation(httpMethod="GET",value="Returns details of resource",notes="Returns details of resource with resource id assigned to the teacher with the teacher Id",response=Resource.class)
	@RequestMapping(value = "/{teacherId}/resources/{resourceId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public Resource getTeacherResource(@PathVariable long teacherId,
			@PathVariable long resourceId, HttpServletRequest request,
			HttpServletResponse response) {

		Resource resource = resourceService.getTeacherResource(teacherId, resourceId);
		return resource;
	}

	@ApiOperation(httpMethod="POST",value="Create New Teacher",response=Teacher.class)
	@Transactional
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	public Teacher createTeacher(@Valid @RequestBody Teacher teacher,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return service.saveTeacher(teacher);
	}
}
