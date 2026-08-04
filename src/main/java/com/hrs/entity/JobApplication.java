package com.hrs.hrs.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="jobapplication")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationid;

    private Integer jobid;
    
    private String jobprofile;

    private String candidateName;

    private String candidateEmail;

    private String qualification;
    
    @Column(length = 255)
    private String resume;

    private String status;

    private LocalDate applicationDate;
    
    // Generate Getters and Setters

	public Integer getApplicationid() {
		return applicationid;
	}

	public void setApplicationid(Integer applicationid) {
		this.applicationid = applicationid;
	}

	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}

	public String getJobprofile() {
	    return jobprofile;
	}

	public void setJobprofile(String jobprofile) {
	    this.jobprofile = jobprofile;
	}	
	
	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getResume() {
	    return resume;
	}

	public void setResume(String resume) {
	    this.resume = resume;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}  
    
}