package com.remlists.user.write.infrastructure.repository.impl;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.infrastructure.repository.impl.RemListBaseRepository;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.valueObjects.RoleName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.repository.RoleRepositoryJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
import com.remlists.user.write.infrastructure.mapper.RoleMapper;
import com.remlists.user.write.infrastructure.mapper.RoleMapperStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.roleMapperWrite;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteDataCustomRepositoryImpl;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteRepository;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;


@Repository(roleWriteRepository)
@Transactional(transactionManagerUserWrite)

public class RoleWriteRepositoryImpl extends RemListBaseRepository<Role,
                                                             Id,
                                                             RoleJPA,
                                                             IdJPA>
                                        implements RoleRepository {


        private Logger LOG = LoggerFactory.getLogger(RoleWriteRepositoryImpl.class);


    private RoleRepositoryJPA repository;
    private RoleMapper mapper;

    private RoleMapperStruct mstruct;

    public RoleWriteRepositoryImpl(@Qualifier(roleWriteDataCustomRepositoryImpl) RoleRepositoryJPA repository,
                                   @Qualifier(roleMapperWrite) RoleMapper mapper
    ) {

        super( repository, mapper );

        this.repository = repository;
        this.mapper = mapper;

    }


    @Override
    public Optional<Role> findByRoleName(RoleName roleName) {

        RoleNameJPA roleNameJPA =  mstruct.INSTANCE.roleNameToRoleNameJPA( roleName );

        Optional<RoleJPA> byRole = repository.findByRoleName(roleNameJPA);


        if(byRole.isEmpty())
            return Optional.empty();


        return  Optional.of(   mstruct.INSTANCE.roleJPAToRole( byRole.orElseThrow() ) );

    }

    @Override
    public  Set<Role> findByRoleNameIn(RoleName... rolesName) {

        Set<RoleNameJPA> rolesNameJPA = mapper.rolesNameToRolesNameJPA(rolesName);

        Set<RoleJPA> allByRoleName = repository.findByRoleNameIn( rolesNameJPA.toArray(new RoleNameJPA[rolesNameJPA.size()]) );

        Set<Role> roles = mapper.rolesJPAToRoles(allByRoleName);

        return roles;

    }

}
