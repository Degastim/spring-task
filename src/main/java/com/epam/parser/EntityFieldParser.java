package com.epam.parser;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.epam.entity.Ticket;
import com.epam.storage.exception.IncorrectPropertyFileFieldNameException;
import com.epam.storage.exception.IncorrectPropertyFileFieldValueException;

public class EntityFieldParser {
    public void parseEntity(Object object, String[] keyValueArray) throws IllegalAccessException {
        try {
            Field field = object.getClass().getDeclaredField(keyValueArray[0]);
            field.setAccessible(true);
            Class<?> type = field.getType();
            Object result;
            if (type == long.class) {
                result = Long.parseLong(keyValueArray[1]);
            } else if (type == int.class) {
                result = Integer.parseInt(keyValueArray[1]);
            } else if (type == Ticket.Category.class) {
                result = Ticket.Category.valueOf(keyValueArray[1]);
            } else if (type == Date.class) {
                result = new SimpleDateFormat("dd-MM-yyyy").parse(keyValueArray[1]);
            } else {
                result = keyValueArray[1];
            }
            field.set(object, result);
        } catch (NoSuchFieldException e) {
            throw new IncorrectPropertyFileFieldNameException("Incorrect field name:" + keyValueArray[0]);
        } catch (ParseException e) {
            throw new IncorrectPropertyFileFieldValueException("Incorrect value:" + keyValueArray[2]);
        }
    }

    public List<List<String[]>> parseStringValues(String value) {
        Pattern objectPattern = Pattern.compile(";");
        Pattern fieldsPattern = Pattern.compile(",");
        Pattern keyValuePattern = Pattern.compile("=");
        String[] stringUser = objectPattern.split(value);
        List<List<String[]>> result = new ArrayList<>();
        for (String string : stringUser) {
            String[] stringFieldArray = fieldsPattern.split(string);
            List<String[]> userKeyValueArray = new ArrayList<>();
            for (String stringField : stringFieldArray) {
                String[] keyValueArray = keyValuePattern.split(stringField);
                userKeyValueArray.add(keyValueArray);
            }
            result.add(userKeyValueArray);
        }
        return result;
    }
}
