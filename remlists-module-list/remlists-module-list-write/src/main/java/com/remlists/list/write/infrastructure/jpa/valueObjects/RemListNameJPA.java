package com.remlists.list.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class RemListNameJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(RemListNameJPA.class);

    @Column(name = "name")
    private String name;


    protected RemListNameJPA() {
    }

    public RemListNameJPA(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemListNameJPA)) return false;

        RemListNameJPA that = (RemListNameJPA) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }


    @Override
    public String toString() {
        return "RemListNameJPA{" +
                "name='" + name + '\'' +
                '}';
    }
}
