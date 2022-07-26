package com.epam;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.entity.Event;
import com.epam.entity.Ticket;
import com.epam.entity.User;
import com.epam.facade.BookingFacade;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class IntegrationTest {
    @Autowired
    BookingFacade bookingFacade;

    @Test
    void test_correctValue_equals() {
        User user = new User("Jonson", "jonson@gmail.com");
        Event event = new Event("Dance", new GregorianCalendar(2020, Calendar.MAY, 6).getTime());
        user = bookingFacade.createUser(user);
        event = bookingFacade.createEvent(event);
        Ticket actual = bookingFacade.bookTicket(user.getId(), event.getId(), 3, Ticket.Category.BAR);
        Ticket expected = new Ticket(1, 1, 1, 3, Ticket.Category.BAR);
        Assertions.assertEquals(expected, actual);
    }
}
