package com.hrs.hrs.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hrs.entity.JobApplication;
import com.hrs.hrs.repository.JobApplicationRepo;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepo applicationRepo;

    public void applyJob(JobApplication application){

        application.setApplicationDate(
                LocalDate.now());

        application.setStatus("APPLIED");

        applicationRepo.save(application);
    }

    public List<JobApplication> getAllApplications(){
        return applicationRepo.findAll();
    }

    public List<JobApplication> getCandidateApplications(
            String email){

        return applicationRepo
                .findByCandidateEmail(email);
    }
    
    public JobApplication getApplicationById(Integer id){
        return applicationRepo.findById(id).get();
    }

    public void updateStatus(Integer id,String status){

        JobApplication app =
                applicationRepo.findById(id).get();

        app.setStatus(status);

        applicationRepo.save(app);
    }
    
    public long countApplications(){
        return applicationRepo.count();
    }
}