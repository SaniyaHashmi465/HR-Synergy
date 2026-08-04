package com.hrs.hrs.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hrs.entity.Feedback;
import com.hrs.hrs.repository.FeedbackRepo;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepo frepo;
	
	public void saveFeedback(Feedback feedback){
		feedback.setFeedbackdate(LocalDate.now());
		   frepo.save(feedback);
	}
	
	public List<Feedback> getAllFeedback(){
		 return frepo.findAll();
	}
	
	public long countFeedback(){
	    return frepo.count();
	}

}
