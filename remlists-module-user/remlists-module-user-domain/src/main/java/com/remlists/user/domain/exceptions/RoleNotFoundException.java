package com.remlists.user.domain.exceptions;


import com.remlists.shared.domain.exceptions.RemlistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleNotFoundException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(RoleNotFoundException.class);


    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(Throwable cause) {
        super(cause);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
