package com.epam.storage;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;
import com.epam.parser.EntityFieldParser;

import lombok.Setter;

@Setter
public class FillStorageBeanPostProcessor implements BeanPostProcessor {
    private static final Logger logger = LogManager.getLogger();
    String userMapValue;
    String eventMapValue;
    String ticketMapValue;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("storage")) {
            logger.info("Injecting values into storage from an external source");
            Storage storage = (Storage) bean;
            EntityFieldParser entityFieldParser = new EntityFieldParser();
            List<List<String[]>> usersKeyValuesList = entityFieldParser.parseStringValues(userMapValue);
            for (List<String[]> userKeyValueArray : usersKeyValuesList) {
                User user = new User();
                for (String[] keyValueArray : userKeyValueArray) {
                    try {
                        entityFieldParser.parseEntity(user, keyValueArray);
                    } catch (IllegalAccessException e) {
                        logger.fatal("Unable to access object field");
                    }
                }
                storage.getUserMap().put(user.getId(), user);
            }
            List<List<String[]>> eventsKeyValuesList = entityFieldParser.parseStringValues(eventMapValue);
            for (List<String[]> eventKeyValueArray : eventsKeyValuesList) {
                Event event = new Event();
                for (String[] keyValueArray : eventKeyValueArray) {
                    try {
                        entityFieldParser.parseEntity(event, keyValueArray);
                    } catch (IllegalAccessException e) {
                        logger.fatal("Unable to access object field");
                    }
                }
                storage.getEventMap().put(event.getId(), event);
            }

            List<List<String[]>> ticketsKeyValuesList = entityFieldParser.parseStringValues(ticketMapValue);
            for (List<String[]> ticketKeyValueArray : ticketsKeyValuesList) {
                Ticket ticket = new Ticket();
                for (String[] keyValueArray : ticketKeyValueArray) {
                    try {
                        entityFieldParser.parseEntity(ticket, keyValueArray);
                    } catch (IllegalAccessException e) {
                        logger.fatal("Unable to access object field");
                    }
                }
                storage.getTicketMap().put(ticket.getId(), ticket);
            }
        }
        return bean;
    }
}

