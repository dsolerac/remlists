package com.remlists.user.domain.objectMother;

import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;

public final class UserObjectMother {

    public static User createBasicUser(){

        ShortName shortName = new ShortName("dsolerac");
        EmailAddress email = new EmailAddress("dsc@g.com");
        Password password = new Password("123456");

        User user = new User.UserBuilder(shortName, email,password).createUser();

        return user;

    }



}
