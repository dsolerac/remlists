package com.remlists.user.domain.repository;


import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.domain.valueObjects.ValueObject;

import java.util.Optional;
import java.util.Set;


public interface RoleRepository<E, ID> extends CrudRepository<E, ID> {

    <VO extends ValueObject> Optional<E> findByRoleName(VO roleName);
    <VO extends ValueObject> Set<E> findByRoleNameIn(VO... rolesName);

}
