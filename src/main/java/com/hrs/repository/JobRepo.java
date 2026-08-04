package com.hrs.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrs.hrs.entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {

}