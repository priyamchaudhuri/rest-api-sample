package org.tat.api.library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;
import org.tat.api.library.model.User;
import org.tat.api.library.service.ResourceService;
import org.tat.api.library.service.StudentService;

@RestController
@RequestMapping("/resources")
public class ResourceController {
	@Autowired
	ResourceService service;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public List<Resource> getResource() {

		List<Resource> resources = service.getResources();
		System.out.println(resources);
		return resources;
	}
	
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public Resource getResource(@PathVariable int resourceId,
			HttpServletRequest request, HttpServletResponse response) {

		Resource resource = service.getResource(resourceId);
		return resource;
	}
	
	@RequestMapping(value = "/{resourceId}/user", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public User getResourceUser(@PathVariable int resourceId,
			HttpServletRequest request, HttpServletResponse response) {

		User user = service.getResourceUser(resourceId);
		return user;
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public Resource createStudent(@RequestBody Resource resource,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return service.saveResource(resource);
	}
}

