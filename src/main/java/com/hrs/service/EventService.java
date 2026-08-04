package com.hrs.hrs.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hrs.entity.EventNotification;
import com.hrs.hrs.repository.EventRepo;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    public void saveEvent(EventNotification event){

        event.setPosteddate(LocalDate.now());

        eventRepo.save(event);
    }

    public List<EventNotification> getAllEvents(){
        return eventRepo.findAll();
    }

    public EventNotification getEventById(Integer id){
        return eventRepo.findById(id).get();
    }

    public void updateEvent(EventNotification event){
        eventRepo.save(event);
    }

    public void deleteEvent(Integer id){
        eventRepo.deleteById(id);
    }
    
    public long countEvents(){
        return eventRepo.count();
    }
}