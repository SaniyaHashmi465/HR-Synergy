package com.hrs.hrs.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="eventnotification")
public class EventNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventid;

    @Column(length = 200, nullable = false)
    private String eventtitle;

    @Column(length = 2000, nullable = false)
    private String eventdescription;

    private LocalDate eventdate;

    private LocalDate posteddate;

    // Getters and Setters
    
	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public String getEventtitle() {
		return eventtitle;
	}

	public void setEventtitle(String eventtitle) {
		this.eventtitle = eventtitle;
	}

	public String getEventdescription() {
		return eventdescription;
	}

	public void setEventdescription(String eventdescription) {
		this.eventdescription = eventdescription;
	}

	public LocalDate getEventdate() {
		return eventdate;
	}

	public void setEventdate(LocalDate eventdate) {
		this.eventdate = eventdate;
	}

	public LocalDate getPosteddate() {
		return posteddate;
	}

	public void setPosteddate(LocalDate posteddate) {
		this.posteddate = posteddate;
	}   
    
}