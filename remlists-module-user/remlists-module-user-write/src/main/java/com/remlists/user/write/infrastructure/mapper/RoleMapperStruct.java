package com.remlists.user.write.infrastructure.mapper;

import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.valueObjects.RoleName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;


//@Component(roleMapperWrite)
@Mapper
public interface RoleMapperStruct  {

    Logger LOG = LoggerFactory.getLogger(RoleMapperStruct.class);

    RoleMapperStruct INSTANCE = Mappers.getMapper( RoleMapperStruct.class );


    RoleJPA roleToRoleJPA(RoleJPA roleJPA);

    RoleNameJPA roleNameToRoleNameJPA(RoleName roleName);

//    Optional<Role> roleJPAToRole(Optional<RoleJPA> roleJPA);
    @Mapping(source = "id.uuid", target = "id.uuid")
    @Mapping(source = "description.description", target = "description.description")
    @Mapping(target = "users", ignore = true)
    Role roleJPAToRole(RoleJPA roleJPA);

    Set<RoleNameJPA> rolesNameToRolesNameJPA( Set<RoleName> rolesName );
//    Set<RoleNameJPA> rolesNameToRolesNameJPA( RoleName... rolesName );

    Set<Role> rolesJPAToRoles(Set<RoleJPA> rolesJPA);


}
