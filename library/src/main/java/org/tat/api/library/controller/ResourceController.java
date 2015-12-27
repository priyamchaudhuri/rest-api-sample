package org.tat.api.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;
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
}
