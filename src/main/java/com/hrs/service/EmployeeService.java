package com.hrs.hrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hrs.dto.EmployeeDto;
import com.hrs.hrs.entity.Employee;
import com.hrs.hrs.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo emprepo;

	public void saveEmployee(EmployeeDto empdto) throws Exception{
		
		String email=empdto.getEmailaddress().trim();
		if(emprepo.existsByEmailaddress(email)){
			throw new Exception("Email already exists.");
		}
		 
		Employee emp=new Employee();
		
		emp.setPasswd(empdto.getPasswd());
		emp.setEmpname(empdto.getEmpname());
		emp.setGender(empdto.getGender());
		emp.setFname(empdto.getFname());
		emp.setAddress1(empdto.getAddress1());
		emp.setAddress2(empdto.getAddress2());
		emp.setContactno(empdto.getContactno());
		emp.setEmailaddress(email);
		emp.setDepartment(empdto.getDepartment());
		emp.setDesignation(empdto.getDesignation());
		emp.setSalary(empdto.getSalary());
		
		emprepo.save(emp);
			
	}
	
	public List<Employee> getAllEmployee(){
		return emprepo.findAll();
	}
	
	public void deleteEmployee(Integer empid){
		emprepo.deleteById(empid);
		
	}
	
	public Employee getEmployeeById(Integer empid){
		return emprepo.findById(empid).get();
	}
	
	public void updateEmployee(Employee emp){
		emprepo.save(emp);
	}
	
	public List<Employee> searchEmployee(String keyword){
		
		if(keyword == null || keyword.trim().isEmpty()) {
	        return emprepo.findAll();
	    }
		return emprepo.searchEmployee(keyword.trim());
		
	}
	
	public Employee getEmployeeByEmail(String email){
		return emprepo.findByEmailaddress(email);
	} 
	
	public void changePassword(String email, String oldPassword, String newPassword) throws Exception {

	    Employee emp = emprepo.findByEmailaddress(email);

	    if(emp == null) {
	        throw new Exception("Employee not found.");
	    }

	    if(!emp.getPasswd().equals(oldPassword)) {
	        throw new Exception("Old password is incorrect.");
	    }

	    emp.setPasswd(newPassword);

	    emprepo.save(emp);
	}
	
	public long countEmployees(){
	    return emprepo.count();
	}
	
	public void resetPassword(String email, String newPassword) throws Exception {

	    Employee emp = emprepo.findByEmailaddress(email);

	    if(emp == null) {
	        throw new Exception("Email address not found.");
	    }

	    emp.setPasswd(newPassword);
	    emprepo.save(emp);
	}
}


