package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.*;

import java.io.Serializable;

public class EmailAddress implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAddress.class);


    @NotEmpty(message = "{EmailAddress.emailAddress.NotEmpty}")
    @Email(message = "{EmailAddress.emailAddress.Email}")
    private String emailAddress;

    protected EmailAddress() {
    }

    public EmailAddress(String emailAddress) {
        this.setEmailAddress(emailAddress);
    }


    private void setEmailAddress(String emailAddress) {

        this.emailAddress = emailAddress;

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean sameValueAs(EmailAddress other) {

        return emailAddress.equals(other.getEmailAddress());

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress)) return false;

        EmailAddress that = (EmailAddress) o;

        return getEmailAddress().equals(that.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return getEmailAddress().hashCode();
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
