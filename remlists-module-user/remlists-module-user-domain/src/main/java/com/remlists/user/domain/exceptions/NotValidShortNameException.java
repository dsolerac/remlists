package com.remlists.user.domain.exceptions;

import com.remlists.shared.domain.exceptions.RemlistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NotValidShortNameException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(NotValidEmailAddressException.class);


    public NotValidShortNameException() {
    }

    public NotValidShortNameException(String message) {
        super(message);
    }

    public NotValidShortNameException(Throwable cause) {
        super(cause);
    }

    public NotValidShortNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidShortNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
