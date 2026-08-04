package com.hrs.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hrs.entity.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

}
