package com.epam.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import com.epam.entity.TicketContainer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class XMLConverter {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public TicketContainer convertFromXMLToObject(String xmlFile) throws IOException {
        try (FileInputStream is = new FileInputStream(xmlFile)) {
            return (TicketContainer) unmarshaller.unmarshal(new StreamSource(is));
        }
    }
    public void convertFromObjectToObjectXML(String xmlFile,TicketContainer ticketContainer) throws IOException {
        try (FileOutputStream os = new FileOutputStream(xmlFile)) {
            this.marshaller.marshal(ticketContainer, new StreamResult(os));
        }
    }
}
