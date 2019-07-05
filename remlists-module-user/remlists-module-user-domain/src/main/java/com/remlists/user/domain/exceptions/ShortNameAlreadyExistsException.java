package com.remlists.user.domain.exceptions;

import com.remlists.shared.domain.exceptions.RemlistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ShortNameAlreadyExistsException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAddressAlreadyExistsException.class);


    public ShortNameAlreadyExistsException() {
    }

    public ShortNameAlreadyExistsException(String message) {
        super(message);
    }

    public ShortNameAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ShortNameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortNameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
