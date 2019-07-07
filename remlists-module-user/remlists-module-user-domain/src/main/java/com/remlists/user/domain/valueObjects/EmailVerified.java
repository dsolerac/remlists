package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailVerified implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(EmailVerified.class);

    private Boolean verified;


    public EmailVerified(Boolean verified) {
        this.verified = verified;
    }

    public EmailVerified() {
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
        if (!(o instanceof EmailVerified)) return false;

        EmailVerified that = (EmailVerified) o;

        return verified != null ? verified.equals(that.verified) : that.verified == null;
    }

    @Override
    public int hashCode() {
        return verified != null ? verified.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EmailVerified{" +
                "verified=" + verified +
                '}';
    }
}
