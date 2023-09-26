package com.agbb.agbbinterns.service;

import java.util.List;

import com.agbb.agbbinterns.dto.MentorDTO;
import com.agbb.agbbinterns.dto.ProjectDTO;
import com.agbb.agbbinterns.exception.AgbbInternsException;

public interface ProjectAllocationService {

	public Integer allocateProject(ProjectDTO project) throws AgbbInternsException;
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws AgbbInternsException;
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws AgbbInternsException;
	public void deleteProject(Integer projectId) throws AgbbInternsException;
	
}
