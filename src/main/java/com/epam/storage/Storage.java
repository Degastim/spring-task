package com.epam.storage;

import java.util.HashMap;
import java.util.Map;

import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Storage {
    private Map<Long, User> userMap = new HashMap<>();
    private Map<Long, Ticket> ticketMap = new HashMap<>();
    private Map<Long, Event> eventMap = new HashMap<>();
}
