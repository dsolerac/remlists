package com.remlists.user.domain.exceptions;

import com.remlists.shared.domain.exceptions.RemlistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmailAddressAlreadyExistsException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAddressAlreadyExistsException.class);


    public EmailAddressAlreadyExistsException() {
    }

    public EmailAddressAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAddressAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public EmailAddressAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAddressAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
