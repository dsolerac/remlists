package com.remlists.entrypoint.controller.list.resource;

import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.shared.domain.valueObjects.Id;
import org.springframework.hateoas.ResourceSupport;

public class RemListResource extends ResourceSupport {

    private com.remlists.shared.domain.valueObjects.Id ID;
    private RemListName name;

    public RemListResource(Id id, RemListName name) {
        this.ID = id;
        this.name = name;
    }

    public RemListResource() {
    }

    public Id getID() {
        return ID;
    }

    public void setID(Id ID) {
        this.ID = ID;
    }

    public RemListName getName() {
        return name;
    }

    public void setName(RemListName name) {
        this.name = name;
    }
}
