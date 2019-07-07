package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;

public class Password implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(Password.class);


    @NotEmpty(message = "{Password.password.NotEmpty}")
    private String password;


    public Password(@NotEmpty(message = "{Password.password.NotEmpty}") String password) {
        this.password = password;
    }

    public Password() {
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
        if (!(o instanceof Password)) return false;

        Password password1 = (Password) o;

        return getPassword() != null ? getPassword().equals(password1.getPassword()) : password1.getPassword() == null;
    }

    @Override
    public int hashCode() {
        return getPassword() != null ? getPassword().hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                '}';
    }
}
