package com.epam.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;
import com.epam.storage.Storage;

class TicketDaoTest {
    TicketDao ticketDao;
    Storage storage;

    @BeforeEach
    void setup() {
        storage = Mockito.mock(Storage.class);
        ticketDao = new TicketDao();
        ticketDao.setStorage(storage);
    }

    @Test
    void create() {
        //Given
        Ticket ticket = new Ticket(1, 2, 3, Ticket.Category.BAR);
        Ticket expected = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);

        //When
        Ticket actual = ticketDao.create(ticket);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTrue() {
        //Given
        Map<Long, Ticket> ticketMap = new HashMap<>();
        Ticket ticket = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        ticketMap.put(1L, ticket);
        Mockito.when(storage.getTicketMap()).thenReturn(ticketMap);

        //When
        boolean actual = ticketDao.delete(1L);

        //Then
        Assertions.assertTrue(actual);
    }

    @Test
    void deleteFalse() {
        //Given
        Map<Long, Ticket> ticketMap = new HashMap<>();
        Ticket ticket = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        ticketMap.put(1L, ticket);
        Mockito.when(storage.getTicketMap()).thenReturn(ticketMap);

        //When
        boolean actual = ticketDao.delete(2L);

        //Then
        Assertions.assertFalse(actual);
    }


    @Test
    void getBookedTickets() {
        //Given
        User user = new User(1, "Dave", "tandra@gmail.com");
        Ticket ticket1 = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        Ticket ticket2 = new Ticket(2, 1, 2, 4, Ticket.Category.BAR);
        Ticket ticket3 = new Ticket(3, 1, 2, 4, Ticket.Category.BAR);
        HashMap<Long, Ticket> ticketMap = new HashMap<>();
        ticketMap.put(1L, ticket1);
        ticketMap.put(2L, ticket2);
        ticketMap.put(3L, ticket3);
        Mockito.when(storage.getTicketMap()).thenReturn(ticketMap);
        List<Ticket> expected = new ArrayList<>();
        expected.add(ticket1);
        expected.add(ticket2);

        //When
        List<Ticket> actual = ticketDao.getBookedTickets(user, 2, 1);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGetBookedTickets() {
        //Given
        Event event = new Event(2, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Ticket ticket1 = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        Ticket ticket2 = new Ticket(2, 1, 2, 4, Ticket.Category.BAR);
        Ticket ticket3 = new Ticket(3, 1, 3, 4, Ticket.Category.BAR);
        HashMap<Long, Ticket> ticketMap = new HashMap<>();
        ticketMap.put(1L, ticket1);
        ticketMap.put(2L, ticket2);
        ticketMap.put(3L, ticket3);
        Mockito.when(storage.getTicketMap()).thenReturn(ticketMap);
        List<Ticket> expected = new ArrayList<>();
        expected.add(ticket1);
        expected.add(ticket2);

        //When
        List<Ticket> actual = ticketDao.getBookedTickets(event, 2, 1);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isPlaceBookedTrue() {
        //Given
        Ticket ticket1 = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        Ticket ticket2 = new Ticket(2, 1, 2, 4, Ticket.Category.BAR);
        Ticket ticket3 = new Ticket(3, 1, 3, 4, Ticket.Category.BAR);
        HashMap<Long, Ticket> ticketMap = new HashMap<>();
        ticketMap.put(1L, ticket1);
        ticketMap.put(2L, ticket2);
        ticketMap.put(3L, ticket3);
        Mockito.when(storage.getTicketMap()).thenReturn(ticketMap);

        //When
        boolean actual = ticketDao.isPlaceBooked(2, 3);

        //Then
        Assertions.assertTrue(actual);
    }

    @Test
    void isPlaceBookedFalse() {
        //Given
        Ticket ticket1 = new Ticket(1, 1, 2, 3, Ticket.Category.BAR);
        Ticket ticket2 = new Ticket(2, 1, 2, 4, Ticket.Category.BAR);
        Ticket ticket3 = new Ticket(3, 1, 3, 4, Ticket.Category.BAR);
        HashMap<Long, Ticket> ticketMap = new HashMap<>();
        ticketMap.put(1L, ticket1);
        ticketMap.put(2L, ticket2);
        ticketMap.put(3L, ticket3);
        Mockito.when(storage.getTicketMap()).thenReturn(ticketMap);

        //When
        boolean actual = ticketDao.isPlaceBooked(2, 2);

        //Then
        Assertions.assertFalse(actual);
    }
}