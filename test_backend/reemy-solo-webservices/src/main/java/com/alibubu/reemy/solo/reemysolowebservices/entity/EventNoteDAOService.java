package com.alibubu.reemy.solo.reemysolowebservices.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EventNoteDAOService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(EventNote eventNote){
        //Open Transaction. When u want to modify the database - u need to be inside a transaction
        entityManager.persist(eventNote);
        //Now a new entity is managed by the EntityManager.
        // eventNote is now in persistence context - whatever changes u make to it are being tracked (entityManager only tracks things
        //that are persisted through it
        //Close Transaction
        return eventNote.getId();
    }
}
