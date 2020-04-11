package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public final class Country  implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Country.class);


    @NotEmpty
    private String code;
    @NotEmpty
    private String name;

    public Country(@NotEmpty String code, @NotEmpty String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (getCode() != null ? !getCode().equals(country.getCode()) : country.getCode() != null) return false;
        return getName() != null ? getName().equals(country.getName()) : country.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
