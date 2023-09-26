package com.agbb.agbbinterns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.agbb.agbbinterns.entity.Mentor;

public interface MentorRepository extends CrudRepository<Mentor, Integer>{
	public List<Mentor> findByNumberOfProjectsMentored(@Param("projects_mentored") Integer numberOfProjectsMentored);
}
