package com.epam.storage.exception;

import org.springframework.beans.BeansException;

public class IncorrectPropertyFileFieldValueException extends BeansException {
    public IncorrectPropertyFileFieldValueException(String msg) {
        super(msg);
    }

    public IncorrectPropertyFileFieldValueException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
