package com.remlists.user.domain.exceptions;

import com.remlists.shared.domain.exceptions.RemlistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UserRoleNotAssociatedException extends RemlistsException {

    private static final Logger LOG = LoggerFactory.getLogger(UserRoleNotAssociatedException.class);


    public UserRoleNotAssociatedException() {
    }

    public UserRoleNotAssociatedException(String message) {
        super(message);
    }

    public UserRoleNotAssociatedException(Throwable cause) {
        super(cause);
    }

    public UserRoleNotAssociatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleNotAssociatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
