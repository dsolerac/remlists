package com.remlists.user.domain.objectMother;

import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;

public class RoleObjectMother {

    public static Role createBasicRole(){

        User user = UserObjectMother.createBasicUser().build();

        Role role = new Role();
//        role.addUsers(user);

        return role;

    }

}
