package com.epam.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Event {
    private long id;
    private String title;
    private Date date;

    public Event() {
    }

    public Event(long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }
}
