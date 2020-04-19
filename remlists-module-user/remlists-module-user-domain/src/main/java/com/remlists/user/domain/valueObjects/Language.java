package com.remlists.user.domain.valueObjects;

import com.remlists.user.domain.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public final class Language implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Language.class);


    @NotEmpty(message = "user.language.code.notEmpty")
    private String code;
    @NotEmpty(message = "user.language.name.notEmpty")
    private String name;

    public Language(String code, String name) {
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
        if (!(o instanceof Language)) return false;

        Language language = (Language) o;

        if (getCode() != null ? !getCode().equals(language.getCode()) : language.getCode() != null) return false;
        return getName() != null ? getName().equals(language.getName()) : language.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Language{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
