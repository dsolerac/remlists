package com.remlists.shared.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemlistsException extends RuntimeException {

    private static final Logger LOG = LoggerFactory.getLogger(RemlistsException.class);


    public RemlistsException() {
        super();
    }

    public RemlistsException(String message) {
        super(message);
    }

    public RemlistsException(Throwable cause) {
        super(cause);
    }

    public RemlistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemlistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
