package com.hrs.hrs.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidateid;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String gender;

    @Column(length = 100, nullable = false, unique = true)
    private String emailaddress;

    @Column(length = 20, nullable = false)
    private String passwd;

    @Column(length = 13, nullable = false)
    private String contactno;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String qualification;

    @Column(length = 50)
    private String experience;

    @Column(length = 1000)
    private String keyskill;

    @Column(length = 1000)
    private String address;
    
    @Column(length = 255)
    private String resume;
    
    // Generate Getters and Setters

	public Integer getCandidateid() {
		return candidateid;
	}

	public void setCandidateid(Integer candidateid) {
		this.candidateid = candidateid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getKeyskill() {
		return keyskill;
	}

	public void setKeyskill(String keyskill) {
		this.keyskill = keyskill;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResume() {
	    return resume;
	}

	public void setResume(String resume) {
	    this.resume = resume;
	}
    
    
}