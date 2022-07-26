package com.epam.facade;

import java.util.List;

import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;
import com.epam.service.EventService;
import com.epam.service.TicketService;
import com.epam.service.UserService;

public class BookingFacade {
    private final EventService eventService;
    private final TicketService ticketService;
    private final UserService userService;

    public BookingFacade(EventService eventService, TicketService ticketService, UserService userService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    public Event getEventById(long eventId) {
        return eventService.getEventById(eventId);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventsByTitle(title, pageSize, pageNum);
    }

    public Event createEvent(Event event) {
        return eventService.create(event);
    }

    public Event updateEvent(Event event) {
        return eventService.update(event);
    }

    public boolean deleteEvent(long eventId) {
        return eventService.delete(eventId);
    }

    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    public User createUser(User user) {
        return userService.create(user);
    }

    public User updateUser(User user) {
        return userService.update(user);
    }

    public boolean deleteUser(long userId) {
        return userService.delete(userId);
    }

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new Ticket(userId, eventId, place, category);
        return ticketService.bookTicket(ticket);
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(user, pageSize, pageNum);
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(event, pageSize, pageNum);
    }

    public boolean cancelTicket(long ticketId) {
        return ticketService.delete(ticketId);
    }
}
