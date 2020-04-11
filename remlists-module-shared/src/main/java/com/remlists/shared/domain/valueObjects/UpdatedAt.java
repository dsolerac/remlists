package com.remlists.shared.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class UpdatedAt implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(UpdatedAt.class);

    @PastOrPresent
    private LocalDateTime updatedAt;

    public UpdatedAt(@PastOrPresent LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Create a Local Date Time of that instant
     */
    public UpdatedAt() {
        updatedAt = LocalDateTime.now();
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatedAt)) return false;

        UpdatedAt createdAt1 = (UpdatedAt) o;

        return getUpdatedAt() != null ? getUpdatedAt().equals(createdAt1.getUpdatedAt()) : createdAt1.getUpdatedAt() == null;
    }

    @Override
    public int hashCode() {
        return getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UpdatedAt{" +
                "updatedAt=" + updatedAt +
                '}';
    }
}
