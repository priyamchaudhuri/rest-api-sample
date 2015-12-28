package org.tat.api.library.controller;

import java.util.List;

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
import org.tat.api.library.model.Student;
import org.tat.api.library.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService service;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public List<Student> getStudents() {

		List<Student> students = service.getStudents();
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
}
