package com.remlists.user.write.infrastructure.jpa.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class IdEmbeddedUserJPA implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(IdEmbeddedUserJPA.class);

    @Column(name = "id_user", columnDefinition = "uuid")
    private UUID uuid;

    public IdEmbeddedUserJPA(UUID uuid) {
        this.uuid = uuid;
    }

    protected IdEmbeddedUserJPA() {
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
        if (!(o instanceof IdEmbeddedUserJPA)) return false;

        IdEmbeddedUserJPA that = (IdEmbeddedUserJPA) o;

        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IdEmbeddedUserJPA{" +
                "uuid=" + uuid +
                '}';
    }
}
