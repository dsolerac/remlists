package com.remlists.user.write.infrastructure.jpa.impl;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.shared.infrastructure.jpa.impl.BaseRepositoryJPA;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.valueObjects.RoleName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
import com.remlists.user.write.infrastructure.mapper.RoleMapper;
import com.remlists.user.write.infrastructure.mapper.RoleMapperStruct;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.roleMapperWrite;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteDataCustomRepositoryImpl;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteRepositoryJPA;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;


@Repository(roleWriteRepositoryJPA)
@Transactional(transactionManagerUserWrite)
public class RoleWriteRepositoryJPAImpl extends BaseRepositoryJPA<Role,
                                                             Id,
                                                             RoleJPA,
                                                             IdJPA>
                                        implements RoleRepository<Role, Id> {

    private Logger LOG = LoggerFactory.getLogger(RoleWriteRepositoryJPAImpl.class);


    private RoleRepository repository;
    private RoleMapper mapper;

    private RoleMapperStruct mstruct;

    public RoleWriteRepositoryJPAImpl(@Qualifier(roleWriteDataCustomRepositoryImpl) RoleRepository repository,
                                      @Qualifier(roleMapperWrite) RoleMapper mapper) {

        super( repository, mapper );

        this.repository = repository;
        this.mapper = mapper;

    }


    @Override
    public <VO extends ValueObject> Optional<Role> findByRoleName(VO roleName) {

        RoleNameJPA roleNameJPA =  mstruct.INSTANCE.roleNameToRoleNameJPA((RoleName) roleName);

//        RoleNameJPA roleNameJPA = mapper.getMapper().map(roleName, RoleNameJPA.class);

        Optional<RoleJPA> byRole = repository.findByRoleName(roleNameJPA);


        if(byRole.isEmpty())
            return Optional.empty();

//        return  Optional.of( mapper.getMapper().map(byRole.orElseThrow(), Role.class) );

        return  Optional.of(   mstruct.INSTANCE.roleJPAToRole( byRole.orElseThrow() ) );

    }

    @Override
    public <VO extends ValueObject> Set<Role> findByRoleNameIn(VO... rolesName) {


//        mstruct.INSTANCE.rolesNameToRolesNameJPA(Set.<RoleName>of(rolesName));
        Set<RoleNameJPA> rolesNameJPA = mstruct.INSTANCE.rolesNameToRolesNameJPA(Set.of((RoleName [])rolesName));

//        Set<RoleNameJPA> rolesNameJPA = mapper.rolesNameToRolesNameJPA( Set.of(rolesName));

        Set<RoleJPA> allByRoleName = repository.findByRoleNameIn(rolesNameJPA.toArray(new RoleNameJPA[rolesNameJPA.size()]));

//        return mapper.iterableEntitiesJPAToIterableEntities2(allByRoleName, new TypeToken<Set<Role>>() {}.getType());

        return mapper.rolesJPAToRoles(allByRoleName);
    }

}
