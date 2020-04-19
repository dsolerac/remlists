package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.RoleGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.UUID;

public enum BaseRoleGroups implements Serializable {

    ROLE_GROUP_USERS    ("ROLE_GROUP_USERS",    new RoleGroup(new Id(UUID.fromString("e72bb89d-f873-4c2b-83f0-03869c08da79")), new RoleGroupName("ROLE_GROUP_USERS") )),
    ROLE_GROUP_ADMINS   ("ROLE_GROUP_ADMINS",   new RoleGroup(new Id(UUID.fromString("613d2ebf-97a1-487a-884c-77e1494afb93")), new RoleGroupName("ROLE_GROUP_ADMINS") )),
    ROLE_GROUP_CLIENTS  ("ROLE_GROUP_CLIENTS",  new RoleGroup(new Id(UUID.fromString("8a47341e-da44-4d2b-bb88-068bc682e0eb")), new RoleGroupName("ROLE_GROUP_CLIENTS") )),
    ROLE_GROUP_EMPLOYEES("ROLE_GROUP_EMPLOYEES", new RoleGroup(new Id(UUID.fromString("8201ff0e-0c41-466f-a460-6d88a28e11b1")), new RoleGroupName("ROLE_GROUP_EMPLOYEES") ));

    private static final Logger LOG = LoggerFactory.getLogger(BaseRoleGroups.class);

    private final String roleGroupName;
    private final RoleGroup roleGroup;

    BaseRoleGroups(String roleName, RoleGroup role) {
        this.roleGroupName = roleName;
        this.roleGroup = role;
    }

    public String getRoleGroupName() {
        return roleGroupName;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }
}
