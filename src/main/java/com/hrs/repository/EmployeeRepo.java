package com.hrs.hrs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrs.hrs.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    boolean existsByEmailaddress(String emailaddress);

    Employee findByEmailaddress(String emailaddress);

    Employee findByEmailaddressAndPasswd(String emailaddress, String passwd);

    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.empname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.emailaddress) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.department) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.designation) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.contactno) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Employee> searchEmployee(@Param("keyword") String keyword);
}