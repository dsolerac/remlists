package com.remlists.user.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleNameJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(RoleNameJPA.class);


    @Column(name = "role")
    private String role;


    public RoleNameJPA(String role) {
        this.role = role;
    }

    public RoleNameJPA() {
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleNameJPA)) return false;

        RoleNameJPA that = (RoleNameJPA) o;

        return getRole() != null ? getRole().equals(that.getRole()) : that.getRole() == null;
    }

    @Override
    public int hashCode() {
        return getRole() != null ? getRole().hashCode() : 0;
    }


    @Override
    public String toString() {
        return "RoleNameJPA{" +
                "role='" + role + '\'' +
                '}';
    }
}
