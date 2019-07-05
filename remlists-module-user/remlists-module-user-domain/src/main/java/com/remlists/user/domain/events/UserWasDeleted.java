package com.remlists.user.domain.events;

import com.remlists.shared.domain.events.Event;
import com.remlists.user.domain.entities.User;

import java.io.Serializable;

public final class UserWasDeleted extends Event<User> implements Serializable {


    public UserWasDeleted(User user, String hostname) {
        super("remlists.user.1.event.user.deleted", hostname, user);
    }

    @Override
    public String toString() {
        return "UserWasDeleted{" +
                "} " + super.toString();
    }
}
