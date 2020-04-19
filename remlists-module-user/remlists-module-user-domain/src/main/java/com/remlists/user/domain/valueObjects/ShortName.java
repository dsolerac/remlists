package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ShortName implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(ShortName.class);

    private static final String CHARACTERS_ALLOWED_IN_SHORT_NAME = "^[a-zA-Z0-9.:_\\\\s]+$";


    @NotEmpty(message = "user.shortName.notEmpty")
    @Size(max = 20, min = 3, message = "{user.shortName.size}")
    @Pattern(regexp = CHARACTERS_ALLOWED_IN_SHORT_NAME, message = "{user.shortName.pattern}")
    private String shortName;


    public ShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getShortName() {
        return shortName;
    }


    public boolean sameValueAs(ShortName other) {
        return shortName.equals(other.getShortName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortName)) return false;

        ShortName shortName1 = (ShortName) o;

        return getShortName().equals(shortName1.getShortName());
    }

    @Override
    public int hashCode() {
        return getShortName().hashCode();
    }

    @Override
    public String toString() {
        return "ShortName{" +
                "shortName='" + shortName + '\'' +
                '}';
    }
}
