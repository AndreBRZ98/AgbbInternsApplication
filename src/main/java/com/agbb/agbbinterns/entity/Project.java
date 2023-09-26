package com.agbb.agbbinterns.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	private String projectName;
	private Integer ideaOwner;
	private LocalDate releaseDate;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mentor_id")
	private Mentor mentor;
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getIdeaOwner() {
		return ideaOwner;
	}
	public void setIdeaOwner(Integer ideaOwner) {
		this.ideaOwner = ideaOwner;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Mentor getMentor() {
		return mentor;
	}
	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
	
	

}
