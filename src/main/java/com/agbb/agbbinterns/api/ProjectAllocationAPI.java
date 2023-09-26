package com.agbb.agbbinterns.api;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agbb.agbbinterns.dto.MentorDTO;
import com.agbb.agbbinterns.dto.ProjectDTO;
import com.agbb.agbbinterns.exception.AgbbInternsException;
import com.agbb.agbbinterns.service.ProjectAllocationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/agbbinterns")
@Validated
public class ProjectAllocationAPI {
	
	@Autowired
	private ProjectAllocationService projectService;
	@Autowired
	private Environment environment;
	
	@PostMapping( value = "/project")
	public ResponseEntity<String> allocateProject(@RequestBody @Valid ProjectDTO project) throws AgbbInternsException{
		
		Integer projectId = projectService.allocateProject(project);
		String successMessage = environment.getProperty("API.ALLOCATION_SUCCESS")+projectId;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@GetMapping(value="mentor/{numberOfProjectsMentored}")
	public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws AgbbInternsException{
		List<MentorDTO> mentorDTOs = projectService.getMentors(numberOfProjectsMentored);
		return new ResponseEntity<>(mentorDTOs, HttpStatus.OK);
	}
	
	@PutMapping(value="project/{projectId}/{mentorId}")
	public ResponseEntity<String> updateProjectMentor(@PathVariable Integer projectId, 
			@PathVariable
			@Min(value = 1000, message ="{mentor.mentorid.invalid}")
			@Max(value = 9999, message ="{mentor.mentorid.invalid}")
			 Integer mentorId) throws AgbbInternsException{
		projectService.updateProjectMentor(projectId, mentorId);
		String successMessage=environment.getProperty("API.PROJECT_UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "project/{projectId}")
	public ResponseEntity<String> deleteProject (@PathVariable Integer projectId) throws AgbbInternsException{
		projectService.deleteProject(projectId);
		String successMessage = environment.getProperty("API.PROJECT_DELETE _SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

} 
