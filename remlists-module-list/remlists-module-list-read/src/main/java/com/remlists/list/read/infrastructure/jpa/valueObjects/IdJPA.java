package com.remlists.list.read.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public final class IdJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(IdJPA.class);

    @Column(name = "id", columnDefinition = "uuid")
    private UUID uuid;

    protected IdJPA() {
    }

    public IdJPA(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdJPA)) return false;

        IdJPA remListID = (IdJPA) o;

        return getUuid().equals(remListID.getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }

    @Override
    public String toString() {
        return "IdJPA{" +
                "uuid=" + uuid +
                '}';
    }
}
