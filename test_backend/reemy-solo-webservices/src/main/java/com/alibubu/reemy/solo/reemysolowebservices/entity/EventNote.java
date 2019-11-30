package com.alibubu.reemy.solo.reemysolowebservices.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class EventNote {

    @Temporal(TemporalType.DATE)
    private Calendar day;

    @Id
    @GeneratedValue
    private long id;
    private String note;

    public EventNote(Calendar day, String note) {
        this.day = day;
        this.note = note;
    }

    protected EventNote() { }

    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

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
