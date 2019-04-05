package com.example.one_to_one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.one_to_one.domain.Course;
import com.example.one_to_one.domain.Instructor;
import com.example.one_to_one.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	InstructorService instructorService;
	
	public Course save(Course theCourse)
	{
		Long id = theCourse.getInstructor().getId();
		
		Instructor theInstructor = instructorService.getInstructorById(id);
		
		theInstructor.add(theCourse);
		
		return courseRepo.save(theCourse);
		
	}
}

