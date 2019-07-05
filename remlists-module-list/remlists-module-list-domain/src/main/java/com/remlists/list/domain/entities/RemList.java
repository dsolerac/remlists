package com.remlists.list.domain.entities;

import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.shared.domain.entities.Entity;
import com.remlists.shared.domain.valueObjects.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class RemList implements Serializable, Entity {

    private static final Logger LOG = LoggerFactory.getLogger(RemList.class);


    private Id id;
    private RemListName name;


    public RemList() {
    }

    public RemList(Id id, RemListName name) {
        this.id = id;
        this.name = name;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public RemListName getName() {
        return name;
    }

    public void setName(RemListName name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "RemList{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

}
