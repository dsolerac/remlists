package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public enum UserStatus implements Serializable {

    PENDING,
    ACTIVE,
    INACTIVE,
    DELETED;

    private static final Logger LOG = LoggerFactory.getLogger(Country.class);


}
