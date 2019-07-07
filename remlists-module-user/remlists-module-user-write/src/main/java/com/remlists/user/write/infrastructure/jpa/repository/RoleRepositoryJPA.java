package com.remlists.user.write.infrastructure.jpa.repository;

import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;

import java.util.Optional;
import java.util.Set;

public interface RoleRepositoryJPA extends CrudRepository<RoleJPA, IdJPA> {


    Optional<RoleJPA> findByRoleName(RoleNameJPA roleName);
    Set<RoleJPA> findByRoleNameIn(RoleNameJPA... rolesName);


}
