package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RoleDescription implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(RoleDescription.class);

    @NotEmpty(message = "role.roleDescription.notEmpty")
    @Size(max = 300, min = 3, message = "role.roleDescription.size")
    private String description;


    public RoleDescription(String description) {
        this.description = description;
    }



    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDescription)) return false;

        RoleDescription that = (RoleDescription) o;

        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }

    @Override
    public String toString() {
        return "RoleDescription{" +
                "description='" + description + '\'' +
                '}';
    }
}
