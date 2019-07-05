package com.remlists.list.domain.events;

import com.remlists.list.domain.entities.RemList;
import com.remlists.shared.domain.events.Event;

import java.io.Serializable;

public final class NewRemListWasCreated extends Event<RemList> implements Serializable {

    public NewRemListWasCreated(RemList remList, String hostName) {
        super("remlists.list.1.event.list.created", hostName, remList);
    }



    @Override
    public String toString() {

        return "NewRemListWasCreated{" +
                "} " + super.toString();

    }

}