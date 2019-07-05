package com.remlists.user.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmailAddressJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(EmailAddressJPA.class);

    @Column(name = "email")
    private String emailAddress;

    protected EmailAddressJPA() {
    }

    public EmailAddressJPA(String emailAddress) {
        this.setEmailAddress(emailAddress);
    }


    private void setEmailAddress(String emailAddress) {

        this.emailAddress = emailAddress;

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean sameValueAs(EmailAddressJPA other) {

        return emailAddress.equals(other.getEmailAddress());

    }


    @Override
    public String toString() {
        return "EmailAddressJPA{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddressJPA)) return false;
        EmailAddressJPA that = (EmailAddressJPA) o;
        return Objects.equals(getEmailAddress(), that.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailAddress());
    }

}
