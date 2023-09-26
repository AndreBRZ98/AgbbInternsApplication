package com.agbb.agbbinterns.dto;



import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;





public class MentorDTO {
	
	@NotNull(message = "{mentor.mentorid.absent}")
	@Max(value = 9999, message = "{mentor.mentorid.invalid}") 
	@Min(value = 1000, message = "{mentor.mentorid.invalid}")
	private Integer mentorId;
	private String mentorName;
	private Integer numberOfProjectsMentored;
	
	public MentorDTO() {
	}
	
	public MentorDTO(Integer mentorId, String mentorName, Integer numberOfProjectsMentored) {
		this.mentorId = mentorId;
		this.mentorName = mentorName;
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

	public Integer getMentorId() {
		return mentorId;
	}

	public void setMentorId(Integer mentorId) {
		this.mentorId = mentorId;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public Integer getNumberOfProjectsMentored() {
		return numberOfProjectsMentored;
	}

	public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}
	
}
