package com.epam.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.dao.EventDao;
import com.epam.dao.TicketDao;
import com.epam.dao.UserDao;
import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;

class TicketServiceTest {
    TicketService ticketService;
    TicketDao ticketDao;
    EventDao eventDao;
    UserDao userDao;

    @BeforeEach
    void ticketService() {
        ticketDao = Mockito.mock(TicketDao.class);
        eventDao = Mockito.mock(EventDao.class);
        userDao=Mockito.mock(UserDao.class);
        ticketService = new TicketService();
        ticketService.setTicketDao(ticketDao);
        ticketService.setEventDao(eventDao);
        ticketService.setUserDao(userDao);
    }

    @Test
    void deleteTrue() {
        //Given
        Mockito.when(ticketDao.delete(3)).thenReturn(true);

        //When
        boolean actual = ticketService.delete(3L);

        //Then
        Assertions.assertTrue(actual);
    }

    @Test
    void deleteFalse() {
        //Given
        Mockito.when(ticketDao.delete(3)).thenReturn(false);

        //When
        boolean actual = ticketService.delete(3L);

        //Then
        Assertions.assertFalse(actual);
    }


    @Test
    void getBookedTickets() {
        //Given
        User user = new User(1, "Dave", "tandra@gmail.com");
        Ticket ticket1 = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        Ticket ticket2 = new Ticket(2, 1, 2, 4, Ticket.Category.BAR);
        List<Ticket> ticketList = List.of(ticket1, ticket2);
        Mockito.when(ticketDao.getBookedTickets(user, 2, 1)).thenReturn(ticketList);
        List<Ticket> expected = List.of(ticket1, ticket2);
        Event event1 = new Event(1, "Music", new GregorianCalendar(2020, Calendar.MAY, 6).getTime());
        Event event2 = new Event(2, "Dance", new GregorianCalendar(2021, Calendar.MAY, 6).getTime());
        Mockito.when(eventDao.getEventById(2)).thenReturn(event1);
        Mockito.when(eventDao.getEventById(3)).thenReturn(event2);

        //When
        List<Ticket> actual = ticketService.getBookedTickets(user, 2, 1);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetBookedTickets() {
        //Given
        Event event = new Event(2, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Ticket ticket1 = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        Ticket ticket2 = new Ticket(2, 2, 2, 4, Ticket.Category.BAR);
        List<Ticket> ticketList = List.of(ticket1, ticket2);
        Mockito.when(ticketDao.getBookedTickets(event, 2, 1)).thenReturn(ticketList);
        List<Ticket> expected = List.of(ticket1, ticket2);
        User user1 = new User(1, "Dane", "dane@gmail.com");
        User user2 = new User(2, "Jacob","jacob@jmail.com");
        Mockito.when(userDao.getUserById(1)).thenReturn(user1);
        Mockito.when(userDao.getUserById(2)).thenReturn(user2);

        //When
        List<Ticket> actual = ticketService.getBookedTickets(event, 2, 1);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void bookTicket_placedBook_exception() {
        //Given
        Ticket ticket = new Ticket(1, 2, 3, Ticket.Category.BAR);
        Mockito.when(ticketDao.isPlaceBooked(2, 3)).thenReturn(true);

        //When-Then
        Assertions.assertThrows(IllegalStateException.class, () -> ticketService.bookTicket(ticket));
    }

    @Test
    void bookTicket_notPlacedBook_ticket() {
        //Given
        Ticket ticket = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        Mockito.when(ticketDao.isPlaceBooked(2, 3)).thenReturn(false);
        Mockito.when(ticketDao.create(ticket)).thenReturn(ticket);
        Ticket expected = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);

        //When
        Ticket actual = ticketService.bookTicket(ticket);

        //Then
        Assertions.assertEquals(expected, actual);
    }
}