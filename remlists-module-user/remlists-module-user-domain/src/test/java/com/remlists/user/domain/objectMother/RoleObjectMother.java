package com.remlists.user.domain.objectMother;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.valueObjects.RoleDescription;
import com.remlists.user.domain.valueObjects.RoleName;


public class RoleObjectMother {

    public static RoleName createTestingRoleName(){
        return new RoleName("ROLE_TESTING");
    }
    public static RoleName createUserRoleName(){
        return new RoleName("ROLE_USER");
    }

    public static Role createBasicUserRole(){
        return Role.createBasicUSerRole();
    }

    public static Role createTestingRole(){

        Id idRole = new Id();
        RoleDescription description = new RoleDescription("This role allows testing tasks");
        RoleName roleName = createTestingRoleName();
        Role role = new Role(idRole, roleName);
        role.setDescription(description);

        return role;
    }


}
