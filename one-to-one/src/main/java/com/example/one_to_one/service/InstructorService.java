package com.example.one_to_one.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.one_to_one.domain.Instructor;
import com.example.one_to_one.domain.InstructorDetail;
import com.example.one_to_one.repository.IInstructorRepository;

@Service
public class InstructorService {

	@Autowired
	IInstructorRepository instructorRepo;
	
	//get by id
	@Transactional
	public Instructor getInstructorById(Long id)
	{
		Instructor tempInstructor= null;
		Optional<Instructor> optInstructor = instructorRepo.findById(id);
		if(optInstructor.isPresent())
			return optInstructor.get();		
		
		return tempInstructor;
	}
	
	
	//save
	@Transactional
	public Instructor save(Instructor instructor)
	{
		return instructorRepo.save(instructor);
	}
	
	//update
	@Transactional
	public Instructor update(Instructor instructor)
	{
		Instructor theInstructor = null;
		Optional<Instructor> optInstructor=instructorRepo.findById(instructor.getId());
		
		if(optInstructor.isPresent())
		{
			theInstructor = optInstructor.get();
			theInstructor.setName(instructor.getName());
			theInstructor.setContact(instructor.getContact());
			theInstructor.setEmail(instructor.getEmail());
			
			InstructorDetail thePersonal = theInstructor.getInstructorDetail();
				
			thePersonal.setChannel(instructor.getInstructorDetail().getChannel());
			thePersonal.setHobbies(instructor.getInstructorDetail().getHobbies());
		
			/*theInstructor.setPersonalDetail(thePersonal);*/

			System.out.println(theInstructor);
			
			return instructorRepo.save(theInstructor);
		}
		
		return theInstructor;
	}


	@Transactional
	public boolean delete(Long id)
	{
		boolean flag = false;
		Instructor theInstructor = null;
		Optional<Instructor> optInstructor=instructorRepo.findById(id);
		
		if(optInstructor!=null)
		{
			theInstructor =  optInstructor.get();
			instructorRepo.delete(theInstructor);
			flag = true;
		}
		return flag;
	}
}
