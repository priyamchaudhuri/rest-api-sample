package org.tat.api.library.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.QueryHint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tat.api.library.model.Address;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;
import org.tat.api.library.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService service;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public List<Student> getStudents(
			@RequestParam(value = "offset", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "20") Integer limit,
			@RequestParam(value = "sort", required = false) String sort) {
		List<Sort> sortConfigs = null;

		if (sort != null && !sort.isEmpty()) {
			sortConfigs = new ArrayList<Sort>();
			List<String> sorts = Arrays.asList(sort.split("\\s*,\\s*"));
			Sort sortConfig;
			for (String s : sorts) {
				sortConfig = new Sort();
				if (s.indexOf("-", 0) >= 0) {
					sortConfig.setField(s.substring(1));
					sortConfig.setSortType(SortType.DESC);
				} else {
					sortConfig.setField(s);
					sortConfig.setSortType(SortType.ASC);
				}
				sortConfigs.add(sortConfig);
			}
		}

		if (sortConfigs != null && sortConfigs.size() > 0)
			for (Sort sort2 : sortConfigs) {
				System.out.println("Sort Field = " + sort2.getField());
				System.out.println("Sort Type = " + sort2.getSortType());
		}

		List<Student> students = service.getStudents(offset, limit, sortConfigs);
		return students;
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public Student getStudent(@PathVariable int studentId,
			HttpServletRequest request, HttpServletResponse response) {

		Student student = service.getStudent(studentId);
		return student;
	}

	@RequestMapping(value = "/{studentId}/address", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public Address getStudentAddress(@PathVariable int studentId,
			HttpServletRequest request, HttpServletResponse response) {

		Address address = service.getStudentAddress(studentId);
		return address;
	}

	@RequestMapping(value = "/{studentId}/resources", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public Set<Resource> getStudentResources(@PathVariable int studentId,
			HttpServletRequest request, HttpServletResponse response) {

		Set<Resource> resources = service.getStudentResources(studentId);
		return resources;
	}
	
	@RequestMapping(value = "/{studentId}/resources/{resourceId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public Resource getStudentResource(@PathVariable int studentId,@PathVariable int resourceId,
			HttpServletRequest request, HttpServletResponse response) {

		Resource resource = service.getStudentResource(studentId,resourceId);
		return resource;
	}	
}
