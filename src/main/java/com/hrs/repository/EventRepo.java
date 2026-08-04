package com.hrs.hrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hrs.entity.EventNotification;

public interface EventRepo
extends JpaRepository<EventNotification,Integer>{

}