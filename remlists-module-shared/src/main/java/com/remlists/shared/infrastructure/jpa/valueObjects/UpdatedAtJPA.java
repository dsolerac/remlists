package com.remlists.shared.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class UpdatedAtJPA implements ValueObject {


    private static final Logger LOG = LoggerFactory.getLogger(CreatedAtJPA.class);

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public UpdatedAtJPA(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public UpdatedAtJPA() {
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UpdatedAtJPA{" +
                "updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatedAtJPA)) return false;

        UpdatedAtJPA that = (UpdatedAtJPA) o;

        return getUpdatedAt() != null ? getUpdatedAt().equals(that.getUpdatedAt()) : that.getUpdatedAt() == null;
    }

    @Override
    public int hashCode() {
        return getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0;
    }
}
