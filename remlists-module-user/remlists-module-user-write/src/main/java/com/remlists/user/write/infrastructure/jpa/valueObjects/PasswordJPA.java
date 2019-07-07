package com.remlists.user.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class PasswordJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordJPA.class);

    @Column(name = "password")
    private String password;


    public PasswordJPA(String password) {
        this.password = password;
    }


    public PasswordJPA() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordJPA)) return false;

        PasswordJPA that = (PasswordJPA) o;

        return getPassword() != null ? getPassword().equals(that.getPassword()) : that.getPassword() == null;
    }

    @Override
    public int hashCode() {
        return getPassword() != null ? getPassword().hashCode() : 0;
    }


    @Override
    public String toString() {
        return "PasswordJPA{" +
                "password='" + password + '\'' +
                '}';
    }
}
