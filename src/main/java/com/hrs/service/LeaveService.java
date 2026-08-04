package com.hrs.hrs.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hrs.entity.LeaveRequest;
import com.hrs.hrs.repository.LeaveRepo;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepo leaveRepo;

    public void applyLeave(LeaveRequest leaveRequest) {
        leaveRequest.setStatus("PENDING");
        leaveRequest.setAppliedDate(LocalDate.now());
        leaveRepo.save(leaveRequest);
    }

    public List<LeaveRequest> getAllLeaves() {
        return leaveRepo.findAll();
    }

    public List<LeaveRequest> getEmployeeLeaves(String email) {
        return leaveRepo.findByEmployeeEmail(email);
    }

    public void approveLeave(Integer id) {
        LeaveRequest leave = leaveRepo.findById(id).get();
        leave.setStatus("APPROVED");
        leaveRepo.save(leave);
    }

    public void rejectLeave(Integer id) {
        LeaveRequest leave = leaveRepo.findById(id).get();
        leave.setStatus("REJECTED");
        leaveRepo.save(leave);
    }
    
    public long countAllLeaves() {
        return leaveRepo.count();
    }

    public long countByStatus(String status) {
        return leaveRepo.countByStatus(status);
    }
    
    public long countLeaves(){
        return leaveRepo.count();
    }
}