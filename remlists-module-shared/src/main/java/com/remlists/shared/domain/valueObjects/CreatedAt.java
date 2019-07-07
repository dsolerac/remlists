package com.remlists.shared.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class CreatedAt implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(CreatedAt.class);

    @PastOrPresent
    private LocalDateTime createdAt;

    public CreatedAt(@PastOrPresent LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CreatedAt() {
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatedAt)) return false;

        CreatedAt createdAt1 = (CreatedAt) o;

        return getCreatedAt() != null ? getCreatedAt().equals(createdAt1.getCreatedAt()) : createdAt1.getCreatedAt() == null;
    }

    @Override
    public int hashCode() {
        return getCreatedAt() != null ? getCreatedAt().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CreatedAt{" +
                "createdAt=" + createdAt +
                '}';
    }

}
