package com.remlists.user.domain.valueObjects;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.UUID;

public enum BaseRoles implements Serializable {

    ROLE_USER("ROLE_USER",      new Role(new Id(UUID.fromString("3070afc6-efbc-464e-adc3-40e5e5487e9a")), new RoleName("ROLE_USER") )),
    ROLE_ADMIN("ROLE_ADMIN",    new Role(new Id(UUID.fromString("dff9ea9a-376c-47b4-8cb6-4db4dce18f1f")), new RoleName("ROLE_ADMIN") )),
    ROLE_CLIENT("ROLE_CLIENT",  new Role(new Id(UUID.fromString("a32518b1-ecf8-46ec-8ca2-30deb9ee00c1")), new RoleName("ROLE_CLIENT") )),
    ROLE_EMPLOYEE("ROLE_EMPLOYEE", new Role(new Id(UUID.fromString("ced2e1c5-f9a2-45d9-a7d4-ee0ce83b27a1")), new RoleName("ROLE_EMPLOYEE") ));

    private static final Logger LOG = LoggerFactory.getLogger(BaseRoles.class);

    private final String roleName;
    private final Role role;

    BaseRoles(String roleName, Role role) {
        this.roleName = roleName;
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role getRole() {
        return role;
    }
}
