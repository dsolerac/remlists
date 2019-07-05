package com.remlists.list.domain.exceptions;

import com.remlists.shared.domain.exceptions.RemlistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemlistNotFoundException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(RemlistNotFoundException.class);


    public RemlistNotFoundException() {
        super();
    }

    public RemlistNotFoundException(String message) {
        super(message);
    }

    public RemlistNotFoundException(Throwable cause) {
        super(cause);
    }

    public RemlistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemlistNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
