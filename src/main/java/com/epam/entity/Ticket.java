package com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    public Ticket(long userId, long eventId, int place, Category category) {
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
    private long userId;
    private long eventId;
    private int place;
    private Category category;
}
