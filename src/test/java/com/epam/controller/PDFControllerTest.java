package com.epam.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContextMVC.xml")
@WebAppConfiguration
public class PDFControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void getBookedTicketsByUserId() throws Exception {
        this.mockMvc.perform(get("/pdf/byUser?userId=1&pageSize=1&pageNum=1")).andDo(print())
                .andExpect(view().name("index"));
    }

    @Test
    public void getBookedTicketsByEventId() throws Exception {
        this.mockMvc.perform(get("/pdf/byEvent?eventId=1&pageSize=1&pageNum=1")).andDo(print())
                .andExpect(view().name("index"));
    }
}
