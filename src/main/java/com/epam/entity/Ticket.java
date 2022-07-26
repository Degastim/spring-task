package com.epam.entity;

import lombok.Data;

@Data
public class Ticket {
    public Ticket() {
    }

    public Ticket(long userId, long eventId, int place, Category category) {
        this.userId = userId;
        this.eventId = eventId;
        this.place = place;
        this.category = category;
    }

    public Ticket(long id, long userId, long eventId, int place, Category category) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.place = place;
        this.category = category;
    }

    public enum Category {
        STANDARD,
        PREMIUM,
        BAR
    }

    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;
}
