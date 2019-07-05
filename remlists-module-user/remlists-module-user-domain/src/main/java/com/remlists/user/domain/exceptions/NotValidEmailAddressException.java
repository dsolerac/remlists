package com.remlists.user.domain.exceptions;


import com.remlists.shared.domain.exceptions.RemlistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotValidEmailAddressException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(NotValidEmailAddressException.class);


    public NotValidEmailAddressException() {
    }

    public NotValidEmailAddressException(String message) {
        super(message);
    }

    public NotValidEmailAddressException(Throwable cause) {
        super(cause);
    }

    public NotValidEmailAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidEmailAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
