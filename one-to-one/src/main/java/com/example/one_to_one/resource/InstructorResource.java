package com.example.one_to_one.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.one_to_one.domain.Instructor;
import com.example.one_to_one.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorResource {

	@Autowired
	InstructorService instructorService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") Long id)
	{
		Instructor instructor = instructorService.getInstructorById(id);
		if(instructor!=null)
			return ResponseEntity.ok().body(instructor);
		else
			return ResponseEntity.badRequest().body(null);
	}
	
	
	
	//save ---done
	@PostMapping("/save")
	public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor)throws Throwable
	{
		return ResponseEntity.created(new URI("/save/"+instructorService.save(instructor).getId())).build();
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Instructor> updateInstructor(@RequestBody Instructor instructor)
	{
		
		Instructor tempInstructor = instructorService.update(instructor);
		if(tempInstructor!=null)
		{
			return ResponseEntity.ok().body(instructor);
		}
		return ResponseEntity.badRequest().body(instructor);
	}
	
	//delete---done
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteInstructor(@PathVariable("id") Long id)
	{
		if(instructorService.delete(id))
			return ResponseEntity.ok().body("Done.... ");
		else 
			return ResponseEntity.badRequest().body("Not Done.... ");
	}
	
	
	
	
	
	
	
	
}
