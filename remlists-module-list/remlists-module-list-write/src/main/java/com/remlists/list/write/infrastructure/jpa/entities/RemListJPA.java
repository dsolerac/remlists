package com.remlists.list.write.infrastructure.jpa.entities;

import com.remlists.list.write.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.list.write.infrastructure.jpa.valueObjects.RemListNameJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "list")
@Table(name = "lists", schema = "write")
public final class RemListJPA implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(RemListJPA.class);

    @EmbeddedId
    private IdJPA id;
    @Embedded
    private RemListNameJPA name;


    protected RemListJPA() {
    }

    public RemListJPA(IdJPA id, RemListNameJPA name) {
        this.id = id;
        this.name = name;
    }

    public IdJPA getId() {
        return id;
    }

    public void setId(IdJPA id) {
        this.id = id;
    }

    public RemListNameJPA getName() {
        return name;
    }

    public void setName(RemListNameJPA name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "RemListJPA{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }


}
