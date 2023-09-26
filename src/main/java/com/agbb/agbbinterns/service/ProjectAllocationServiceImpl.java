package com.agbb.agbbinterns.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agbb.agbbinterns.dto.MentorDTO;
import com.agbb.agbbinterns.dto.ProjectDTO;
import com.agbb.agbbinterns.entity.Mentor;
import com.agbb.agbbinterns.entity.Project;
import com.agbb.agbbinterns.exception.AgbbInternsException;
import com.agbb.agbbinterns.repository.MentorRepository;
import com.agbb.agbbinterns.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service(value = "projectAllocationService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {

	@Autowired
	private MentorRepository mentorRepository;
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Integer allocateProject(ProjectDTO project) throws AgbbInternsException {

		Optional<Mentor> mentorOptional = mentorRepository.findById(project.getMentorDTO().getMentorId());
		Mentor mentorEntity = mentorOptional.orElseThrow(() -> new AgbbInternsException("Service.MENTOR_NOT_FOUND"));
		if (mentorEntity!=null && mentorEntity.getNumberOfProjectsMentored() >= 3) {
			throw new AgbbInternsException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Project projectEntity = new Project();
		projectEntity.setIdeaOwner(project.getIdeaOwner());
		projectEntity.setMentor(mentorEntity);
		projectEntity.setProjectName(project.getProjectName());
		projectEntity.setReleaseDate(project.getReleaseDate());
		mentorEntity.setNumberOfProjectsMentored(mentorEntity.getNumberOfProjectsMentored() + 1);
		mentorRepository.save(mentorEntity);
		projectRepository.save(projectEntity);
		Integer generatedProjectId = projectEntity.getProjectId();
		mentorRepository.save(mentorEntity);

		return generatedProjectId;
	}

	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws AgbbInternsException {

		Iterable<Mentor> mentorIterable = mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);
		List<MentorDTO> filteredMentorsList = new ArrayList<>();
		mentorIterable.forEach(mentor -> {
			MentorDTO filteredMentorDTO = new MentorDTO();
			filteredMentorDTO.setMentorId(mentor.getMentorId());
			filteredMentorDTO.setMentorName(mentor.getMentorName());
			filteredMentorDTO.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
			filteredMentorsList.add(filteredMentorDTO);
		});
		if (filteredMentorsList.isEmpty()) {
			throw new AgbbInternsException("Service.MENTOR_NOT_FOUND");
		}
		return filteredMentorsList;
	}

	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws AgbbInternsException {

		Optional<Mentor> optionalMentor = mentorRepository.findById(mentorId);
		Mentor mentor = optionalMentor.orElseThrow(() -> new AgbbInternsException("Service.MENTOR_NOT_FOUND"));
		if (mentor.getNumberOfProjectsMentored() >= 3) {
			throw new AgbbInternsException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		Project project = optionalProject.orElseThrow(() -> new AgbbInternsException("Service.PROJECT_NOT_FOUND"));
		project.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);
	}

	@Override
	public void deleteProject(Integer projectId) throws AgbbInternsException {
		
		Optional<Project> optionalProject = projectRepository.findById(projectId);
		Project project = optionalProject.orElseThrow(() -> new AgbbInternsException("Service.PROJECT_NOT_FOUND"));
		if (project.getMentor() == null) {
			projectRepository.delete(project);
		} else if (project.getMentor() != null) {
			project.getMentor().setNumberOfProjectsMentored(project.getMentor().getNumberOfProjectsMentored() - 1);
			Mentor mentor = new Mentor();
			project.setMentor(mentor);
			projectRepository.delete(project);

		}

	}

}
