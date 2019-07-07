package com.remlists.user.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleDescriptionJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(RoleDescriptionJPA.class);

    @Column(name = "description")
    private String description;


    public RoleDescriptionJPA(String description) {
        this.description = description;
    }

    public RoleDescriptionJPA() {
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
        if (!(o instanceof RoleDescriptionJPA)) return false;

        RoleDescriptionJPA that = (RoleDescriptionJPA) o;

        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        return getDescription() != null ? getDescription().hashCode() : 0;
    }


    @Override
    public String toString() {
        return "RoleDescriptionJPA{" +
                "description='" + description + '\'' +
                '}';
    }
}
