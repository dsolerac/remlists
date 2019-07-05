package com.remlists.user.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailVerifiedJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(EmailVerifiedJPA.class);

    @Column(name = "email_verified")
    private Boolean verified;

    public EmailVerifiedJPA(Boolean verified) {
        this.verified = verified;
    }


    public EmailVerifiedJPA() {
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailVerifiedJPA)) return false;

        EmailVerifiedJPA that = (EmailVerifiedJPA) o;

        return getVerified() != null ? getVerified().equals(that.getVerified()) : that.getVerified() == null;
    }

    @Override
    public int hashCode() {
        return getVerified() != null ? getVerified().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EmailVerifiedJPA{" +
                "verified=" + verified +
                '}';
    }
}
