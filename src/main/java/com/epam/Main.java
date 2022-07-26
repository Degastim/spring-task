package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.storage.Storage;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(context.getBean("storage"));
    }
}
