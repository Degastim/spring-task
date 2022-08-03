package com.epam.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.dao.EventDao;
import com.epam.entity.Event;

import lombok.Setter;

@Setter
public class EventService {
    private static final Logger logger = LogManager.getLogger();
    private EventDao eventDao;

    public Event getEventById(long eventId) {
        logger.info("Get Event By id");
        return eventDao.getEventById(eventId);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        logger.info("Get Events by title with page size " + pageSize + " and page number" + pageNum);
        return eventDao.getEventsByTitle(title, pageSize, pageNum);
    }

    public Event create(Event event) {
        logger.info("Put Event. Event" + event);
        return eventDao.create(event);
    }

    public Event update(Event event) {
        logger.info("Update Event. Event" + event);
        return eventDao.update(event);
    }

    public boolean delete(long eventId) {
        logger.info("Delete Event from storage. Event id" + eventId);
        return eventDao.delete(eventId);
    }
}
