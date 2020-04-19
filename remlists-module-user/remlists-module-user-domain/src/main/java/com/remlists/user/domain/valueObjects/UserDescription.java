package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public final class UserDescription implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(UserDescription.class);


    @NotEmpty(message = "user.description.notEmpty")
    private String description;

    public UserDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDescription)) return false;

        UserDescription that = (UserDescription) o;

        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        return getDescription() != null ? getDescription().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserDescription{" +
                "description='" + description + '\'' +
                '}';
    }
}
