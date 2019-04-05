package com.example.one_to_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.one_to_one.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
