package com.alibubu.reemy.solo.reemysolowebservices.restcontroller;

import com.alibubu.reemy.solo.reemysolowebservices.entity.EventNote;
import com.alibubu.reemy.solo.reemysolowebservices.entity.EventNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventNoteJPAResourceController {

    @Autowired
    EventNoteRepository eventNoteRepository;


    @GetMapping("/notes")
    public List<EventNote> retrieveAllUsers(){
        return eventNoteRepository.findAll();
    }
}
