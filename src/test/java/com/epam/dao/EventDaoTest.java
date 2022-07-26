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
import com.epam.storage.Storage;

class EventDaoTest {
    EventDao eventDao;
    Storage storage;

    @BeforeEach
    void setup() {
        storage = Mockito.mock(Storage.class);
        eventDao = new EventDao();
        eventDao.setStorage(storage);
    }

    @Test
    void getEventById() {
        //Given
        long eventId = 3;
        Map<Long, Event> map = new HashMap<>();
        Event event1 = new Event(eventId, "Music", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Event event2 = new Event(4, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        map.put(eventId, event1);
        map.put(4L, event2);
        Event expected = new Event(eventId, "Music", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Mockito.when(storage.getEventMap()).thenReturn(map);

        //When
        Event actual = eventDao.getEventById(eventId);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getEventsByTitle() {
        //Given
        String title = "Music";
        Map<Long, Event> map = new HashMap<>();
        Event event1 = new Event(3L, title, new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Event event2 = new Event(5L, title, new GregorianCalendar(2022, Calendar.AUGUST, 3).getTime());
        Event event3 = new Event(6L, title, new GregorianCalendar(2022, Calendar.JULY, 7).getTime());
        map.put(3L, event1);
        map.put(5L, event2);
        map.put(6L, event3);
        Event event4 = new Event(4, "Dance", new GregorianCalendar(2022, Calendar.FEBRUARY, 10).getTime());
        map.put(4L, event4);
        List<Event> expected = new ArrayList<>();
        expected.add(event1);
        expected.add(event2);
        Mockito.when(storage.getEventMap()).thenReturn(map);

        //When
        List<Event> actual = eventDao.getEventsByTitle(title, 2, 1);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void create() {
        //Given
        Event event = new Event("Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        Event expected = new Event(1, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());

        //When
        Event actual = eventDao.create(event);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update() {
        //Given
        Map<Long, Event> eventMap = new HashMap<>();
        Event event = new Event(1, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        eventMap.put(1L, event);
        Event updateEvent = new Event(1, "Music", new GregorianCalendar(2023, Calendar.APRIL, 4).getTime());
        Event expected = new Event(1, "Music", new GregorianCalendar(2023, Calendar.APRIL, 4).getTime());
        Mockito.when(storage.getEventMap()).thenReturn(eventMap);

        //When
        Event actual = eventDao.update(updateEvent);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTrue() {
        //Given
        Map<Long, Event> eventMap = new HashMap<>();
        Event event = new Event(1, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        eventMap.put(1L, event);
        Mockito.when(storage.getEventMap()).thenReturn(eventMap);

        //When
        boolean actual = eventDao.delete(1L);

        //Then
        Assertions.assertTrue(actual);
    }
    @Test
    void deleteFalse() {
        //Given
        Map<Long, Event> eventMap = new HashMap<>();
        Event event = new Event(1, "Dance", new GregorianCalendar(2022, Calendar.APRIL, 4).getTime());
        eventMap.put(1L, event);
        Mockito.when(storage.getEventMap()).thenReturn(eventMap);

        //When
        boolean actual = eventDao.delete(2L);

        //Then
        Assertions.assertFalse(actual);
    }
}