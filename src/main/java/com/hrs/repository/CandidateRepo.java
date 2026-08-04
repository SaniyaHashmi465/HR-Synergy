package com.hrs.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrs.hrs.entity.Candidate;

public interface CandidateRepo extends JpaRepository<Candidate, Integer> {

    boolean existsByEmailaddress(String emailaddress);

    Candidate findByEmailaddressAndPasswd(String emailaddress, String passwd);

    Candidate findByEmailaddress(String emailaddress);
}