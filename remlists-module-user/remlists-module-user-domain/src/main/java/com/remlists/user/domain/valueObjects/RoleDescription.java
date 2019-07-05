package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RoleDescription implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(RoleName.class);

    @NotEmpty(message = "{RoleDescription.description.NotEmpty}")
    @Size(max = 300, min = 3, message = "{RoleDescription.description.Size}")
    private String description;


    public RoleDescription(@NotEmpty(message = "{RoleDescription.description.NotEmpty}")
                           @Size(max = 20, min = 3, message = "{RoleDescription.description.Size}") String description) {
        this.description = description;
    }

    public RoleDescription() {
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
