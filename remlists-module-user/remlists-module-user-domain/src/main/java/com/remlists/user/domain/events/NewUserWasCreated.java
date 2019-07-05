package com.remlists.user.domain.events;

import com.remlists.shared.domain.events.Event;
import com.remlists.user.domain.entities.User;

import java.io.Serializable;

public final class NewUserWasCreated extends Event<User> implements Serializable {


    public NewUserWasCreated(User user, String hostName) {
        super("remlists.user.1.event.user.created", hostName, user);
    }


    @Override
    public String toString() {
        return "NewUserWasCreated{" +
                "} " + super.toString();
    }

}
