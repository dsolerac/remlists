package com.remlists.user.domain.repository;


import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.valueObjects.RoleName;

import java.util.Optional;
import java.util.Set;


public interface RoleRepository extends CrudRepository<Role, Id> {

    Optional<Role> findByRoleName(RoleName roleName);
    Set<Role> findByRoleNameIn(RoleName... rolesName);

}
