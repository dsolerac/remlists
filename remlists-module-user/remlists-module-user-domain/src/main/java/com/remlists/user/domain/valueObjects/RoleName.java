package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RoleName implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(RoleName.class);

    private static final String CHARACTERS_ALLOWED_IN_ROLE_NAME = "^[A-Z_]*$";


    @NotEmpty(message = "{RoleName.role.NotEmpty}")
    @Size(max = 20, min = 3, message = "{RoleName.role.Size}")
    @Pattern(regexp = CHARACTERS_ALLOWED_IN_ROLE_NAME, message = "{RoleName.role.notAllowed}")
    private String role;


    public RoleName( String role ) {
        this.role = role;
    }

    public RoleName() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean sameValueAs(ShortName other) {
        return role.equals(other.getShortName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleName)) return false;

        RoleName roleName = (RoleName) o;

        return getRole().equals(roleName.getRole());
    }

    @Override
    public int hashCode() {
        return getRole().hashCode();
    }

    @Override
    public String toString() {
        return "RoleName{" +
                "role='" + role + '\'' +
                '}';
    }
}
