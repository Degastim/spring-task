package com.epam.storage.exception;

import org.springframework.beans.BeansException;

public class IncorrectPropertyFileFieldNameException extends BeansException {
    public IncorrectPropertyFileFieldNameException(String msg) {
        super(msg);
    }

    public IncorrectPropertyFileFieldNameException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
