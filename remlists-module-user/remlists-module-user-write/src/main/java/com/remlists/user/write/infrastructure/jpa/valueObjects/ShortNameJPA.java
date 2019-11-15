package com.remlists.user.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShortNameJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(ShortNameJPA.class);

    @Column(name = "shortname")
    private String shortName;


    public ShortNameJPA(String shortName) {
        this.setShortName(shortName);
    }

    public ShortNameJPA() {
    }


    public String getShortName() {
        return shortName;
    }


    public void setShortName(String shortName) {

        this.shortName = shortName;
    }


    public boolean sameValueAs(ShortNameJPA other) {
        return shortName.equals(other.getShortName());
    }

    @Override
    public String toString() {
        return "ShortNameJPA{" +
                "shortName='" + shortName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortNameJPA)) return false;
        ShortNameJPA that = (ShortNameJPA) o;
        return Objects.equals(getShortName(), that.getShortName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShortName());
    }
}
