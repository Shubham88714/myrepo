package com.example.one_to_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.one_to_one.domain.InstructorDetail;

@Repository
public interface IPersonalDetailsRepository extends JpaRepository<InstructorDetail, Long>{

}
