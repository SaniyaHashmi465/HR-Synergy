package com.hrs.hrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hrs.entity.LeaveRequest;

public interface LeaveRepo extends JpaRepository<LeaveRequest, Integer>{

	List<LeaveRequest> findByEmployeeEmail(String employeeemail);
	
	long countByStatus(String status);
}
