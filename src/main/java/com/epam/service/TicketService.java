package com.epam.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.dao.EventDao;
import com.epam.dao.TicketDao;
import com.epam.dao.UserDao;
import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;

import lombok.Setter;

@Setter
public class TicketService {
    private static final Logger logger = LogManager.getLogger();
    private TicketDao ticketDao;
    private EventDao eventDao;
    private UserDao userDao;

    public Ticket bookTicket(Ticket ticket) {
        long eventId = ticket.getEventId();
        int place = ticket.getPlace();
        boolean isPlaceBooked = ticketDao.isPlaceBooked(eventId, place);
        if (isPlaceBooked) {
            logger.warn("This place has already been booked.Place" + place + "for event" + eventId);
            throw new IllegalStateException(
                    "This place has already been booked.Place" + place + "for event" + eventId);
        }
        logger.info("Input ticket.Ticket" + ticket);
        return ticketDao.create(ticket);
    }

    public boolean delete(long ticketId) {
        logger.info("Delete ticket.Ticket id" + ticketId);
        return ticketDao.delete(ticketId);
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        logger.info("Get all booked tickets for specified user.Reverse by event date. User " + user + " page size:"
                + pageSize + "and Page number" + pageNum);
        return ticketDao.getBookedTickets(user, pageSize, pageNum).stream()
                .sorted((o1, o2) -> eventDao.getEventById(o2.getEventId()).getDate()
                        .compareTo(eventDao.getEventById(o1.getEventId()).getDate()))
                .collect(Collectors.toList());
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        logger.info("Get all booked tickets for specified event. Event " + event + " page size" + pageSize
                + "and Page number" + pageNum);
        return ticketDao.getBookedTickets(event, pageSize, pageNum).stream()
                .sorted(Comparator.comparing(o -> userDao.getUserById(o.getUserId()).getEmail()))
                .collect(Collectors.toList());
    }
}
