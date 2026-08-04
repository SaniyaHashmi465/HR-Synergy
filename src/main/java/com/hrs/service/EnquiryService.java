package com.hrs.hrs.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hrs.entity.Enquiry;
import com.hrs.hrs.repository.EnquiryRepo;

@Service
public class EnquiryService {

    @Autowired
    private EnquiryRepo enquiryRepo;

    public void saveEnquiry(Enquiry enquiry) {
        enquiry.setEnquirydate(LocalDate.now());
        enquiry.setStatus("PENDING");
        enquiryRepo.save(enquiry);
    }

    public List<Enquiry> getAllEnquiries() {
        return enquiryRepo.findAll();
    }

    public void markResolved(Integer enquiryid) {
        Enquiry enquiry = enquiryRepo.findById(enquiryid).get();
        enquiry.setStatus("RESOLVED");
        enquiryRepo.save(enquiry);
    }

    public void deleteEnquiry(Integer enquiryid) {
        enquiryRepo.deleteById(enquiryid);
    }
    
    public long countEnquiries(){
        return enquiryRepo.count();
    }
}