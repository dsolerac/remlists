package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public final class Twitter implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Twitter.class);

    @Pattern(regexp = "^@", message = "user.twitter.pattern")
    private String twiterId;

    public Twitter(String twiterId) {
        this.twiterId = twiterId;
    }

    public String getTwiterId() {
        return twiterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Twitter)) return false;

        Twitter twitter = (Twitter) o;

        return getTwiterId() != null ? getTwiterId().equals(twitter.getTwiterId()) : twitter.getTwiterId() == null;
    }

    @Override
    public int hashCode() {
        return getTwiterId() != null ? getTwiterId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Twitter{" +
                "twiterId='" + twiterId + '\'' +
                '}';
    }
}
