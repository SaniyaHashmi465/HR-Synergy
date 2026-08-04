package com.hrs.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrs.hrs.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

}