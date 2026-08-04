package com.hrs.hrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hrs.entity.JobApplication;

public interface JobApplicationRepo
        extends JpaRepository<JobApplication,Integer>{

    List<JobApplication> findByCandidateEmail(
            String candidateEmail);
}