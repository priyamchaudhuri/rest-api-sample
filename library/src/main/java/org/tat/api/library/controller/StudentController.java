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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tat.api.core.crypto.MD5Util;
import org.tat.api.core.filter.Embed;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;
import org.tat.api.library.service.ResourceService;
import org.tat.api.library.service.StudentService;

@RestController
@Api(value = "Students API")
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService service;

	@Autowired
	ResourceService resourceService;

	@ApiOperation(httpMethod = "GET", value = "Returns list of Students", responseContainer = "List", notes = "Returns list of students")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of students list", response = Student.class),
			@ApiResponse(code = 400, message = "Bad Request") })
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@Embed
	public List<Student> getStudents(
			@RequestParam(value = "offset", defaultValue = "0") @ApiParam(required = false, value = "Start index for page", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam(required = false, value = "No.of items", defaultValue = "20") Integer limit,
			@RequestParam(value = "sorts", required = false) @ApiParam(required = false, value = "List of sort fields") String sorts,
			@RequestParam(value = "fields", required = false) @ApiParam(required = false, value = "List of fields to be fetched") String fields,
			@RequestParam(value = "all", required = false, defaultValue = "false") @ApiParam(required = false, value = "Flag to fetch all records", defaultValue = "false") boolean all,
			@RequestParam(value = "id", required = false) @ApiParam(required = false, value = "Id of the student") String id,
			@RequestParam(value = "fname", required = false) @ApiParam(required = false, value = "First Name of the student") String fname,
			@RequestParam(value = "lname", required = false) @ApiParam(required = false, value = "Last Name of the student") String lname,
			@RequestParam(value = "joiningDate", required = false) @ApiParam(required = false, value = "Joining Date of the student") String joiningDate,
			@RequestParam(value = "city", required = false) @ApiParam(required = false, value = "City of the student") String city,
			@RequestParam(value = "state", required = false) @ApiParam(required = false, value = "State of the student") String state,
			@RequestParam(value = "country", required = false) @ApiParam(required = false, value = "Country of the student") String country,
			@RequestParam(value = "rollNo", required = false) @ApiParam(required = false, value = "Roll No. of the student") String rollNo,
			@RequestParam(value = "addressLine1", required = false) @ApiParam(required = false, value = "Address Line 1 of the student") String addressLine1,
			@RequestParam(value = "addressLine2", required = false) @ApiParam(required = false, value = "Address Line 2 of the student") String addressLine2,
			@RequestParam(value = "addressLine3", required = false) @ApiParam(required = false, value = "Address Line 3 of the student") String addressLine3)
			throws Exception {

		// Form the Search request map

		Map<String, String> searchRequest = new HashMap<String, String>();

		if (id != null)
			searchRequest.put("id", id);
		if (fname != null)
			searchRequest.put("fname", fname);
		if (lname != null)
			searchRequest.put("lname", lname);

		if (joiningDate != null)
			searchRequest.put("joiningDate", joiningDate);
		if (city != null)
			searchRequest.put("city", city);
		if (state != null)
			searchRequest.put("state", state);
		if (country != null)
			searchRequest.put("country", country);
		if (rollNo != null)
			searchRequest.put("rollNo", rollNo);
		if (addressLine1 != null)
			searchRequest.put("addressLine1", addressLine1);
		if (addressLine2 != null)
			searchRequest.put("addressLine2", addressLine2);
		if (addressLine3 != null)
			searchRequest.put("addressLine3", addressLine3);
		List<Student> students = service.getStudents(offset, limit, sorts,all, searchRequest);
		return students;
	}

	@ApiOperation(httpMethod = "GET", value = "Returns student with the specified Id", notes = "Returns student detail", response = Student.class)
	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of student details", response = Student.class),
			@ApiResponse(code = 404, message = "Student with given id does not exist"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@Embed
	public ResponseEntity<Student> getStudent(
			@PathVariable("studentId") long studentId,
			@RequestHeader(value = "If-None-Match", required = false) String requestEtag,
			@ApiParam(required = false, value = "List of fields to be fetched") @RequestParam(value = "fields", required = false) String fields,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Student student = service.getStudent(studentId);
		String etag = MD5Util.hash(student);
		response.setHeader("ETag", etag);
		// etag = "121";
		if (etag.equals(requestEtag)) {
			return new ResponseEntity<Student>(HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
	}

	@ApiOperation(httpMethod = "GET", value = "Returns list of resources", notes = "Returns resources assigned to the student with the specified Id", responseContainer = "List")
	@RequestMapping(value = "/{studentId}/resources", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@Embed
	public List<Resource> getStudentResources(
			@RequestParam(value = "offset", defaultValue = "0") @ApiParam(required = false, value = "Start index for page", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") @ApiParam(required = false, value = "No.of items", defaultValue = "20") Integer limit,
			@RequestParam(value = "sorts", required = false) @ApiParam(required = false, value = "List of sort fields") String sorts,
			@RequestParam(value = "fields", required = false) @ApiParam(required = false, value = "List of fields to be fetched") String fields,
			@RequestParam(value = "all", required = false, defaultValue = "false") @ApiParam(required = false, value = "Flag to fetch all records", defaultValue = "false") boolean all,
			@RequestParam(value = "id", required = false) @ApiParam(required = false, value = "Id of the resource") String id,
			@RequestParam(value = "name", required = false) @ApiParam(required = false, value = "Name of the resource") String name,
			@RequestParam(value = "author", required = false) @ApiParam(required = false, value = "Author of the resource") String author,
			@RequestParam(value = "publication", required = false) @ApiParam(required = false, value = "Publication of the resource") String publication,
			@RequestParam(value = "year", required = false) @ApiParam(required = false, value = "Year of resource publication") String year,
			@RequestParam(value = "type", required = false) @ApiParam(required = false, value = "Type of resource") String type,
			@PathVariable long studentId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, String> searchRequest = new HashMap<String, String>();

		if (id != null)
			searchRequest.put("id", id);
		if (name != null)
			searchRequest.put("fname", name);
		if (author != null)
			searchRequest.put("lname", author);
		if (publication != null)
			searchRequest.put("publication", publication);
		if (year != null)
			searchRequest.put("year", year);
		if (type != null)
			searchRequest.put("type", type);
		List<Resource> resources = resourceService.getStudentResources(
				studentId, offset, limit, sorts, all, searchRequest);
		return resources;
	}

	@ApiOperation(httpMethod = "GET", value = "Returns details of resource", notes = "Returns details of resource with resource id assigned to the student with the student Id", response = Resource.class)
	@RequestMapping(value = "/{studentId}/resources/{resourceId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	public Resource getStudentResource(@PathVariable long studentId,
			@PathVariable long resourceId, HttpServletRequest request,
			HttpServletResponse response) {

		Resource resource = resourceService.getStudentResource(studentId,
				resourceId);
		return resource;
	}

	@ApiOperation(httpMethod = "POST", value = "Create New Student", response = Student.class)
	@Transactional
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public Student createStudent(@Valid @RequestBody Student student,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return service.saveStudent(student);
	}
}
