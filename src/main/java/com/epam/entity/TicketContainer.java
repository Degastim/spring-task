package com.epam.entity;

import lombok.Setter;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tickets")
@Setter
public class TicketContainer {
    private List<Ticket> ticketList;

    @XmlElement(name = "ticket")
    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
