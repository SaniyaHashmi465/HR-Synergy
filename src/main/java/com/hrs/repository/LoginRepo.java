package com.hrs.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hrs.entity.Login;

public interface LoginRepo extends JpaRepository<Login,String>{

	Login findByUseridAndPasswdAndUsertype(String userid,String passwd, String usertype);
}
