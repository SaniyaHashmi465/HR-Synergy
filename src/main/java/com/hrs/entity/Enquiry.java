package com.hrs.hrs.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "enquiry")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enquiryid;

    private String name;
    private String emailaddress;
    private String contactno;
    private String subject;

    @Column(length = 1000)
    private String message;

    private LocalDate enquirydate;

    private String status; // PENDING / RESOLVED

    // Generate Getters and Setters
    
	public Integer getEnquiryid() {
		return enquiryid;
	}

	public void setEnquiryid(Integer enquiryid) {
		this.enquiryid = enquiryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getEnquirydate() {
		return enquirydate;
	}

	public void setEnquirydate(LocalDate enquirydate) {
		this.enquirydate = enquirydate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

   
    
    
    
}