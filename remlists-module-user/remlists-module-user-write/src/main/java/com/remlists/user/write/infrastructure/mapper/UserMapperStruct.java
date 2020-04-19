package com.remlists.user.write.infrastructure.mapper;

import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserRolesJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.ShortNameJPA;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


@Mapper(componentModel = "spring")
public interface UserMapperStruct {

    Logger LOG = LoggerFactory.getLogger(UserMapperStruct.class);

    UserMapperStruct INSTANCE = Mappers.getMapper( UserMapperStruct.class );




    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "roleName.role", target = "roleName.role"),
            @Mapping(source = "description.description", target = "description.description")

    })
    RoleJPA roleToRoleJPA(Role role);



    /*==== NAMED ====*/

/*    @Named("userToUserJPA_withRoles")
    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "verified.verified", target = "verified.verified"),
            @Mapping(source = "updatedAt.updatedAt", target = "updatedAt.updatedAt"),
            @Mapping(source = "createdAt.createdAt", target = "createdAt.createdAt"),
            @Mapping(source = "shortName.shortName", target = "shortName.shortName"),
            @Mapping(source = "email.emailAddress", target = "email.emailAddress"),
            @Mapping(source = "password.password", target = "password.password")
    })
    UserJPA userToUserJPA_withRoles(User user);*/

    @Named("userToUserJPA_withoutRoles")
    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "verified.verified", target = "verified.verified"),
            @Mapping(source = "updatedAt.updatedAt", target = "updatedAt.updatedAt"),
            @Mapping(source = "createdAt.createdAt", target = "createdAt.createdAt"),
            @Mapping(source = "shortName.shortName", target = "shortName.shortName"),
            @Mapping(source = "email.emailAddress", target = "email.emailAddress"),
            @Mapping(source = "password.password", target = "password.password"),
            @Mapping(target = "roles", ignore = true)
    })
    UserJPA userToUserJPA_withoutRoles(User user);


    @Named("userJPAToUser_withoutRoles")
    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "verified.verified", target = "verified.verified"),
            @Mapping(source = "updatedAt.updatedAt", target = "updatedAt.updatedAt"),
            @Mapping(source = "createdAt.createdAt", target = "createdAt.createdAt"),
            @Mapping(source = "shortName.shortName", target = "shortName.shortName"),
            @Mapping(source = "email.emailAddress", target = "email.emailAddress"),
            @Mapping(source = "password.password", target = "password.password"),
            @Mapping(target = "roles", ignore = true)
    })
    User userJPAToUser_withoutRoles(UserJPA userJPA);

    @Named("userJPAToUser_withRoles")
    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "verified.verified", target = "verified.verified"),
            @Mapping(source = "updatedAt.updatedAt", target = "updatedAt.updatedAt"),
            @Mapping(source = "createdAt.createdAt", target = "createdAt.createdAt"),
            @Mapping(source = "shortName.shortName", target = "shortName.shortName"),
            @Mapping(source = "email.emailAddress", target = "email.emailAddress"),
            @Mapping(source = "password.password", target = "password.password")
    })
    User userJPAToUser_withRoles(UserJPA userJPA);

    @IterableMapping(qualifiedByName="userRolesJPAToRole_fromRole")
    Set<Role> userRolesJPASetToRoleSet(Set<UserRolesJPA> set);

    @Named("userRolesJPAToRole_fromRole")
    @Mappings({
            @Mapping(source = "role.id.uuid", target = "id.uuid"),
            @Mapping(source = "role.roleName.role", target = "role.role"),
            @Mapping(source = "role.description.description", target = "description.description")
    })
    Role userRolesJPAToRole_fromRole(UserRolesJPA userRolesJPA);

    @IterableMapping(qualifiedByName="userToUserJPA_withoutRoles")
    Set<UserJPA> userSetToUserJPASet(Set<User> users);


    /* ==== Value Objects ====*/

    ShortNameJPA ShortNameToShortNameJPA(ShortName username);

}
