package com.remlists.shared.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class Id implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(Id.class);

    @NotNull
    private UUID uuid;


    /**
     * Create a Id Value Object with a random UUID
     */
    public Id() {
        this.uuid = UUID.randomUUID();
    }

    public Id(@NotEmpty UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    private void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id)) return false;

        Id remListID = (Id) o;

        return getUuid().equals(remListID.getUuid());
    }

    @Override
    public int hashCode() {
        return getUuid().hashCode();
    }

    @Override
    public String toString() {
        return "Id{" +
                "uuid=" + uuid +
                '}';
    }
}
