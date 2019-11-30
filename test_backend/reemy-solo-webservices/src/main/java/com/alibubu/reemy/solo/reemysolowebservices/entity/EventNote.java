package com.alibubu.reemy.solo.reemysolowebservices.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EventNote {

    @Id
    @GeneratedValue
    private long id;

    @Temporal(TemporalType.DATE)
    private Date day;

    private String note;

    public EventNote(long id, Date day, String note) {
        this.id = id;
        this.day = day;
        this.note = note;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    protected EventNote() { }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
