package com.example.one_to_one.resource;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.one_to_one.domain.Course;
import com.example.one_to_one.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseResource {

	@Autowired
	CourseService courseService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveCourse(@RequestBody Course course) throws URISyntaxException
	{
		return ResponseEntity.created(new URI("/save/"+courseService.save(course).getId())).build();
	}
}
