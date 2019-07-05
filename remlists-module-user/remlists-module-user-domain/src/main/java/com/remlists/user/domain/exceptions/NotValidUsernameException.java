package com.remlists.user.domain.exceptions;


import com.remlists.shared.domain.exceptions.RemlistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotValidUsernameException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(NotValidUsernameException.class);


    public NotValidUsernameException() {
    }

    public NotValidUsernameException(String message) {
        super(message);
    }

    public NotValidUsernameException(Throwable cause) {
        super(cause);
    }

    public NotValidUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
