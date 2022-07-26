package com.epam.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.dao.EventDao;
import com.epam.entity.Event;

class EventServiceTest {
    EventService eventService;
    EventDao eventDao;

    @BeforeEach
    void setup() {
        eventDao = Mockito.mock(EventDao.class);
        eventService = new EventService();
        eventService.setEventDao(eventDao);
    }

    @Test
    void getEventById() {
        //Given
        long eventId = 3;
        Event event1 = new Event(eventId, "Music", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Event expected = new Event(eventId, "Music", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Mockito.when(eventDao.getEventById(eventId)).thenReturn(event1);

        //When
        Event actual = eventService.getEventById(eventId);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getEventsByTitle() {
        //Given
        String title = "Music";
        Event event1 = new Event(3L, title, new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Event event2 = new Event(5L, title, new GregorianCalendar(2022, Calendar.AUGUST, 3).getTime());
        List<Event> eventList = List.of(event1, event2);
        List<Event> expected = new ArrayList<>();
        expected.add(event1);
        expected.add(event2);
        Mockito.when(eventDao.getEventsByTitle(title, 2, 1)).thenReturn(eventList);

        //When
        List<Event> actual = eventService.getEventsByTitle(title, 2, 1);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void create() {
        //Given
        Event event = new Event(1, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Event expected = new Event(1, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Mockito.when(eventDao.create(event)).thenReturn(event);

        //When
        Event actual = eventService.create(event);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update() {
        //Given
        Event updateEvent = new Event(1, "Music", new GregorianCalendar(2023, Calendar.APRIL, 4).getTime());
        Event expected = new Event(1, "Music", new GregorianCalendar(2023, Calendar.APRIL, 4).getTime());
        Mockito.when(eventDao.update(updateEvent)).thenReturn(updateEvent);

        //When
        Event actual = eventService.update(updateEvent);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTrue() {
        //Given
        Mockito.when(eventDao.delete(1L)).thenReturn(true);

        //When
        boolean actual = eventService.delete(1L);

        //Then
        Assertions.assertTrue(actual);
    }

    @Test
    void deleteFalse() {
        //Given
        Mockito.when(eventDao.delete(2L)).thenReturn(false);

        //When
        boolean actual = eventService.delete(2L);

        //Then
        Assertions.assertFalse(actual);
    }
}