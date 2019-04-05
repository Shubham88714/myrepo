package com.example.one_to_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.one_to_one.domain.Instructor;

@Repository
public interface IInstructorRepository extends JpaRepository<Instructor, Long> {

}
