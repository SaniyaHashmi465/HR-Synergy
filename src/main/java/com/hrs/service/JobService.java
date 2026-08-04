package com.hrs.hrs.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hrs.entity.Job;
import com.hrs.hrs.repository.JobRepo;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    public void saveJob(Job job) {
        job.setPosteddate(LocalDate.now());
        jobRepo.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    public void deleteJob(Integer jobid) {
        jobRepo.deleteById(jobid);
    }

    public Job getJobById(Integer jobid) {
        return jobRepo.findById(jobid).get();
    }

    public void updateJob(Job job) {
        jobRepo.save(job);
    }
    public long countJobs(){
        return jobRepo.count();
    }
}