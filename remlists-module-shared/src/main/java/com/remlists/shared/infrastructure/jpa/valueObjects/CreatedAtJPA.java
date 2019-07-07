package com.remlists.shared.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class CreatedAtJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(CreatedAtJPA.class);

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public CreatedAtJPA(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public CreatedAtJPA() {
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
        if (!(o instanceof CreatedAtJPA)) return false;

        CreatedAtJPA that = (CreatedAtJPA) o;

        return getCreatedAt() != null ? getCreatedAt().equals(that.getCreatedAt()) : that.getCreatedAt() == null;
    }

    @Override
    public int hashCode() {
        return getCreatedAt() != null ? getCreatedAt().hashCode() : 0;
    }


    @Override
    public String toString() {
        return "CreatedAtJPA{" +
                "createdAt=" + createdAt +
                '}';
    }
}
