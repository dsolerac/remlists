package com.remlists.entrypoint.controller.user.resource;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.valueObjects.ShortName;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private Id ID;
    private ShortName shortName;

    public UserResource(Id id, ShortName shortName) {
        this.ID = id;
        this.shortName = shortName;
    }

    public UserResource() {
    }

    public Id getID() {
        return ID;
    }

    public void setID(Id ID) {
        this.ID = ID;
    }

    public ShortName getShortName() {
        return shortName;
    }

    public void setShortName(ShortName shortName) {
        this.shortName = shortName;
    }
}
