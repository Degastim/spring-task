package com.epam.storage;

import java.util.HashMap;
import java.util.Map;

import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;

public class Storage {
    private Map<Long, User> userMap = new HashMap<>();
    private Map<Long, Ticket> ticketMap = new HashMap<>();
    private Map<Long, Event> eventMap = new HashMap<>();

    public Map<Long, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Long, User> userMap) {
        this.userMap = userMap;
    }

    public Map<Long, Ticket> getTicketMap() {
        return ticketMap;
    }

    public void setTicketMap(Map<Long, Ticket> ticketMap) {
        this.ticketMap = ticketMap;
    }

    public Map<Long, Event> getEventMap() {
        return eventMap;
    }

    public void setEventMap(Map<Long, Event> eventMap) {
        this.eventMap = eventMap;
    }
}
